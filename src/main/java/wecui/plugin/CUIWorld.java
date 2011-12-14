package wecui.plugin;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.EntityType;
import com.sk89q.worldedit.LocalWorld;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.blocks.BaseItemStack;
import com.sk89q.worldedit.regions.Region;
import wecui.WorldEditCUI;

public class CUIWorld extends LocalWorld {
    
    protected WorldEditCUI controller;

    public CUIWorld(WorldEditCUI controller) {
        this.controller = controller;
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean setBlockType(Vector pt, int type) {
        System.out.println("Block: " + pt.toString() + " to type " + type);
        return true;
    }

    @Override
    public int getBlockType(Vector pt) {
        return 0;
    }

    @Override
    public void setBlockData(Vector pt, int data) {
        
    }

    @Override
    public void setBlockDataFast(Vector pt, int data) {
    }

    @Override
    public int getBlockData(Vector pt) {
        return 0;
    }

    @Override
    public int getBlockLightLevel(Vector pt) {
        return 0;
    }

    @Override
    public boolean regenerate(Region region, EditSession editSession) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean copyToWorld(Vector pt, BaseBlock block) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean copyFromWorld(Vector pt, BaseBlock block) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean clearContainerBlockContents(Vector pt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean generateTree(EditSession editSession, Vector pt) throws MaxChangedBlocksException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean generateBigTree(EditSession editSession, Vector pt) throws MaxChangedBlocksException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean generateBirchTree(EditSession editSession, Vector pt) throws MaxChangedBlocksException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean generateRedwoodTree(EditSession editSession, Vector pt) throws MaxChangedBlocksException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean generateTallRedwoodTree(EditSession editSession, Vector pt) throws MaxChangedBlocksException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void dropItem(Vector pt, BaseItemStack item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int killMobs(Vector origin, int radius) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int killMobs(Vector origin, int radius, boolean killPets) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int removeEntities(EntityType type, Vector origin, int radius) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CUIWorld)) {
            return false;
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
