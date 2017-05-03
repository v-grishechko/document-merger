package by.documentmerger.desktop.screen

import javafx.application.Application
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TextField

class LoginScreenController : BaseController() {

    val login = "root"
    val password = "root"

    @FXML
    lateinit var loginButton: Button

    @FXML
    lateinit var loginField: TextField

    @FXML
    lateinit var passwordField: TextField

    @FXML
    fun initialize() {

    }

    @FXML
    fun onLoginClick() {
        if (loginField.text.equals(login) && passwordField.text.equals(password)) {
            app.showMainView()
        }
    }
}