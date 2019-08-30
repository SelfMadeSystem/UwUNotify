package uwu.smsgamer.uwunotify.Vars;

import uwu.smsgamer.uwunotify.Utils.ChatUtils;
import uwu.smsgamer.uwunotify.UwUNotify;

import java.time.LocalDateTime;

/**
 * Stores values (vars, variables) that will be taken from a config file and used in this program.
 */
public class Vars {

    public static LocalDateTime now = LocalDateTime.now();
    public static String time = now.toString();
    public static String noPerm = ChatUtils.colorize((String) UwUNotify.instance.getConfig().get("messages.no_permission"));
    public static String list = ChatUtils.colorize((String) UwUNotify.instance.getConfig().get("messages.list"));
    public static String listFormat = ChatUtils.colorize((String) UwUNotify.instance.getConfig().get("messages.list_format"));
    public static String noType = ChatUtils.colorize((String) UwUNotify.instance.getConfig().get("messages.no_type"));
}
