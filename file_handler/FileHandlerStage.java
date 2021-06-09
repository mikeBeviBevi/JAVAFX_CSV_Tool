

package gov.faa.cab.util.FX.file_handler;

import gov.faa.cab.util.FX.service.StagedProduction;
import gov.faa.cab.util.file_types.CSVFileData;
import gov.faa.cpat.Util.CabLog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

/**
 *
 * @author Concept Analysis Branch 
 */
public class FileHandlerStage extends StagedProduction  {

/////////////////////////////////////////////////////////////////////
private BorderPane rootLayout;

private SimpleBooleanProperty done=new SimpleBooleanProperty(false);

CSVFileData results;
////////////////////////////////////////////////////////////////////

/**
 * Produce results of this Staged Production.
 * In this case return CSVFileData.
 * @return 
 */
@Override
	public CSVFileData produce(){
		FXMLFileHandlerController controller=new FXMLFileHandlerController();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(JavaFX_FileHandler.class.getResource("FXMLFileHandler.fxml"));
			this.rootLayout = loader.load();
			controller = (FXMLFileHandlerController)loader.getController();
			controller.setStage(this);
			//show scene containing root Layout.
			Scene scene = new Scene(rootLayout);
			this.setTitle("File Handler");
			this.setScene(scene);
			//Platform.setImplicitExit(false);
			this.showAndWait();
			
		} catch (IOException ex) {
			CabLog.logException(ex);
		}
return this.getResults(controller);
	}
	
	/**
	 * Close this stage.
	 */
@Override
	public void close(){
		Platform.runLater(()->{done.set(true);});		
	}
	
	/**
	 * returns a list of CSV records ready to be processed along with
	 * column names for aiding the processing.
	 * @param controller FXMLController
	 * @return CSVFileData
	 */
	private CSVFileData  getResults (FXMLFileHandlerController controller){
		File file=controller.getInputFile();
	try {
		final FileReader reader = new FileReader(file);
		CSVFormat format=this.getCSVFormat(controller);	
		final CSVParser parser = new CSVParser(reader,format);
		
		return new CSVFileData(format.getHeader(),parser.getRecords());
	} catch (FileNotFoundException ex) {
		CabLog.logException(ex);
	} catch (IOException ex) {
		CabLog.logException(ex);
	}
	//return empty collection, file reading failed
	return new CSVFileData(new String[0],new ArrayList<>());
	}
	
	/**
	 * Returns the CSV format for use by the CSV Parser.
	 * this will have the quote character, comment marker,
	 * file delimiter and header delimiter set. 
	 * @param controller FXML Controller
	 * @return CSVFormat
	 */
	private CSVFormat getCSVFormat(FXMLFileHandlerController controller){
		try{
		String fileDelim=controller.getFileDelim();
		String comment = controller.getCommentMarker();
		String quote = controller.getQuote();
		String header=controller.getHeader();
		String headerDelim=controller.getHeaderDelim();
		CSVFormat format = CSVFormat.DEFAULT;
		format.withDelimiter(fileDelim.charAt(0));
		format.withQuote(quote.charAt(0));
		format.withCommentMarker(comment.charAt(0));
		return format.withHeader(header.substring(1).split(headerDelim));
		}catch(NullPointerException ex){
						CabLog.logException(ex);
						}
		return CSVFormat.DEFAULT;
	}
	
}
