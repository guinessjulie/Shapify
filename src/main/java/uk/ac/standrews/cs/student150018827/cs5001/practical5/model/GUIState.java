package uk.ac.standrews.cs.student150018827.cs5001.practical5.model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.DrawTools;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.focusoutline.FocusOutline;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GUIState {

    private MainController mainController;

    private List<Observer> observers;

    private double zoomLevel;

    private File lastUsedDirectory;

    private DrawTools selectedDrawTool;

    private Color fillColor;

    private Node selectedObject;
    private FocusOutline focusOutline;

    public GUIState(MainController mainController) {
        this.mainController = mainController;

        observers = new ArrayList<>();

        zoomLevel = 1.0;
        lastUsedDirectory = new File(System.getProperty("user.home"));
        fillColor = Color.BLACK;
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        observers.forEach(Observer::update);
    }

    public File getLastUsedDirectory() {
        return lastUsedDirectory;
    }

    public void setLastUsedDirectory(File lastUsedDirectory) {
        this.lastUsedDirectory = lastUsedDirectory;
    }

    public Node getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(Node selectedObject) {
        this.selectedObject = selectedObject;

        // Create focus outline
        focusOutline = new FocusOutline(mainController, selectedObject);

        notifyObservers();
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color foreground) {
        fillColor = foreground;
        notifyObservers();
    }

    public FocusOutline getFocusOutline() {
        return focusOutline;
    }

    public void setSelectedDrawTool(DrawTools selectedDrawTool) {
        this.selectedDrawTool = selectedDrawTool;
        notifyObservers();
    }

    public DrawTools getSelectedDrawTool() {
        return selectedDrawTool;
    }

    public void setZoomLevel(double zoomLevel) {
        zoomLevel = Math.min(zoomLevel, 5);
        zoomLevel = Math.max(zoomLevel, 0.1);

        this.zoomLevel = zoomLevel;
        notifyObservers();
    }

    public double getZoomLevel() {
        return zoomLevel;
    }

}