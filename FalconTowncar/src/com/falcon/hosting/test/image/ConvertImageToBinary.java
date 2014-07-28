package com.falcon.hosting.test.image;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class ConvertImageToBinary {
	public static void main(String[] args) throws IOException{
		List<String> files = new ArrayList<String>();
		
		files.add("hello.jpg");
		files.add("world.jpg");
		files.add("hello");
		files.add(".DS_Store");
		
		for (String file: files){
			if (file.contains(".jpg")){
				System.out.println(file);
			}
		}
	}
}
