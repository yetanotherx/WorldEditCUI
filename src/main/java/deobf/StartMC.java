package deobf;

import java.io.File;
import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;

/**
 * Starts the Minecraft main class by setting the minecraft directory
 * 
 * @author yetanotherx
 * 
 * @obfuscated
 */
public class StartMC {

    public static void main(String[] args) {
        try {
            // aj = the location of the .minecraft directory
            // This overrides it to a local copy, to preserve my production copy
            // It also ensures that I start with a fresh bin directory
            Field field = Minecraft.class.getDeclaredField("aj");
            field.setAccessible(true);
            field.set(null, new File("."));
            Minecraft.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
