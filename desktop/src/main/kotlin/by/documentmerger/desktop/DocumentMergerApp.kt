package by.documentmerger.desktop

/**
 * Created by vgrishechko on 13.04.17.
 */

import by.documentmerger.core.parser.Token
import by.documentmerger.desktop.screen.BaseController
import by.documentmerger.desktop.screen.TemplateScreenController
import javafx.application.Application
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.stage.Stage
import java.io.File

class DocumentMergerApp : Application() {

    lateinit var primaryStage: Stage

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
        templateController.setDocument(file)
    }

    fun setView(fxmlFile: String): BaseController {
        val fxmlLoader = FXMLLoader()
        val mainContainer = fxmlLoader.load<Pane>(fxmlFile)
        val baseController = fxmlLoader.getController<BaseController>()
        baseController.setApplication(this)
        primaryStage.scene = Scene(mainContainer)
        return baseController
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
