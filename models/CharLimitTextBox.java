

package gov.faa.cab.util.FX.models;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 * A JavaFX textField which allows only a certain number of
 * characters to be typed in it.  ALso links a String to the
 * text value of this text field
 * @author Concept Analysis Branch 
 */
public class CharLimitTextBox extends StackPane {
/////////////////////////////////////////////////////////////////
	private final TextField valuetoUpdate;
/////////////////////////////////////////////////////////////////	
	
	/**
	 * Constructor.
	 * @param numChars number of allowable characters.
	 */
		public CharLimitTextBox(int numChars) {

		Rectangle rec = new Rectangle();
		valuetoUpdate = getCharLimitTextField(numChars);
		rec.setArcWidth(20.0);
		rec.setArcHeight(20.0);
		this.getChildren().add(rec);
		this.getChildren().add(valuetoUpdate);
	}

		/**
		 * 
		 * @param text text to display in this text box 
		 */
public void setText(String text){
	this.valuetoUpdate.setText(text);
}
		
	/**
	 * 
	 * @param s the String this text field is linked to.
	 * @param numChars the number of characters allowed in this textfield.
	 * @return Text field allowing a limited number of characters.
	 */
	private TextField getCharLimitTextField(int numChars){
		TextField tf = new TextField();
		tf.setPrefWidth(30);
		tf.setMinWidth(Region.USE_PREF_SIZE);
		tf.setMaxWidth(Region.USE_PREF_SIZE);
		//valuetoUpdate.setText(this.valuetoUpdate);
		tf.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (tf.getText().length() >= numChars) {
											//set char to previous char
                        tf.setText(tf.getText().substring(0, numChars));
                    }
                }
            }
        });
		return tf;
	}
	
	/**
	 * 
	 * @return text in character box 
	 */
	public String getText(){
		return this.valuetoUpdate.getText();
	}
	
}
