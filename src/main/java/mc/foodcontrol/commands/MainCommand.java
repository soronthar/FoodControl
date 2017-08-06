package mc.foodcontrol.commands;

import mc.foodcontrol.FoodControl;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;


import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainCommand extends CommandBase {
    @Override
    public String getName() {
        return "fcntl";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return getName() + "[dump|reload]";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("foodcontrol", "fcntl", "fc");
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 0) {
            return Arrays.asList("dump","reload");
        } else if (args[0].startsWith("d")) {
            return Arrays.asList("dump","reload");
        } else if (args[0].startsWith("r")) {
            return Arrays.asList("reload");
        } else {
            return Arrays.asList("dump","reload");
        }
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length >0 ) {
            switch (args[0]) {
                case "dump":
                    FoodControl.foodValuesFile.dumpFoodInfo(sender);
                    break;
                case "reload":
                    FoodControl.foodValuesFile.reloadFoodValues();
                    break;
                default: break;
            }
        }
        sender.sendMessage(new TextComponentString("Done."));
    }


}
