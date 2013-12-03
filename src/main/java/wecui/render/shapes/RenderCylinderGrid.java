package wecui.render.shapes;

import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

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
        Tessellator tess = Tessellator.instance;
        for (LineInfo tempColor : this.color.getColors()) {
            tempColor.prepareRender();

            int tmaxY = this.maxY + 1;
            int tminY = this.minY;
            int posRadiusX = (int) Math.ceil(this.radX);
            int negRadiusX = (int) -Math.ceil(this.radX);
            int posRadiusZ = (int) Math.ceil(this.radZ);
            int negRadiusZ = (int) -Math.ceil(this.radZ);

            for (double tempX = negRadiusX; tempX <= posRadiusX; ++tempX) {
                double tempZ = this.radZ * Math.cos(Math.asin(tempX / this.radX));
                tess.startDrawing(GL11.GL_LINE_LOOP);
                tempColor.prepareColor();

                tess.addVertex(this.centerX + tempX, tmaxY, this.centerZ + tempZ);
                tess.addVertex(this.centerX + tempX, tmaxY, this.centerZ - tempZ);
                tess.addVertex(this.centerX + tempX, tminY, this.centerZ - tempZ);
                tess.addVertex(this.centerX + tempX, tminY, this.centerZ + tempZ);

                tess.draw();
            }

            for (double tempZ = negRadiusZ; tempZ <= posRadiusZ; ++tempZ) {
                double tempX = this.radX * Math.sin(Math.acos(tempZ / this.radZ));
                tess.startDrawing(GL11.GL_LINE_LOOP);
                tempColor.prepareColor();

                tess.addVertex(this.centerX + tempX, tmaxY, this.centerZ + tempZ);
                tess.addVertex(this.centerX - tempX, tmaxY, this.centerZ + tempZ);
                tess.addVertex(this.centerX - tempX, tminY, this.centerZ + tempZ);
                tess.addVertex(this.centerX + tempX, tminY, this.centerZ + tempZ);

                tess.draw();
            }
        }
    }
}
