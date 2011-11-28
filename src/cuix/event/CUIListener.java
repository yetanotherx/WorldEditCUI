package cuix.event;

import cuix.fevents.Listener;
import cuix.render.CuboidRegion;
import cuix.render.Polygon2DRegion;
import cuix.CUIx;

/**
 * Listener class for CUIEvent
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class CUIListener implements Listener<CUIEvent> {

    private final CUIx cuix;

    public CUIListener(CUIx cuix) {
        this.cuix = cuix;
    }

    public void onEvent(CUIEvent event) {
        String[] params = event.params;

        if (event.type.isEmpty()) {

            if (params.length > 0 && params[0].length() > 0) {
                event.markInvalid("Handshake event takes no parameters.");
            }
            if (cuix.getObfuscation().isMultiplayerWorld()) {
                cuix.getObfuscation().sendChat("/worldedit cui");
            }
            CUIx.getDebugger().debug("Received handshake event, sending CUI command.");

        } else if (event.type.equals("s")) {

            if (params.length == 0) {
                event.markInvalid("Selection type event requires parameters.");
            } else if (params.length > 1) {
                event.markInvalid("Selection type event only takes one parameter.");
            }

            if (params[0].equals("cuboid")) {
                cuix.setSelection(new CuboidRegion());
            } else if (params[0].equals("polygon2d")) {
                cuix.setSelection(new Polygon2DRegion());
            }
            event.setHandled(true);
            CUIx.getDebugger().debug("Received selection event, initalizing new region instance.");

        } else if (event.type.equals("p")) { // point

            if (params.length < 5 || params.length > 6) {
                event.markInvalid("Point event requires either 5 or 6 parameters.");
            }

            int id = event.getInt(0);
            int x = event.getInt(1);
            int y = event.getInt(2);
            int z = event.getInt(3);
            int regionSize = event.getInt(4);
            cuix.getSelection().setPoint(id, x, y, z, regionSize);
            event.setHandled(true);
            CUIx.getDebugger().debug("Setting point #" + id);

        } else if (event.type.equals("p2")) { // point2d

            if (params.length < 4 || params.length > 5) {
                event.markInvalid("Point2d event requires either 4 or 5 parameters.");
            }

            int id = event.getInt(0);
            int x = event.getInt(1);
            int z = event.getInt(2);
            int regionSize = event.getInt(3);
            cuix.getSelection().setPoint(id, x, z, regionSize);
            event.setHandled(true);
            CUIx.getDebugger().debug("Setting point2d #" + id);

        } else if (event.type.equals("mm")) { // minmax
            
            if (params.length < 2 || params.length > 3) {
                event.markInvalid("Minmax event requires either 2 or 3 parameters.");
            }
            
            int min = event.getInt(0);
            int max = event.getInt(1);
            cuix.getSelection().setMinMax(min, max);
            event.setHandled(true);
            CUIx.getDebugger().debug("Expanding/contracting selection.");
            
        }
    }
}
