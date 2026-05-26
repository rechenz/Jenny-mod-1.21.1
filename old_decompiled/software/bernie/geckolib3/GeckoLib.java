/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package software.bernie.geckolib3;

import java.util.concurrent.FutureTask;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.resource.ResourceListener;

public class GeckoLib {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String ModID = "geckolib3";
    public static boolean hasInitialized;
    public static final String VERSION = "3.0.30";

    public static void initialize() {
        if (!hasInitialized) {
            FMLCommonHandler.callFuture(new FutureTask<Object>(() -> {
                if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
                    GeckoLib.doOnlyOnClient();
                }
            }, null));
        }
        hasInitialized = true;
    }

    @SideOnly(value=Side.CLIENT)
    private static void doOnlyOnClient() {
        ResourceListener.registerReloadListener();
    }
}

