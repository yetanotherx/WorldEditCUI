package wecui.preview;

import wecui.util.BaseBlock;
import deobf.Block;
import deobf.GLAllocation;
import deobf.RenderBlocks;
import java.util.HashSet;
import org.lwjgl.opengl.GL11;
import wecui.InitializationFactory;
import wecui.WorldEditCUI;
import wecui.exception.InitializationException;
import wecui.obfuscation.RenderObfuscation;

/**
 * Main controller for previewing a set of points
 * 
 * @author yetanotherx
 * 
 * @obfuscated 1.2.5
 */
public class PointPreview implements InitializationFactory {

    private WorldEditCUI controller;
    protected HashSet<BaseBlock> points = new HashSet<BaseBlock>();
    private int renderList = -1;

    public PointPreview(WorldEditCUI controller) {
        this.controller = controller;
    }

    public void render(float tick) {
        if (renderList < 0) {
            renderList = GLAllocation.a(1);
        }

        RenderBlocks blockRenderer = new RenderBlocks(controller.getObfuscation().getWorld());

        RenderObfuscation obf = RenderObfuscation.getInstance();

        int cycle = 0;

        GL11.glNewList(renderList, GL11.GL_COMPILE);
        GL11.glPushMatrix();
        obf.setTranslation(0.0D, 0.0D, 0.0D);
        GL11.glColorMask(true, true, true, true);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        for (BaseBlock previewBlock : points) {
            while (true) {
                if (cycle >= 2) {
                    break;
                }

                boolean continue_cycle = false;
                boolean first_run = false;

                int x = (int) previewBlock.getPoint().getX();
                int y = (int) previewBlock.getPoint().getY();
                int z = (int) previewBlock.getPoint().getZ();

                int id = previewBlock.getType();

                if (id <= 0) {
                    continue;
                }

                if (!first_run) {
                    first_run = true;
                    obf.startDrawing(GL11.GL_QUADS);
                }

//                if (cycle == 0 && Block.blocksList[id].hasTileEntity()) {
//                    TileEntity tileentity = chunkcache.getBlockTileEntity(z, x, y);
//
//                    if (TileEntityRenderer.instance.hasSpecialRenderer(tileentity)) {
//                        tileEntityRenderers.add(tileentity);
//                    }
//                }

                Block block = controller.getObfuscation().getBlockFromID(id);
                int renderPass = obf.getRenderBlockPass(block);

                if (renderPass != cycle) {
                    continue_cycle = true;
                    continue;
                }

                if (renderPass == cycle) {
                    try {
                        obf.renderBlockByRenderType(blockRenderer, block, x, y, z);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                if (first_run) {
                    obf.finishDrawing();
                }

                if (!continue_cycle) {
                    break;
                }

                cycle++;
            }

        }

        GL11.glPopMatrix();
        GL11.glEndList();
        GL11.glCallList(renderList);
        obf.setTranslation(0.0D, 0.0D, 0.0D);
    }

    public void setPoint(int x, int y, int z, int id, int data) {
        if (id == 0) {
            points.remove(new BaseBlock(x, y, z, id, data));
        } else {
            points.add(new BaseBlock(x, y, z, id, data));
        }
    }

    public void initialize() throws InitializationException {
    }

    public boolean hasPreview() {
        return points.size() > 0;
    }

    public void clear() {
        points.clear();
    }
}
