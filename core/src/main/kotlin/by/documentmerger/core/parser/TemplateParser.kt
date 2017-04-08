package by.documentmerger.core.parser

import by.documentmerger.core.parser.Token

class TemplateParser {

    val EXPRESION_REGULAR = "(?<=\\$\\{).+?(?=\\})" //${example} -> example

    val regex = Regex(EXPRESION_REGULAR)

    fun parse(text: String): List<Token> {
        return regex.findAll(text).map { Token(it.value) }.toList()
    }
}