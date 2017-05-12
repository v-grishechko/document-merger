package by.documentmerger.core.document

import by.documentmerger.core.parser.Token
import org.apache.poi.xwpf.extractor.XWPFWordExtractor
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class WordDocument(var file: File) : Document {
    override fun save(file: File) {
        doc.write(FileOutputStream(file))
    }

    val doc: XWPFDocument = XWPFDocument(FileInputStream(file))

    override fun getText(): String {
        val we = XWPFWordExtractor(doc)
        return we.text
    }

    override fun replace(tokens: MutableList<Token>) {
        for (p in doc.paragraphs) {
            val runs = p.runs
            if (runs != null) {
                for (r in runs) {
                    var text: String? = r.getText(0)
                    if (text != null) {
                        val replaceToken: Token? = tokens.firstOrNull { text == it.name }

                        if (replaceToken != null) {
                            text = text.replace(replaceToken.name, replaceToken.value)
                            tokens.remove(replaceToken)
                            r.setText(text, 0)
                        }
                    }
                }
            }
        }

        for (tbl in doc.tables) {
            for (row in tbl.rows) {
                for (cell in row.tableCells) {
                    for (p in cell.paragraphs) {
                        for (r in p.runs) {
                            var text: String? = r.getText(0)

                            if (text != null) {
                                val replaceToken: Token? = tokens.firstOrNull { text == it.name }

                                if (replaceToken != null) {
                                    text = text.replace(replaceToken.name, replaceToken.value)
                                    tokens.remove(replaceToken)
                                    r.setText(text, 0)
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}
