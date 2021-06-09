

package gov.faa.cab.util.FX.service;

import javafx.stage.Stage;

/**
 * Classes that extend this stage produce output for other applications
 * to process.  
 * @author Concept Analysis Branch 
 */
public abstract class StagedProduction extends Stage  {

	public abstract Object produce();
	
}
