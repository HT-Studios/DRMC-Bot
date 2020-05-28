package DiscordBotJDA.command.commands;

import DiscordBotJDA.command.CommandContext;
import DiscordBotJDA.command.ICommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDAInfo;

public class PingCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx) {
        JDA jda=ctx.getJDA();

        jda.getRestPing().queue(
                (ping)->ctx.getChannel()
                .sendMessageFormat("Reset ping: %sms\nWS ping: %sms",ping,jda.getGatewayPing()).queue()
        );
    }

    @Override
    public String getname() {
        return "ping";
    }

    @Override
    public String gethelp() {
        return "Show the bot's ping";
    }
}
