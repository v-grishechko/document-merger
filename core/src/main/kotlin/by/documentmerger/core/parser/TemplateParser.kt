package by.documentmerger.core.parser

import by.documentmerger.core.document.Document

class TemplateParser {

    val REGULAR_EXPRESSION = "(?<=\\$\\{).+?(?=\\})" //${example} -> example

    val regex = Regex(REGULAR_EXPRESSION)

    fun parse(text: String): List<Token> {
        return regex.findAll(text).map { Token(it.value) }.toList()
    }

    fun parse(document: Document): List<Token> {
        return parse(document.getText())
    }
}