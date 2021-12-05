package com.maven.example.demo.discord;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Discordとの接続情報定義クラス
 */
@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "extension.discord")
public class DiscordConfig {

    private String ownerId;
    private String token;
}
