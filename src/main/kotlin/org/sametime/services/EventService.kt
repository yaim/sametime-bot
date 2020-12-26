package org.sametime.services

import net.dv8tion.jda.api.entities.Message
import org.sametime.models.Event
import javax.transaction.Transactional

class EventService {
    companion object Factory {

        @Transactional
        fun createEvent(
            name: String,
            authorId: Long,
            message: Message
        ): Event {
            val event =
                Event(
                    name,
                    authorId,
                    message.id.toLong(),
                    message.channel.id.toLong(),
                    message.guild.id.toLong()
                )

            event.persist()

            return event
        }
    }
}
