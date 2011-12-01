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

public class DataPacketList<T> extends ArrayList<T> {

    private static final long serialVersionUID = 1L;
    protected WorldEditCUI controller;
    protected Class<T> typeClass;

    public DataPacketList(WorldEditCUI controller, Class<T> typeClass) {
        this.controller = controller;
        this.typeClass = typeClass;
    }

    public boolean add(T e) {
        if (e instanceof Packet3Chat) {
            
            boolean cancelled = false;
            String s = ((Packet3Chat) e).a;

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
                return super.add((T) e);
            }
            return true;
        }
        return super.add((T) e);
    }

    public static void register(WorldEditCUI controller) {

        DataPacketList<Packet> list = new DataPacketList<Packet>(controller, Packet.class);

        Minecraft mc = controller.getMinecraft();
        if (!mc.f.I) {
            return;
        }

        EntityClientPlayerMP player = (EntityClientPlayerMP) mc.h;

        try {
            NetClientHandler nch = player.a;

            Field nmField = NetClientHandler.class.getDeclaredField("g");
            nmField.setAccessible(true);
            NetworkManager nm = (NetworkManager) nmField.get(nch);

            Field listField = NetworkManager.class.getDeclaredField("n");
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
