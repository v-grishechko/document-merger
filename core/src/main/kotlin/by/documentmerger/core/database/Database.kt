package by.documentmerger.core.database

import org.jetbrains.exposed.sql.Database
import java.sql.ResultSet

object Database {

    fun transaction(query: String): ResultSet? {
        Database.connect("jdbc:mysql://localhost/DP_PMS",
                "com.mysql.jdbc.Driver",
                "root",
                "root")

        return org.jetbrains.exposed.sql.transactions.transaction {
            debug = true
            exec(query) {
                it
            }
        }
    }
}
