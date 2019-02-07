package edu.westminster.cachinglab;

import edu.westminstercollege.cmpt328.memory.*;

public class Experiment1 {

	public Experiment1() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		MainMemory aMem = new MainMemory("System A", 200);
		MainMemory bMem = new MainMemory("System B", 200);
		
		Cache aCache = Cache.builder().name("Cache A").drawingFrom(aMem).lineCount(256).accessTime(20).directMapping().build();
		Cache bCache = Cache.builder().name("Cache B").drawingFrom(bMem).lineCount(8).accessTime(25).setAssociative(2, ReplacementAlgorithm.LRU).build();
		
		MemorySystem aSys = new MemorySystem(aCache);
		MemorySystem bSys = new MemorySystem(bCache);
		
		IntArrayValue arrayA = aSys.allocateIntArray(6144);
		IntArrayValue arrayB = aSys.allocateIntArray(6144);
		IntArrayValue arrayC = aSys.allocateIntArray(6144);

		for (IntValue i = aSys.allocateInt(0); i.get() < arrayA.getLength(); i.increment())
			arrayB.set(i, arrayA.get(i));
		
		System.out.println(aSys.getTotalAccessTime());
		aSys.printStatistics();
		aSys.resetMemories();
		
		for (IntValue i = aSys.allocateInt(0); i.get() < arrayA.getLength(); i.increment())
			arrayC.set(i, arrayA.get(i));
		
		System.out.println(aSys.getTotalAccessTime());
		aSys.printStatistics();
		
	}

}
