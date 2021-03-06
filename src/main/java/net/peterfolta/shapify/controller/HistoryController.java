package net.peterfolta.shapify.controller;

import net.peterfolta.shapify.model.Document;
import net.peterfolta.shapify.model.GUIState;

import java.util.LinkedList;
import java.util.List;

public class HistoryController {

    private static HistoryController instance;

    private MainController mainController;
    private Document document;

    private List<Document> history;
    private volatile int historyPointer;

    private HistoryController(MainController mainController) {
        this.mainController = mainController;

        history = new LinkedList<>();
        historyPointer = -1;
    }

    public static HistoryController getInstance(MainController mainController) {
        if (instance == null) {
            instance = new HistoryController(mainController);
        }

        return instance;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public void undo() {
        if (isUndoAvailable()) {
            GUIState guiState = mainController.getGUIController().getGuiState();

            historyPointer--;

            document = history.get(historyPointer).clone();

            mainController.getDocumentController().setDocument(document);
            guiState.setSelectedObject(null);

            document.notifyObservers();
        }
    }

    public boolean isUndoAvailable() {
        return (historyPointer > 0);
    }

    public void redo() {
        if (isRedoAvailable()) {
            historyPointer++;

            document = history.get(historyPointer).clone();

            mainController.getDocumentController().setDocument(document);
            mainController.getGUIController().setSelectedTool(mainController.getGUIController().getGuiState().getSelectedDrawTool());
            mainController.getGUIController().getGuiState().notifyObservers();
        }
    }

    public boolean isRedoAvailable() {
        return (historyPointer < history.size() - 1);
    }

    public void reset() {
        history.clear();
        historyPointer = -1;
    }

    public void createHistoryPoint() {
        if (historyPointer != (history.size() - 1)) {
            while ((historyPointer + 1) < history.size()) {
                history.remove(historyPointer + 1);
            }
        }

        if (historyPointer >= 0) {
            // Set File to Unsaved
            mainController.getDocumentController().getDocument().setSaved(false);
        }

        Document clone = document.clone();
        history.add(clone);
        historyPointer++;

        mainController.getDocumentController().getDocument().notifyObservers();
    }

}