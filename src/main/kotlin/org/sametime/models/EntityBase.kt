package org.sametime.models

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import org.hibernate.annotations.GenericGenerator
import java.time.Instant
import javax.persistence.*

@MappedSuperclass
abstract class EntityBase : PanacheEntityBase {
    @Id
    @GenericGenerator(name = "global_seq_id", strategy = "org.sametime.models.GlobalIdGenerator")
    @GeneratedValue(generator = "global_seq_id")
    var id: Long? = null


    @Column(name = "created_at")
    var createdAt: Instant? = null

    @Column(name = "updated_at")
    var updatedAt: Instant? = null

    @Column(name = "deleted_at")
    var deletedAt: Instant? = null

    @PrePersist
    private fun setTimeStamps() {
        this.updatedAt = Instant.now()

        if (this.createdAt == null) {
            this.createdAt = Instant.now()
        }
    }
}
