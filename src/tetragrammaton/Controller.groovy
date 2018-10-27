package tetragrammaton

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.control.TextArea
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import javafx.scene.control.cell.TextFieldTreeCell
import javafx.stage.FileChooser
import javafx.stage.Window

class Controller implements Initializable{

    @FXML
    TextArea inputSpace
    @FXML
    Tab tabName
    @FXML
    TabPane tabPane
    @FXML
    TreeView fileTree

    void openFile() {
        //get file
        def chooser = new FileChooser()
        def file = chooser.showOpenDialog(Window.getResource('Main.java') as Window)
        def openFile = new Tab()
        tabPane.getTabs().add(openFile)

        //set file name to tab title
        def dotPosition = file.name.indexOf('.')
        def fileName = file.name.substring(0,dotPosition)
        openFile.text = fileName

        //add file data to text area
        def inputArea = new TextArea()
        openFile.setContent(inputArea)
        inputArea.text = file.text

        //bring to front
        openFile.getTabPane().getSelectionModel().select(openFile)
    }

    /**
     * save text data to file
     * and set title of tab
     */
    void saveFile() {
        FileChooser chooser = new FileChooser()
        def data = inputSpace.text
        def file = chooser.showSaveDialog(Window.getResource('Main.java') as Window)
        if(data != null){
            file.text = ''
            file << data
        }

        //set tab title to saved file name
        int dotPosition = file.name.indexOf('.')
        def fileName = file.name.substring(0,dotPosition)
        tabName.text = fileName
    }

    /**
     * stackoverflow.com Answer
     * https://stackoverflow.com/questions/38278601/javafx-treeview-directory-listing
     * answered Jul 9 '16 at 6:46
     * Author: fabian
     * Original was using CheckBoxTreeItem, I updated code to my needs
     * @param inputDirectoryLocation
     */
    void displayTreeView(String inputDirectoryLocation) {
        GetTree tree = new GetTree()

        // Creates the root item.
        TreeItem<String> rootItem = new TreeItem<>(inputDirectoryLocation)

        // Hides the root item of the tree view.
        fileTree.setShowRoot(false)

        // Creates the cell factory.
        fileTree.setCellFactory(TextFieldTreeCell.forTreeView())

        // Get a list of files.
        File fileInputDirectoryLocation = new File(inputDirectoryLocation)
        List<File> fileList = fileInputDirectoryLocation.listFiles()

        // create tree
        for (File file : fileList) {
            tree.createTree(file, rootItem)
        }

        fileTree.setRoot(rootItem)
    }

    @Override
    void initialize(URL url, ResourceBundle resourceBundle) {
        displayTreeView(System.getProperty('user.dir'))
    }
}
