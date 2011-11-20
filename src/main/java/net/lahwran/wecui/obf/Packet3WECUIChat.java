/**
 * 
 */
package net.lahwran.wecui.obf;

import java.io.DataInputStream;
import java.lang.reflect.Field;
import java.util.Map;

import net.lahwran.ChatEvent;
import net.lahwran.fevents.EventManager;


import deobf.fe;
import deobf.abb;
import deobf.ob;
import deobf.gt;

/**
 * @author lahwran
 *
 */
public class Packet3WECUIChat extends abb {
    private static boolean registered = false;

    public Packet3WECUIChat() {
        super();
    }

    public Packet3WECUIChat(String s) {
        super(s);
    }

    @SuppressWarnings("unchecked")
    public static void register() {
        if (registered)
            return;
        registered = true;
        try {
            Class<gt> packetclass = gt.class;
            Field idstoclassesfield = packetclass.getDeclaredField("j");
            Field classestoidsfield = packetclass.getDeclaredField("a");
            idstoclassesfield.setAccessible(true);
            classestoidsfield.setAccessible(true);
            ob idstoclasses = (ob) idstoclassesfield.get(null);
            Map<Class<?>, Integer> classestoids = (Map<Class<?>, Integer>) classestoidsfield.get(null);
            idstoclasses.a(3, Packet3WECUIChat.class);
            classestoids.put(Packet3WECUIChat.class, 3);
        } catch (Exception e) {
            throw new RuntimeException("Error inserting chat handler - WorldEditClientUserInterface will not work!", e);
        }
    }
    public void a(fe nethandler) {
        ChatEvent chatevent = new ChatEvent(a);
        EventManager.callEvent(chatevent);
        if (!chatevent.isCancelled())
            nethandler.a(this);
    }
}
