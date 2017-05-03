package by.documentmerger.desktop.screen

import by.documentmerger.core.document.WordDocument
import by.documentmerger.core.parser.TemplateParser
import by.documentmerger.core.parser.Token
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import sun.font.TextLabel
import java.io.File

class TemplateScreenController : BaseController() {

    @FXML
    lateinit var gridPane: GridPane

    @FXML
    lateinit var progressPane: Pane

    @FXML
    lateinit var mainContent: ScrollPane

    val templateParser = TemplateParser()

    @FXML
    fun initialize() {
        showProgress()
    }

    fun setDocument(file: File) {
        showProgress()
        val tokens = templateParser.parse(WordDocument(file))

        tokens.forEach { addTokenField(it) }

        hideProgress()
    }

    fun showProgress() {
        mainContent.isVisible = false
        progressPane.isVisible = true
    }

    fun hideProgress() {
        mainContent.isVisible = true
        progressPane.isVisible = false
    }

    var rowIndex = 0

    fun addTokenField(token: Token) {

        val tokenName = TextField(token.name)

        val equalsField = Label("=")

        val tokenValue = TextField()
        tokenValue.promptText = "Введите значение..."

        gridPane.add(tokenName, 0, rowIndex)
        gridPane.add(equalsField, 1, rowIndex)
        gridPane.add(tokenValue, 2, rowIndex)

        rowIndex++
    }
}
