package wecui.plugin;

import com.sk89q.worldedit.LocalPlayer;
import com.sk89q.worldedit.LocalWorld;
import com.sk89q.worldedit.ServerInterface;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldVector;
import com.sk89q.worldedit.bags.BlockBag;
import wecui.WorldEditCUI;

public class CUIPlayer extends LocalPlayer {

    protected WorldEditCUI controller;

    public CUIPlayer(ServerInterface server, WorldEditCUI controller) {
        super(server);
        this.controller = controller;
    }
    
    @Override
    public int getItemInHand() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getName() {
        return "player";
    }

    @Override
    public WorldVector getPosition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LocalWorld getWorld() {
        return controller.getLocalPlugin().getWorld();
    }

    @Override
    public double getPitch() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getYaw() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void giveItem(int type, int amt) {
    }

    @Override
    public void printRaw(String msg) {
    }

    @Override
    public void printDebug(String msg) {
    }

    @Override
    public void print(String msg) {
    }

    @Override
    public void printError(String msg) {
    }

    @Override
    public void setPosition(Vector pos, float pitch, float yaw) {
    }

    @Override
    public String[] getGroups() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BlockBag getInventoryBlockBag() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasPermission(String perm) {
        return true;
    }
    
}
