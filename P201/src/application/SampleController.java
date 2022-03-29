package application;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.glass.ui.Window.Level;
import com.sun.javafx.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
public class SampleController {
    @FXML
    private Button pdf;
    @FXML
    private Button Email;
    @FXML
    private MenuItem saveAs;
    @FXML
    private MenuItem excelLoad;
    @FXML
    private MenuItem save;
    @FXML
    private MenuItem tempL;
    @FXML
    private ChoiceBox<?> Tags;
    @FXML
    public TextArea TxtArea;
    @FXML
    private Button Savepdf;
    @FXML
    private TableView<?> Table;
    @FXML
    TableColumn title;

    @FXML
    void LoadExcel(ActionEvent event) throws IOException {



    }
    @FXML
    void TempLoader(ActionEvent event) throws IOException {
    	FileChooser fileChooser = new FileChooser();
    	FileChooser.ExtensionFilter Text= new FileChooser.ExtensionFilter("Text files(*.txt)", "*.txt");
    	fileChooser.getExtensionFilters().add(Text);
    	File file= fileChooser.showOpenDialog(null);
    	fileChooser.setTitle("Load Template");
    	       FileReader reader =new FileReader(file);
    	   BufferedReader br=new BufferedReader(reader);
    	   Scanner sc =new Scanner(br);
    	   String line = null;
    	   while(sc.hasNextLine()){
    		   line += sc.nextLine()+"\n";
    	   }
    	   TxtArea.setText(line);
    }
    @FXML
    void SaveTemp(ActionEvent event) throws FileNotFoundException {

    }
    @FXML
    void SaveTempAs(ActionEvent event) throws FileNotFoundException {

    	FileChooser fileChooser = new FileChooser();
    	FileChooser.ExtensionFilter Text= new FileChooser.ExtensionFilter("Pdf files(*.pdf)", "*.pdf");
    	fileChooser.getExtensionFilters().add(Text);
    	File file= fileChooser.showSaveDialog(null);
    	fileChooser.setTitle("Save Template");
    	if(file!=null){
    		File dir =file.getParentFile();
    		fileChooser.setInitialDirectory(dir);
    	            PrintWriter writer;
    	            writer = new PrintWriter(file);
    	            writer.println(TxtArea.getText());
    	            writer.close();
    	            if(!(event.getSource()==tempL)){
    	        		saveAs.setDisable(true);
    	        	}
    	}
    }
    @FXML
    void SavePdf(ActionEvent event) throws IOException {
    	  FXMLLoader fxmlLoader = new  FXMLLoader(getClass().getResource("Pdf.fxml"));
    	  Parent root1 = (Parent) fxmlLoader.load();
    	    Stage stage = new Stage();
    	    stage.initModality(Modality.APPLICATION_MODAL);
    	    stage.setTitle(" Save pdf");
    	    stage.setScene(new Scene(root1));
    	    stage.setResizable(false);
    	    stage.show();
    }
    @FXML
    void OpenEmail(ActionEvent event) throws IOException {
    	 FXMLLoader fxmlLoader = new  FXMLLoader(getClass().getResource("SignIn.fxml"));
   	  Parent root1 = (Parent) fxmlLoader.load();
   	    Stage stage = new Stage();
   	    stage.initModality(Modality.APPLICATION_MODAL);
   	    stage.setTitle("Log in");
   	    stage.setScene(new Scene(root1));
   	    stage.setResizable(false);
   	    stage.show();
    }
    //Send Email tools
    @FXML
    private Button Send;
    @FXML
    private TextField userMail;
    @FXML
    private PasswordField Pass;
    @FXML
    void SendMail(ActionEvent event) {
    	String AccountName=userMail.getText();
		String Password=Pass.getText();
		String to="khalid.habbash.kah@gmail.com";
		String subject="testing";
		String messageContent = TxtArea.getText();
				SendEmailOffice365 AAA=	new SendEmailOffice365(AccountName,Password,to,subject,messageContent);
		AAA.sendEmail();
    }
    // Save PDF tools

    @FXML
    void PdfSaved(ActionEvent event) throws DocumentException, IOException {
    	FileChooser fileChooser = new FileChooser();
    	FileChooser.ExtensionFilter Text= new FileChooser.ExtensionFilter("PDF files(*.pdf)", "*.pdf");
    	fileChooser.getExtensionFilters().add(Text);
    	File file= fileChooser.showSaveDialog(null);
    	fileChooser.setTitle("Save pdf");
    	if(file!=null){
    		File dir =file.getParentFile();
    		fileChooser.setInitialDirectory(dir);
    		OutputStream files = new FileOutputStream(dir);
    	          Document doc = new Document();
    	          PdfWriter.getInstance(doc,files);
    	          doc.open();
    	          doc.add(new Paragraph(TxtArea.getText()));
    	          doc.close();
    	          files.close();
    	}
    }
    @FXML
    void ShowCont(ActionEvent event) throws IOException {

    	String[][] arr = getContacts(event);
    	ObservableList<String[]> data = FXCollections.observableArrayList();
		data.addAll(Arrays.asList(arr));
		String[] tags = arr[0];

		for(int i=0;i<tags.length;i++){
			Table.getItems().addAll();
		}
		for (int i=0; i<tags.length;i++){
			TableColumn columnTitle = new TableColumn(tags[i]);
			final int columnNo = i;

			columnTitle.setCellValueFactory(new
			Callback<TableColumn.CellDataFeatures<String[],String>, ObservableValue<String>>() {

			public ObservableValue<String>
			call(TableColumn.CellDataFeatures<String[],String> param) {
			return new SimpleStringProperty((param.getValue()[columnNo]));
			}
			});

			columnTitle.setResizable(true);
			title.getColumns().add(columnTitle);
			}
    }

    public String[][] getContacts(ActionEvent event) throws IOException{

    	FileChooser fileChooser = new FileChooser();
    	FileChooser.ExtensionFilter Text= new FileChooser.ExtensionFilter("CSV files(*.csv)", "*.csv");
    	fileChooser.getExtensionFilters().add(Text);
    	File file= fileChooser.showOpenDialog(null);
    	fileChooser.setTitle("Load Contact");
    	       FileReader reader =new FileReader(file);
    	   BufferedReader br=new BufferedReader(reader);
    	   Scanner sc =new Scanner(br);

    	ArrayList<String[]> row = new ArrayList<String[]>();

		String[] line;

		do {
			int i = 0;
			while (sc.hasNextLine()) {
				line = sc.nextLine().split(",");
				row.add(i, line);
				i++;
			}

		} while (sc.hasNextLine());
		String[][] arr = row.toArray(new String[row.size()][]);

		return arr;


    }
}