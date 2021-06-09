package gov.faa.cab.util.FX.models;

import gov.faa.cpat.Util.CabLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * A JavaFX object used to preview a certain number of lines in
 * a file.
 * @author Concept Analysis Branch
 */
public class FileHeaderPreview extends VBox {
/////////////////////////////////////////////////////////////////
	private File f;
	
	private int numLines;
	
	private ArrayList<TextField>linesInFile=new ArrayList<>();
/////////////////////////////////////////////////////////////////
	
	/**
	 * Constructor.
	 * @param f the file associated with this HeaderPreview 
	 * @param numLines number of lines to display
	 */
	public FileHeaderPreview(File f, int numLines) {
		this.f = f;
		this.numLines = numLines;
		this.getStyleClass().add("headerPreview");
		this.setSpacing(5);
			try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			for (int i = 0; i < numLines; i++) {
				TextField text = new TextField(reader.readLine());
				text.setEditable(false);
				text.getStyleClass().add("highlightText");
				linesInFile.add(text);
				this.getChildren().add(text);
			}
		} catch (IOException ex) {
			CabLog.logException(ex);
		}
	}
	
	/**
	 * 
	 * @return null if no textField is focused else focused textField
	 */
	public TextField getFocused(){
		TextField result=null;
		for (TextField field : linesInFile) {
			if (field.isFocused()){
				result= field;
			}
		}
			return result;
	}
	
	/**
	 * Set the action on Mouse Clicked for each cell in preview.
	 * @param mouseClicked mouse clicked action. 
	 */
	public void setMouseClicked(EventHandler<MouseEvent> mouseClicked) {
		for (TextField field : linesInFile) {
			field.setOnMouseClicked(mouseClicked);
		}
	}
	
	/**
	 * Get a line of text.
	 * @param num line number of text to return
	 * @return a text field for that has that line of text.
	 */
	public TextField getLineNumber(int num){
		return linesInFile.get(num);
	}
}
