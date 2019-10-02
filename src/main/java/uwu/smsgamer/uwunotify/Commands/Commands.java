package uwu.smsgamer.uwunotify.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uwu.smsgamer.uwunotify.Utils.ChatUtils;
import uwu.smsgamer.uwunotify.UwUNotify;
import uwu.smsgamer.uwunotify.Vars.Vars;

import java.util.List;

/**
 * Main command executor
 */
public class Commands implements CommandExecutor {
    /**
     * Main command executor.
     *
     * @param sender The user (sender, player, console) that's executing this command.
     * @param command The command that is being executed (not alias).
     * @param label The command that the sender is executing (could be alias).
     * @param args The arguments of the command.
     */
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
                boolean kicks;
                String msg = ChatUtils.colorize(UwUNotify.instance.getConfig().getString("types." + num + ".message"));
                Player p = null;
                if(args.length <= 1) {
                    Bukkit.broadcast(msg, UwUNotify.instance.getConfig().getString("types." + num + ".permission_see"));
                    return true;
                }
                if(args[1].equalsIgnoreCase("kick")){
                    try {
                        p = Bukkit.getPlayer(args[2]);
                    }catch (IndexOutOfBoundsException e){
                        sender.sendMessage(ChatUtils.basicReplace(Vars.noPlayerSpecified, sender.getName(), Vars.time));
                        return true;
                    }
                    if(p == null){
                        sender.sendMessage(ChatUtils.listReplace(Vars.noPlayer, args[2], "%alias%", sender.getName(), Vars.time));
                        return true;
                    }
                    kicks = true;
                    if(args.length > 0){
                        for (int i2 = 0; i2 + 1 < args.length; i2++) {
                            args[i2] = args[i2 + 1];
                        }
                        args[args.length-1] = "";
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
                }else{
                    kicks = false;
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
                }
                if(kicks){
                    p.kickPlayer(msg);
                }else{
                    Bukkit.broadcast(msg, UwUNotify.instance.getConfig().getString("types." + num + ".permission_see"));
                }
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
