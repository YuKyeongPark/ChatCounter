package edu.handong.csee.java.ChatCounter;	//package name
import java.io.File;	//import File class
import java.util.ArrayList;	//import ArrayList class

//DataReader class reads all chat messages
public class DataReader {

	public ArrayList<String> getData(String strDir) {	//method get data about file directory
		
		//(1) get Directory
		File myDir = getDirectory(strDir);
		
		//(2)
		File[] files = getListOfFilesFromDirectory(myDir);

		//(3)
		ArrayList<String> messages = readFiles(files);
		
		return messages;
		
	}
	
	private File getDirectory(String strDir) {//before we read file directory reader
		//File class has data and action
		File myDirectory = new File(strDir);	//File instance
		return myDirectory;	//return myDirectory
	
	}

	private File[] getListOfFilesFromDirectory(File dataDir) {	//we know length of array so array is better
		//listFiles():returns an array of abstract pathnames denoting the files in the directory denoted by this abstract pathname. 
		for(File file:dataDir.listFiles()) {
			System.out.println(file.getAbsolutePath());	//all the path of files directory
		}
		return dataDir.listFiles();	
	}
	
 	private ArrayList<String> readFiles(File[] files) { //method, read contents of file
 		ArrayList<String> messages = new ArrayList<String>();
 		
 		//some logics that read files must be here
 		
 		
 		return messages;	//return messages that include reading file
 	}
}
