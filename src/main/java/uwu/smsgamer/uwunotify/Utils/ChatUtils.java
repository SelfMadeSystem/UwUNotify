package uwu.smsgamer.uwunotify.Utils;

import org.bukkit.ChatColor;

/**
 * Some utilities that will be used a lot throughout my program.
 */
public class ChatUtils {
    /**
     * Replace '&amp;' with the section symbol and '&amp;&amp;' with '&amp;' for a message.
     *
     * @param msg The message that will be "colorized".
     * @return Will return a string that has been "colorized".
     */
    public static String colorize(String msg) {
        String fmsg = msg;
        fmsg = fmsg.replaceAll("&&", "_=-fa");
        fmsg = fmsg.replaceAll("&", String.valueOf(ChatColor.COLOR_CHAR));
        fmsg = fmsg.replaceAll("_=-fa", "&");
        return fmsg;
    }
    public static String basicReplace(String msg, String sender, String date){
        String m = msg;
        m = m.replaceAll("%sender%", sender);
        m = m.replaceAll("%date%", date);
        return m;
    }
    public static String listReplace(String msg, String type, String alias, String sender, String date){
        String m = msg;
        m = m.replaceAll("%type%", type);
        m = m.replaceAll("%sender%", sender);
        m = m.replaceAll("%alias%", alias);
        m = m.replaceAll("%date%", date);
        return m;
    }
}
