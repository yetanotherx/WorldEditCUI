package wecui.render.shapes;

import org.lwjgl.opengl.GL11;
import wecui.obfuscation.RenderObfuscation;
import wecui.render.LineColor;
import wecui.render.LineInfo;
import wecui.render.points.PointCube;

/**
 * Draws the grid lines around a cylindrical region
 * 
 * @author yetanotherx
 */
public class RenderCylinderGrid {

    protected LineColor color;
    protected double radX = 0;
    protected double radZ = 0;
    protected int minY;
    protected int maxY;
    protected RenderObfuscation obf = RenderObfuscation.getInstance();
    protected double centerX;
    protected double centerZ;

    public RenderCylinderGrid(LineColor color, PointCube center, double radX, double radZ, int minY, int maxY) {
        this.color = color;
        this.radX = radX;
        this.radZ = radZ;
        this.minY = minY;
        this.maxY = maxY;
        this.centerX = center.getPoint().getX() + 0.5;
        this.centerZ = center.getPoint().getZ() + 0.5;
    }

    public void render() {
        for (LineInfo tempColor : color.getColors()) {
            tempColor.prepareRender();

            int tmaxY = maxY + 1;
            int tminY = minY;
            int posRadiusX = (int) Math.ceil(radX);
            int negRadiusX = (int) -Math.ceil(radX);
            int posRadiusZ = (int) Math.ceil(radZ);
            int negRadiusZ = (int) -Math.ceil(radZ);

            for (double tempX = negRadiusX; tempX <= posRadiusX; ++tempX) {
                double tempZ = radZ * Math.cos(Math.asin(tempX / radX));
                obf.startDrawing(GL11.GL_LINE_LOOP);
                tempColor.prepareColor();

                obf.addVertex(centerX + tempX, tmaxY, centerZ + tempZ);
                obf.addVertex(centerX + tempX, tmaxY, centerZ - tempZ);
                obf.addVertex(centerX + tempX, tminY, centerZ - tempZ);
                obf.addVertex(centerX + tempX, tminY, centerZ + tempZ);

                obf.finishDrawing();
            }

            for (double tempZ = negRadiusZ; tempZ <= posRadiusZ; ++tempZ) {
                double tempX = radX * Math.sin(Math.acos(tempZ / radZ));
                obf.startDrawing(GL11.GL_LINE_LOOP);
                tempColor.prepareColor();

                obf.addVertex(centerX + tempX, tmaxY, centerZ + tempZ);
                obf.addVertex(centerX - tempX, tmaxY, centerZ + tempZ);
                obf.addVertex(centerX - tempX, tminY, centerZ + tempZ);
                obf.addVertex(centerX + tempX, tminY, centerZ + tempZ);

                obf.finishDrawing();
            }
        }
    }
}
