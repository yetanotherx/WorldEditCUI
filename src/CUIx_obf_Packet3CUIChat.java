
import java.io.DataOutputStream;
import java.lang.reflect.Field;
import java.util.Map;

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
public class CUIx_obf_Packet3CUIChat extends abb {

    private static boolean registered = false;
    private boolean cancelled = false;

    public CUIx_obf_Packet3CUIChat() {
        super();
    }

    public CUIx_obf_Packet3CUIChat(String s) {
        super(s);
        CUIx_events_ChatEvent chatevent = new CUIx_events_ChatEvent(s, CUIx_events_ChatEvent.Direction.OUTGOING);
        CUIx_fevent_EventManager.callEvent(chatevent);
        if (!chatevent.isCancelled() && s.startsWith("/") && s.length() > 1) {
            CUIx_events_ChatCommandEvent commandevent = new CUIx_events_ChatCommandEvent(s);
            CUIx_fevent_EventManager.callEvent(chatevent);
            if (commandevent.isHandled())
                cancelled = true;
        } else {
            cancelled = true;
        }
    }

    @SuppressWarnings("unchecked")
    public static void register() {
        if (registered) {
            return;
        }
        registered = true;
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
            idstoclasses.a(3, CUIx_obf_Packet3CUIChat.class);
            classestoids.put(CUIx_obf_Packet3CUIChat.class, 3);
        } catch (Exception e) {
            throw new RuntimeException("Error inserting chat handler - CUIx and anything that depends on it will not work!", e);
        }
    }
    
    public void a(DataOutputStream dataoutputstream) {
        if (!cancelled)
            a(a, dataoutputstream);
    }

    public void a(fe nethandler) {
        CUIx_events_ChatEvent chatevent = new CUIx_events_ChatEvent(a, CUIx_events_ChatEvent.Direction.INCOMING);
        CUIx_fevent_EventManager.callEvent(chatevent);
        if (!chatevent.isCancelled()) {
            nethandler.a(this);
        }
    }
}
