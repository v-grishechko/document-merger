package by.documentmerger.desktop

/**
 * Created by vgrishechko on 13.04.17.
 */

import by.documentmerger.core.document.WordDocument
import by.documentmerger.core.parser.Token
import by.documentmerger.desktop.screen.BaseController
import by.documentmerger.desktop.screen.TemplateScreenController
import by.documentmerger.desktop.screen.TokenValueScreenController
import javafx.application.Application
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.stage.Modality
import javafx.stage.Stage
import java.io.File

class DocumentMergerApp : Application() {

    lateinit var primaryStage: Stage

    lateinit var secondaryStage: Stage

    lateinit var currentController: BaseController

    lateinit var secondaryController: BaseController

    override fun start(primaryStage: Stage) {
        this.primaryStage = primaryStage
        this.primaryStage.title = "Document merger"

        showLoginView()

        primaryStage.show()
    }

    fun showLoginView() {
        setView("login.fxml")
    }

    fun showMainView() {
        setView("main.fxml")
    }

    fun showTemplateView(file: File) {
        val templateController = setView("template.fxml") as TemplateScreenController
        templateController.setDocument(WordDocument(file))
    }

    fun showTokenValue(token: Token) {
        secondaryStage = Stage()
        secondaryStage.initModality(Modality.APPLICATION_MODAL)
        secondaryStage.initOwner(primaryStage)
        val fxmlLoader = FXMLLoader()
        val mainContainer = fxmlLoader.load<Pane>("tokenvalue.fxml")
        secondaryController = fxmlLoader.getController()
        secondaryStage.scene = Scene(mainContainer)
        (secondaryController as TokenValueScreenController).setUpContent(token)
        secondaryController.setApplication(this)
        secondaryStage.show()
    }

    fun setView(fxmlFile: String): BaseController {
        val fxmlLoader = FXMLLoader()
        val mainContainer = fxmlLoader.load<Pane>(fxmlFile)
        currentController = fxmlLoader.getController<BaseController>()
        currentController.setApplication(this)
        primaryStage.scene = Scene(mainContainer)
        return currentController
    }

    fun updateToken(token: Token) {
        secondaryStage.close()
        (currentController as TemplateScreenController).setToken(token)
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            launch(DocumentMergerApp::class.java)
        }
    }

    fun <T> FXMLLoader.load(fxmlFileName: String): T {
        location = DocumentMergerApp::class.java.classLoader.getResource(fxmlFileName)
        return load()
    }
}
