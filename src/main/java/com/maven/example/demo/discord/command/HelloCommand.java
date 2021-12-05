package com.maven.example.demo.discord.command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class HelloCommand extends Command {

    public HelloCommand() {
        this.name = "hello"; // コマンド名を設定（!helloで実行可能）
        this.help = "あいさつするだけ"; // コマンドの説明（!helpと実行したときに表示される説明）
    }

    // コマンドを実行したときに呼ばれるメソッド
    @Override
    public void execute(CommandEvent event) {
        event.reply("こんにちは、" + event.getAuthor().getName() + "さん！"); // 返信
    }
}
