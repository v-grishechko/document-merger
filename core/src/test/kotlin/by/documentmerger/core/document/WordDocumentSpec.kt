package by.documentmerger.core.document

import by.documentmerger.core.parser.TemplateParser
import by.documentmerger.core.parser.Token
import by.documentmerger.utils.Resources
import org.amshove.kluent.`should equal to`
import org.hamcrest.MatcherAssert.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import java.io.File

class WordDocumentSpec: Spek({

    describe("Word document") {

        given("template with one token") {

            var wordDocument = WordDocument(Resources.getFile("positive_template_two.docx"))

            it("should replace token") {
                var tokens = TemplateParser().parse(wordDocument)
                tokens[0].value = "success"
                wordDocument.replace(tokens as MutableList<Token>)
                wordDocument.save(Resources.createFile("test.docx"))

                wordDocument = WordDocument(Resources.getFile("test.docx"))
                wordDocument.getText().trim() `should equal to` "success"
            }
        }
    }
})
