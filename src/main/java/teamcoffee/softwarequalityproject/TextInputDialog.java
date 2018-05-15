/*
 * Copyright (c) 2014, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package teamcoffee.softwarequalityproject;

//import javafx.scene.control.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.resources.ControlResources;
import javafx.application.Platform;
import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.util.Callback;

/**
 * A dialog that shows a text input control to the user.
 *
 * @see Dialog
 * @since JavaFX 8u40
 */
public class TextInputDialog extends Dialog<String> {

    /**
     * ************************************************************************
     *
     * Fields
     *
     *************************************************************************
     */
    private final GridPane grid;
    private final Label label;
    private final JFXTextField textField;
    private final String defaultValue;

    /**
     * ************************************************************************
     *
     * Constructors
     *
     *************************************************************************
     */
    /**
     * Creates a new TextInputDialog without a default value entered into the
     * dialog {@link TextField}.
     */
    public TextInputDialog() {
        this("");
    }

    /**
     * Creates a new TextInputDialog with the default value entered into the
     * dialog {@link TextField}.
     *
     * @param defaultValue
     */
    public TextInputDialog(@NamedArg("defaultValue") String defaultValue) {
        final DialogPane dialogPane = new DialogPane() {
            @Override
            protected Node createButton(ButtonType buttonType) {
                final JFXButton button = new JFXButton(buttonType.getText());
                final ButtonData buttonData = buttonType.getButtonData();
                ButtonBar.setButtonData(button, buttonData);
                button.setDefaultButton(buttonData.isDefaultButton());
                button.setCancelButton(buttonData.isCancelButton());
                button.addEventHandler(ActionEvent.ACTION, ae -> {
                    if (ae.isConsumed()) {
                        return;
                    }
                    Callback<ButtonType, String> resultConverter = getResultConverter();

                    String priorResultValue = getResult();
                    String newResultValue;

                    if (resultConverter == null) {
                        newResultValue = buttonType.toString();
                    } else {
                        newResultValue = resultConverter.call(buttonType);
                    }

                    setResult(newResultValue);

                    if (priorResultValue.equals(newResultValue)) {
                        close();
                    }
                });

                return button;
            }

        };
        setDialogPane(dialogPane);

        // -- textfield
        this.textField = new JFXTextField(defaultValue);
        this.textField.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(textField, Priority.ALWAYS);
        GridPane.setFillWidth(textField, true);

        // -- label
        label = createContentLabel(dialogPane.getContentText());
        label.setPrefWidth(Region.USE_COMPUTED_SIZE);
        label.textProperty().bind(dialogPane.contentTextProperty());

        this.defaultValue = defaultValue;

        this.grid = new GridPane();
        this.grid.setHgap(10);
        this.grid.setMaxWidth(Double.MAX_VALUE);
        this.grid.setAlignment(Pos.CENTER_LEFT);

        dialogPane.contentTextProperty().addListener(o -> updateGrid());

        setTitle(ControlResources.getString("Dialog.confirm.title"));
        dialogPane.setHeaderText(ControlResources.getString("Dialog.confirm.header"));
        dialogPane.getStyleClass().add("text-input-dialog");
        dialogPane.getStylesheets().add("/styles/Styles.css");
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        updateGrid();

        setResultConverter((dialogButton) -> {
            ButtonData data = dialogButton == null ? null : dialogButton.getButtonData();
            return data == ButtonData.OK_DONE ? textField.getText() : null;
        });
    }

    /**
     * ************************************************************************
     *
     * Public API
     *
     *************************************************************************
     */
    /**
     * Returns the {@link TextField} used within this dialog.
     *
     * @return
     */
    public final TextField getEditor() {
        return textField;
    }

    /**
     * Returns the default value that was specified in the constructor.
     *
     * @return
     */
    public final String getDefaultValue() {
        return defaultValue;
    }

    /**
     * ************************************************************************
     *
     * Private Implementation
     *
     *************************************************************************
     */
    private void updateGrid() {
        grid.getChildren().clear();

        grid.add(label, 0, 0);
        grid.add(textField, 1, 0);
        getDialogPane().setContent(grid);

        Platform.runLater(() -> textField.requestFocus());
    }

    /**
     * Creates a Label node that works well within a Dialog.
     *
     * @param text The text to display
     */
    private static Label createContentLabel(String text) {
        Label label = new Label(text);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setMaxHeight(Double.MAX_VALUE);
        label.getStyleClass().add("content");
        label.setWrapText(true);
        label.setPrefWidth(360);
        return label;
    }
}
