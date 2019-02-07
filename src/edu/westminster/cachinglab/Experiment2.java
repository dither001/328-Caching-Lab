package edu.westminster.cachinglab;

import java.util.Random;

import edu.westminstercollege.cmpt328.memory.*;

public class Experiment2 {
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
