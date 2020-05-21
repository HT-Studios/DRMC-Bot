package bot;

import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private Main() throws LoginException {
        new JDABuilder().setToken(getBotToken()).build();
    }

    public static void main(String[] arguments) throws LoginException {
        new Main();
    }

    protected String getBotToken() {
        String data = "";

        try {
            File file = new File("C:\\Users\\harry\\IdeaProjects\\DRMC Bot\\src\\main\\java\\bot\\important\\bot_token.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                data = scanner.nextLine();
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("An error occurred.");
            fileNotFoundException.printStackTrace();
        }

        return data;
    }
}
