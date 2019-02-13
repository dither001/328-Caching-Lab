package edu.westminster.cachinglab;

import java.util.Random;

import edu.westminstercollege.cmpt328.memory.Cache;
import edu.westminstercollege.cmpt328.memory.DoubleArrayValue;
import edu.westminstercollege.cmpt328.memory.MainMemory;
import edu.westminstercollege.cmpt328.memory.MemorySystem;
import edu.westminstercollege.cmpt328.memory.ReplacementAlgorithm;

public class Experiment3 {

	public static void main(String[] args) {
		MainMemory aMem = new MainMemory("System A", 200);
		MainMemory bMem = new MainMemory("System B", 200);
		MainMemory cMem = new MainMemory("System C", 200);
		MainMemory dMem = new MainMemory("System D", 200);
		MainMemory eMem = new MainMemory("System E", 200);
		
		
		Cache L4Cache = Cache.builder().name("Cache B").drawingFrom(bMem).lineCount(1024).accessTime(64)
				.setAssociative(4, ReplacementAlgorithm.LRU).build();
		
		
		
		Cache L3Cache = Cache.builder().name("Cache B").drawingFrom(bMem).lineCount(1024).accessTime(64)
				.setAssociative(4, ReplacementAlgorithm.LRU).build().builder().name("Cache C").drawingFrom(cMem).lineCount(256).accessTime(32)
				.setAssociative(4, ReplacementAlgorithm.LRU).build();
		
		
		
		Cache L2Cache = Cache.builder().name("Cache B").drawingFrom(bMem).lineCount(1024).accessTime(64)
				.setAssociative(4, ReplacementAlgorithm.LRU).build().builder().name("Cache C").drawingFrom(cMem).lineCount(256).accessTime(32)
				.setAssociative(4, ReplacementAlgorithm.LRU).build().builder().name("Cache D").drawingFrom(dMem).lineCount(64).accessTime(16)
				.setAssociative(4, ReplacementAlgorithm.LRU).build();
		
		
		
		Cache L1Cache = Cache.builder().name("Cache B").drawingFrom(bMem).lineCount(1024).accessTime(64)
				.setAssociative(4, ReplacementAlgorithm.LRU).build().builder().name("Cache C").drawingFrom(cMem).lineCount(256).accessTime(32)
				.setAssociative(4, ReplacementAlgorithm.LRU).build().builder().name("Cache D").drawingFrom(dMem).lineCount(64).accessTime(16)
				.setAssociative(4, ReplacementAlgorithm.LRU).build().builder().name("Cache E").drawingFrom(eMem).lineCount(16).accessTime(8)
				.setAssociative(4, ReplacementAlgorithm.LRU).build();		
		
		
		MemorySystem aSys = new MemorySystem(aMem);
		MemorySystem bSys = new MemorySystem(L4Cache);
		MemorySystem cSys = new MemorySystem(L3Cache);
		MemorySystem dSys = new MemorySystem(L2Cache);
		MemorySystem eSys = new MemorySystem(L1Cache);

		
	
		quick(aSys);
		quick(bSys);
		quick(cSys);
		quick(dSys);
		quick(eSys);
		
		
	
	}

	public static void quick(MemorySystem mem) {
		Random random = new Random();
		
		
		DoubleArrayValue arr = mem.allocateDoubleArray(20000); 
		
		Quicksort q = new Quicksort(mem);
		for(int i = 0; i < 20000; i++) {
			arr.set(i, random.nextDouble()); 
			
			
		}
		q.sort(arr);
		System.out.println(mem.getTotalAccessTime() + "\n");
	}
	
	
	
	
	
	
}
