package edu.handong.csee.java.ChatCounter;	//package name
import java.io.*;	//import file class 

public class ChatCounterMain {	

	public static void main(String[] args) throws IOException {		
		int ch;
		
		//Creating File object
		File file1 = new File("C:\\HGU\\18-1 Java\\JavaProgramming-L13.csv");	//csv file
//		File file2 = new File("C:\\HGU\\18-1 Java\\JavaProgramming-L21.txt");	//txt file

		FileReader fileReader = new FileReader(file1);	//file read
		
		while((ch = fileReader.read()) != -1) { // 
				System.out.print((char)ch);
		}
		fileReader.close();	//

	}

}
