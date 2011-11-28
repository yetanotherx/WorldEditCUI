package cuix.event;

import cuix.fevents.EventManager;
import cuix.fevents.Listener;
import cuix.CUIx;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses incoming/outgoing chat messages, and calls a CUIEvent if it matches the WorldEdit CUI header
 * WorldEdit itself sends a message that is prepended by 4 special characters
 * Relevant: http://bit.ly/v3Me4m
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class ChatListener implements Listener<ChatEvent> {

    public static Pattern commandpattern = Pattern.compile("\u00a75\u00a76\u00a74\u00a75([^|]*)\\|?(.*)");
    protected final CUIx cuix;

    public ChatListener(CUIx cuix) {
        this.cuix = cuix;
    }

    @Override
    public void onEvent(ChatEvent event) {
        if (event.direction == ChatEvent.Direction.INCOMING) {
            Matcher matcher = commandpattern.matcher(event.message);

            if (matcher.find()) {
                String type = matcher.group(1);
                String args = matcher.group(2);
                CUIx.getDebugger().debug("Received CUI event from server: " + event.message);

                CUIEvent cuievent = new CUIEvent(type, args.split("[|]"));
                EventManager.callEvent(cuievent);

                if (!cuievent.isHandled()) {
                    cuievent.markInvalid("Invalid message type. Update both CUIx and WorldEdit.");
                }

                event.setCancelled(cuievent.isHandled());
            }
        }
    }
}
