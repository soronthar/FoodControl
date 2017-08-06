Food Control
=================
Minecraft mod that allows to configure the hunger and saturation restored by all registered foods. Uses the [AppleCore API](https://github.com/squeek502/AppleCore).

Building
--------

1. Clone this repository into a folder named FoodControl
2. If you have [Gradle](http://www.gradle.org/) installed, open a command line in the FoodControl folder and execute: ```gradle build```.
   * If you don't have Gradle installed, you can use [ForgeGradle](http://www.minecraftforge.net/forum/index.php?topic=14048.0)'s gradlew/gradlew.bat instead.  
   
Usage
-----
Food Control reads its configuration from the `config/foodcontrol/foodvalues.json` file. The file has the following format:

```javascript
{
  "foods": {
    "<foodid>": {
      "hunger": <intvalue>,
      "saturationModifier": <floatvalue>
    },
    "<foodid>": {
      "hunger": <intvalue>,
      "saturationModifier": <floatvalue>
    }
  }
}
```
Where:
* `<foodid>` is the id of the food item as defined by owning mod or vanilla
* `<intvalue>` is an integer value representing the amount of hunger points the item will restore. Each point restores half a drumstick.
* `<floatvalue>` is a decimal value representing the saturation modifier for the food. This value times the hunger value will determine the number of saturation points restored.

For example:
```javascript
{
  "foods": {
    "minecraft:beef": {
      "hunger": 6,
      "saturationModifier": 1
    },
    "minecraft:baked_potato": {
      "hunger": 3,
      "saturationModifier": 0.6
    }
  }
}
```
This file will configure beef to return 6 hunger (3 drumsticks), and at most 6 saturation points, and configure the backed potato to restore 3 hunger (1.5 drumsticks) and at most 2 saturation points.

For more information on how hunger and saturation works, check [the minecraft forums](https://minecraft.gamepedia.com/Hunger)

You can change the configuration of any food that conforms with the [AppleCore API](https://github.com/squeek502/AppleCore)

Commands
--------
Food Control provides a single command `/foodcontrol` (aliased to `/fc` and `/fcntl`) with two options:

* `/foodcontrol dump`: Dumps all the information for the food items into the `config/foodcontrol/fooddump.json` file.
* `/foodcontrol reload`: Reloads the `config/foodcontrol/foodvalues.json` file, effectively reloading the hunger/saturation configuration.

The easiest way to configure this mod is to dump the food information, rename the file to `foodvalues.json` and edit it as needed.
