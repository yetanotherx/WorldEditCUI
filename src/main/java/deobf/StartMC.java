package deobf;

import java.io.File;
import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;

/**
 * Starts the Minecraft main class by setting the minecraft directory
 * 
 * @author yetanotherx
 * 
 * @obfuscated 1.4.5
 */
public class StartMC {

    public static void main(String[] args) {
        try {
            // This overrides it to a local copy, to preserve my production copy
            Field f = Minecraft.class.getDeclaredField("an");
            f.setAccessible(true);
            f.set(null, new File("."));

            Minecraft.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
