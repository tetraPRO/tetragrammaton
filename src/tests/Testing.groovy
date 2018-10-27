package tests

import org.junit.Test
import tetragrammaton.Controller


class Testing extends GroovyTestCase{

    @Test
    void testSaveFile(){
        Controller controller = new Controller()
        controller.inputSpace.text = 'This is a test'
        controller.saveFile()
        assert controller.tabName.text == 'testing.txt'
    }

}