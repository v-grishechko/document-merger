package by.documentmerger.desktop.screen

import by.documentmerger.core.document.Document
import by.documentmerger.core.document.WordDocument
import by.documentmerger.core.parser.TemplateParser
import by.documentmerger.core.parser.Token
import by.documentmerger.desktop.utils.isWordDocument
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.cell.TextFieldTableCell
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.stage.FileChooser
import sun.font.TextLabel
import java.io.File

class TemplateScreenController : BaseController() {

    @FXML
    lateinit var tableView: TableView<Token>

    @FXML
    lateinit var progressPane: Pane

    @FXML
    lateinit var mainContent: ScrollPane

    @FXML
    lateinit var tokenNameColumn: TableColumn<Token, String>

    @FXML
    lateinit var tokenValueColumn: TableColumn<Token, String>

    val templateParser = TemplateParser()

    lateinit var tokens: ObservableList<Token>

    lateinit var documentTemplate: Document

    @FXML
    fun initialize() {
        showProgress()

        tokenNameColumn.setCellValueFactory {
            SimpleStringProperty(it.value.name)
        }

        tokenValueColumn.setCellValueFactory {
            SimpleStringProperty(if (it.value.value.isNullOrEmpty()) "Выберите значение..." else it.value.value)
        }

        tokenValueColumn.setCellFactory {
            val cell = object : TableCell<Token, String>() {
                override fun updateItem(item: String?, empty: Boolean) {
                    super.updateItem(item, empty)
                    text = item
                }

            }

            cell.onMouseClicked = EventHandler {
                app.showTokenValue(tokens.get((it.source as TableCell<*, *>).index))
            }
            cell
        }

        tokenValueColumn.isEditable = true
    }

    @FXML
    fun saveDocument() {
        var fileChooser = FileChooser()
        fileChooser.title = "Сохранить документ"
        val file: File? = fileChooser.showSaveDialog(app.primaryStage)

        if(file != null) {
            if (!file.isWordDocument()) {

            } else {
                documentTemplate.replace(tokens)
                documentTemplate.save(file)
            }
        }
    }

    fun setDocument(document: Document) {
        showProgress()

        documentTemplate = document

        tokens = FXCollections.observableList(templateParser.parse(document))

        tableView.items = tokens

        hideProgress()
    }

    fun setToken(token: Token) {
        var index = 0

        for ((iterableIndex, iterableToken) in tokens.withIndex()) {
            if (iterableToken.name.equals(token.name)) {
                index = iterableIndex
                break
            }
        }

        tokens[index] = token
    }

    fun showProgress() {
        mainContent.isVisible = false
        progressPane.isVisible = true
    }

    fun hideProgress() {
        mainContent.isVisible = true
        progressPane.isVisible = false
    }

}
