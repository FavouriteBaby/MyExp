/**
 * only calculate every field and their entropy
 * does not get the integer entropy
 */

package pers.pinvon.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;

import pers.pinvon.hash.SimHash;
import pers.pinvon.packet.PacketAnalysis;
import pers.pinvon.tool.CallBack;
import pers.pinvon.tool.OperateFile;

public class Test {
	
	public static void main(String[] args){
		String path = "";
		PacketAnalysis objPA = new PacketAnalysis();
		StringBuilder srcIP = new StringBuilder("");
		StringBuilder desIP = new StringBuilder("");
		StringBuilder srcPort = new StringBuilder("");
		StringBuilder desPort = new StringBuilder("");
		
		for(int nIndex = 0; nIndex < 45; ++nIndex){
			path = "data/Week1/" + nIndex + ".txt";
			objPA.count(path);
				
			HashMap<String, Integer> srcIPHashMap = objPA.getSrcIPList();
			HashMap<String, Integer> desIPHashMap = objPA.getDesIPList();
			HashMap<String, Integer> srcPortHashMap = objPA.getSrcPortList();
			HashMap<String, Integer> desPortHashMap = objPA.getDesPortList();
				
			//calc entropy
			double srcIPEntropy = objPA.calcEntropy(srcIPHashMap);
			double desIPEntropy = objPA.calcEntropy(desIPHashMap);
			double srcPortEntropy = objPA.calcEntropy(srcPortHashMap);
			double desPortEntropy = objPA.calcEntropy(desPortHashMap);
				
			//debug
			System.out.println(srcIPEntropy);
			System.out.println(desIPEntropy);
			System.out.println(srcPortEntropy);
			System.out.println(desPortEntropy);
			String info = path;
			OperateFile.writeFile("data/Week1/entropy.txt", true, new CallBack(){
				public void doSomething(BufferedReader reader){}
				public void doSomething(BufferedWriter writer){
					StringBuilder str = new StringBuilder();
					str.append(String.format("%f, %f, %f, %f", srcIPEntropy, desIPEntropy, srcPortEntropy, desPortEntropy));
					try{
						writer.write(info);
						writer.newLine();
						writer.write(str.toString());
						writer.newLine();
					}catch(IOException e){
						e.printStackTrace();
					}
					
				}
			});
				
			final double scale = 2.48f;
				
			srcIP.append(" " + String.format("%.1f", srcIPEntropy/scale));
			desIP.append(" " + String.format("%.1f", desIPEntropy/scale));
			srcPort.append(" " + String.format("%.1f", srcPortEntropy/scale));
			desPort.append(" " + String.format("%.1f", desPortEntropy/scale));
		}
		
		System.out.println(srcIP);
		SimHash srcIPHash = new SimHash(srcIP.toString(), 32);
		System.out.println(srcIPHash.getStrSimHash() + " " + srcIPHash.getStrSimHash().bitLength());
		
		System.out.println(desIP);
		SimHash desIPHash = new SimHash(desIP.toString(), 32);
		System.out.println(desIPHash.getStrSimHash() + " " + desIPHash.getStrSimHash().bitLength());
		
		System.out.println(srcPort);
		SimHash srcPortHash = new SimHash(srcPort.toString(), 32);
		System.out.println(srcPortHash.getStrSimHash() + " " + srcPortHash.getStrSimHash().bitLength());
		
		System.out.println(desPort);
		SimHash desPortHash = new SimHash(desPort.toString(), 32);
		System.out.println(desPortHash.getStrSimHash() + " " + desPortHash.getStrSimHash().bitLength());
		
		System.out.println(srcIPHash.hammingDistance(desIPHash));
		System.out.println(srcIPHash.hammingDistance(srcPortHash));
		System.out.println(srcIPHash.hammingDistance(desPortHash));
		System.out.println(desIPHash.hammingDistance(srcPortHash));
		System.out.println(desIPHash.hammingDistance(desPortHash));
		System.out.println(srcPortHash.hammingDistance(desPortHash));
	}
}
