package pers.pinvon.packet;

import java.io.BufferedReader;
import java.util.HashMap;

import pers.pinvon.tool.CallBack;
import pers.pinvon.tool.OperateFile;

public class PacketAnalysis {
	private HashMap<String, Integer> srcIP = new HashMap<String, Integer>();
	private HashMap<String, Integer> desIP = new HashMap<String, Integer>();
	private HashMap<String, Integer> srcPort = new HashMap<String, Integer>();
	private HashMap<String, Integer> desPort = new HashMap<String, Integer>();
	private double size = 0.0f;
	
	public PacketAnalysis(){
		size = 0;
		srcIP.clear();
		desIP.clear();
		srcPort.clear();
		desPort.clear();
	}
	
	public void count(){
		OperateFile.readFile("data/result_0.txt", new CallBack(){
			public void doSomething(BufferedReader reader){
				dealRow(reader);
			}
		});
	}
	
	private void dealRow(BufferedReader reader){
		String str = null;
		try{
			while((str = reader.readLine()) != null){
				String[] row = str.split(",");
				countEveryValue(srcIP, row[0]);
				countEveryValue(desIP, row[1]);
				if(!row[2].equals(' ')){
					countEveryValue(srcPort, row[2]);
					countEveryValue(desPort, row[3]);
				}
				size += 1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void countEveryValue(HashMap<String, Integer> hashMap, String str){
		if(!hashMap.containsKey(str)){
			hashMap.put(str, new Integer(1));
		}else{
			int k = hashMap.get(str).intValue()+1;
			hashMap.put(str, new Integer(k));
		}
	}
	
	public static void main(String[] args){
		PacketAnalysis a = new PacketAnalysis();
		a.count();
	}
}
