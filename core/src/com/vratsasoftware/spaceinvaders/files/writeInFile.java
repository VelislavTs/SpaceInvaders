package com.vratsasoftware.spaceinvaders.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class writeInFile {

	int points;
	
	public writeInFile(int points) { 
		this.points = points;
	}
	
//	public static void main(String[] args) { 
//		createNewFile(150);
//	}
	public void createNewFile(int points) {
		
		addNewPlayerScore(points);
	}
	//TODO Make that shit work.
	//Think of an algorithm to sort the highscores
	//TODO IT SHIT, you have 1 fucking day left!
	public void addNewPlayerScore(int points) {
		File input = new File("C:\\Users\\velis\\Documents\\SpaceInvaders\\core\\src\\score.txt");
		Scanner inputFile = null;
		PrintStream outputFile = null;
		
		try {
			inputFile = new Scanner(input, "UTF-8");
			outputFile = new PrintStream(input, "UTF-8");
			outputFile.print(points + "gosho");
			System.out.println(input.getAbsolutePath());
		} catch (FileNotFoundException fnfe) {
			System.err.println(fnfe.getMessage());
		} catch (UnsupportedEncodingException u) {
			System.err.println(u.getMessage());
		} finally {
			if (null != inputFile) {
				inputFile.close();
			}
			if (null != outputFile) {
				outputFile.close();
			}
		}
	}
}
