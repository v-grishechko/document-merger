package by.documentmerger.utils

import java.io.File
import java.net.URL


object Resources {
    fun readResourse(fileName: String): String {
        val fileUrl = Thread.currentThread().contextClassLoader.getResource(fileName)
        return fileUrl.readText()
    }

    fun getFile(fileName: String): File {
        val fileUrl = Thread.currentThread().contextClassLoader.getResource(fileName)
        return File(fileUrl.toURI())
    }

    fun createFile(fileName: String): File {
        val dir = Thread.currentThread().contextClassLoader.
        return File(dir.toString() + fileName)
    }
}