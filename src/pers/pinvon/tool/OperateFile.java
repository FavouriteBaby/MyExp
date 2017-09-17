package pers.pinvon.tool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OperateFile {
	public static void readFile(String filePath, CallBack call){
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(filePath));
			call.doSomething(reader);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(reader != null){
				try{
					reader.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
}
