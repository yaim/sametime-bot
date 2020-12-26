package org.sametime.models

import org.hibernate.HibernateException
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.IdentifierGenerator
import java.io.Serializable
import java.sql.SQLException

class GlobalIdGenerator : IdentifierGenerator {
    @Throws(HibernateException::class)
    override fun generate(session: SharedSessionContractImplementor, `object`: Any): Serializable? {
        val connection = session.connection()

        try {
            connection.prepareStatement("SELECT id_generator() as nextval").use { ps ->
                val rs = ps.executeQuery()

                if (rs.next()) {
                    return rs.getLong("nextval")
                }
            }
        } catch (e: SQLException) {
            throw HibernateException("Unable to generate Stock Code Sequence")
        }

        return null
    }
}