package edu.handong.csee.java.ChatCounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//txt읽고 hashmap에 저장
//line 읽고 메세지 폼에 맞춰 저장해서 user가 존재하면 key가 존재하면 그 키에 메세지 저장
public class DataReaderForTXT extends DataReader {
	public HashMap<String, ArrayList<Message>> getMessagesFromTXTFiles(File file) {

		String thisLine = "";
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(file), "UTF-8"));
			br.readLine();
			br.readLine();
			br.readLine();	//ignore three line
			
			int currentYear = -1;
			int currentMonth = -1;
			int currentDay = -1;
			String currentDate = "";
			while((thisLine = br.readLine()) != null) {
				if(thisLine.matches("-+\\s[0-9]+.\\s([0-9]+.\\s[0-9]+.+")) {
					
					String pattern = "-+\\s([0-9]+).\\s([0-9]+).\\s+([0-9]+).+";
					Pattern r = Pattern.compile(pattern);
					
					Matcher m = r.matcher(thisLine);
					
					if(m.find()) {
						currentYear = Integer.parseInt(m.group(1));
						currentMonth = Integer.parseInt(m.group(2));
						currentDay = Integer.parseInt(m.group(3));
					}
					
					currentDate = getCurrentDate(currentYear, currentMonth, currentDay);
					continue;
					
				}else if(thisLine.matches(".+,\\s(.+)\\s([0-9]+),\\s([0-9]+)\\s.+")) {
					String pattern = ".+,\\s(.+)\\s([0-9]+),\\s([0-9]+)\\s.+";
					Pattern r = Pattern.compile(pattern);
					
					Matcher m = r.matcher(thisLine);
					
					if(m.find()) {
						currentMonth = getMonthNumberFromString(m.group(1));
						currentDay = Integer.parseInt(m.group(2));
						currentYear = Integer.parseInt(m.group(3));				
					}
					currentDate = getCurrentDate(currentYear, currentMonth, currentDay);
					continue;
				}
				
				if(thisLine.matches("(\\[.+\\])\\s(\\[.+\\]\\s.+")) {
					String pattern = "\\[(.+)\\]\\s(\\[.+\\])\\s(.+)";
					Pattern r = Pattern.compile(pattern);
					Matcher m = r.matcher(thisLine);
					
					if(m.find()) {
						String id = m.group(1);
						String time = m.group(2);
						String strMessage = m.group(3);

						Message message = new Message(getDateTime(currentDate, time), id, strMessage);
						
						if(!messages.containsKey(message.getId())) {
							messages.put(id, new ArrayList<Message>());
						}
						
//						if(!existingMessage(messages, message))
//							messages.get(message.getId()).add(message);
						}
					}
				}
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		return messages;

		}
}
