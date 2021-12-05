package com.maven.example.demo.discord.command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.maven.example.demo.discord.config.GcpConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * GCE インスタンスの停止を行うコマンド
 */
@Slf4j
@Component
public class DownCommand extends Command {

    private final GcpConfig gcpConfig;

    public DownCommand(GcpConfig gcpConfig) {
        this.name = "down";
        this.help = "GCE インスタンスの停止を行います。 Minecraftを終了する際は必ず 本コマンドを実行してください";
        this.gcpConfig = gcpConfig;
    }

    /**
     * GCEのインすタウンスの停止を行う
     *
     * @param event : commandEvent
     */
    @Override
    public void execute(CommandEvent event) {

        event.reply("Minecraftサーバーを停止します。");
        event.reply("停止完了通知が来るまで他のコマンドを実行しないでください。");

        // 実行するコマンドの作成
        // example gcloud compute instances start minecraft-multi-server --project stoked-sanctum-334108 --zone asia-northeast1-b
        // TODO computeの前　--account=<サービスアカウントID>　を指定する必要あり？ (同じアカウント使うなら不要?)
        final StringBuilder commandBuilder = new StringBuilder();
        commandBuilder.append("gcloud compute instances stop ");
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
            event.reply("Minecraftサーバーを停止しました。");
        } catch (Exception e) {
            e.printStackTrace();
            event.reply("Minecraftサーバーが停止できませんでした。 GCP上から手動で停止してください");
        }
    }
}
