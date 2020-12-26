package org.sametime

import org.sametime.services.ConfigService
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("/test")
@ApplicationScoped
class TestResource {
    @Inject
    lateinit var config: ConfigService;

    @GET
    @Transactional
    fun hello() {
        MessageListener().listen(config.discordBotToken);

//        val eventObject: Event =
//            Event(
//                "eventName",
//                0.toLong(),
//                1.toLong(),
//                2.toLong(),
//                3.toLong()
//            )
//
//        println(eventObject.name)
//
//        eventObject.persist()
//
//        val event = Event.findByName("test event", 4)
//
//        if (event != null) {
//            println(event.name)
//            println(event.messageId)
//            println(event.channelId)
//            println(event.serverId)
//            println(event.createdAt)
//        }
    }
}