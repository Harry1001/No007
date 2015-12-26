package dataService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class _RMI {

	private static String ip = "127.0.0.1:8081";
	
	public static String getIP(){
		return ip;
	}
	
	public static void setIP() {
		File file = new File("RMI_.txt");
		try {
			@SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			ip = bufferedReader.readLine();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
