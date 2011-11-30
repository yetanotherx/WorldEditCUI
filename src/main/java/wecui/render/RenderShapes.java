package wecui.render;

import wecui.obfuscation.ObfuscationHandler;
import org.lwjgl.opengl.GL11;

/**
 * Actually renders various shapes. 
 * 
 * @author lahwran
 * @author yetanotherx
 *
 */
public class RenderShapes {

    /**
     * Draws a box between two points.
     * First draws the bottom face and top face, then fills in the four other lines.
     * 
     * @param info LineInfo to draw lines with
     * @param x1 X of first vertex
     * @param y1 Y of first vertex
     * @param z1 Z of first vertex
     * @param x2 X of second vertex
     * @param y2 Y of second vertex
     * @param z2 Z of second vertex
     */
    public static void drawBox(LineInfo info, double x1, double y1, double z1, double x2, double y2, double z2) {
        info.prepareRender();

        ObfuscationHandler o = ObfuscationHandler.instance;

        // Draw bottom face
        o.draw_begin(GL11.GL_LINE_LOOP);
        info.prepareColor();
        o.addVertex(x1, y1, z1);
        o.addVertex(x2, y1, z1);
        o.addVertex(x2, y1, z2);
        o.addVertex(x1, y1, z2);
        o.draw();

        // Draw top face
        o.draw_begin(GL11.GL_LINE_LOOP);
        info.prepareColor();
        o.addVertex(x1, y2, z1);
        o.addVertex(x2, y2, z1);
        o.addVertex(x2, y2, z2);
        o.addVertex(x1, y2, z2);
        o.draw();

        // Draw join top and bottom faces
        o.draw_begin(GL11.GL_LINES);
        info.prepareColor();

        o.addVertex(x1, y1, z1);
        o.addVertex(x1, y2, z1);

        o.addVertex(x2, y1, z1);
        o.addVertex(x2, y2, z1);

        o.addVertex(x2, y1, z2);
        o.addVertex(x2, y2, z2);

        o.addVertex(x1, y1, z2);
        o.addVertex(x1, y2, z2);

        o.draw();
    }

    /**
     * Draws a grid between two points.
     * 
     * @param info LineInfo to draw lines with
     * @param x1 X of first vertex
     * @param y1 Y of first vertex
     * @param z1 Z of first vertex
     * @param x2 X of second vertex
     * @param y2 Y of second vertex
     * @param z2 Z of second vertex
     */
    public static void drawGridSurface(LineInfo info, double x1, double y1, double z1, double x2, double y2, double z2) {
        info.prepareRender();
        ObfuscationHandler o = ObfuscationHandler.instance;
        o.draw_begin(GL11.GL_LINES);
        info.prepareColor();
        double x, y, z;
        double offsetSize = 1.0;

        // Zmax XY plane, y axis
        z = z2;
        y = y1;
        int msize = 150;
        if ((y2 - y / offsetSize) < msize) {
            for (double yoff = 0; yoff + y <= y2; yoff += offsetSize) {
                o.addVertex(x1, y + yoff, z);
                o.addVertex(x2, y + yoff, z);
            }
        }

        // Zmin XY plane, y axis
        z = z1;
        if ((y2 - y / offsetSize) < msize) {
            for (double yoff = 0; yoff + y <= y2; yoff += offsetSize) {
                o.addVertex(x1, y + yoff, z);
                o.addVertex(x2, y + yoff, z);
            }
        }

        // Xmin YZ plane, y axis
        x = x1;
        if ((y2 - y / offsetSize) < msize) {
            for (double yoff = 0; yoff + y <= y2; yoff += offsetSize) {
                o.addVertex(x, y + yoff, z1);
                o.addVertex(x, y + yoff, z2);
            }
        }

        // Xmax YZ plane, y axis
        x = x2;
        if ((y2 - y / offsetSize) < msize) {
            for (double yoff = 0; yoff + y <= y2; yoff += offsetSize) {
                o.addVertex(x, y + yoff, z1);
                o.addVertex(x, y + yoff, z2);
            }
        }

        // Zmin XY plane, x axis
        x = x1;
        z = z1;
        if ((x2 - x / offsetSize) < msize) {
            for (double xoff = 0; xoff + x <= x2; xoff += offsetSize) {
                o.addVertex(x + xoff, y1, z);
                o.addVertex(x + xoff, y2, z);
            }
        }
        // Zmax XY plane, x axis
        z = z2;
        if ((x2 - x / offsetSize) < msize) {
            for (double xoff = 0; xoff + x <= x2; xoff += offsetSize) {
                o.addVertex(x + xoff, y1, z);
                o.addVertex(x + xoff, y2, z);
            }
        }
        // Ymin XZ plane, x axis
        y = y2;
        if ((x2 - x / offsetSize) < msize) {
            for (double xoff = 0; xoff + x <= x2; xoff += offsetSize) {
                o.addVertex(x + xoff, y, z1);
                o.addVertex(x + xoff, y, z2);
            }
        }
        // Ymax XZ plane, x axis
        y = y1;
        if ((x2 - x / offsetSize) < msize) {
            for (double xoff = 0; xoff + x <= x2; xoff += offsetSize) {
                o.addVertex(x + xoff, y, z1);
                o.addVertex(x + xoff, y, z2);
            }
        }

        // Ymin XZ plane, z axis
        z = z1;
        y = y1;
        if ((z2 - z / offsetSize) < msize) {
            for (double zoff = 0; zoff + z <= z2; zoff += offsetSize) {
                o.addVertex(x1, y, z + zoff);
                o.addVertex(x2, y, z + zoff);
            }
        }
        // Ymax XZ plane, z axis
        y = y2;
        if ((z2 - z / offsetSize) < msize) {
            for (double zoff = 0; zoff + z <= z2; zoff += offsetSize) {
                o.addVertex(x1, y, z + zoff);
                o.addVertex(x2, y, z + zoff);
            }
        }
        // Xmin YZ plane, z axis
        x = x2;
        if ((z2 - z / offsetSize) < msize) {
            for (double zoff = 0; zoff + z <= z2; zoff += offsetSize) {
                o.addVertex(x, y1, z + zoff);
                o.addVertex(x, y2, z + zoff);
            }
        }
        // Xmax YZ plane, z axis
        x = x1;
        if ((z2 - z / offsetSize) < msize) {
            for (double zoff = 0; zoff + z <= z2; zoff += offsetSize) {
                o.addVertex(x, y1, z + zoff);
                o.addVertex(x, y2, z + zoff);
            }
        }

        o.draw();
    }

    public static void draw2DLines(LineInfo info, double off, HighlightPosition2D[] pts, int min, int max) {
        info.prepareRender();
        ObfuscationHandler o = ObfuscationHandler.instance;
        o.draw_begin(GL11.GL_LINES);
        info.prepareColor();

        for (int i = 0; i < pts.length; i++) {
            if (pts[i] != null) {
                o.addVertex(pts[i].x + 0.5, min + off, pts[i].z + 0.5);
                o.addVertex(pts[i].x + 0.5, max + 1 + off, pts[i].z + 0.5);
            }
        }
        o.draw();
    }
    
    public static void draw2DPolygon(LineInfo info, double height, HighlightPosition2D[] pts) {
        info.prepareRender();
        ObfuscationHandler o = ObfuscationHandler.instance;

        o.draw_begin(GL11.GL_LINE_LOOP);
        info.prepareColor();
        for (int i = 0; i < pts.length; i++) {
            if (pts[i] != null) {
                o.addVertex(pts[i].x + 0.5, height, pts[i].z + 0.5);
            }
        }
        o.draw();
    }
}
