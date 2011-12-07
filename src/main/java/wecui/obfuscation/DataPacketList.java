package wecui.obfuscation;

import deobf.EntityClientPlayerMP;
import deobf.NetClientHandler;
import deobf.NetworkManager;
import deobf.Packet;
import deobf.Packet3Chat;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import wecui.WorldEditCUI;
import wecui.event.ChatCommandEvent;
import wecui.event.ChatEvent;

/**
 * Overrides the dataPacket list in NetworkManager
 * Uses reflection to replace the list with this. It overrides the add()
 * method to check if it's a Packet3Chat event.
 * 
 * @author yetanotherx
 * 
 */
public class DataPacketList<T> extends ArrayList<T> {

    private static final long serialVersionUID = 1L;
    protected WorldEditCUI controller;
    protected Class<T> typeClass;

    public DataPacketList(WorldEditCUI controller, Class<T> typeClass) {
        this.controller = controller;
        this.typeClass = typeClass;
    }

    /**
     * Overrides the packet addition class. If a Packet3Chat is added, there's an outgoing
     * message and we need to parse it. If it's a command, send a command event. If it's 
     * cancelled, let's not add it at all.
     * 
     * @param packet
     * @return 
     */
    public boolean add(T packet) {
        if (packet instanceof Packet3Chat) {
            
            boolean cancelled = false;
            String s = Obfuscation.getChatFromPacket((Packet3Chat) packet);

            ChatEvent chatevent = new ChatEvent(controller, s, ChatEvent.Direction.OUTGOING);
            controller.getEventManager().callEvent(chatevent);
            if (!chatevent.isCancelled() && s.startsWith("/") && s.length() > 1) {
                ChatCommandEvent commandevent = new ChatCommandEvent(controller, s);
                controller.getEventManager().callEvent(commandevent);
                if (commandevent.isHandled()) {
                    cancelled = true;
                }
            } else {
                cancelled = true;
            }

            if (!cancelled) {
                return super.add((T) packet);
            }
            return true;
        }
        return super.add((T) packet);
    }

    /**
     * Attaches the new packet handler to the actual NetworkManager class
     * 
     * @param controller 
     */
    public static void register(WorldEditCUI controller) {

        DataPacketList<Packet> list = new DataPacketList<Packet>(controller, Packet.class);
        Obfuscation obf = controller.getObfuscation();

        //Checks if it's a multiplayer world
        if (!obf.isMultiplayerWorld()) {
            return;
        }

        EntityClientPlayerMP player = (EntityClientPlayerMP) obf.getPlayer();

        try {
            NetClientHandler nch = obf.getNetClientHandler(player);

            Field nmField = NetClientHandler.class.getDeclaredField(MethodObfuscation.NETWORKMANAGER.getVariable());
            nmField.setAccessible(true);
            NetworkManager nm = (NetworkManager) nmField.get(nch);

            Field listField = NetworkManager.class.getDeclaredField(MethodObfuscation.PACKETLIST.getVariable());
            listField.setAccessible(true);
            List oldPacketList = (List) listField.get(nm);
            for (Object item : oldPacketList) {
                list.add((Packet) item);
            }

            listField.set(nm, list);
            nmField.set(nch, nm);
            
        } catch (Exception e) {
            throw new RuntimeException("Error inserting outgoing chat handler - Certain parts of WorldEditCUI will not work!", e);
        }

        controller.getDebugger().debug("Outgoing chat handler registered.");
    }
}
