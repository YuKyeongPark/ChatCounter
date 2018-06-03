package edu.handong.csee.java.ChatCounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is DataReaderForCSV.
 * This class has getMessagesFromCSVFiles
 * getMessagesFromCSVFiles: get messages in CSV file into Hashmap by using fit form
 * 
 * @author YukyeongPark
 *
 */

//csv읽고 해시맵에 저장
public class DataReaderForCSV extends DataReader{
	public HashMap<String, ArrayList<Message>> getMessagesFromCSVFiles(File file) {
		
		Reader in;
		try {
			in = new FileReader(file);
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
			for(CSVRecord record : records) {
				String date = record.get(0).substring(11,16);	//get time part(hours and minutes)
				String user = record.get(1);
				String strMessage = record.get(2);
				
				Message message = new Message(date, record.get(0), user, strMessage, FromWhere.CSV);
			
				//Before we add new key in hashmap, checking hashmap whether hashmap already have the key
				if(!messages.containsKey(message.getId())) {
					//if hashmap have the key, put message
					messages.put(user, new ArrayList<Message>());
				}
				
				//if hashmap doesn't have message that we add, 
				if(!existingMessage(messages, message, FromWhere.CSV))
					//put new user and message that we add
					messages.get(message.getId()).add(message);
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return messages;
		
	}
}
