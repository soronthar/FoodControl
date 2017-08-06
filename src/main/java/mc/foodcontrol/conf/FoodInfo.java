package mc.foodcontrol.conf;

import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class FoodInfo {
    Map<String, FoodValues> foods=new HashMap<>();

    public FoodInfo() {
    }

    public void addFood(String food, int hunger, float saturationFactor) {
        foods.put(food,new FoodValues(hunger,saturationFactor));
    }
    public void addFood(ResourceLocation food, int hunger, float saturationFactor) {
        foods.put(food.toString(),new FoodValues(hunger,saturationFactor));
    }

    public FoodValues getFoodValue(String foodName) {
        return foods.get(foodName);
    }

    public FoodValues getFoodValue(ResourceLocation resourceLocation) {
        return getFoodValue(resourceLocation.toString());
    }


    public static class FoodValues {
        public int hunger;
        public float saturationModifier;

        public FoodValues(int hunger, float saturationModifier) {
            this.hunger = hunger;
            this.saturationModifier = saturationModifier;
        }
    }

}
