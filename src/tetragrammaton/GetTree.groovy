package tetragrammaton

import javafx.scene.control.TreeItem

/**
 * stackoverflow.com Answer
 * https://stackoverflow.com/questions/38278601/javafx-treeview-directory-listing
 * answered Jul 9 '16 at 6:46
 * Author: fabian
 * Original was using CheckBoxTreeItem, I updated code to my needs
 */
class GetTree {
    static void createTree(File file, TreeItem<String> parent) {
        if (file.isDirectory()) {
            TreeItem<String> treeItem = new TreeItem<>(file.getName())
            parent.getChildren().add(treeItem)
            for (File f : file.listFiles()) {
                createTree(f, treeItem)
            }
        } else{//must be a file
            parent.getChildren().add(new TreeItem<>(file.getName()))
        }
    }

}
