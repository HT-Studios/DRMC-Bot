package DRMCBot;

import DRMCBot.Command.CommandContext;
import DRMCBot.Database.SQLiteDataSource;
import me.duncte123.botcommons.BotCommons;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Listener extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);
    private final CommandManager manager=new CommandManager();

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        LOGGER.info(event.getJDA().getSelfUser().getAsTag()+" is ready");
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        User user=event.getAuthor();

        if (user.isBot() || event.isWebhookMessage()){
            return;
        }

        final long guildID=event.getGuild().getIdLong();
        String prefix=VeryBadDesign.PREFIXES.computeIfAbsent(guildID,this::getPrefix);
        String raw=event.getMessage().getContentRaw();

        if (raw.equalsIgnoreCase(prefix+"shutdown")){
            LOGGER.info("Shutting Down");
            event.getJDA().shutdown();
            BotCommons.shutdown(event.getJDA());

            return;
        }

        if (raw.startsWith(prefix)){
            manager.handle(event,prefix);
        }
    }

    private String getPrefix(long guildId){
        try(final PreparedStatement preparedStatement=SQLiteDataSource
        .getConnection().prepareStatement("SELECT prefix FROM guild_settings WHERE guild_id = ?")){
            preparedStatement.setString(1,String.valueOf(guildId));

            try(final ResultSet resultSet=preparedStatement.executeQuery()){
                if ((resultSet.next())){
                    return resultSet.getString("prefix");
                }
            }

            try(final PreparedStatement insertStatement=SQLiteDataSource
                    .getConnection()
                    .prepareStatement("INSERT INTO guild_settings(guild_id) VALUES(?)")){
                insertStatement.setString(1,String.valueOf(guildId));

                insertStatement.execute();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return Config.get("prefix");
    }
}
