package mc.foodcontrol.conf;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mc.foodcontrol.FoodControl;
import mc.foodcontrol.FoodControlInfo;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import squeek.applecore.api.AppleCoreAPI;
import squeek.applecore.api.food.FoodValues;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FoodValuesFile {
    public static FoodInfo foodInfo = new FoodInfo();

    private final File modConfigDirectory;

    public FoodValuesFile(File configDirectory) {
        modConfigDirectory = new File(configDirectory, FoodControlInfo.MODID);
        modConfigDirectory.mkdirs();
        loadFoodValues();
    }

    private void loadFoodValues() {
        Gson gson = new Gson();
        try (FileReader in = new FileReader(new File(modConfigDirectory,"foodvalues.json"))) {
            foodInfo=gson.fromJson(in,FoodInfo.class);
        } catch (IOException e) {
            FoodControl.logger.warn("Cannot read foodvalues file: ",e);
        }
    }

    public void reloadFoodValues() {
        loadFoodValues();
    }

    public void dumpFoodInfo(ICommandSender sender) {
        FoodInfo foodInfo = new FoodInfo();

        IForgeRegistry<Item> registry = GameRegistry.findRegistry(Item.class);
        File file = new File(modConfigDirectory, "fooddump.json");

        for (Item item : registry) {
            if (item instanceof ItemFood) {
                ItemFood food = (ItemFood) item;
                ItemStack stack = new ItemStack(food);
                FoodValues foodValues = AppleCoreAPI.accessor.getFoodValues(stack);
                foodInfo.addFood(item.getRegistryName(), foodValues.hunger, foodValues.saturationModifier);
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter out = new FileWriter(file)) {
            gson.toJson(foodInfo, out);
        } catch (IOException e) {
            FoodControl.logger.error("Error dumping food file.",e);
            sender.sendMessage(new TextComponentString("Cannot dump food file: " + e.getMessage()));
        }
    }

}
