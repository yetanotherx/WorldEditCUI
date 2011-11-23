
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
public class CUIx_events_ChatListener implements CUIx_fevent_Listener<CUIx_events_ChatEvent> {

    public static Pattern commandpattern = Pattern.compile("\u00a75\u00a76\u00a74\u00a75([^|]*)\\|?(.*)");
    private final CUIx cuix;

    public CUIx_events_ChatListener(CUIx cuix) {
        this.cuix = cuix;
    }

    @Override
    public void onEvent(CUIx_events_ChatEvent event) {
        if (event.direction == CUIx_events_ChatEvent.Direction.INCOMING) {
            Matcher matcher = commandpattern.matcher(event.message);


            if (matcher.find()) {
                String type = matcher.group(1);
                String args = matcher.group(2);
                this.cuix.getDebugger().debug("server-sent event: '" + type + "'  '" + args + "'");

                CUIx_events_CUIEvent cuievent = new CUIx_events_CUIEvent(type, args.split("[|]"));
                CUIx_fevent_EventManager.callEvent(cuievent);
                
                if( !cuievent.isHandled() ) {
                    cuievent.markInvalid("Invalid message type. Update both CUIx and WorldEdit.");
                }
                
                event.setCancelled(cuievent.isHandled());
            }
        }
    }
}
