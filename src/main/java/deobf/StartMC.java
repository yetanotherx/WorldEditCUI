package deobf;

import java.io.File;
import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import wecui.obfuscation.FieldObfuscation;

/**
 * Starts the Minecraft main class by setting the minecraft directory
 * 
 * @author yetanotherx
 * 
 */
public class StartMC {

    public static void main(String[] args) {
        try {
            // This overrides it to a local copy, to preserve my production copy
            // It also ensures that I start with a fresh bin directory
            Field field = Minecraft.class.getDeclaredField(FieldObfuscation.MINECRAFTDIR.getVariable());
            field.setAccessible(true);
            field.set(null, new File("."));
            Minecraft.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
