package edu.westminster.cachinglab;

import java.io.IOException;

import edu.westminstercollege.cmpt328.memory.MainMemory;
import edu.westminstercollege.cmpt328.memory.MemorySystem;

public class Experiment4 {

	public static void main(String[] args) throws IOException {
		
		MemorySystem.CoreI7 main = new MemorySystem.CoreI7();
		
		main.setDefault(main);
		
		
		Image im = Image.load("image.jpg");
		
		
		
		for(int i = 0; i < im.getHeight(); i++) {
			for(int j = 0; j < im.getWidth(); j++) {
				Pixel pix = im.getPixelAt(i, j);
				int blue = pix.getBlue();
				int red = pix.getRed();
				int green = pix.getGreen();
				int all = (blue + red + green)/3;
				pix.setBlue(all);
				pix.setRed(all);
				pix.setGreen(all);
			}
			
		}
		try {
			im.save("newImage");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(main.getTotalAccessTime());
	}

}
