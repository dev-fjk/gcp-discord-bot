package com.maven.example.demo.discord.command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.maven.example.demo.discord.config.GcpConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * GCE インスタンスの起動を行うコマンド
 */
@Slf4j
@Component
public class BuildCommand extends Command {

    private final GcpConfig gcpConfig;

    public BuildCommand(GcpConfig gcpConfig) {
        this.name = "build";
        this.help = "GCE インスタンスの起動を行います。 Minecraftを終了する際は必ず downコマンドを実行してください。";
        this.gcpConfig = gcpConfig;
    }

    /**
     * GCEのインすタウンスの起動を行う
     *
     * @param event : commandEvent
     */
    @Override
    public void execute(CommandEvent event) {

        event.reply("Minecraftサーバーを起動します。");
        event.reply("起動完了通知が来るまで他のコマンドを実行しないでください。");

        // 実行するコマンドの作成
        // example gcloud compute instances start minecraft-multi-server --project stoked-sanctum-334108 --zone asia-northeast1-b
        // TODO computeの前　--account=<サービスアカウントID>　を指定する必要あり？
        final StringBuilder commandBuilder = new StringBuilder();
        commandBuilder.append("gcloud compute instances start ");
        commandBuilder.append(gcpConfig.getInstanceName());
        commandBuilder.append(" --project ");
        commandBuilder.append(gcpConfig.getProjectName());
        commandBuilder.append(" --zone ");
        commandBuilder.append(gcpConfig.getZoneName());
        log.info("execute Command : {}", commandBuilder);

        try {
            Runtime runtime = Runtime.getRuntime();
            Process p = runtime.exec(commandBuilder.toString());
            p.waitFor();
            p.destroy();
            event.reply("Minecraftサーバーを起動しました。");
        } catch (Exception e) {
            e.printStackTrace();
            event.reply("Minecraftサーバーが起動できませんでした。GCP上から手動で起動してください");
        }
    }
}
