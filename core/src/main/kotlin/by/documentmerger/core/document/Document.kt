package by.documentmerger.core.document

import by.documentmerger.core.parser.Token
import java.io.File

interface Document {
    fun getText() : String

    fun replace(tokens: MutableList<Token>)

    fun save(file: File)
}