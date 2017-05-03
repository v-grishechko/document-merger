package by.documentmerger.desktop.screen

import by.documentmerger.desktop.DocumentMergerApp
import javafx.application.Application
import javafx.scene.control.Alert

open class BaseController {
    protected lateinit var app: DocumentMergerApp

    fun setApplication(app: Application) {
        this.app = app as DocumentMergerApp
    }

    fun showError(message: String, title: String = "Ошибка", header: String = "Произошла ошибка") {
        val alert = Alert(Alert.AlertType.ERROR)
        alert.title = title
        alert.headerText = header
        alert.contentText = message
        alert.showAndWait()
    }
}