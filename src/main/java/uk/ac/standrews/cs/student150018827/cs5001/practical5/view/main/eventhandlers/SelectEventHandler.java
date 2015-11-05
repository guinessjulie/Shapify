package uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.controller.MainController;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.model.GUIState;
import uk.ac.standrews.cs.student150018827.cs5001.practical5.view.main.ArtBoard;

public class SelectEventHandler extends MouseEventHandler {

    private int originalX;
    private int originalY;

    private int deltaX;
    private int deltaY;

    public SelectEventHandler(MainController mainController) {
        super(mainController);
    }

    public EventHandler<MouseEvent> getMousePressedEventHandler() {
        return event -> {
            originalX = (int) event.getX();
            originalY = (int) event.getY();

            Node selectedObject = (Node) event.getSource();

            GUIState guiState = mainController.getGUIController().getGuiState();

            // Reset selected object
            guiState.setSelectedObject(null);

            if (selectedObject instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) selectedObject;

                // Set selected object color
                guiState.setCurrentForeground(((Color) rectangle.getFill()));

                deltaX = (int) (originalX - rectangle.getX());
                deltaY = (int) (originalY - rectangle.getY());
            }

            if (selectedObject instanceof Ellipse) {
                Ellipse ellipse = (Ellipse) selectedObject;

                // Set selected object color
                guiState.setCurrentForeground(((Color) ellipse.getFill()));

                deltaX = (int) (originalX - ellipse.getCenterX());
                deltaY = (int) (originalY - ellipse.getCenterY());
            }

            if (selectedObject instanceof Line) {
                Line line = (Line) selectedObject;

                // Set selected object color
                guiState.setCurrentForeground(((Color) line.getStroke()));

                deltaX = (int) (originalX - line.getStartX());
                deltaY = (int) (originalY - line.getStartY());
            }

            // Set selected object
            guiState.setSelectedObject(selectedObject);
        };
    }

    public EventHandler<MouseEvent> getMouseDraggedEventHandler() {
        return event -> {
            ArtBoard artBoard = mainScene.getArtBoard();

            int x = (int) event.getX();
            int y = (int) event.getY();

            int xpos = x - deltaX;
            int ypos = y - deltaY;

            Node object = (Node) event.getSource();

            if (object instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) object;

                // Ensure event is within artboard boundaries
                xpos = Math.max(xpos, 0);
                xpos = Math.min(xpos, (int) (artBoard.getWidth() - rectangle.getWidth()));

                ypos = Math.max(ypos, 0);
                ypos = Math.min(ypos, (int) (artBoard.getHeight() - rectangle.getHeight()));

                rectangle.setX(xpos);
                rectangle.setY(ypos);
            }

            if (object instanceof Ellipse) {
                Ellipse ellipse = (Ellipse) object;

                // Ensure event is within artboard boundaries
                xpos = Math.max(xpos, (int) (0 + ellipse.getRadiusX()));
                xpos = Math.min(xpos, (int) (artBoard.getWidth() - ellipse.getRadiusX()));

                ypos = Math.max(ypos, (int) (0 + ellipse.getRadiusY()));
                ypos = Math.min(ypos, (int) (artBoard.getHeight() - ellipse.getRadiusY()));

                ellipse.setCenterX(xpos);
                ellipse.setCenterY(ypos);
            }

            if (object instanceof Line) {
                Line line = (Line) object;

                // Compute endX and endY delta
                int endXDelta = (int) (line.getEndX() - line.getStartX());
                int endYDelta = (int) (line.getEndY() - line.getStartY());

                // Ensure event is within artboard boundaries
                xpos = Math.max(xpos, 0);
                xpos = Math.min(xpos, (int) (artBoard.getWidth() - endXDelta));

                ypos = Math.max(ypos, 0);
                ypos = Math.min(ypos, (int) (artBoard.getHeight() - endYDelta));

                int endX = xpos + endXDelta;
                int endY = ypos + endYDelta;

                line.setStartX(xpos);
                line.setStartY(ypos);

                line.setEndX(endX);
                line.setEndY(endY);
            }

            // Ensure event is within artboard boundaries for coordinates display
            x = Math.max(x, 0);
            x = Math.min(x, (int) artBoard.getWidth());

            y = Math.max(y, 0);
            y = Math.min(y, (int) artBoard.getHeight());

            mainScene.getStatusBar().setCoordinatesLabel(x, y);
        };
    }

    public EventHandler<MouseEvent> getMouseReleasedEventHandler() {
        return event -> {

        };
    }

}