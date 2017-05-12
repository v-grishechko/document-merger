package by.documentmerger.core.parser

import by.documentmerger.core.document.WordDocument
import by.documentmerger.utils.Resources
import org.amshove.kluent.`should contain`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Test
import org.junit.runner.RunWith
import java.net.URL

object TemplateParserTest : Spek({

    describe("a template parser") {
        val templateParser = TemplateParser()

        val tokens = arrayListOf<Token>(Token("\${Смысл сайта}"), Token("\${поможет}"), Token("\${test}"))

        it("should parse all expressions from .txt") {
            val expressions = templateParser.parse(Resources.readResourse("positive_template.txt"))

            for (token in expressions) {
                tokens `should contain` token
            }
        }

        it("should parse all expressions from .docx") {
            val expressions = templateParser.parse(WordDocument(Resources.getFile("positive_template.docx")))

            for (token in expressions) {
                tokens `should contain` token
            }
        }
    }
})