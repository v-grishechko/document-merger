package by.documentmerger.desktop.screen

import by.documentmerger.core.parser.Token
import javafx.fxml.FXML
import javafx.scene.control.TextField
import javafx.scene.text.Text

class TokenValueScreenController: BaseController() {

    @FXML
    lateinit var tokenNameView: Text

    @FXML
    lateinit var tokenValueView: TextField

    lateinit var token: Token

    fun setUpContent(token: Token) {
        this.token = token
        tokenNameView.text = token.name
        tokenValueView.text = token.value
    }

    @FXML
    fun onSaveClick() {
        token.value = tokenValueView.text
        app.updateToken(token)
    }
}
