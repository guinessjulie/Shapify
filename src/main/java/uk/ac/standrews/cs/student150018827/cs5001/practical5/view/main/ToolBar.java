package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.GUIController;

public class ToolBar extends javafx.scene.control.ToolBar {

    private GUIController guiController;
    private MainWindow mainWindow;

    private Button btnNew;
    private Button btnOpen;
    private Button btnSave;

    private Button btnUndo;
    private Button btnRedo;

    private ColorPicker btnColorPicker;

    public ToolBar(GUIController guiController, MainWindow mainWindow) {
        super();

        this.guiController = guiController;
        this.mainWindow = mainWindow;

        buildToolBar();
    }

    private void buildToolBar() {
        buildFileControls();
        getItems().add(new Separator());
        buildEditControls();
        getItems().add(new Separator());
        buildColorControls();
    }

    private void buildFileControls() {
        btnNew = new Button();
        btnNew.setText("New");
        btnNew.setTooltip(new Tooltip("New (Ctrl+N)"));
        btnNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                guiController.openNewDrawingDialog((Stage) ToolBar.this.getScene().getWindow());
            }
        });

        btnOpen = new Button();
        btnOpen.setText("Open...");
        btnOpen.setTooltip(new Tooltip("Open (Ctrl+O)"));
        btnOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainWindow.openFile();
            }
        });

        btnSave = new Button();
        btnSave.setText("Save");
        btnSave.setTooltip(new Tooltip("Save (Ctrl+S)"));
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainWindow.saveFile();
            }
        });

        getItems().addAll(btnNew, btnOpen, btnSave);
    }

    private void buildEditControls() {
        btnUndo = new Button();
        btnUndo.setText("Undo");
        btnUndo.setTooltip(new Tooltip("Undo (Ctrl+Z)"));
        btnUndo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Undo Clicked!");
            }
        });

        btnRedo = new Button();
        btnRedo.setText("Redo");
        btnRedo.setTooltip(new Tooltip("Redo (Ctrl+Shift+Z)"));
        btnRedo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Redo Clicked!");
            }
        });

        getItems().addAll(btnUndo, btnRedo);
    }

    private void buildColorControls() {
        btnColorPicker = new ColorPicker();
        btnColorPicker.getStyleClass().add("split-button");

        btnColorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Color Chosen: " + btnColorPicker.getValue());
            }
        });

        getItems().addAll(btnColorPicker);
    }

}