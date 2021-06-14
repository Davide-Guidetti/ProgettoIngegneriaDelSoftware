package it.unibo.tw.web.examples;

import java.util.concurrent.CopyOnWriteArrayList;

public class Slave extends Thread{

	CopyOnWriteArrayList array;
	
	public Slave(CopyOnWriteArrayList <String> provaArray)
	{
		this.array = provaArray;
	}
	
	
	public void run() {
		array.add("sono lo schiavo n "+ Thread.currentThread().getName());
	}
	
}
