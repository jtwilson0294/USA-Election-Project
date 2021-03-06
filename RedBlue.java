/*
Tommy Wilson
CS 1233
Created January 30, 2015
Submitted February 15, 2015
This program expands upon White.java and draws the region specified either in red or blue to denote the
majority vote of that particular year. This accepts two command line arguments, region and year
*/

import java.util.Scanner;
import java.io.*;
import java.awt.Color;

public class RedBlue {
	public static final String PATH_TO_FILE   = "purple/";
	public static final String FILE_EXTENSION = ".txt";
	
	public static void main(String[] args) throws FileNotFoundException { 
		
		//Make sure two command arguments are provided
		if (args.length < 2) { 
			System.out.println("Usage: java RedBlue <region> <year>");
			System.exit(-1);
		}
		
		//Reading the file for geometric points
		Scanner scan = new Scanner(new File(PATH_TO_FILE + args[0] + FILE_EXTENSION));
		
		//Reading min x/y 
		double minX = scan.nextDouble(); 
		double minY = scan.nextDouble(); 
		System.out.println(minX + " " + minY);
		
		//Reading max x/y
		double maxX = scan.nextDouble(); 
		double maxY = scan.nextDouble(); 
		System.out.println(maxX + " " + maxY);
		
		//Set window size
		StdDraw.setCanvasSize(800, 600); //any particular value or standard?
		
		//Scale the window
		StdDraw.setXscale(minX, maxX);
		StdDraw.setYscale(minY, maxY);
		
		//Reading number of subregions
		int numSubRegions, numPoints;
		String subRegionName, regionName;
		
		//how many times to loop through each subregion to collect geometric points
		numSubRegions = scan.nextInt(); 
		
		//hold newlines and empty strings from the file
		String junk;
		
		
		for (int i = 0; i < numSubRegions; i++) {
			junk = scan.nextLine();   		 //consume extra new line after subregion value
			junk = scan.nextLine();          //scan again for empty line
			subRegionName = scan.nextLine(); //find the subregion being scanned (ex. State or county)
			regionName = scan.nextLine();	 //find the region the subregion belongs to (ex. USA or a particular State)
			
			System.out.println(subRegionName + " " + regionName); //test
			
			//Reading number of points
			numPoints = scan.nextInt();
			double[] longs = new double [numPoints];
			double[] lats = new double [numPoints];
			
			//find coordinates
			for (int j = 0; j < numPoints; j++) {
				//read longitude
				longs[j] = scan.nextDouble();
				//read latitude
				lats[j] = scan.nextDouble();
				//System.out.println(longs[j] + " " + lats[j]); //test - commented out for debugging clarity
				
			}
			
			//variables to hold poll data values
			int rep = 0; //Republican
			int dem = 0; //Democrat
			int ind = 0; //Independent
			
			//read another file and match subregion to poll data
			Scanner sc = new Scanner(new File(PATH_TO_FILE + regionName + args[1] + FILE_EXTENSION));
			junk = sc.nextLine(); //remove first line of poll data file
			
			while (sc.hasNextLine()) { //loop as long as there is another line to loop through and scan
				
				String pollData = sc.nextLine(); //scan for poll data
				String[] pD = pollData.split(","); //split data by comma
				
				//System.out.println(pD[0] + " " + subRegionName + " " + "TEST"); //test
				
				if (pD[0].equals(subRegionName)) { //if the first word in the scanned line (subregion) 
												   //is equal to the subregion scanned, parse those values
												   //to rep, dem, and ind respectively
					rep = Integer.parseInt(pD[1]); 
					dem = Integer.parseInt(pD[2]); 
					ind = Integer.parseInt(pD[3]);
					
					//compare which value is greater to color either red, blue, or green
					if (rep > dem && rep > ind) {
					StdDraw.setPenColor(StdDraw.RED); //change fill color
					StdDraw.filledPolygon(longs, lats);
					}
					
					else if (dem > rep && dem > ind) {
						StdDraw.setPenColor(StdDraw.BLUE);
						StdDraw.filledPolygon(longs, lats);
					}
					
					else {
						StdDraw.setPenColor(StdDraw.GREEN);
						StdDraw.filledPolygon(longs, lats);
					}
					
					System.out.println(rep + " " + dem + " " + ind);
				}
				
				//if the subregion scanned contains the first word of the line from poll data, then parse
				//those values to rep, dem, and ind (ex. Acadia Parish, LA and Acadia, LA)
				else if (subRegionName.toLowerCase().contains(pD[0].toLowerCase())) {
					rep = Integer.parseInt(pD[1]);
					dem = Integer.parseInt(pD[2]);
					ind = Integer.parseInt(pD[3]);
					
					//compare which value is greater to color either red, blue, or green
					if (rep > dem && rep > ind) {
					StdDraw.setPenColor(StdDraw.RED); //change fill color
					StdDraw.filledPolygon(longs, lats);
					}
					
					else if (dem > rep && dem > ind) {
						StdDraw.setPenColor(StdDraw.BLUE);
						StdDraw.filledPolygon(longs, lats);
					}
					
					else {
						StdDraw.setPenColor(StdDraw.GREEN);
						StdDraw.filledPolygon(longs, lats);
					}
					
					System.out.println(rep + " " + dem + " " + ind);
				}
				
				//if the first word of the line from poll data contains the subregion scanned, then parse
				//those values to rep, dem, and ind (ex. Miami-Dade, FL and Dade, FL)
				else if (pD[0].toLowerCase().contains(subRegionName.toLowerCase())) {
					rep = Integer.parseInt(pD[1]);
					dem = Integer.parseInt(pD[2]);
					ind = Integer.parseInt(pD[3]);
					
					//compare which value is greater to color either red, blue, or green
					if (rep > dem && rep > ind) {
					StdDraw.setPenColor(StdDraw.RED); //change fill color
					StdDraw.filledPolygon(longs, lats);
					}
					
					else if (dem > rep && dem > ind) {
						StdDraw.setPenColor(StdDraw.BLUE);
						StdDraw.filledPolygon(longs, lats);
					}
					
					else {
						StdDraw.setPenColor(StdDraw.GREEN);
						StdDraw.filledPolygon(longs, lats);
					}
					
					System.out.println(rep + " " + dem + " " + ind);
				}
			}
			
			//Will outline the subregions (states and/or counties) in WHITE to match the assignment
			//description picture. I prefer black outlines from White.java
			StdDraw.setPenColor(StdDraw.WHITE); //outline color - change color to preference
			StdDraw.polygon(longs, lats);
			
		}
	}
}