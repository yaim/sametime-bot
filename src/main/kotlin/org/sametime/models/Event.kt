package org.sametime.models

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.PrePersist
import javax.persistence.Table

@Entity
@Table(name = "events")
class Event() : EntityBase() {
    constructor(name: String, authorId: Long,  messageId: Long, channelId: Long, serverId: Long) : this() {
        this.name = name
        this.authorId = authorId
        this.messageId = messageId
        this.channelId = channelId
        this.serverId = serverId
    }

    companion object : PanacheCompanion<Event, Long> {
        fun findByName(name: String, channelId: Long) =
            find("channel_id = ?1 and name = ?2", channelId, name).firstResult()
    }

    lateinit var name: String

    @Column(name = "author_id")
    var authorId: Long? = null

    @Column(name = "message_id")
    var messageId: Long? = null

    @Column(name = "channel_id")
    var channelId: Long? = null

    @Column(name = "server_id")
    var serverId: Long? = null
}
