package com.maven.example.demo.discord;


import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.maven.example.demo.discord.command.HelloCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.springframework.stereotype.Controller;

/**
 * DiscordとのDAOクラス
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class DiscordDao {

    private final DiscordConfig discordConfig;

    private static final String prefix = "!";

    /**
     * DiscordBotとの接続と立ち上げを行う
     */
    public void connectToDiscordBot() {

        log.info("Discordとの接続処理を開始します。");

        try {
            JDABuilder.createDefault(discordConfig.getToken())
                    .addEventListeners(setUpCommand())
                    .build();
        } catch (final Exception e) {
            // GCPのローカル上でしか使わないので スタックトレースは全部出す
            e.printStackTrace();
            throw new RuntimeException("Discordとの接続に失敗しました。");
        }

        log.info("Discord Botとの接続に成功しました。");
    }

    /**
     * CommandClientのセットアップを行う
     */
    private CommandClient setUpCommand() {
        return new CommandClientBuilder()
                .setOwnerId(discordConfig.getOwnerId())
                .setPrefix(prefix)
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.listening("DiscordBot"))
                .addCommand(new HelloCommand())
                .build();
    }
}
