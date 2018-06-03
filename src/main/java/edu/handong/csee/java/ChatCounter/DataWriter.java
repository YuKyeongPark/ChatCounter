package edu.handong.csee.java.ChatCounter;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * This is DataWriter class.
 * This class has a writeAFile method.
 * writeAFile method: write list into file

 * 
 * @author YukyeongPark
 *
 */

//write list that is returned in counter to csv file
public class DataWriter {
	
	public void writeAFile(ArrayList<String> lines, String targetFileName){
		try {
			File file = new File(targetFileName);
			FileOutputStream fos = new FileOutputStream(file);
			//DataOuptStream can not work with files by itself so it get help with FileOuputStream
			DataOutputStream dos = new DataOutputStream(fos);
				
			for(String line: lines) {
				//write data in lines
				//getBytes():returns a Unicode string managed in Java as a byte array in charset with arguments
				dos.write((line+"\n").getBytes());
			}
			dos.close();
			fos.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
			
	}

}
