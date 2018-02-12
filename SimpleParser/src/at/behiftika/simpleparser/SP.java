package at.behiftika.simpleparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class SP {
	
	/**Gives you the value of the key in the .sp file */
	public static String getValue(File file, String key) {
		if(isFileValid(file)) {
			
			BufferedReader r = null;
			try {
				r = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			String s;
			String[] k = new String[1];
			int lineCount = getLineCount(file);
			k[0] = "";
			
			for(int i = 0; i < lineCount; i++) {
				try {
					
					s = Files.readAllLines(file.toPath()).get(i);
					
					k = s.split(": ");
					
					if(k.length == 2) {
						
						if(k[0].equals(key)) {
							r.close();
							return k[1];
							
						}
						
					}else if(k.length > 2){
						error(ErrorType.unvalidCharacter, file);
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			error(ErrorType.keyNotFound, file);
			
		}else {
			error(ErrorType.unvalidFile, file);
		}
		return null;
	}
	
	/** Returns the line count of the file  */
	private static int getLineCount(File file) {
		int a = 0;
		
		try {
		BufferedReader r = new BufferedReader(new FileReader(file));
		
			while(r.readLine() != null) {
				a++;
			}
			
			r.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	private static boolean isFileValid(File file) {
		return file.exists() && file.isFile() && file.canRead();
	}
	
	private static void error(ErrorType e, File file) {
		if(e == ErrorType.fileNotFound) {
			System.out.println("Error parsing '"+ file.getPath() + "'! File not found!");
		}
		
		if(e == ErrorType.unvalidCharacter) {
			System.out.println("Error parsing '"+ file.getPath() + "'! Unvalid character");
		}
		
		if(e == ErrorType.keyNotFound) {
			System.out.println("Error parsing '"+ file.getPath() + "'! Key not found");
		}
	}
}
