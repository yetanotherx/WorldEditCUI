package wecui.obfuscation;

import deobf.NetHandler;
import deobf.Packet;
import deobf.Packet3Chat;
import deobf.UnknownClass;
import wecui.event.ChatEvent;
import java.io.DataOutputStream;
import java.lang.reflect.Field;
import java.util.Map;
import wecui.WorldEditCUI;

/**
 * Replacement for Packet3Chat, in order to listen 
 * to incoming chat without conflicting with mods
 * 
 * This uses reflection to be set as the packet.
 * Don't look at it, it's horribly ugly.
 * 
 * @author lahwran
 * @author yetanotherx
 *
 * @obfuscated
 */
public class Packet3CUIChat extends Packet3Chat {

    protected static WorldEditCUI controller;
    protected static boolean registered = false;
    protected boolean cancelled = false;

    public Packet3CUIChat() {
        super();
    }

    public Packet3CUIChat(String s) {
        super(s);
    }

    /**
     * Replaces the incoming packet ID 3 with this class
     * @param controller 
     */
    @SuppressWarnings("unchecked")
    public static void register(WorldEditCUI controller) {

        if (registered) {
            return;
        }
        registered = true;

        Packet3CUIChat.controller = controller;

        try {
            Class<Packet> packetClass = Packet.class;
            Field idstoclassesfield;
            Field classestoidsfield;

            idstoclassesfield = packetClass.getDeclaredField("j");
            classestoidsfield = packetClass.getDeclaredField("a");

            idstoclassesfield.setAccessible(true);
            classestoidsfield.setAccessible(true);

            UnknownClass idstoclasses = (UnknownClass) idstoclassesfield.get(null);
            Map<Class<?>, Integer> classestoids = (Map<Class<?>, Integer>) classestoidsfield.get(null);
            idstoclasses.a(3, Packet3CUIChat.class);
            classestoids.put(Packet3CUIChat.class, 3);

        } catch (Exception e) {
            throw new RuntimeException("Error inserting chat handler - WorldEditCUI and anything that depends on it will not work!", e);
        }

        controller.getDebugger().debug("Chat handler registered.");
    }

    public void a(DataOutputStream dataoutputstream) {
        if (!cancelled) {
            a(a, dataoutputstream);
        }
    }

    /**
     * Called on incoming Packet3Chat, calls a new ChatEvent
     * @param nethandler 
     */
    public void a(NetHandler nethandler) {
        ChatEvent chatevent = new ChatEvent(controller, a, ChatEvent.Direction.INCOMING);
        controller.getEventManager().callEvent(chatevent);
        if (!chatevent.isCancelled()) {
            nethandler.a(this);
        }
    }
}
