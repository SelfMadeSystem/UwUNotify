package uwu.smsgamer.uwunotify.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import uwu.smsgamer.uwunotify.Utils.ChatUtils;
import uwu.smsgamer.uwunotify.UwUNotify;
import uwu.smsgamer.uwunotify.Vars.Vars;

import java.util.List;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("UwU.Notify.commands")){
            sender.sendMessage(ChatUtils.basicReplace(Vars.noPerm, sender.getName(), Vars.time));
            return true;
        }
        if(args.length == 0 || args[0].equalsIgnoreCase("list")
                || args[0].equalsIgnoreCase("types")
                || args[0].equalsIgnoreCase("type")){
            sender.sendMessage(ChatUtils.basicReplace(Vars.list, sender.getName(), Vars.time));
            for (String num : UwUNotify.instance.getConfig().getConfigurationSection("types").getKeys(false)) {
                sender.sendMessage(ChatUtils.listReplace(Vars.listFormat,
                        num,
                        UwUNotify.instance.getConfig().getStringList("types." + num + ".alias").toString(),
                        sender.getName(),
                        Vars.time));
            }
            return true;
        }
        for (String num : UwUNotify.instance.getConfig().getConfigurationSection("types").getKeys(false)) {
            List<String> ls = UwUNotify.instance.getConfig().getStringList("types." + num + ".alias");
            if (ls.contains(args[0])) {
                if(!sender.hasPermission(UwUNotify.instance.getConfig().getString("types." + num + ".permission_use"))){
                    sender.sendMessage(ChatUtils.basicReplace(Vars.noPerm, sender.getName(), Vars.time));
                    return true;
                }
                String msg = ChatUtils.colorize(UwUNotify.instance.getConfig().getString("types." + num + ".message"));
                if(args.length > 0){
                    for(int i = 1; i < args.length ; i++){
                        String[] txt = args;
                        for (int i2 = 0; i2 + 1 < args.length; i2++) {
                            txt[i2] = txt[i2 + 1];
                        }
                        args[args.length - 1] = "";
                        String listString;
                        listString = ChatUtils.colorize(String.join(" ", args));
                        msg = msg.replaceAll("%"+i+"%", txt[0]);
                        msg = msg.replace("%"+i+"+%", listString);
                    }
                }

                Bukkit.broadcast(msg, UwUNotify.instance.getConfig().getString("types." + num + ".permission_see"));
                return true;
            }

        }
        sender.sendMessage(ChatUtils.listReplace(Vars.noType,
                args[0],
                "null",
                sender.getName(),
                Vars.time));
        return true;
    }
}
