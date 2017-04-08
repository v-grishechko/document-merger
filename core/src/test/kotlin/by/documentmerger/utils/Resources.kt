package by.documentmerger.utils

import java.net.URL


object Resources {
    fun readResourse(fileName: String): String {
        val fileUrl = Thread.currentThread().contextClassLoader.getResource(fileName)
        return fileUrl.readText()
    }
}