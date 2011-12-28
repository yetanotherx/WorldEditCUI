package wecui.render;

import java.util.List;
import org.lwjgl.opengl.GL11;
import wecui.obfuscation.RenderObfuscation;
import wecui.render.points.PointContainer;
import wecui.render.points.PointCube;
import wecui.render.points.PointRectangle;

public class RenderShapes {

    public static void drawGridBetweenCorners(LineColor color, PointContainer first, PointContainer second) {
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

    public static void drawBoxBetweenCorners(LineColor color, PointContainer first, PointContainer second) {
        drawBox(color.getHidden(), first.getX(), first.getY(), first.getZ(), second.getX(), second.getY(), second.getZ());
    }

    public static void drawGridBetweenPoints(LineColor color, List<PointRectangle> points, int min, int max) {
        double off = 0.03;
        for (double height = min; height <= max + 1; height++) {

            if (height == min || height == max + 1) {
                drawPolygon(color.getHidden(), height + off, points);
                drawPolygon(color.getNormal(), height + off, points);
            } else {
                drawPolygon(color.getHidden(), height + off, points);
                drawPolygon(color.getNormal(), height + off, points);
            }
        }
    }

    public static void drawBoxBetweenPoints(LineColor color, List<PointRectangle> points, int min, int max) {
        double off = 0.03;
        for (LineInfo tempColor : color.getColors()) {
            tempColor.prepareRender();

            RenderObfuscation o = RenderObfuscation.getInstance();
            o.startDrawing(GL11.GL_LINES);
            tempColor.prepareColor();

            for (PointRectangle point : points) {
                if (point != null) {
                    o.addVertex(point.getPoint().getX() + 0.5, min + off, point.getPoint().getZ() + 0.5);
                    o.addVertex(point.getPoint().getX() + 0.5, max + 1 + off, point.getPoint().getZ() + 0.5);
                }
            }
            o.finishDrawing();
        }
    }

    public static void drawEllipsoidAroundPoint(LineColor color, PointCube center, PointContainer radii) {
        double twoPi = Math.PI * 2;
        double[] sinThetas = new double[]{0.84806, 0.5236, 0.25272, -0.84806, -0.5236, -0.25272}; //The values of theta such that sin(theta) is 1/4, 2/4, 3/4, etc.
        double[] cosThetas = new double[]{0.7227, 1.047197, 1.3181, 1.8234, 2.09439, 2.418858}; //The values of theta such that cos(theta) is 1/4, 2/4, 3/4, etc.

        double x = center.getPoint().getX() + 0.5;
        double y = center.getPoint().getY() + 0.5;
        double z = center.getPoint().getZ() + 0.5;

        RenderObfuscation o = RenderObfuscation.getInstance();

        for (LineInfo tempColor : LineColor.POLYPOINT.getColors()) {
            tempColor.prepareRender();

            //XZ plane
            for (double yBlock = -radii.getY(); yBlock < radii.getY(); yBlock++) {
                o.startDrawing(GL11.GL_LINE_LOOP);
                tempColor.prepareColor();

                for (int i = 0; i <= 40; i++) {
                    double tempTheta = i * twoPi / 40;
                    double tempX = radii.getX() * Math.cos(tempTheta) * Math.cos(Math.asin(yBlock / radii.getY()));
                    double tempZ = radii.getZ() * Math.sin(tempTheta) * Math.cos(Math.asin(yBlock / radii.getY()));

                    o.addVertex(x + tempX, y + yBlock, z + tempZ);
                }
                o.finishDrawing();
            }

            o.startDrawing(GL11.GL_LINE_LOOP);
            tempColor.prepareColor();

            for (int i = 0; i <= 40; i++) {
                double tempTheta = i * twoPi / 40;
                double tempX = radii.getX() * Math.cos(tempTheta);
                double tempZ = radii.getZ() * Math.sin(tempTheta);

                o.addVertex(x + tempX, y, z + tempZ);
            }
            o.finishDrawing();

            //YZ plane
            for (double xBlock = -radii.getX(); xBlock < radii.getX(); xBlock++) {
                o.startDrawing(GL11.GL_LINE_LOOP);
                tempColor.prepareColor();

                for (int i = 0; i <= 40; i++) {
                    double tempTheta = i * twoPi / 40;
                    double tempY = radii.getY() * Math.cos(tempTheta) * Math.sin(Math.acos(xBlock / radii.getX()));
                    double tempZ = radii.getZ() * Math.sin(tempTheta) * Math.sin(Math.acos(xBlock / radii.getX()));

                    o.addVertex(x + xBlock, y + tempY, z + tempZ);
                }
                o.finishDrawing();
            }

            o.startDrawing(GL11.GL_LINE_LOOP);
            tempColor.prepareColor();

            for (int i = 0; i <= 40; i++) {
                double tempTheta = i * twoPi / 40;
                double tempY = radii.getY() * Math.cos(tempTheta);
                double tempZ = radii.getZ() * Math.sin(tempTheta);

                o.addVertex(x, y + tempY, z + tempZ);
            }
            o.finishDrawing();

            //XY plane
            for (double zBlock = -radii.getZ(); zBlock < radii.getZ(); zBlock++) {
                o.startDrawing(GL11.GL_LINE_LOOP);
                tempColor.prepareColor();

                for (int i = 0; i <= 40; i++) {
                    double tempTheta = i * twoPi / 40;
                    double tempX = radii.getX() * Math.sin(tempTheta) * Math.sin(Math.acos(zBlock / radii.getZ()));
                    double tempY = radii.getY() * Math.cos(tempTheta) * Math.sin(Math.acos(zBlock / radii.getZ()));

                    o.addVertex(x + tempX, y + tempY, z + zBlock);
                }
                o.finishDrawing();
            }

            o.startDrawing(GL11.GL_LINE_LOOP);
            tempColor.prepareColor();

            for (int i = 0; i <= 40; i++) {
                double tempTheta = i * twoPi / 40;
                double tempX = radii.getX() * Math.cos(tempTheta);
                double tempY = radii.getY() * Math.sin(tempTheta);

                o.addVertex(x + tempX, y + tempY, z);
            }
            o.finishDrawing();
        }
    }

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

        RenderObfuscation o = RenderObfuscation.getInstance();

        // Draw bottom face
        o.startDrawing(GL11.GL_LINE_LOOP);
        info.prepareColor();
        o.addVertex(x1, y1, z1);
        o.addVertex(x2, y1, z1);
        o.addVertex(x2, y1, z2);
        o.addVertex(x1, y1, z2);
        o.finishDrawing();

        // Draw top face
        o.startDrawing(GL11.GL_LINE_LOOP);
        info.prepareColor();
        o.addVertex(x1, y2, z1);
        o.addVertex(x2, y2, z1);
        o.addVertex(x2, y2, z2);
        o.addVertex(x1, y2, z2);
        o.finishDrawing();

        // Draw join top and bottom faces
        o.startDrawing(GL11.GL_LINES);
        info.prepareColor();

        o.addVertex(x1, y1, z1);
        o.addVertex(x1, y2, z1);

        o.addVertex(x2, y1, z1);
        o.addVertex(x2, y2, z1);

        o.addVertex(x2, y1, z2);
        o.addVertex(x2, y2, z2);

        o.addVertex(x1, y1, z2);
        o.addVertex(x1, y2, z2);

        o.finishDrawing();
    }

    public static void drawPolygon(LineInfo info, double height, List<PointRectangle> points) {
        info.prepareRender();
        RenderObfuscation o = RenderObfuscation.getInstance();

        o.startDrawing(GL11.GL_LINE_LOOP);
        info.prepareColor();
        for (PointRectangle point : points) {
            if (point != null) {
                o.addVertex(point.getPoint().getX() + 0.5, height, point.getPoint().getZ() + 0.5);
            }
        }
        o.finishDrawing();
    }
}
