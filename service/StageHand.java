package gov.faa.cab.util.FX.service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

/**
 *  This is a helper class to run a StagedProduction outside of
 * a JavaFX application.  This is mostly used in non JavaFX classes
 * in order to pass data between a non JavaFX program and a FX program.
 * @author Concept Analysis Branch
 */
public class StageHand {
/////////////////////////////////////////////////////////////////
	private FutureTask result;
/////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 * @param rightStage the canonical name of StagedProduction
	 * you would like to run.
	 */
	public StageHand(String rightStage) {
		launch(rightStage);
	}
	
	/**
	 * Run the future task responsible for collecting output data from
	 * a stagedProduciton on the JavaFX thread.
	 * @param stage the canonical name of the StagedProduction class
	 * you would like to retrieve results from.
	 */
	public void launch(String stage) {
		new JFXPanel();
		result = new FutureTask(new Callable(){
        
            @Override
            public Object call() throws Exception {
                 StagedProduction prod=(StagedProduction)Class.forName(stage).getConstructor().newInstance();
                return (prod.produce());
            }
            
            
        });
        Platform.runLater(result);
	}
	
	/**
	 * if stage associated with this is a StagedProduction than
	 * will retrieve its output.
	 * @return results of a StagedProduction, if stage is not a 
	 * StagedProduction will return null.  Results are a futureTask
	 * since the JavaFX application runs on its own thread. 
	 * @throws InterruptedException 
	 * @throws ExecutionException 
	 */
	public Object getResults() throws InterruptedException, ExecutionException {
			return result.get();
	}
}
