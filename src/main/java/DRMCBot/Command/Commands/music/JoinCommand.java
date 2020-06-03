package DRMCBot.Command.Commands.music;

import DRMCBot.Command.CommandContext;
import DRMCBot.Command.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class JoinCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        TextChannel channel=ctx.getChannel();
        AudioManager audioManager=ctx.getGuild().getAudioManager();

        if (audioManager.isConnected()){
            channel.sendMessage("我已連線至一個語音頻道！").queue();
            return;
        }

        GuildVoiceState memberVoiceState=ctx.getMember().getVoiceState();

        if (!memberVoiceState.inVoiceChannel()){
            channel.sendMessage("請先加入一個語音頻道！").queue();
            return;
        }

        VoiceChannel voiceChannel=memberVoiceState.getChannel();
        Member selfMember=ctx.getGuild().getSelfMember();

        if (!selfMember.hasPermission(voiceChannel, Permission.VOICE_CONNECT)){
            channel.sendMessageFormat("I am missing permission to join %s",voiceChannel).queue();
            return;
        }

        audioManager.openAudioConnection(voiceChannel);
        channel.sendMessage("Join your voice channel").queue();
    }

    @Override
    public String getName() {
        return "join";
    }
}
