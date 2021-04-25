package mc322.lab05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
	private String dataSource;
	private String[] commands;

	public CSVReader() {				//Construtor
	 	this.commands = null;
		this.dataSource = null;
	 }

	public String getDataSource() { //acessa o local do aquivo
		return dataSource;
	 }

	 public void setDataSource(String dataSource) { //recebe o local do arquivo e lê o arquivo
		 this.dataSource = dataSource;
		 if (dataSource == null) {
		commands = null;
	} else
		readCSV();
	}


	 public String[] requestCommands() { //acessa o commands lido pelo readCSV
		return commands;
	 }

	private void readCSV() { //lê o arquivo
		try {
		 	BufferedReader file = new BufferedReader(new FileReader(dataSource));

			String line = file.readLine();
			if (line != null) {
			 commands = line.split(",");
		 	line = file.readLine();
		}
		file.close();
		} catch (IOException erro) {
		 erro.printStackTrace();
	 	}
	}
}
