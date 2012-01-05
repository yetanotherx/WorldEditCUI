package wecui.render.shapes;

import org.lwjgl.opengl.GL11;
import wecui.obfuscation.RenderObfuscation;
import wecui.render.LineColor;
import wecui.render.LineInfo;
import wecui.render.points.PointCube;
import wecui.util.Vector3;

/**
 * Draws an ellipsoid shape around a center point.
 * 
 * @author yetanotherx
 */
public class RenderEllipsoid {

    protected LineColor color;
    protected PointCube center;
    protected Vector3 radii;
    protected RenderObfuscation obf = RenderObfuscation.getInstance();
    protected final static double twoPi = Math.PI * 2;
    protected double centerX;
    protected double centerY;
    protected double centerZ;

    public RenderEllipsoid(LineColor color, PointCube center, Vector3 radii) {
        this.color = color;
        this.center = center;
        this.radii = radii;
        this.centerX = center.getPoint().getX() + 0.5;
        this.centerY = center.getPoint().getY() + 0.5;
        this.centerZ = center.getPoint().getZ() + 0.5;
    }

    public void render() {
        for (LineInfo tempColor : color.getColors()) {
            tempColor.prepareRender();
            drawXZPlane(tempColor);
            drawYZPlane(tempColor);
            drawXYPlane(tempColor);
        }
    }

    protected void drawXZPlane(LineInfo color) {
        int yRad = (int) Math.floor(radii.getY());
        for (int yBlock = -yRad; yBlock < yRad; yBlock++) {
            obf.startDrawing(GL11.GL_LINE_LOOP);
            color.prepareColor();

            for (int i = 0; i <= 40; i++) {
                double tempTheta = i * twoPi / 40;
                double tempX = radii.getX() * Math.cos(tempTheta) * Math.cos(Math.asin(yBlock / radii.getY()));
                double tempZ = radii.getZ() * Math.sin(tempTheta) * Math.cos(Math.asin(yBlock / radii.getY()));

                obf.addVertex(centerX + tempX, centerY + yBlock, centerZ + tempZ);
            }
            obf.finishDrawing();
        }

        obf.startDrawing(GL11.GL_LINE_LOOP);
        color.prepareColor();

        for (int i = 0; i <= 40; i++) {
            double tempTheta = i * twoPi / 40;
            double tempX = radii.getX() * Math.cos(tempTheta);
            double tempZ = radii.getZ() * Math.sin(tempTheta);

            obf.addVertex(centerX + tempX, centerY, centerZ + tempZ);
        }
        obf.finishDrawing();
    }

    protected void drawYZPlane(LineInfo color) {
        int xRad = (int) Math.floor(radii.getX());
        for (int xBlock = -xRad; xBlock < xRad; xBlock++) {
            obf.startDrawing(GL11.GL_LINE_LOOP);
            color.prepareColor();

            for (int i = 0; i <= 40; i++) {
                double tempTheta = i * twoPi / 40;
                double tempY = radii.getY() * Math.cos(tempTheta) * Math.sin(Math.acos(xBlock / radii.getX()));
                double tempZ = radii.getZ() * Math.sin(tempTheta) * Math.sin(Math.acos(xBlock / radii.getX()));

                obf.addVertex(centerX + xBlock, centerY + tempY, centerZ + tempZ);
            }
            obf.finishDrawing();
        }

        obf.startDrawing(GL11.GL_LINE_LOOP);
        color.prepareColor();

        for (int i = 0; i <= 40; i++) {
            double tempTheta = i * twoPi / 40;
            double tempY = radii.getY() * Math.cos(tempTheta);
            double tempZ = radii.getZ() * Math.sin(tempTheta);

            obf.addVertex(centerX, centerY + tempY, centerZ + tempZ);
        }
        obf.finishDrawing();
    }

    protected void drawXYPlane(LineInfo color) {
        int zRad = (int) Math.floor(radii.getZ());
        for (int zBlock = -zRad; zBlock < zRad; zBlock++) {
            obf.startDrawing(GL11.GL_LINE_LOOP);
            color.prepareColor();

            for (int i = 0; i <= 40; i++) {
                double tempTheta = i * twoPi / 40;
                double tempX = radii.getX() * Math.sin(tempTheta) * Math.sin(Math.acos(zBlock / radii.getZ()));
                double tempY = radii.getY() * Math.cos(tempTheta) * Math.sin(Math.acos(zBlock / radii.getZ()));

                obf.addVertex(centerX + tempX, centerY + tempY, centerZ + zBlock);
            }
            obf.finishDrawing();
        }

        obf.startDrawing(GL11.GL_LINE_LOOP);
        color.prepareColor();

        for (int i = 0; i <= 40; i++) {
            double tempTheta = i * twoPi / 40;
            double tempX = radii.getX() * Math.cos(tempTheta);
            double tempY = radii.getY() * Math.sin(tempTheta);

            obf.addVertex(centerX + tempX, centerY + tempY, centerZ);
        }
        obf.finishDrawing();
    }
}
