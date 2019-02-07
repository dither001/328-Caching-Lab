package edu.westminster.cachinglab;

import java.util.Random;

import edu.westminstercollege.cmpt328.memory.*;

public class Experiment2 {
	
	public static void main(String...args) {
		MainMemory aMem = new MainMemory("System A", 200);
		MainMemory bMem = new MainMemory("System B", 200);

		Cache aCache = Cache.builder().name("Cache A").drawingFrom(aMem).lineCount(256).accessTime(20).directMapping()
				.build();
		Cache bCache = Cache.builder().name("Cache B").drawingFrom(bMem).lineCount(8).accessTime(25)
				.setAssociative(2, ReplacementAlgorithm.LRU).build();

		MemorySystem aSys = new MemorySystem(aCache);
		MemorySystem bSys = new MemorySystem(bCache);
		
		benchmark(aSys);
		
		benchmark(bSys);
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
}
