package DRMCBot;

import me.duncte123.botcommons.BotCommons;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

public class Listener extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        LOGGER.info(event.getJDA().getSelfUser().getAsTag()+" is ready");
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String prefix=Config.get("prefix");
        String raw=event.getMessage().getContentRaw();

        if (raw.equalsIgnoreCase(prefix+"shutdown")){
            LOGGER.info("Shutting Down");
            event.getJDA().shutdown();
            BotCommons.shutdown(event.getJDA());
        }
    }
}
