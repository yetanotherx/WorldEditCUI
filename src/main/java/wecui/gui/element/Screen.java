package wecui.gui.element;

import deobf.ct;
import deobf.xe;

/**
 * Alias for the gui screen element. Can only be used for new objects, cannot be used as a type
 * 
 * @author yetanotherx
 * 
 * @obfuscated
 */
public class Screen extends xe {

    /**
     * drawScreen
     * @param i
     * @param i1
     * @param f 
     */
    @Override
    public void a(int i, int i1, float f) {
        super.a(i, i1, f);
    }

    /**
     * keyTyped
     * @param c
     * @param i 
     */
    @Override
    protected void a(char c, int i) {
        super.a(c, i);
    }

    /**
     * mouseClicked
     * @param i
     * @param i1
     * @param i2 
     */
    @Override
    protected void a(int i, int i1, int i2) {
        super.a(i, i1, i2);
    }

    /**
     * actionPerformed
     * @param ct 
     */
    @Override
    protected void a(ct ct) {
        super.a(ct);
    }

    /**
     * initGui
     */
    @Override
    public void a() {
        super.a();
    }

    public void drawWorldBackground(int i) {
        super.a(i);
    }

    public void drawBackground(int i) {
        super.b(i);
    }

    /**
     * doesGuiPauseGame
     * @return 
     */
    @Override
    public boolean b() {
        return this.doesGuiPauseGame();
    }

    public boolean doesGuiPauseGame() {
        return true;
    }

    /**
     * onGuiClosed
     */
    @Override
    public void d() {
        super.d();
    }

    public void drawDefualtBackground() {
        super.j();
    }

    public void updateScreen() {
        super.s_();
    }
}
