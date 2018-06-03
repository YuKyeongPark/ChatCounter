package edu.handong.csee.java.ChatCounter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * This class is ChatCounter 
 * This class has main, run, countMessages, parseOptions, createOptions, printHelp, sortByValue method
 * countMessages method: counting messages.
 * parseOptions method: 
 * createOptions method: creating option using in cmd 
 * printHelp: automatically generate the help statement
 * sortByValue: sorting message in descending order

 * 
 * @author YukyeongPark
 *
 */

//Count chat message 
public class ChatCounter {
	String inputPath;
	String outputPath;
	boolean help;
	
	public void main(String[] args) {
		ChatCounter counter = new ChatCounter();
		counter.run(args);
	}
	
	public void run(String[] args) {
		//Options constructor
		Options options= createOptions();
		
		if(parseOptions(options, args)) {
			if(help) {
				printHelp(options);
				return;
			}
			DataReader dataReader = new DataReader();
			HashMap<String, ArrayList<Message>> messages = dataReader.getData(inputPath);
			
			//counting message
			HashMap<String, Integer> counter = countMessages(messages);
			
			//sorting value
			List<String> ids = sortByValue(counter);
			
			ArrayList<String> linesToWrite = new ArrayList<String>();
			for(String key:ids) {
				linesToWrite.add(key + "," + counter.get(key));
			}
			
			DataWriter writer = new DataWriter();
			writer.writeAFile(linesToWrite, outputPath);
		
		}
	}
	
	//counting number of messages 
	private HashMap<String, Integer> countMessages(HashMap<String, ArrayList<Message>> messages){
		HashMap<String, Integer> counter = new HashMap<String, Integer>();
		
		//
		for(String key:messages.keySet()) {
			counter.put(key, messages.get(key).size());	//size means the number of messages
		}
		
		return counter;
	}
	
	//parsing stage
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();
		
		try {
			
			CommandLine cmd = parser.parse(options, args);
			
			inputPath = cmd.getOptionValue("i");
			outputPath = cmd.getOptionValue("o");
			help = cmd.hasOption("h");
		} catch (Exception e) {
			printHelp(options);
			return false;
		}
		
		return true;
	}
	
	//add options
	private Options createOptions() {
		Options options = new Options();
		
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set a path of a directory that contains chat data files.")
				.hasArg()
				.argName("A directory path")
				.required()
				.build());
		
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set a file path name where the count results are saved.")
				.hasArg()
				.argName("A file path")
				.required()
				.build());
		
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Help")
				.build());
		
		return options;
	}
	
	//automatically generate the help statement
	private void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		String header = "Chat Counter program";
		String footer = "\nPlease report issues at~";
		formatter.printHelp("ChatCounter", header, options, footer, true);
	}
	
	public List<String> sortByValue(final HashMap<String, Integer> map){
		List<String> list = new ArrayList<String>();
		list.addAll(map.keySet());

		Collections.sort(list, new Comparator<Object>() {
			
			@SuppressWarnings("unchecked")
			public int compare(Object o1, Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);
				
				return ((Comparable<Object>) v1).compareTo(v2);
			}
		});
		Collections.reverse(list);
		return list;	//sotred list
		
	}
	
	
	
}


