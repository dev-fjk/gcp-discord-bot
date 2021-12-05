# gcp-discord-bot
GCEのインスタンス起動、停止を DiscordBotで行えるようにする

GCPでの Linux OSを積んだインスタンス上で動作させることを前提<br>
また、制御対象のインスタンスと同じプロジェクトで稼働している GCEインスタンス上で動作させるアプリケーションという想定

※ Always freeなどの低スぺインスタンス上で動作させると良い<br>
※ 秘密鍵設定とか入れていないので他人のアカウントのGCEの制御はできない気がする

## 事前にOS側に導入しておく必要があるもの

- open jdk 11
- maven
- git(本プロジェクトのクローンに必要)

## アプリケーション起動方法

- ① 以下の環境変数をマシンに設定しておく(shellで①~③までまとめて実行できるようにするのが良い)

~~~
(1) Discord関連の環境変数(特に通知先チャンネル変更不要であれば聞いてください)
${ownerId} # DiscordBotのAPPIDを設定
${token} # DiscordBotのトークンを設定

(2) GCP関連の環境変数
${projectName} # 制御対象のGCEインスタンスの稼働しているプロジェクト名を設定
${instanceName} # 制御対象のGCEインスタンス名を設定
${zoneName}　# 制御対象のGCEインスタンスが稼働しているリージョン名を設定(ex:asia-northeast1-b)
~~~

- ② pom.xmlが配置されている階層に移動して以下のコマンドを実行する

~~~
mvn package
~~~

- ③ そのまま以下のコマンドを実行し Spring Boot アプリケーションを実行する

~~~
cd /target
java -jar gcp-discord-bot.jar
~~~

## 使用可能コマンド一覧
~~~
!hello - あいさつするだけ デバッグ用コマンド
!build - GCE インスタンスの起動を行います。 Minecraftを終了する際は必ず downコマンドを実行してください。
!down - GCE インスタンスの停止を行います。 Minecraftを終了する際は必ず 本コマンドを実行してください
!help - 各コマンドの説明が見れる
~~~
