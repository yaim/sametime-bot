package org.sametime

import io.quarkus.runtime.StartupEvent
import io.quarkus.runtime.configuration.ProfileManager
import org.sametime.models.Event
import javax.enterprise.event.Observes


class ApplicationLifeCycle {
    fun onStart(@Observes ev: StartupEvent?) {
        println("Running Profile: ${ProfileManager.getActiveProfile()}")
    }
}