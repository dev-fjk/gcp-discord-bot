package com.maven.example.demo.discord;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DiscordDao {

    private final DiscordConfig discordConfig;

    public void HelloWorld() {
        System.out.println(discordConfig.getOwnerId());
        System.out.println(discordConfig.getToken());
        System.out.println("Hello");
    }
}
