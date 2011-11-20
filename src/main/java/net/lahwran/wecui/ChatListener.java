/**
 * 
 */
package net.lahwran.wecui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.lahwran.ChatEvent;
import net.lahwran.WECUIEvent;
import net.lahwran.fevents.EventManager;
import net.lahwran.fevents.Listener;


/**
 * @author lahwran
 *
 */
public class ChatListener implements Listener<ChatEvent> {

    public static Pattern commandpattern = Pattern.compile("\u00a75\u00a76\u00a74\u00a75([^|]*)\\|?(.*)");

    @Override
    public void onEvent(ChatEvent event) {
        Matcher matcher = commandpattern.matcher(event.chat);

        if (matcher.find()) {
            String type = matcher.group(1);
            String args = matcher.group(2);
            WorldEditCUI.debug("server-sent event: '" + type + "'  '" + args + "'");

            WECUIEvent wecuievent = new WECUIEvent(type, args.split("[|]"));
            EventManager.callEvent(wecuievent);
            event.setCancelled(wecuievent.isHandled());
        }
    }
}
