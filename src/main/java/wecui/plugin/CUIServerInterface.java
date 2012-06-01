package wecui.plugin;

import com.sk89q.worldedit.BiomeTypes;
import com.sk89q.worldedit.ServerInterface;
import wecui.WorldEditCUI;

/**
 * Stub class for a WorldEdit server interface
 * This may need more stuff in the future, but it works for now.
 * 
 * @author yetanotherx
 */
public class CUIServerInterface extends ServerInterface {

    protected WorldEditCUI controller;

    public CUIServerInterface(WorldEditCUI controller) {
        this.controller = controller;
    }

    @Override
    public int resolveItem(String name) {
        return 0;
    }

    @Override
    public boolean isValidMobType(String type) {
        return true;
    }

    @Override
    public void reload() {
    }

    @Override
    public BiomeTypes getBiomes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
