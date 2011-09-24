/**
 * 
 */
package net.lahwran.wecui.obf;

import java.io.DataInputStream;
import java.lang.reflect.Field;
import java.util.Map;

import net.lahwran.ChatEvent;
import net.lahwran.fevents.EventManager;


import deobf.kx;
import deobf.vc;
import deobf.vl;
import deobf.yc;

/**
 * @author lahwran
 *
 */
public class Packet3WECUIChat extends yc {
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
            Class<vl> packetclass = vl.class;
            Field idstoclassesfield = packetclass.getDeclaredField("a");
            Field classestoidsfield = packetclass.getDeclaredField("b");
            idstoclassesfield.setAccessible(true);
            classestoidsfield.setAccessible(true);
            vc idstoclasses = (vc) idstoclassesfield.get(null);
            Map<Class<?>, Integer> classestoids = (Map<Class<?>, Integer>) classestoidsfield.get(null);
            idstoclasses.a(3, Packet3WECUIChat.class);
            classestoids.put(Packet3WECUIChat.class, 3);
        } catch (Exception e) {
            throw new RuntimeException("Error inserting chat handler - WorldEditClientUserInterface will not work!", e);
        }
    }
    public void a(kx kx1) {
        ChatEvent chatevent = new ChatEvent(a);
        EventManager.callEvent(chatevent);
        if (!chatevent.isCancelled())
            kx1.a(this);
    }
}
