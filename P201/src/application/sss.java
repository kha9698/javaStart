package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class sss {

	// public void TempLoader() throws IOException {
	//// FileChooser fileChooser = new FileChooser();
	//// FileChooser.ExtensionFilter Text= new FileChooser.ExtensionFilter("Text
	// files(.txt)", ".txt");
	// fileChooser.getExtensionFilters().add(Text);
	// File file= fileChooser.showOpenDialog(null);
	//
	// FileReader reader = new FileReader(file);
	// BufferedReader br = new BufferedReader(reader);
	// Scanner sc = new Scanner(br);
	// String line = null ;
	// ArrayList<String> a = new ArrayList<String>();
	//
	// while((line = br.readLine()) != null)
	// a.add(line);
	// System.out.println(line);
	// }

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(new FileInputStream("Contacts.csv"));

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
		System.out.println(Arrays.deepToString(arr) + "\n");
		
		
		
		
		ObservableList<String[]> data = FXCollections.observableArrayList();
		data.addAll(Arrays.asList(arr));
		
		String[] tags = arr[0];
		
		for (int i=0; i<tags.length;i++){
			TableColumn columnTitle = new TableColumn(tags[i]);
			final int columnNo = i;
			columnTitle.setCellValueFactory(new
			Callback<TableColumn.CellDataFeatures<String[],String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String>
			call(TableColumn.CellDataFeatures<String[],String> param) {
			return new SimpleStringProperty((param.getValue()[columnNo]));
			}
			});
			//‫القيم‬ ‫حسب‬ ‫متغير‬ ‫بعرض‬ ‫عمود‬ ‫ال‬ ‫لجعل‬
			columnTitle.setResizable(true);
			
		}
		
		
		
		
		
		// for (int i = 0; i < row.size(); i++) {

		// FileChooser fc = new FileChooser();
		// FileChooser.ExtensionFilter csv = new
		// FileChooser.ExtensionFilter("CSV files(.csv)", ".csv");
		// fc.getExtensionFilters().add(csv);
		// File file = fc.showOpenDialog(null);
		//
		// Scanner sc = new Scanner(new FileInputStream(file));
		//
		// ObservableList<String[]> contacts =
		// FXCollections.observableArrayList();
		//
		// String[] singleContact = new String[12];
		// sc.nextLine();
		//
		// contacts.addAll(singleContact);
		//
		// for (int j = 0; j < contacts.size(); j++)
		// for (int i = 0; i < singleContact.length; i++) {
		// if (sc.hasNextLine())
		// contacts.add(singleContact = sc.nextLine().split(","));
		//
		// }
		// for (String part : contacts.get(2)) {
		// System.out.println(part);
		// }
	}
}