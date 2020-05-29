package DRMCBot;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Bot {

    private Bot() throws LoginException {
        WebUtils.setUserAgent("Mozilla/5.0 DRMC Bot#7872");
        EmbedUtils.setEmbedBuilder(
                ()-> new EmbedBuilder()
                .setColor(0x01afef)
                .setFooter("DRMC Bot")
        );

        new JDABuilder()
                .setToken(Config.get("token"))
                .addEventListeners(new Listener())
                .setActivity(Activity.playing("Welcome to New DL/RS/MC Chatroom"))
                .build();

    }
    public static void main(String[] args) throws LoginException {
        new Bot();
    }
}
