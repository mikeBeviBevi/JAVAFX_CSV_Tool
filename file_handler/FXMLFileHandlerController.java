/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.faa.cab.util.FX.file_handler;

import gov.faa.cab.util.FX.models.CharLimitTextBox;
import gov.faa.cab.util.FX.models.FileButton;
import gov.faa.cab.util.FX.models.FileHeaderPreview;
import gov.faa.cab.util.FX.models.ModelFactory;
import gov.faa.cab.util.text.TextUtil;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Concept Analysis Branch
 */
public class FXMLFileHandlerController {
/////////////////////////////////////////////////////////////////
	@FXML
	private Pane mainPane;

	private File inputFile;

	private Stage fileHandlerStage;

	private String header;
	
	private CharLimitTextBox headerDelimBox;
	
	private CharLimitTextBox fileDelimBox;
	
	private CharLimitTextBox quoteBox;
	
	private CharLimitTextBox commentMarkerBox;
	
	private boolean delimBoxSetup=false;
/////////////////////////////////////////////////////////////////

	/**
	 * Initializes the controller class.
	 */
	@FXML
	public void initialize() {

	}

	/**
	 * handles choose input file choosing.
	 * @param event user clicked on input file menu item.
	 */
	@FXML
	private void handleInFileMenuItem(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select input File");
		inputFile = fileChooser.showOpenDialog(fileHandlerStage);
		mainPane().setCenter(getFileBoxLayout(getFileButton(inputFile.getName(),inputFile.getAbsolutePath())));
	}

	/**
	 * Sets the stage area for all the gui items.
	 * Use this when you would like to clear and update what the user is seeing in the gui.
	 * @param stage the main stage for this application
	 */
	public void setStage(Stage stage) {
		this.fileHandlerStage = stage;
	}

	/**
	 *  get a file button with functionality specific for this application.
	 * @param buttonText text to display on button
	 * @param fileLocation location of file
	 * @return a FileButton linked to a fileHeader preview
	 */
	private Button getFileButton(String buttonText,String fileLocation) {
		FileButton button = ModelFactory.getFileButton(buttonText, fileLocation);
		button.getStyleClass().add("fileButton");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				mainPane().setBottom(getFileHeaderPreview(button.getFile(),10,""));
			}
		});
		return button;
	}

	/**
	 * 
	 * @param n  and number of JavaFX.Nodes
	 * @return a Hbox with default spacing for this program in a Horizontal Box.
	 */
	private HBox getFileBoxLayout(Node... n) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.getChildren().addAll(n);
		return hbox;
	}

	/**
	 * This application currently only has one scene.
	 * this scene has a borderPane layout and all updates are done directly to this scene.
	 * if you wanted to use a different layout with different objects on the stage
	 * you shoudl use setStage().
	 * @return the BorderPane layout for this application 
	 */
	private BorderPane mainPane() {
		return ((BorderPane) fileHandlerStage.getScene().getRoot());
	}

	/**
	 * get a preview of the file the user selected and links the preview to text fields to input the delimiter for these files. 
	 * @param f file to preview
	 * @param num number of lines to display
	 * @return a fileheader preview box which i s linked to delimiter display boxes
	 */
	private VBox getFileHeaderPreview(File f, int num, String s) {
		FileHeaderPreview fileHeader =ModelFactory.getFileHeaderPreview(f, num);
		fileHeader.setMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						//get the line of text selected by user
						header = fileHeader.getFocused().getText();
						headerDelimBox=ModelFactory.getCharLimitTextBox(2);
						fileDelimBox=ModelFactory.getCharLimitTextBox(2);
						quoteBox=ModelFactory.getCharLimitTextBox(2);
						commentMarkerBox=ModelFactory.getCharLimitTextBox(2);
						//make best guess at delimiter
						headerDelimBox.setText(TextUtil.guessdelim(header));
						fileDelimBox.setText(TextUtil.guessdelim(fileHeader.getLineNumber(num-1).getText()));
						commentMarkerBox.setText(fileHeader.getLineNumber(0).getText().substring(0,1));
						quoteBox.setText("\"");
						if(!delimBoxSetup){
						VBox vBoxHeadDelim = new VBox();
						VBox vBoxFileDelim = new VBox();
						VBox vBoxCommentMarker = new VBox();
						VBox vBoxquote = new VBox();
						vBoxHeadDelim.getChildren().addAll(new Label("Header"),headerDelimBox);
						vBoxFileDelim.getChildren().addAll(new Label("File"), fileDelimBox);
						vBoxCommentMarker.getChildren().addAll(new Label("Comment"), commentMarkerBox);
						vBoxquote.getChildren().addAll(new Label("Quote"), quoteBox);
						vBoxHeadDelim.setPadding(new Insets(0,20,0,20));
						vBoxFileDelim.setPadding(new Insets(0,20,0,20));
						vBoxCommentMarker.setPadding(new Insets(0,20,0,20));
						vBoxquote.setPadding(new Insets(0,20,0,20));
						HBox hbox = new HBox();
						//set delimeter box
						hbox.getChildren().addAll(mainPane().getCenter(),vBoxHeadDelim, vBoxFileDelim, vBoxCommentMarker, vBoxquote);
						//vBoxDelim.getChildren().addAll(mainPane().getCenter(),hbox);
						mainPane().setCenter(hbox);
						delimBoxSetup=true;
						}
					}
				});
		return fileHeader;
	}

	public File getInputFile() {
		return inputFile;
	}

	public String getHeader() {
		return header;
	}

	public String getHeaderDelim() {
		return headerDelimBox.getText();
	}

	public String getFileDelim() {
		return fileDelimBox.getText();
	}

	public String getQuote() {
		return quoteBox.getText();
	}

	public String getCommentMarker() {
		return commentMarkerBox.getText();
	}
	
}
