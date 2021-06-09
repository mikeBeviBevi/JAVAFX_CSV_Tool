

package gov.faa.cab.util.FX.models;

import java.io.File;

/**
 * Use this factory in order to generate Concepts analysis JavaFX
 * Models.
 * @author Concept Analysis Branch 
 */
public class ModelFactory {

	/**
	 * Get an expanding text Box.
	 * @param s string that is linked to expanding TextBox
	 * @return 
	 */
	public static ExpandingTextBox getExpandingTextBox(String s){
		return new ExpandingTextBox(s);
	}

	/**
	 * Get a file button.
	 * @param s Text to show on button.
	 * @param location File location.
	 * @return a file button.
	 */
	public static FileButton getFileButton(String s, String location){
		return new FileButton(new File(location), s);
	}
	
	/**
	 * Get a file header preview box.
	 * @param f file associated with this preview box
	 * @param numLines number of lines to preview.
	 * @return a FileHeaderPreview.
	 */
	public static FileHeaderPreview getFileHeaderPreview(File f, int numLines){
		return new FileHeaderPreview(f, numLines);
	}
	
	/**
	 * Get an character size limited text Box.
	 * @param num number of characters to limit box by.
	 * @return a character size limited text box.
	 */
	public static CharLimitTextBox getCharLimitTextBox(int num){
		return new CharLimitTextBox(num);
	}
	
	private ModelFactory() {
	}
	
}
