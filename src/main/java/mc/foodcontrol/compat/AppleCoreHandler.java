package mc.foodcontrol.compat;

import mc.foodcontrol.conf.FoodInfo;
import mc.foodcontrol.conf.FoodValuesFile;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import squeek.applecore.api.food.FoodEvent;
import squeek.applecore.api.food.FoodValues;

public class AppleCoreHandler {

    @SubscribeEvent
    public void getFoodValues(FoodEvent.GetFoodValues event) {
        ItemStack actualFood = event.food;
        FoodInfo.FoodValues values = FoodValuesFile.foodInfo.getFoodValue(actualFood.getItem().getRegistryName());
        if (values != null) {
            event.foodValues = new FoodValues(values.hunger, values.saturationModifier);
        }
    }
}
