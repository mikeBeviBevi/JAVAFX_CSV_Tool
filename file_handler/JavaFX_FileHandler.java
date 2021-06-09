/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.faa.cab.util.FX.file_handler;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Driver for FileHandlerStage.
 * Normally do not use this stand alone unless you are just
 * previewing a file. 
 * @author Concept Analysis Branch
 */
public class JavaFX_FileHandler extends Application {
/////////////////////////////////////////////////////////////////
	private FileHandlerStage primaryStage;
/////////////////////////////////////////////////////////////////
	
	/**
	 * runs FileHandlerStage
	 * @param primaryStage
	 * @throws java.lang.Exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = new FileHandlerStage();
		this.primaryStage.produce();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
