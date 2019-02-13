package edu.westminster.cachinglab;

import java.util.Random;

import edu.westminstercollege.cmpt328.memory.*;

public class Experiment2 {
	
	public static void main(String...args) {
		MainMemory aMem = new MainMemory("System A", 200);
		MainMemory bMem = new MainMemory("System B", 200);
		MainMemory cMem = new MainMemory("System C", 200);
		
		Cache aCache = Cache.builder().name("Cache A").drawingFrom(aMem).lineCount(256).accessTime(25)
				.setAssociative(2, ReplacementAlgorithm.LRU).build();
		Cache bCache = Cache.builder().name("Cache B").drawingFrom(bMem).lineCount(256).accessTime(25)
				.setAssociative(8, ReplacementAlgorithm.LRU).build();
		Cache cCache = Cache.builder().name("Cache C").drawingFrom(cMem).lineCount(256).accessTime(25)
				.fullyAssociative(ReplacementAlgorithm.LRU).build();
				
		
		MemorySystem aSys = new MemorySystem(aCache);
		MemorySystem bSys = new MemorySystem(bCache);
		MemorySystem cSys = new MemorySystem(cCache);
		
		benchmark(aSys);
		
		benchmark(bSys);
		
		benchmark(cSys);
		
		
		
		benchmark1(aSys);
		
		benchmark1(bSys);
		
		benchmark1(cSys);
		
		
	}
	
	
	
	private static void benchmark(MemorySystem sys) {
        Random random = new Random();
        IntArrayValue data = sys.allocateIntArray(100000);
        IntValue i = sys.allocateInt();
        IntValue tmp = sys.allocateInt();
        IntValue a = sys.allocateInt();
        IntValue b = sys.allocateInt();

        for (i.set(0); i.get() < 1000000; i.increment()) {
            a.set(random.nextInt(data.getLength()));
            b.set(random.nextInt(data.getLength()));

            tmp.set(data.get(a));
            data.set(a, data.get(b));
            data.set(b, tmp);
        }

        System.out.printf("\nTotal access time: %,d\n", sys.getTotalAccessTime());
        sys.printStatistics();
    }
	
	private static void benchmark1(MemorySystem sys) {
        Random r = new Random();
        IntArrayValue data = sys.allocateIntArray(100000);
        IntValue i = sys.allocateInt();
        IntValue tmp = sys.allocateInt();
        IntValue a = sys.allocateInt();
        IntValue b = sys.allocateInt();

        for (i.set(0); i.get() < 1000000; i.increment()) {
            a.set(data.getLength());
            b.set(data.getLength());

            tmp.set(data.get(a));
            data.set(a, data.get(b));
            data.set(b, tmp);
        }

        System.out.printf("\nTotal access time: %,d\n", sys.getTotalAccessTime());
        sys.printStatistics();
    }
}
