package wecui.render.shapes;

import org.lwjgl.opengl.GL11;
import wecui.obfuscation.RenderObfuscation;
import wecui.render.LineColor;
import wecui.render.LineInfo;
import wecui.render.points.PointContainer;
import wecui.render.points.PointCube;

/**
 * Draws an ellipsoid shape around a center point.
 * 
 * @author yetanotherx
 */
public class RenderCylinder {

    protected LineColor color;
    protected PointCube center;
    protected double radius;
    protected int minY;
    protected int maxY;
    protected RenderObfuscation obf = RenderObfuscation.getInstance();
    protected double centerX;
    protected double centerZ;

    public RenderCylinder(LineColor color, PointCube center, double radius, int minY, int maxY) {
        this.color = color;
        this.center = center;
        this.radius = radius;
        this.minY = minY;
        this.maxY = maxY;
        this.centerX = center.getPoint().getX() + 0.5;
        this.centerZ = center.getPoint().getZ() + 0.5;
    }

    public void render() {
        for (LineInfo tempColor : color.getColors()) {
            tempColor.prepareRender();
            drawCircles(tempColor);
            drawGrid(tempColor);
        }
    }

    protected void drawCircles(LineInfo color) {
        double twoPi = Math.PI * 2;
        
        for (int yBlock = minY; yBlock <= maxY + 1; yBlock++) {
            obf.startDrawing(GL11.GL_LINE_LOOP);
            color.prepareColor();

            for (int i = 0; i <= 75; i++) {
                double tempTheta = i * twoPi / 75;
                double tempX = radius * Math.cos(tempTheta);
                double tempZ = radius * Math.sin(tempTheta);

                obf.addVertex(centerX + tempX, yBlock, centerZ + tempZ);
            }
            obf.finishDrawing();
        }
    }

    protected void drawGrid(LineInfo color) {
        int tmaxY = maxY + 1;
        int tminY = minY;
        int posRadius = (int) Math.ceil(radius);
        int negRadius = (int) -Math.ceil(radius);

        for (double tempX = negRadius; tempX <= posRadius; ++tempX) {
            double tempZ = radius * Math.cos(Math.asin(tempX / radius));
            obf.startDrawing(GL11.GL_LINE_LOOP);
            color.prepareColor();

            obf.addVertex(centerX + tempX, tmaxY, centerZ + tempZ);
            obf.addVertex(centerX + tempX, tmaxY, centerZ - tempZ);
            obf.addVertex(centerX + tempX, tminY, centerZ - tempZ);
            obf.addVertex(centerX + tempX, tminY, centerZ + tempZ);

            obf.finishDrawing();
        }

        for (double tempZ = negRadius; tempZ <= posRadius; ++tempZ) {
            double tempX = radius * Math.sin(Math.acos(tempZ / radius));
            obf.startDrawing(GL11.GL_LINE_LOOP);
            color.prepareColor();

            obf.addVertex(centerX + tempX, tmaxY, centerZ + tempZ);
            obf.addVertex(centerX - tempX, tmaxY, centerZ + tempZ);
            obf.addVertex(centerX - tempX, tminY, centerZ + tempZ);
            obf.addVertex(centerX + tempX, tminY, centerZ + tempZ);

            obf.finishDrawing();
        }
    }
}
