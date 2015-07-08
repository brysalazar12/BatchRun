/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batchrun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class BatchRun {

	private String defaultConfig = "program.dat";
	private String currentDir = "";
	private String configFile = "";
	private String defaultContent = "# '#' is comment. List the program here you want to batch run separated by new line.";
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		BatchRun batchRun = new BatchRun();
		batchRun.run(args);
	}

	public void run(String[] args) {
		this.currentDir = System.getProperty("user.dir") + File.separatorChar;

		// check if config file is specified
		if(args.length < 1) 
			this.configFile = this.createDefaultConfig();
		else
			this.configFile = this.createConfig(args[0]);
		this.executeAllProgram(this.configFile);
	}

	protected String createConfig(String configFile) {
		File file = new File(configFile);
		if(!file.exists())
			this.writeDefaultMessage(file);
		return configFile;
	}

	protected void executeAllProgram(String configFile) {
		File file = new File(configFile);
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			Runtime runtime = Runtime.getRuntime();

			try {
				while((line = reader.readLine()) != null) {
					line = line.trim();
					try {
						if(!line.startsWith("#") && !line.equals(""))
							runtime.exec(line);
					} catch(Exception e) {
						System.err.println("Failed to run: " + line);
					}
				}
			} catch (IOException ex) {
				Logger.getLogger(BatchRun.class.getName()).log(Level.SEVERE, null, ex);
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(BatchRun.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Create default config file if not exist
	 */
	protected String createDefaultConfig() {
		File file = new File(this.currentDir + this.defaultConfig);
		if(!file.exists())
			this.writeDefaultMessage(file);

		return this.currentDir + this.defaultConfig;
	}
	
	/**
	 * Write default message
	 * @param file 
	 */
	protected void writeDefaultMessage(File file )
	{
		BufferedWriter writer = null;
		try {
			if(file.createNewFile()) {
				writer = new BufferedWriter(new FileWriter(file));
				writer.write(this.defaultContent);
				writer.close();
			}
		} catch (IOException ex) {
			Logger.getLogger(BatchRun.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
