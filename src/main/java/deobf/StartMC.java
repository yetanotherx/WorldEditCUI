package deobf;

import java.io.File;
import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;

/**
 * Starts the Minecraft main class by setting the minecraft directory
 * 
 * @author yetanotherx
 */
public class StartMC {

    public static void main(String[] args) {
        try {
            Field field = Minecraft.class.getDeclaredField("aj");
            field.setAccessible(true);
            field.set(null, new File("."));
            Minecraft.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
