package by.documentmerger.core.document

import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.net.URI
import java.net.URL
import org.apache.poi.xwpf.extractor.XWPFWordExtractor
import java.io.File
import java.io.FileInputStream


class WordDocument(var file: File) : Document {

    override fun getText(): String {
        val docx = XWPFDocument(FileInputStream(file))
        val we = XWPFWordExtractor(docx)
        return we.text
    }
}
