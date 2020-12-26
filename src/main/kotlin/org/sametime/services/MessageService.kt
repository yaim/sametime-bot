package org.sametime.services

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.sametime.MessageListener
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MessageService {
    @ConfigProperty(name = "discord.bot.token", defaultValue = "default")
    lateinit var token: String

    lateinit var api: JDA

    fun listen() {
        if (!::api.isInitialized) {
            api = JDABuilder.createDefault(token)
                .addEventListeners(MessageListener())
                .build()
        }

        api.awaitReady();
    }
}