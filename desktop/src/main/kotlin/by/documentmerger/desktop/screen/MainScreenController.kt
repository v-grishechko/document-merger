package by.documentmerger.desktop.screen

import by.documentmerger.desktop.utils.isWordDocument
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.stage.FileChooser
import java.io.File

class MainScreenController: BaseController() {

    @FXML
    lateinit var loadTemplate: Button

    @FXML
    fun onLoadTemplateClick() {
        var fileChooser = FileChooser()
        val file = fileChooser.showOpenDialog(app.primaryStage)

        if(!file.isWordDocument()) {
            showError("Можно загрузить шаблоны только с расширением .doс и .docx")
        } else {
            app.showTemplateView(file)
        }
    }
}