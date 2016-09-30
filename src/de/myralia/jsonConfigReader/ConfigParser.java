package de.myralia.jsonConfigReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

public class ConfigParser {

	public static Object parseString(String _json, Class<?> _type)
	{		
		Gson g = new Gson();
		Object ret = g.fromJson(_json, _type);			
		return ret;		
	}
	
	public static Object parseFile(String _filename , Class<?> _type)
	{		
		try(BufferedReader br = new BufferedReader(new FileReader(_filename))) {
		    StringBuilder sb = new StringBuilder();
		    
		    String line = br.readLine();
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    
		    return ConfigParser.parseString(sb.toString(),_type);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;			
		} catch (IOException e) {
			e.printStackTrace();
			return null;			
		}	
	}
	
}
