package wecui.render.shapes;

import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

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
        for (LineInfo tempColor : this.color.getColors()) {
            tempColor.prepareRender();
            this.drawXZPlane(tempColor);
            this.drawYZPlane(tempColor);
            this.drawXYPlane(tempColor);
        }
    }

    protected void drawXZPlane(LineInfo color) {
        Tessellator tess = Tessellator.instance;
        int yRad = (int) Math.floor(this.radii.getY());
        for (int yBlock = -yRad; yBlock < yRad; yBlock++) {
            tess.startDrawing(GL11.GL_LINE_LOOP);
            color.prepareColor();

            for (int i = 0; i <= 40; i++) {
                double tempTheta = i * twoPi / 40;
                double tempX = this.radii.getX() * Math.cos(tempTheta) * Math.cos(Math.asin(yBlock / this.radii.getY()));
                double tempZ = this.radii.getZ() * Math.sin(tempTheta) * Math.cos(Math.asin(yBlock / this.radii.getY()));

                tess.addVertex(this.centerX + tempX, this.centerY + yBlock, this.centerZ + tempZ);
            }
            tess.draw();
        }

        tess.startDrawing(GL11.GL_LINE_LOOP);
        color.prepareColor();

        for (int i = 0; i <= 40; i++) {
            double tempTheta = i * twoPi / 40;
            double tempX = this.radii.getX() * Math.cos(tempTheta);
            double tempZ = this.radii.getZ() * Math.sin(tempTheta);

            tess.addVertex(this.centerX + tempX, this.centerY, this.centerZ + tempZ);
        }
        tess.draw();
    }

    protected void drawYZPlane(LineInfo color) {
        Tessellator tess = Tessellator.instance;
        int xRad = (int) Math.floor(this.radii.getX());
        for (int xBlock = -xRad; xBlock < xRad; xBlock++) {
            tess.startDrawing(GL11.GL_LINE_LOOP);
            color.prepareColor();

            for (int i = 0; i <= 40; i++) {
                double tempTheta = i * twoPi / 40;
                double tempY = this.radii.getY() * Math.cos(tempTheta) * Math.sin(Math.acos(xBlock / this.radii.getX()));
                double tempZ = this.radii.getZ() * Math.sin(tempTheta) * Math.sin(Math.acos(xBlock / this.radii.getX()));

                tess.addVertex(this.centerX + xBlock, this.centerY + tempY, this.centerZ + tempZ);
            }
            tess.draw();
        }

        tess.startDrawing(GL11.GL_LINE_LOOP);
        color.prepareColor();

        for (int i = 0; i <= 40; i++) {
            double tempTheta = i * twoPi / 40;
            double tempY = this.radii.getY() * Math.cos(tempTheta);
            double tempZ = this.radii.getZ() * Math.sin(tempTheta);

            tess.addVertex(this.centerX, this.centerY + tempY, this.centerZ + tempZ);
        }
        tess.draw();
    }

    protected void drawXYPlane(LineInfo color) {
        Tessellator tess = Tessellator.instance;
        int zRad = (int) Math.floor(this.radii.getZ());
        for (int zBlock = -zRad; zBlock < zRad; zBlock++) {
            tess.startDrawing(GL11.GL_LINE_LOOP);
            color.prepareColor();

            for (int i = 0; i <= 40; i++) {
                double tempTheta = i * twoPi / 40;
                double tempX = this.radii.getX() * Math.sin(tempTheta) * Math.sin(Math.acos(zBlock / this.radii.getZ()));
                double tempY = this.radii.getY() * Math.cos(tempTheta) * Math.sin(Math.acos(zBlock / this.radii.getZ()));

                tess.addVertex(this.centerX + tempX, this.centerY + tempY, this.centerZ + zBlock);
            }
            tess.draw();
        }

        tess.startDrawing(GL11.GL_LINE_LOOP);
        color.prepareColor();

        for (int i = 0; i <= 40; i++) {
            double tempTheta = i * twoPi / 40;
            double tempX = this.radii.getX() * Math.cos(tempTheta);
            double tempY = this.radii.getY() * Math.sin(tempTheta);

            tess.addVertex(this.centerX + tempX, this.centerY + tempY, this.centerZ);
        }
        tess.draw();
    }
}
