package DRMCBot.Command.Commands;

import DRMCBot.Command.CommandContext;
import DRMCBot.Command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDAInfo;

import javax.lang.model.element.Name;

public class PingCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda=ctx.getJDA();

        jda.getRestPing().queue(
                (ping) -> ctx.getChannel()
                .sendMessage(new EmbedBuilder().setColor(0x01afef).setTitle("Ping Info").setDescription("Rest Ping: "+ping+"\nWS Ping: "+jda.getGatewayPing()).build()).queue()
        );
    }

    @Override
    public String getName() {
        return "ping";
    }
}
