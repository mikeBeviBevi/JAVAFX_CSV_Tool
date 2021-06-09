

package gov.faa.cab.util.FX.models;

import java.io.File;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * Links a JavaFX Button with a File.
 * This class can be used to visually represent a file in a JavaFX
 * project.
 * @author Concept Analysis Branch 
 */
public class FileButton extends Button {
/////////////////////////////////////////////////////////////////
	private final File file;
/////////////////////////////////////////////////////////////////
	
	/**
	 * Constructor.
	 * @param f the file to link to this button.
	 */
	public FileButton(File f) {
		this.file = f;
	}

	/**
	 * Constructor.
	 * @param f the file to link to this button.
	 * @param text text to display on this button.
	 */
	public FileButton(File f, String text) {
		super(text);
		this.file = f;
	}

	/**
	 * Constructor.
	 * @param f the file to link to this button.
	 * @param text text to display on this button.
	 * @param graphic a graphic to use with this button.
	 */
	public FileButton(File f, String text, Node graphic) {
		super(text, graphic);
		this.file = f;
	}
/**
 * 
 * @return the file associated with this button. 
 */
	public File getFile() {
		return file;
	}	

}
