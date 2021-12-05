package com.maven.example.demo.discord.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * GCP関係の設定定義クラス
 */
@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "extension.gcp")
public class GcpConfig {

    private String projectName;
    private String instanceName;
    private String zoneName;
}
