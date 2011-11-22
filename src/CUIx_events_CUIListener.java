
/**
 * Listener class for CUIx_events_CUIEvent
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class CUIx_events_CUIListener implements CUIx_fevent_Listener<CUIx_events_CUIEvent> {

    /**
     * CUIx main class. Prevents calling singleton every time it is used
     */
    private final CUIx cuix;

    public CUIx_events_CUIListener(CUIx cuix) {
        this.cuix = cuix;
    }

    public void onEvent(CUIx_events_CUIEvent event) {
        if (event.type.isEmpty()) {
            if (event.params.length > 0 && event.params[0].length() > 0) {
                event.markInvalid("handshake event takes no parameters.");
            }
            if (cuix.getObfuscation().isMultiplayerWorld()) {
                cuix.getObfuscation().sendChat("/worldedit cui");
            }
            CUIx.debug("/worldedit cui");
        } else if (event.type.equals("s")) {
            if (event.params.length == 0) {
                event.markInvalid("selection type event requires parameters.");
            } else if (event.params.length > 1) {
                event.markInvalid("selection type event only takes one parameter.");
            }

            if (event.params[0].equals("cuboid")) {
                cuix.setSelection(new CUIx_render_CuboidRegion());
            } else if (event.params[0].equals("polygon2d")) {
                cuix.setSelection(new CUIx_render_Polygon2DRegion());
            }
            event.setHandled(true);
        } else if (event.type.equals("p")) { // point
            if (event.params.length < 5 || event.params.length > 6) {
                event.markInvalid("point event requires either 5 or 6 parameters.");
            }

            int id = event.getInt(0);
            int x = event.getInt(1);
            int y = event.getInt(2);
            int z = event.getInt(3);
            int regionSize = event.getInt(4);
            cuix.getSelection().setPoint(id, x, y, z, regionSize);
            event.setHandled(true);
        } else if (event.type.equals("p2")) { // point2d
            if (event.params.length < 4 || event.params.length > 5) {
                event.markInvalid("point2d event requires either 4 or 5 parameters.");
            }

            int id = event.getInt(0);
            int x = event.getInt(1);
            int z = event.getInt(2);
            int regionSize = event.getInt(3);
            cuix.getSelection().setPoint(id, x, z, regionSize);
            event.setHandled(true);
        } else if (event.type.equals("mm")) { // minmax
            if (event.params.length < 2 || event.params.length > 3) {
                event.markInvalid("minmax event requires either 2 or 3 parameters.");
            }
            int min = event.getInt(0);
            int max = event.getInt(1);
            cuix.getSelection().setMinMax(min, max);
            event.setHandled(true);
        }
    }
}
