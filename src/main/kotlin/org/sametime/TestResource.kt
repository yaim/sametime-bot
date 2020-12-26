package org.sametime

import org.sametime.services.MessageService
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("/test")
@ApplicationScoped
class TestResource {
    @Inject
    lateinit var message: MessageService;

    @GET
    @Transactional
    fun hello() {
        message.listen();
    }
}