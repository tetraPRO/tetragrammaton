package tetragrammaton

import javafx.fxml.FXML
import javafx.scene.control.Tab
import javafx.scene.control.TextArea
import javafx.stage.FileChooser

class Controller {

    @FXML
    TextArea inputSpace
    @FXML
    Tab tabName

    void openFile() {

    }

    void saveFile() {
        FileChooser chooser = new FileChooser()
        def data = inputSpace.text
        def file = chooser.showSaveDialog(javafx.stage.Window.getResource('Main.java'))
        if(data != null){
            file.text = ''
            file << data
        }
    }
}
