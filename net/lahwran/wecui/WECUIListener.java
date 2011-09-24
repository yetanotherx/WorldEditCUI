/**
 * 
 */
package net.lahwran.wecui;

import net.lahwran.WECUIEvent;
import net.lahwran.fevents.Listener;

/**
 * @author lahwran
 *
 */
public class WECUIListener implements Listener<WECUIEvent> {

    private final WorldEditCUI wecui;

    public WECUIListener(WorldEditCUI wecui) {
        this.wecui = wecui;
    }

    public void onEvent(WECUIEvent event) {
        if (event.type.equals("")) {
            if (event.params.length > 0 && event.params[0].length() > 0) {
                event.markInvalid("handshake event takes no parameters.");
            }
            if (wecui.getObfHub().isMultiplayerWorld()) {
                wecui.getObfHub().sendChat("/worldedit cui");
            }
            WorldEditCUI.debug("/worldedit cui");
        } else if (event.type.equals("s")) {
            if (event.params.length == 0) {
                event.markInvalid("selection type event requires parameters.");
            } else if (event.params.length > 1) {
                event.markInvalid("selection type event only takes one parameter.");
            }

            if (event.params[0].equals("cuboid")) {
                wecui.setSelection(new CuboidRegion());
            } else if (event.params[0].equals("polygon2d")) {
                wecui.setSelection(new Polygon2DRegion());
            }
            event.markHandled();
        } else if (event.type.equals("p")) { // point
            if (event.params.length < 5 || event.params.length > 6) {
                event.markInvalid("point event requires either 5 or 6 parameters.");
            }

            int id = event.getInt(0);
            int x = event.getInt(1);
            int y = event.getInt(2);
            int z = event.getInt(3);
            int regionSize = event.getInt(4);
            wecui.getSelection().setPoint(id, x, y, z, regionSize);
            event.markHandled();
        }
    }

}
