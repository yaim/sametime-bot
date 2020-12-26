package org.sametime.services;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConfigService {
  @ConfigProperty(name = "discord.bot.token", defaultValue = "default")
  String discordBotToken;

  public String getDiscordBotToken() {
    return this.discordBotToken;
  }
}
