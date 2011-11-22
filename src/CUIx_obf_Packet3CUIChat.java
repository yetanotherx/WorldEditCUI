
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
 */
public class CUIx_obf_Packet3CUIChat extends abb {

    private static boolean registered = false;

    public CUIx_obf_Packet3CUIChat() {
        super();
    }

    public CUIx_obf_Packet3CUIChat(String s) {
        super(s);
    }

    @SuppressWarnings("unchecked")
    public static void register() {
        if (registered) {
            return;
        }
        registered = true;
        try {
            Class<gt> packetclass = gt.class;
            Field idstoclassesfield = packetclass.getDeclaredField("j");
            Field classestoidsfield = packetclass.getDeclaredField("a");
            idstoclassesfield.setAccessible(true);
            classestoidsfield.setAccessible(true);
            ob idstoclasses = (ob) idstoclassesfield.get(null);
            Map<Class<?>, Integer> classestoids = (Map<Class<?>, Integer>) classestoidsfield.get(null);
            idstoclasses.a(3, CUIx_obf_Packet3CUIChat.class);
            classestoids.put(CUIx_obf_Packet3CUIChat.class, 3);
        } catch (Exception e) {
            throw new RuntimeException("Error inserting chat handler - WorldEditClientUserInterface will not work!", e);
        }
    }

    public void a(fe nethandler) {
        CUIx_events_IncomingChatEvent chatevent = new CUIx_events_IncomingChatEvent(a);
        CUIx_fevent_EventManager.callEvent(chatevent);
        if (!chatevent.isCancelled()) {
            nethandler.a(this);
        }
    }
}
