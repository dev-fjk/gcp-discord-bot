package com.maven.example.demo.discord.command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.springframework.stereotype.Component;

@Component
public class HelloCommand extends Command {

    public HelloCommand() {
        this.name = "hello";
        this.help = "あいさつするだけ デバッグ用コマンド";
    }

    // コマンドを実行したときに呼ばれるメソッド
    @Override
    public void execute(CommandEvent event) {
        event.reply("こんにちは、" + event.getAuthor().getName() + "さん！"); // 返信
    }
}
