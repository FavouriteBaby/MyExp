package pers.pinvon.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public interface CallBack {
	public void doSomething(BufferedReader reader);
	public void doSomething(BufferedWriter writer);
}
