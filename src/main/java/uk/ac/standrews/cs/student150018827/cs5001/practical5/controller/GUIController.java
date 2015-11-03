package uk.ac.standrews.cs.student150018827.cs5001.practical5.controller;

import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.about.AboutStage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.MainWindow;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.newdrawing.NewDrawingStage;

public class GUIController {

    private MainController mainController;

    private MainWindow mainWindow;

    public GUIController(MainController mainController, Stage mainStage) {
        this.mainController = mainController;

        this.mainWindow = new MainWindow(this, mainStage);
    }

    public void openMainWindow() {
        mainWindow.open();
    }

    public void exit() {
        mainController.exit();
    }

    public void close() {
        mainWindow.close();
    }

    public void openNewDrawingDialo(Stage parent) {
        NewDrawingStage newDrawingStage = new NewDrawingStage(this, parent);
        newDrawingStage.show();
    }

    public void openAboutDialog(Stage parent) {
        AboutStage aboutStage = new AboutStage(this, parent);
        aboutStage.show();
    }

}