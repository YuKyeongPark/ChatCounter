package edu.handong.csee.java.ChatCounter;	//package name
import java.io.BufferedReader;
import java.io.File;	//import File class
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;	//import ArrayList class
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//reads all chat messages in path.csv and prints text
public class DataReader {
	//main method
	HashMap<String, ArrayList<Message>> messages = new HashMap<String, ArrayList<Message>>();

	public HashMap<String, ArrayList<Message>> getData(String path){

		File[] dataFiles = getFiles(path);
		
		for(File file:dataFiles) {
			//if file isn't .csv or .txt, ignore that files
			if(!(file.getName().endsWith(".csv") || file.getName().endsWith(".txt"))) {
				continue;
			}
			
			System.out.println("Read a file: " + file.getName());
			
			//if file name ends with .csv, calls a getMessagesFromCSVFiles
			if(file.getName().endsWith(".csv")) {
				DataReaderForCSV drCSV = new DataReaderForCSV();
				messages = drCSV.getMessagesFromCSVFiles(file);
			}
			
			//if file name ends with .txt, calls a getMessagesFromTXTFiles
			if(file.getName().endsWith(".txt")) {
				DataReaderForTXT drTXT = new DataReaderForTXT();
				messages = drTXT.getMessagesFromTXTFiles(file);
			}
		}
		
		return messages;
		
//		(1) get Directory
//				File myDir = getDirectory(strDir);
//				
//				//(2) put instances in file array. return type is file type array
//				File[] files = getListOfFilesFromDirectory(myDir);
//
//				//(3) read contents in files
//				ArrayList<String> messages = readFiles(files);
//				
//				return messages;
		
	}
	
 	public String getDateTime(String currentDate, String time) {
 		
 		//[오전 10:20]
 		String pattern = "\\[(.+)\\s([0-9]+):([0-9]+)\\]";
 		Pattern r = Pattern.compile(pattern);
 		Matcher m = r.matcher(time);
 		
 		String ampm = "", hour = "", minute = "";
 		if(m.find()) {
 			ampm = m.group(1);
 			hour = m.group(2);
 			minute = m.group(3);
 			
 			if(ampm.equals("오전")) {
 				if(hour.equals("12"))
 					return formatFor2Digit(0)
 							+ ":" + formatFor2Digit(Integer.parseInt(minute));
 				return formatFor2Digit(Integer.parseInt(hour))
 						+ ":" + formatFor2Digit(Integer.parseInt(minute));
 			}
 			
 			// 오후12:00 is an unique case
 			if(hour.equals("12"))
 				return formatFor2Digit(Integer.parseInt(hour))
 						+ ":" + formatFor2Digit(Integer.parseInt(minute));
 			return formatFor2Digit(Integer.parseInt(hour)+12)
 					+ ":" + formatFor2Digit(Integer.parseInt(minute));
 		}
 		
 		// [12:23 AM]
 		pattern = "\\[([0-9]+):([0-9]+)\\s(.+)\\]";
 		r = Pattern.compile(pattern);
 		m = r.matcher(time);
 		
 		if(m.find()) {
 			ampm = m.group(3);
 			hour = m.group(1);
 			minute = m.group(2);
 			
 			if(ampm.equals("AM")) {
 				if(hour.equals("12"))
 					return formatFor2Digit(0)
 							+ ":" + formatFor2Digit(Integer.parseInt(minute));
 				
 				return formatFor2Digit(Integer.parseInt(hour))
 						+ ":" + formatFor2Digit(Integer.parseInt(minute));
 			}
 			
 			//PM 12:00 is unique case
 			if(hour.equals("12"))
 				return formatFor2Digit(Integer.parseInt(hour))
 						+ ":" + formatFor2Digit(Integer.parseInt(minute));
 			return formatFor2Digit(Integer.parseInt(hour)+12)
 					+ ":" + formatFor2Digit(Integer.parseInt(minute));
 			
 		}
 		
 		return "";
 	}
 	
 	
 	protected String getCurrentDate(int currentYear, int currentMonth, int currentDay) {
 		return "";
 	}
 	
 	
 	private String formatFor2Digit(int number) {	
 	//if 
 		if(number < 10)
 			return "0" + number;
 		return number+"";
 	}
 	
 	private File[] getFiles(String path) {
 		File dirPath = new File(path);
 		
 		return dirPath.listFiles();
 	}
 	
// 	protected boolean existingMessage(HashMap<String, ArrayList<Message>> messagesToCheck,
// 			Message message,
// 			FromWhere fromWhere) {
// 		
// 		ArrayList<Message> messages = messagesToCheck.get(message.getId());
// 		
// 		for(Message messageInMessages: messages) {
// 			String datetimeOFMessageInMessages = messageInMessages.getFromWhere().equals(FromWhere.CSV) && message
// 		}
// 	}
}
