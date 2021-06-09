

package gov.faa.cab.util.FX.models;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * A JavaFX TextField object that expands its width as user types.
 * this object can also be linked to a String in your program.
 * this will link the strings value to the text value of this
 * textField.
 * @author Concept Analysis Branch 
 */
public class ExpandingTextBox extends StackPane {
/////////////////////////////////////////////////////////////////
	private final TextField valuetoUpdateBox;
/////////////////////////////////////////////////////////////////
	
	/**
	 * Constructor.
	 * @param valuetoUpdate String linked to this box
	 */
	public ExpandingTextBox(String s) {
		Rectangle rec = new Rectangle();
		valuetoUpdateBox= getExpandingTextField();
		rec.setArcWidth(20.0);
		rec.setArcHeight(20.0);
		this.getChildren().add(rec);
		this.getChildren().add(valuetoUpdateBox);
	}

	/**
	 * 
	 * @param valuetoUpdate change the String you would like linked
	 * to this textField
	 */
	public void setValuetoUpdate(String valuetoUpdate) {
		this.valuetoUpdateBox.setText(valuetoUpdate);
	}
	
	/**
	 * 
	 * @return text in this box. 
	 */
	public String getText(){
		return this.valuetoUpdateBox.getText();
	}
	
	/**
	 * 
	 * @param s the String this text field is linked to.
	 * @return Text field allowing a limited number of characters.
	 */
	private TextField getExpandingTextField(){
		TextField tf = new TextField();
		tf.setPrefWidth(30);
		tf.setMinWidth(Region.USE_PREF_SIZE);
		tf.setMaxWidth(Region.USE_PREF_SIZE);
		tf.textProperty().addListener(new ChangeListener<String>(){
			@Override
			public void changed
			(ObservableValue<? extends String> observable, String oldValue, String newValue)  {
			tf.setText(newValue);
			//this code form Kalasch @http://stackoverflow.com/questions/12737829/javafx-textfield-resize-to-text-lenght
			Platform.runLater(() -> {
			 Text text = new Text(tf.getText());
        text.setFont(tf.getFont()); // Set the same font, so the size is the same
        double width = text.getLayoutBounds().getWidth() // This big is the Text in the TextField
                + tf.getPadding().getLeft() + tf.getPadding().getRight() // Add the padding of the TextField
                + 2d; // Add some spacing
        tf.setPrefWidth(width); // Set the width
        tf.positionCaret(tf.getCaretPosition());
			//end Kalasch's code
			});
			};
		});
		return tf;
	}
	
}
