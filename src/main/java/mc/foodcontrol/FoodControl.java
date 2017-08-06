package mc.foodcontrol;


import mc.foodcontrol.commands.MainCommand;
import mc.foodcontrol.compat.AppleCoreHandler;
import mc.foodcontrol.conf.FoodValuesFile;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import org.apache.logging.log4j.Logger;

import java.io.File;

import static mc.foodcontrol.FoodControlInfo.*;


@Mod(
        modid = MODID,
        version = MODVERSION,
        dependencies = "required-after:Forge@[14.21.1.2387,), required-after:applecore",
        useMetadata = true
)
public class FoodControl {

    @Mod.Instance
    public static FoodControl instance;

    public static FoodValuesFile foodValuesFile;
    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();
        File configDirectory=event.getModConfigurationDirectory();
        foodValuesFile =new FoodValuesFile(configDirectory);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        if (Loader.isModLoaded("applecore")) {
            MinecraftForge.EVENT_BUS.register(new AppleCoreHandler());
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        //The hack to try to work without applecore should go here. maybe.
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new MainCommand());
    }
}
