package by.documentmerger.core

import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>) {
    println(by.documentmerger.core.database.Database.transaction("SELECT * FROM diplom_project").toString())
}