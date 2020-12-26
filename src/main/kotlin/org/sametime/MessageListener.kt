package org.sametime

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.sametime.services.EventService
import java.awt.Color
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MessageListener() : ListenerAdapter() {
    fun listen(token: String) {
        JDABuilder.createDefault(token)
            .addEventListeners(MessageListener())
            .build()
            .awaitReady()
    }

/*
    override fun onGenericEvent(event: GenericEvent) {
        println("*************************************")
        println(event)
        println(event.responseNumber)
        println("*************************************")
    }
*/

    override fun onMessageReactionAdd(event: MessageReactionAddEvent) {
        println("####################################")
        println(event.reaction)
        println(event.reactionEmote)
        println(event.user)
        println(event.messageId)
        println(event.retrieveMessage())
        println("####################################")
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.channelType !in listOf(ChannelType.TEXT, ChannelType.PRIVATE)) {
            return
        }

        if (event.channelType == ChannelType.TEXT && !event.message.contentDisplay.startsWith("@SameTime")) {
            return
        }

        val jda = event.jda
//        val events = listOf(Event("Among Us!"), Event("Warzone"), Event("Squadrons"))

        println("=========================================")
/*
        println(event)
        println("channelType: " + event.channelType)
        println("channel: " + event.channel)
        try {
            println("privateChannel: " + event.privateChannel)
        } catch (e: Exception) {
            println("privateChannel: not in a private channel (${e.message})")
        }
        try {
            println("textChannel: " + event.textChannel)
        } catch (e: Exception) {
            println("textChannel: not in a channel (${e.message})")
        }

        println("messageId: " + event.messageId)
        println("responseNumber: " + event.responseNumber)
        println("author: " + event.author)
        println("member: " + event.member)
*/

        try {
            println("guild: " + event.guild)
        } catch (e: Exception) {
            println("guild: not in a channel (${e.message})")
        }

        println("message: " + event.message)
        println("Human Readable message: " + event.message.contentDisplay)
        println("=========================================")

        val message: String = event.message.contentDisplay.toLowerCase().replace("@sametime ", "")

        val channel = event.channel

        when {
            message.startsWith("help") -> {
                val reply: MessageEmbed = EmbedBuilder()
                    .setColor(Color.darkGray)
                    .setTitle("Same Time!")
                    .setDescription("Find the best time to play with friends 👯")
                    .addBlankField(false)
                    .addField(
                        "For Events:",
                        """
                        🎟 **@SameTime** event list
                        Show all events.

                        📝 **@SameTime** event create *__Game Night__*
                        Creates a new event named *__Game Night__*.

                        💀 **@SameTime** event cancel *__Game Night__*
                        Cancels the *__Game Night__* event.
                        """,
                        false
                    )
                    .addBlankField(false)
                    .addField(
                        "Plan",
                        "...",
                        false
                    )
                    .build()
                channel.sendMessage(reply).queue()
            }
            message.startsWith("event") -> {
                val command = message.replace("event ", "")
                when {
                    command.startsWith("list ") -> {
//                        events.stream().forEach { gameEvent ->
//                            val reply: MessageEmbed = EmbedBuilder()
//                                .setColor(Color.pink)
//                                .setTitle(gameEvent.name)
//                                .build()
//
//                            channel.sendMessage(reply).queue()
//                        }
                    }
                    command.startsWith("create ") -> {
                        val eventName = command.replace("create ", "")
                        val reply: MessageEmbed = EmbedBuilder()
                            .setColor(Color.pink)
                            .setTitle(eventName)
                            .addBlankField(false)
                            .addField("🙋 Attendees: ", "", false)
                            .build()

                        channel.sendMessage(reply).queue { eventMessage: Message ->
                            EventService.createEvent(
                                eventName,
                                event.author.id.toLong(),
                                eventMessage
                            )

                            println(eventName + ": " + eventMessage.id)
                            println(eventMessage.jumpUrl)
                            eventMessage.addReaction("🙋").queue()
                            eventMessage.pin().queue()
                        }
                    }
                    else -> {
                        channel.sendMessage(
                            "Whaaaaaaat...?\nNeed help? Call **__@SameTime help__**."
                        ).queue()
                    }
                }
            }
            message.startsWith("ping") -> {
                val time = System.currentTimeMillis()

                val mention = if (event.member != null) "<@${event.author.id}>: " else ""

                channel.sendMessage(mention + "Pong!")
                    .queue { response: Message ->
                        response.editMessageFormat(
                            "Pong: %d ms",
                            System.currentTimeMillis() - time
                        ).queue()
                    }
            }
            else -> {
                val mention = if (event.member != null) "<@${event.author.id}> " else ""

                channel.sendMessage(
                    "$mention :person_shrugging:\nNeed help? Call **__@SameTime help__**."
                ).queue()
            }
        }
    }
}

//class Event(
//    val id: Long,
//    val author_id: Long,
//    val name: String,
//    val hash: String, // name
//    val messageId: Long,
//    val serverId: Long,
//    val channelId: Long,
//    val created_at: Instant,
//    val updated_at: Instant,
//    val deleted_at: Instant
//) {}
