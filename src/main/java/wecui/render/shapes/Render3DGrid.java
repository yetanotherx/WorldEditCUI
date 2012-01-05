package wecui.render.shapes;

import org.lwjgl.opengl.GL11;
import wecui.obfuscation.RenderObfuscation;
import wecui.render.LineColor;
import wecui.render.LineInfo;
import wecui.util.Vector3;

/**
 * Draws the grid for a region between
 * two corners in a cuboid region.
 * 
 * @author yetanotherx
 */
public class Render3DGrid {

    protected RenderObfuscation obf = RenderObfuscation.getInstance();
    protected LineColor color;
    protected Vector3 first;
    protected Vector3 second;

    public Render3DGrid(LineColor color, Vector3 first, Vector3 second) {
        this.color = color;
        this.first = first;
        this.second = second;
    }

    public void render() {
        double x1 = first.getX();
        double y1 = first.getY();
        double z1 = first.getZ();
        double x2 = second.getX();
        double y2 = second.getY();
        double z2 = second.getZ();

        for (LineInfo tempColor : color.getColors()) {
            tempColor.prepareRender();

            RenderObfuscation o = RenderObfuscation.getInstance();
            o.startDrawing(GL11.GL_LINES);
            tempColor.prepareColor();

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

            o.finishDrawing();
        }
    }
}
