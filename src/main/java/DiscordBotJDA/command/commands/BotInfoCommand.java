package DiscordBotJDA.command.commands;

import DiscordBotJDA.command.CommandContext;
import DiscordBotJDA.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;

public class BotInfoCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) {
        JDA jda=ctx.getJDA();
        jda.getRestPing().queue(
                
        );
    }

    @Override
    public String getname() {
        return "botinfo";
    }

    @Override
    public String gethelp() {
        return "Show the info of ihe bot";
    }
}
