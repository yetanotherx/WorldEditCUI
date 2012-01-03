package wecui.render.shapes;

import org.lwjgl.opengl.GL11;
import wecui.obfuscation.RenderObfuscation;
import wecui.render.LineColor;
import wecui.render.LineInfo;
import wecui.render.points.PointCube;

/**
 * Draws the top and bottom circles around a cylindrical region
 * 
 * @author yetanotherx
 */
public class RenderCylinderBox {

    protected LineColor color;
    protected double radX = 0;
    protected double radZ = 0;
    protected int minY;
    protected int maxY;
    protected RenderObfuscation obf = RenderObfuscation.getInstance();
    protected double centerX;
    protected double centerZ;

    public RenderCylinderBox(LineColor color, PointCube center, double radX, double radZ, int minY, int maxY) {
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
            
            double twoPi = Math.PI * 2;
            for (int yBlock : new int[]{minY, maxY + 1}) {
                obf.startDrawing(GL11.GL_LINE_LOOP);
                tempColor.prepareColor();

                for (int i = 0; i <= 75; i++) {
                    double tempTheta = i * twoPi / 75;
                    double tempX = radX * Math.cos(tempTheta);
                    double tempZ = radZ * Math.sin(tempTheta);

                    obf.addVertex(centerX + tempX, yBlock, centerZ + tempZ);
                }
                obf.finishDrawing();
            }
        }
    }
}
