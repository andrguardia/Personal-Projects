package project;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Menu {
	/*
	 * This is the main class, it is the one that runs the entire application. It includes the readfile(String) method to determine where to read the file from
	 * The choice() method which is the entire program with references to all available features
	 */
	
	static ArrayList<Train_Station> Station_List = new ArrayList<Train_Station>();//Initialize object ArrayList to store all stations as objects
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Train_Station.readfile("src/project/CTAStops.csv");
		Train_Station.choice();
		//Train_Station.choice();
		
		}

}
