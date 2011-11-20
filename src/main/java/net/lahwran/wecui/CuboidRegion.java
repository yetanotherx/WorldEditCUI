/**
 * 
 */
package net.lahwran.wecui;

import net.lahwran.wecui.rendering.HighlightPosition;
import net.lahwran.wecui.rendering.LineInfo;
import net.lahwran.wecui.rendering.RenderShapes;

import org.lwjgl.opengl.GL11;


/**
 * @author lahwran
 *
 */
public class CuboidRegion extends CuiRegion {

    private final HighlightPosition[] pts;

    public static LineInfo firstpoint = new LineInfo(3.0f, 0.2f, 0.8f, 0.2f);
    public static LineInfo secondpoint = new LineInfo(3.0f, 0.2f, 0.2f, 0.8f);
    public static LineInfo gridnormal = new LineInfo(3.0f, 0.8F, 0.2F, 0.2F, 0.2f, GL11.GL_LESS);
    public static LineInfo gridhidden = new LineInfo(2.0f, 0.4F, 0.1F, 0.1F, 0.1f, GL11.GL_GEQUAL);
    public static LineInfo boxnormal = new LineInfo(3.0f, 0.8F, 0.2F, 0.2F, 1.0f, GL11.GL_LESS);
    public static LineInfo boxhidden = new LineInfo(2.0f, 0.4F, 0.1F, 0.1F, 0.2f, GL11.GL_GEQUAL);

    public double x1, y1, z1, x2, y2, z2;

    public CuboidRegion() {
        pts = new HighlightPosition[2];
        pts[0] = new HighlightPosition(firstpoint, 0.8f, 0.2f);
        pts[1] = new HighlightPosition(secondpoint, 0.8f, 0.2f);
    }

    @Override
    public void render() {
        pts[0].render();
        pts[1].render();
        if (pts[0].active && pts[1].active) {
            RenderShapes.gridSurface(gridnormal, x1, y1, z1, x2, y2, z2);
            RenderShapes.gridSurface(gridhidden, x1, y1, z1, x2, y2, z2);
            RenderShapes.box(boxhidden, x1, y1, z1, x2, y2, z2);
            RenderShapes.box(boxnormal, x1, y1, z1, x2, y2, z2);
        }
    }

    private void calcBounds() {
        double off = 0.02;
        double off1 = 1 + off;
        x1 = Double.MAX_VALUE;
        y1 = Double.MAX_VALUE;
        z1 = Double.MAX_VALUE;
        x2 = -Double.MAX_VALUE;
        y2 = -Double.MAX_VALUE;
        z2 = -Double.MAX_VALUE;

        for (int index = 0; index < pts.length; index++) {
            HighlightPosition pos = pts[index];
            if (!pos.active)
                continue;
            if (pos.x + off1 > x2)
                x2 = pos.x + off1;

            if (pos.x - off < x1)
                x1 = pos.x - off;

            if (pos.y + off1 > y2)
                y2 = pos.y + off1;

            if (pos.y - off < y1)
                y1 = pos.y - off;

            if (pos.z + off1 > z2)
                z2 = pos.z + off1;

            if (pos.z - off < z1)
                z1 = pos.z - off;
        }
    }

    @Override
    public void setPoint(int id, int x, int y, int z, int regionSize) {
        if (id < pts.length) {
            pts[id].x = x;
            pts[id].y = y;
            pts[id].z = z;
            pts[id].active = true;
            calcBounds();
        }
    }

}
