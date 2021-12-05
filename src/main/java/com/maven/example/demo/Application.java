package com.maven.example.demo;

import com.maven.example.demo.discord.DiscordDao;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot application root class
 */
@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

    private final DiscordDao discordDao;

    /**
     * root
     *
     * @param args : command line program args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * root
     * connect and build discord bot
     *
     * @param args : command line program args
     */
    @Override
    public void run(String... args) {
        discordDao.connectToDiscordBot();
    }
}
