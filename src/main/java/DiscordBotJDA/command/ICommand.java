package DiscordBotJDA.command;

import java.util.List;

public interface ICommand {
    void handle(CommandContext ctx);

    String getname();

    String gethelp();

    default List<String> getAliases(){
        return  List.of();
    }
}
