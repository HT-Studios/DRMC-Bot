package DRMCBot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Bot {

    private Bot() throws LoginException {
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
