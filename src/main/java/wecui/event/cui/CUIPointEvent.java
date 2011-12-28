package wecui.event.cui;

import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldVector;
import com.sk89q.worldedit.regions.RegionSelector;
import wecui.WorldEditCUI;
import wecui.plugin.CUIWorld;

/**
 * Called when point event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIPointEvent extends CUIBaseEvent {

    public CUIPointEvent(WorldEditCUI controller, String[] args) {
        super(controller, args);
    }

    @Override
    public CUIEventType getEventType() {
        return CUIEventType.POINT;
    }

    @Override
    public String run() {
        int id = this.getInt(0);
        int x = this.getInt(1);
        int y = this.getInt(2);
        int z = this.getInt(3);

        controller.getSelection().setCuboidPoint(id, x, y, z);
        this.setLocalPoint(id, x, y, z);
        controller.getDebugger().debug("Setting point #" + id);

        return null;
    }

    protected void setLocalPoint(int id, int x, int y, int z) {
        if (controller.getLocalPlugin().isEnabled()) {

            WorldEdit plugin = controller.getLocalPlugin().getPlugin();
            CUIWorld world = controller.getLocalPlugin().getWorld();

            WorldVector clicked = new WorldVector(world, x, y, z);
            LocalSession session = plugin.getSession("player");
            RegionSelector selector = session.getRegionSelector(world);

            if (id == 0) {
                selector.selectPrimary(clicked);
            } else {
                selector.selectSecondary(clicked);
            }
        }
    }
}
