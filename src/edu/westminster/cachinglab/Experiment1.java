package edu.westminster.cachinglab;

import edu.westminstercollege.cmpt328.memory.*;

public class Experiment1 {

	public Experiment1() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		MainMemory aMem = new MainMemory("System A", 200);
		MainMemory bMem = new MainMemory("System B", 200);

		Cache aCache = Cache.builder().name("Cache A").drawingFrom(aMem).lineCount(256).accessTime(20).directMapping()
				.build();
		Cache bCache = Cache.builder().name("Cache B").drawingFrom(bMem).lineCount(8).accessTime(25)
				.setAssociative(2, ReplacementAlgorithm.LRU).build();

		MemorySystem aSys = new MemorySystem(aCache);
		MemorySystem bSys = new MemorySystem(bCache);

		IntArrayValue arrayA = aSys.allocateIntArray(6144);
		IntArrayValue arrayB = aSys.allocateIntArray(6144);
		IntArrayValue arrayC = aSys.allocateIntArray(6144);

		IntArrayValue brrayA = bSys.allocateIntArray(6144);
		IntArrayValue brrayB = bSys.allocateIntArray(6144);
		IntArrayValue brrayC = bSys.allocateIntArray(6144);

		/*
		 * SYSTEM A
		 */
		for (IntValue i = aSys.allocateInt(0); i.get() < arrayA.getLength(); i.increment())
			arrayB.set(i, arrayA.get(i));

		System.out.println(aSys.getTotalAccessTime());
		System.out.println();
		aSys.printStatistics();
		aSys.resetMemories();

		System.out.println();
		for (IntValue i = aSys.allocateInt(0); i.get() < arrayA.getLength(); i.increment())
			arrayC.set(i, arrayA.get(i));

		System.out.println(aSys.getTotalAccessTime());
		System.out.println();
		aSys.printStatistics();

		/*
		 * SYSTEM B
		 */
		for (IntValue i = bSys.allocateInt(0); i.get() < brrayA.getLength(); i.increment())
			brrayB.set(i, brrayA.get(i));

		System.out.println(bSys.getTotalAccessTime());
		System.out.println();
		bSys.printStatistics();
		bSys.resetMemories();

		System.out.println();
		for (IntValue i = bSys.allocateInt(0); i.get() < brrayA.getLength(); i.increment())
			brrayC.set(i, brrayA.get(i));

		System.out.println(bSys.getTotalAccessTime());
		System.out.println();
		bSys.printStatistics();

	}

}
