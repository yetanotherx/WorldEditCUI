
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.minecraft.client.Minecraft;

/**
 * Main CUIx class
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIx {

    /**
     * Version identifier
     */
    public static final String VERSION = "1.0beta for Minecraft version 1.0";
    /**
     * Singleton instance
     */
    private static CUIx instance;
    /**
     * Current region selection.
     */
    private CUIx_render_CuiRegion selection;
    /**
     * Obfuscater instance
     */
    private CUIx_obf_Handler obfuscation;
    //TODO: Better debugging
    private static boolean stdoutdebug = new File(CUIx_obf_Handler.getAppDir("minecraft"), "wecui-stdout-debug.txt").exists();
    private static File debugfile = new File(CUIx_obf_Handler.getAppDir("minecraft"), "wecui-debug.txt");
    private static boolean debug = debugfile.exists();
    private static BufferedWriter debugwriter = null;

    /**
     * Initialize CUIx instance
     * @param obfuscation Obfuscation handler class
     * 
     */
    private CUIx(CUIx_obf_Handler obfuscation) {

        this.obfuscation = obfuscation;

        /**
         * Register listeners for each event
         */
        CUIx_events_CUIEvent.handlers.register(new CUIx_events_CUIListener(this), CUIx_fevent_Order.Default);
        CUIx_events_IncomingChatEvent.handlers.register(new CUIx_events_IncomingChatListener(this), CUIx_fevent_Order.Default);
        CUIx_events_WorldRenderEvent.handlers.register(new CUIx_events_WorldRenderListener(this), CUIx_fevent_Order.Default);

    }

    /**
     * Singleton getter
     * @return CUIx singleton
     */
    public static CUIx getInstance() {
        if (CUIx.instance == null) {
            CUIx.setInstance(ModLoader.getMinecraftInstance());
        }
        return CUIx.instance;
    }

    /**
     * Singleton setter
     * Ideally, this should be set manually, instead of 
     * relying on the ModLoader global instance.
     * 
     * @param minecraft Minecraft main class instance
     */
    public static void setInstance(Minecraft minecraft) {
        if (CUIx.instance != null) {
            return;
        }

        CUIx.instance = new CUIx(new CUIx_obf_Handler(minecraft));
        CUIx_obf_Packet3CUIChat.register();
    }

    public static void debug(String message) {
        if (debug) {
            try {
                if (debugwriter == null) {
                    debugwriter = new BufferedWriter(new FileWriter(debugfile, true));
                    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

                        public void run() {
                            try {
                                CUIx.debugwriter.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }));
                }
                debugwriter.write(message + "\n");
            } catch (IOException e) {
                debug = false;
                stdoutdebug = true;
                debug("Could not write to debug file! turning on stdout log ...");
                e.printStackTrace();
            }
        }
        if (stdoutdebug) {
            System.out.println("WECUI DEBUG: " + message);
        }
    }

    /**
     * Sets the currently active region
     * @param region
     */
    public void setSelection(CUIx_render_CuiRegion region) {
        selection = region;
    }

    /**
     * Gets the currently active region
     * @return 
     */
    public CUIx_render_CuiRegion getSelection() {
        return selection;
    }

    /**
     * Gets the obfuscation class
     * @return 
     */
    public CUIx_obf_Handler getObfuscation() {
        return obfuscation;
    }
}
