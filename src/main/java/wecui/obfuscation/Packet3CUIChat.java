package wecui.obfuscation;

import wecui.event.ChatCommandEvent;
import wecui.event.ChatEvent;
import wecui.fevents.EventManager;
import deobf.abb;
import deobf.fe;
import deobf.gt;
import deobf.ob;
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
public class Packet3CUIChat extends abb {

    protected static WorldEditCUI controller;
    protected static boolean registered = false;
    protected boolean cancelled = false;

    public Packet3CUIChat() {
        super();
    }

    public Packet3CUIChat(String s) {
        super(s);
        ChatEvent chatevent = new ChatEvent(controller, s, ChatEvent.Direction.OUTGOING);
        controller.getEventManager().callEvent(chatevent);
        if (!chatevent.isCancelled() && s.startsWith("/") && s.length() > 1) {
            ChatCommandEvent commandevent = new ChatCommandEvent(controller, s);
            controller.getEventManager().callEvent(chatevent);
            if (commandevent.isHandled()) {
                cancelled = true;
            }
        } else {
            cancelled = true;
        }
    }

    @SuppressWarnings("unchecked")
    public static void register(WorldEditCUI controller) {
        if (registered) {
            return;
        }
        registered = true;
        Packet3CUIChat.controller = controller;
        
        try {
            Class<gt> packetclass = gt.class;
            Field idstoclassesfield;
            Field classestoidsfield;
            try {
                idstoclassesfield = packetclass.getDeclaredField("j");
                classestoidsfield = packetclass.getDeclaredField("a");
            } catch (NoSuchFieldException e) {
                try {
                    idstoclassesfield = packetclass.getDeclaredField("packetIdToClassMap");
                    classestoidsfield = packetclass.getDeclaredField("packetClassToIdMap");
                } catch (NoSuchFieldException e1) {
                    e.printStackTrace();
                    throw e1;
                }
            }
            idstoclassesfield.setAccessible(true);
            classestoidsfield.setAccessible(true);
            ob idstoclasses = (ob) idstoclassesfield.get(null);
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
     * TODO: Apparently this doesn't work right yet
     * @param nethandler 
     */
    public void a(fe nethandler) {
        ChatEvent chatevent = new ChatEvent(controller, a, ChatEvent.Direction.INCOMING);
        controller.getEventManager().callEvent(chatevent);
        if (!chatevent.isCancelled()) {
            nethandler.a(this);
        }
    }
}
