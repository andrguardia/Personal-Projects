package project;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class Train_Station extends Menu{
	/*This is the Train_Station class. It includes the declaration of the Station object, and all of its fields as can be seen below.
	 * In addition to this, this class includes all the accessor, mutator and constructor methods for the Train Station Object, so that every field can be accessed and modified. 
	 * Additionally, this class contains the method to read the csv called: readfile()
	 * This class also contains the createStation(), modifyStation() and search methods used in the program. 
	 */
	String Name; //This field is for the Station's name
	Double Latitude; //This field is for the Station's latitude
	Double Longitude; //This field is for the Station's longitude
	String Description;//This field is for the Station's description
	Boolean Wheelchair;//This field is for the Station's wheelchair access (true or false)
	Integer Red; //This field is for the Station's red line station order
	Integer Green;//This field is for the Station's green line station order
	Integer Blue;//This field is for the Station's blue line station order
	Integer Brown;//This field is for the Station's brown line station order
	Integer Purple;//This field is for the Station's purple line station order
	Integer Pink;//This field is for the Station's pink line station order
	Integer Orange;//This field is for the Station's orange line station order
	Integer Id; //This field is for the Station's unique identifier
	
	//Constructor Methods
	public Train_Station() {
		//This is the default constructor method for this class. It sets all the values of the Train Station object to their default state. 
		setName("Default");
		setLatitude(0.0);
		setLongitude(0.0);
		setDescription("Default Description");
		setWheelchair(false);
		setRed(-1);
		setGreen(-1);
		setBlue(-1);
		setBrown(-1);
		setPurple(-1);
		setPink(-1);
		setOrange(-1);
		setId(0);	
	}
	public Train_Station(String Name, Double Latitude, Double Longitude, String Description, Boolean Wheelchair, Integer Red, Integer Green, Integer Blue, Integer Brown, Integer Purple, Integer Pink, Integer Orange, Integer Id) {
		//This is the non-default constructor method. It is used to create a Train Station object with all the required fields in the object. 
		setName(Name);
		setLatitude(Latitude);
		setLongitude(Longitude);
		setDescription(Description);
		setWheelchair(Wheelchair);
		setRed(Red);
		setGreen(Green);
		setBlue(Blue);
		setBrown(Brown);
		setPurple(Purple);
		setPink(Pink);
		setOrange(Orange);
		setId(Id);		
	}
	
	//Access Methods
	public String getName() {
		//This is the Name accessor method, it is used to access the Train Station's Name
		return Name;
	}
	public Double getLatitude() {
		//This is the Latitude accessor method, it is used to access the Train Station's Latitude
		return Latitude;
	}
	public Double getLongitude() {
		//This is the Longitude accessor method, it is used to access the Train Station's Longitude
		return Longitude;
	}
	public String getDescription() {
		//This is the Description accessor method, it is used to access the Train Station'sDescription
		return Description;
	}
	public Boolean getWheelchair() {
		//This is the Wheelchair accessor method, it is used to access the Train Station's Wheelchair status
		return Wheelchair;
	}
	public Integer getRed() {
		//This is the station order accessor methhod for the red line
		return Red;
	}
	public Integer getGreen() {
		//This is the station order accessor methhod for the greenline
		return Green;
	}
	public Integer getBlue() {
		//This is the station order accessor methhod for the blue line
		return Blue;
	}
	public Integer getBrown() {
		//This is the station order accessor methhod for the brown line
		return Brown;
	}
	public Integer getPurple() {
		//This is the station order accessor methhod for the purple line
		return Purple;
	}
	public Integer getPink() {
		//This is the station order accessor methhod for the pinkline
		return Pink;
	}
	public Integer getOrange() {
		//This is the station order accessor methhod for the orange line
		return Orange;
	}	
	public Integer getId() {
		//This is the station unique identifier (ID) accessor method. Use it to return the station ID
		return Id;
	}

	//Mutator Methods
	public void setName(String Name) {
		//This method is used to modify the name of the station
		this.Name = Name;
	}
	public void setLatitude(Double Latitude) {
		//This method is used to modify the latitude of the station
		this.Latitude = Latitude;
	}
	public void setLongitude(Double Longitude) {
		//This method is used to modify the longitude of the station
		this.Longitude = Longitude;
	}
	public void setDescription(String Description) {
		//This method is used to modify the description of the station
		this.Description = Description;
	}
	public void setWheelchair(Boolean Wheelchair) {
		//This method is used to modify the wheelchair access of the station
		this.Wheelchair = Wheelchair;
	}
	public void setRed(Integer Red) {
		//This method is used to modify the Red Order of the station
		this.Red = Red;
	}
	public void setGreen(Integer Green) {
		//This method is used to modify the Green Order of the station
		this.Green = Green;
	}
	public void setBlue(Integer Blue) {
		//This method is used to modify the Blue Order of the station
		this.Blue = Blue;
	}
	public void setBrown(Integer Brown) {
		//This method is used to modify the Brown Order of the station
		this.Brown = Brown;
	}
	public void setPurple(Integer Purple) {
		//This method is used to modify the Purple Order of the station
		this.Purple = Purple;
	}
	public void setPink(Integer Pink) {
		//This method is used to modify the Pink Order of the station
		this.Pink = Pink;
	}
	public void setOrange(Integer Orange) {
		//This method is used to modify the Orange Order of the station
		this.Orange = Orange;
	}	
	public void setId(Integer Id) {
		//This method is used to modify the ID of the station
		this.Id = Id;
	}
	//toString Method
	public String toString() {
		//This method is used to convert the Train Station object to a String for ease of console printing. 
		return "|ID: " + getId() + "| Name: " + getName() + "| Latitude: " + getLatitude() + "| Longitude: " + getLongitude() + "| Description: " + getDescription() + "| Wheelchair: " + getWheelchair() + "| Red Line Order: " + getRed() + "| Green Line Order: " +getGreen() + "| Blue Line Order: " + getBlue() + "| Brown Line Order: " +getBrown() + "| Purple Line Order: " + getPurple() + "| Pink Line Order: " + getPink() + "| Orange Line Order: " + getOrange();
	}
	
	//Main Methods
	public static void readfile(String filepath) throws FileNotFoundException {
		//This method is used to import the CSV file with all the stations values. This file is then Scanned line by line, and each Station is saved as Train Station object within the Stations List ArrayList.
		File mypath = new File(filepath); //Create file object with path directed to grades.csv
		Scanner scnr = new Scanner(mypath); //Initialize scanner
		scnr.nextLine(); // Skip initial line with category data
		int id_count = 0;
		while(scnr.hasNextLine()){
			String line = scnr.nextLine(); //Prompt user for String input
			String[] data = line.split(",");
			Train_Station station = new Train_Station(data[0],Double.parseDouble(data[1]),Double.parseDouble(data[2]),data[3],Boolean.parseBoolean(data[4]),Integer.parseInt(data[5]),Integer.parseInt(data[6]),Integer.parseInt(data[7]),Integer.parseInt(data[8]),Integer.parseInt(data[9]),Integer.parseInt(data[10]),Integer.parseInt(data[11]),id_count);
			Station_List.add(station);
			id_count++;
		}
		scnr.close();
	}
	public static void choice() {
		/*This method is one of the main methods that make sure this code works. It prompts the user for a choice based on the menu_prompt method. 
		 * The menu_prompt method is a voided method with a printed prompt to save space.
		 * This method constantly prompts the user for a choice after done executing a branch of the switch clause.
		 * The switch clause determines what feature the user interacts with. When the user selects one of these branches, the accompanying method is called to execute the action desired by the user.
		 * After the user is done interacting with the branch, the program prompts the user to enter a new choice until -1 is selected, which will cause the program to exit. 
		 */
		
		//Prompts user
		menu_prompt();
		Scanner user_choice = new Scanner(System.in); //Reading data from System.in
		int mychoice = user_choice.nextInt();
		
		while (mychoice!=-1)//Exit case	
			{
			switch(mychoice) {
			case 1:
				System.out.println(" ");
				System.out.println("*****************************************************************************");
				System.out.println("Create a Station");
				createStation();
				break;
			case 2:
				System.out.println(" ");
				System.out.println("*****************************************************************************");
				System.out.println("Search for a Station");
				System.out.println(" ");
				search_menu();
				System.out.println("*****************************************************************************");
			
				break;
			case 3:
				System.out.println(" ");
				System.out.println("*****************************************************************************");
				System.out.println("Enter your Coordinates: ");
				System.out.println(" ");
				nearMe();
				System.out.println("*****************************************************************************");
				break;
			case 4:
				System.out.println(" ");
				System.out.println("*****************************************************************************");
				Route.directionsMenu();
				break;
			default:
				System.out.println("Incorrect Value, try again...");
			}
			menu_prompt();
			mychoice = user_choice.nextInt();
	}
	System.out.println("Program Terminated");//Final statement user sees when the program is terminated.
}
	
	
	public static void menu_prompt() {
		//This method will prompt the user the main menu, and is used in the choice() method to save code. 
		System.out.println("*****************************************************************************");
		System.out.println("Main Menu: ");
		System.out.println(" (1) - Create Station");
		System.out.println(" (2) - Search Station");
		System.out.println(" (3) - Find Nearest Station");
		System.out.println(" (4) - Directions");
		System.out.println("(-1) - Exit");
		System.out.println("*****************************************************************************");
	}
	public static void modify_prompt() {
		//This method will prompt the user the modification menu, and is used in the modify_menu() method to save code.
		//It essentially prints all the different fields that can be modified from a Train Station object once selected. 
		System.out.println(" (1) - Modify Name");
		System.out.println(" (2) - Modify Latitude");
		System.out.println(" (3) - Modify Longitude");
		System.out.println(" (4) - Modify Description");
		System.out.println(" (5) - Modify Wheelchair");
		System.out.println(" (6) - Modify Red Line Order");
		System.out.println(" (7) - Modify Green Line Order");
		System.out.println(" (8) - Modify Blue Line Order");
		System.out.println(" (9) - Modify Brown Line Order");
		System.out.println(" (10) - Modify Purple Line Order");
		System.out.println(" (11) - Modify Pink Line Order");
		System.out.println(" (12) - Modify Orange Line Order");
		System.out.println("(-1) - Back to Main Menu");
	}
	public static void search_menu() {
		/*This is the menu that is displayed after the user selects the option to "Search Station" from the main menu.
		 * It allows the user to search Stations by name, line and wheelchair access. 
		 * Again, using a while and switch statements combination, we are able to constantly prompt the user for another search until the user decides to go back to the main menu
		 * After a user selects to go back to main menu, the choice() method is called and the user is returned to the main screen
		 */
		
		System.out.println(" ");
		System.out.println(" (1) - Search by Name");
		System.out.println(" (2) - Search by Line and Wheelchair Access");
		System.out.println(" (-1) - Back to Menu");
		Scanner editStation_menu = new Scanner(System.in); //Initialize scanner input
		int choice = editStation_menu.nextInt();//Prompt user for choice
		
		while(choice!=-1) {
			switch(choice) {
			case 1:
				//Search by Name
				sequentialSearchbyName();
				modify_menu(); //Allows the user to modify and remove stations
				break;
			case 2:
				//Search by Line and Wheelchair Access
				sequentialSearch();
				modify_menu();//Allows the user to modify and remove stations
				break;

			default:
				System.out.println("Incorrect Value, try again..."); //Default case to avoid the program from exiting if the user messes up the input
				break;
			}
			
			choice = editStation_menu.nextInt(); //Get the user's net choice
		}
		choice();//Go back to the main menu after the user decides to exit
	}
	public static void modify_menu() {
		/*
		 * This method is the menu for the user to modify or remove a station. It follows from the user searching a station, in which the stations will all be displayed based on their ID numbers
		 * The user is prompted to enter the desired station's ID, to avoid naming confusions, the user can be certain he is selecting the right station
		 * After this, the user can either modify or remove the station from the list.
		 * This menu again uses the combination of a while statement and switch clauses to ensure a simple way of constantly prompting the user for their choice
		 * Once the user selects -1, this method calls search_menu() to navigate back to the search many and have the user perform another search
		 * 
		 */
		System.out.println(" ");
		System.out.println(" (1) - Modify Station");
		System.out.println(" (2) - Remove Station");
		System.out.println(" (-1) - Back");
		Scanner editStation_menu = new Scanner(System.in); //Initialize scanner input
		int choice = editStation_menu.nextInt();
		
		while(choice!=-1) {
			switch(choice) {
			case 1:
				//Modify Station
				modifyStation();
				break;
			case 2:
				//Remove Station
				removeStation();
				break;
			default:
				System.out.println("Incorrect Value, try again...");
				break;
			}
			System.out.println(" ");
			System.out.println(" (1) - Modify Station");
			System.out.println(" (2) - Remove Station");
			System.out.println(" (-1) - Back");
			choice = editStation_menu.nextInt();
		}
		search_menu();
	}
	
	
	//Create Station Methods
	public static void createStation() {
		/*
		 * This code will prompt the user to enter the station data in a sequential manner
		 *Then it will create a new Station object and store it in the stations object arraylist	
		 */
		System.out.println("To create a station, fill the following data points: ");
		Scanner newStation = new Scanner(System.in); //Initialize scanner
		System.out.println("Name: ");
		String name = newStation.next(); //Prompt for name
		System.out.println("Latitude: ");
		Double lat = Double.parseDouble(newStation.next());//Prompt for latitude
		System.out.println("Longitude: ");
		Double lon = Double.parseDouble(newStation.next());//Prompt for longitude
		System.out.println("Description: ");
		String description = newStation.next();//Prompt for description
		System.out.println("Wheelchair (true or false): ");
		newStation.nextLine();
		Boolean wheel = Boolean.parseBoolean(newStation.next());//Prompt for wheelchair access 
		System.out.println("Red Line Order (Enter -1 if n/a): ");
		int red = Integer.parseInt(newStation.next()); //Prompt for red line order
		System.out.println("Green Line Order (Enter -1 if n/a): ");
		int green = Integer.parseInt(newStation.next()); //Prompt for green line order
		System.out.println("Blue Line Order (Enter -1 if n/a): ");
		int blue = Integer.parseInt(newStation.next()); //Prompt for blue line order
		System.out.println("Brown Line Order (Enter -1 if n/a): ");
		int brown = Integer.parseInt(newStation.next()); //Prompt for brown line order
		System.out.println("Purple Line Order (Enter -1 if n/a): ");
		int purple = Integer.parseInt(newStation.next()); //Prompt for purple line order
		System.out.println("Pink Line Order (Enter -1 if n/a): ");
		int pink = Integer.parseInt(newStation.next()); //Prompt for pink line order
		System.out.println("Orange Line Order (Enter -1 if n/a): ");
		int orange = Integer.parseInt(newStation.next()); //Prompt for orange line order
		
		int id = Station_List.get(Station_List.size()-1).Id+1; //Assign Unique ID to the new station
		System.out.println("Station ID: "+id);
		//Call constructor method to create a new station object
		Train_Station station = new Train_Station(name,lat,lon,description,wheel,red,green,blue,brown,purple,pink,orange,id);
		Station_List.add(station);//Add station to stations list
		System.out.println(station.Name + " succesfully created!");
		System.out.println("*****************************************************************************");
		}
	
	
	// Search for a Station Methods
	public static void sequentialSearchbyName() {
		/*
		 * This method will prompt the user for a Station Name to search for, and parse the data sequentially to find stations whose name matches with the input
		 * When a station's name matches user input it is added to the result arraylist, where is is displayed back to the user.
		 */
		
		// Prompting user for their search name
		System.out.println("Enter Station Name: ");
		Scanner search = new Scanner(System.in); //Initialize scanner input
		String search_name = search.next(); // Prompt user for name of desired station
		search_name = search_name + search.nextLine();
		// Performs iterative search using the Stations Name
		int counter = 0;
		ArrayList<Train_Station> result = new ArrayList<Train_Station>();//Initialize object ArrayList to store all stations as objects
		//Sequential Search algorithm
		while(counter < Station_List.size()) {
			
			if (Station_List.get(counter).Name.equals(search_name)) {
				result.add(Station_List.get(counter)); //Populate results ArrayList
				counter++;
			}
			
			else {
				counter++;
			}
		}
		//Protect input from crashing code by providing safeguard to empty submission
		if (result.size()==0) {
			System.out.println(" ");
			System.out.println("Station not found");
			sequentialSearchbyName();
		}
		//Print the results arraylist using a while loop. 
		else {
			int count = 0;
			System.out.println(" ");
			while(count < result.size()) {
				//Print the results 
				System.out.println(result.get(count).toString());
			count++; 
			}
		}
	}
	public static void sequentialSearchbyLine_All() {
		/*
		 * This method is used to prompt the user to select what line he wants to search by
		 * This method returns all the stations within one line, regardless of their wheelchair accessibility features. 
		 */
		// Prompting user for their search name
		System.out.println("Enter Line Color ");
		Scanner search = new Scanner(System.in); //Initialize scanner input
		// This method performs iterative search using the Stations Name
		int counter=0;
		//Print prompt
		System.out.println(" (1) - Search for Stations in the Red Line");System.out.println(" (2) - Search for Stations in the Green Line");
		System.out.println(" (3) - Search for Stations in the Blue Line");System.out.println(" (4) - Search for Stations in the Brown Line");
		System.out.println(" (5) - Search for Stations in the Purple Line");System.out.println(" (6) - Search for Stations in the Pink Line");
		System.out.println(" (7) - Search for Stations in the Orange Line");System.out.println(" (-1) - Back to Search");
		
		int search_line = 0; // Prompt user for name of desired line
		//Initialize results and station id array
		ArrayList<Train_Station> result = new ArrayList<Train_Station>();
		
		//Sequential Search algorithm
		//Prompt user
		search_line = Integer.parseInt(search.next());
		switch(search_line) {
			case 1:
				// To Find All Red Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Red.equals(-1)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						counter++;}	
				}
			
			case 2:
				// To Find All Green Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Green.equals(-1)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						counter++;}	
					}
			case 3:
				// To Find All Blue Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Blue.equals(-1)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						counter++;}	
					}
				
			case 4:
				// To Find All Brown Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Brown.equals(-1)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						counter++;}	
					}
			case 5:
				// To Find All Purple Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Purple.equals(-1)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						counter++;}	
					}
			case 6:
				// To Find All Pink Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Pink.equals(-1)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						counter++;}	
					}
			case 7:
				// To Find All Orange Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Orange.equals(-1)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						counter++;}	
					}
			int count = 0;
			System.out.println(" ");
			while(count < result.size()) {
				//Print the results 
				System.out.println(result.get(count).toString());
			count++; 
			}
		}

	}
	public static void sequentialSearchbyLine_WheelchairAccess() {
		/*
		 * This method is used to prompt the user to select what line he wants to search by
		 * This method returns all the stations within one line, including only the stations with wheelchair access
		 */
		// Prompting user for their search name
		System.out.println("Enter Line Color ");
		Scanner search = new Scanner(System.in); //Initialize scanner input
		// This method performs iterative search using the Stations Name
		int counter=0;
		//Print prompt
		System.out.println(" (1) - Search for Stations in the Red Line");System.out.println(" (2) - Search for Stations in the Green Line");
		System.out.println(" (3) - Search for Stations in the Blue Line");System.out.println(" (4) - Search for Stations in the Brown Line");
		System.out.println(" (5) - Search for Stations in the Purple Line");System.out.println(" (6) - Search for Stations in the Pink Line");
		System.out.println(" (7) - Search for Stations in the Orange Line");System.out.println(" (-1) - Back to Search");
		
		int search_line = 0; // Prompt user for name of desired line
		//Initialize results and station id array
		ArrayList<Train_Station> result = new ArrayList<Train_Station>();
		ArrayList<Integer> result_id = new ArrayList<Integer>();//Initialize result id arraylist
		
		//Sequential Search algorithm
		search_line = Integer.parseInt(search.next());
		switch(search_line) {
			case 1:
				// To Find All Red Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Red.equals(-1)||Station_List.get(counter).Wheelchair.equals(false)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						result_id.add(counter);
						counter++;}	
				}
			
			case 2:
				// To Find All Green Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Green.equals(-1)||Station_List.get(counter).Wheelchair.equals(false)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						result_id.add(counter);
						counter++;}	
					}
			case 3:
				// To Find All Blue Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Blue.equals(-1)||Station_List.get(counter).Wheelchair.equals(false)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						result_id.add(counter);
						counter++;}	
					}
				
			case 4:
				// To Find All Brown Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Brown.equals(-1)||Station_List.get(counter).Wheelchair.equals(false)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						result_id.add(counter);
						counter++;}	
					}
			case 5:
				// To Find All Purple Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Purple.equals(-1)||Station_List.get(counter).Wheelchair.equals(false)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						result_id.add(counter);
						counter++;}	
					}
			case 6:
				// To Find All Pink Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Pink.equals(-1)||Station_List.get(counter).Wheelchair.equals(false)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						result_id.add(counter);
						counter++;}	
					}
			case 7:
				// To Find All Orange Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Orange.equals(-1)||Station_List.get(counter).Wheelchair.equals(false)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						result_id.add(counter);
						counter++;}	
					}
				}
		int count = 0;
		System.out.println(" ");
		while(count < result.size()) {
			//Print the results 
			System.out.println(result.get(count).toString());
		count++; 
		}
		}
	public static void sequentialSearchbyLine_NoWheelchairAccess() {
		/*
		 * This method is used to prompt the user to select what line he wants to search by
		 * This method returns all the stations within one line, including only the stations with NO wheelchair access
		 */
		// Prompting user for their search name
		System.out.println("Enter Line Color ");
		Scanner search = new Scanner(System.in); //Initialize scanner input
		// This method performs iterative search using the Stations Name
		int counter=0;
		//Prompting user for input
		System.out.println(" (1) - Search for Stations in the Red Line");System.out.println(" (2) - Search for Stations in the Green Line");
		System.out.println(" (3) - Search for Stations in the Blue Line");System.out.println(" (4) - Search for Stations in the Brown Line");
		System.out.println(" (5) - Search for Stations in the Purple Line");System.out.println(" (6) - Search for Stations in the Pink Line");
		System.out.println(" (7) - Search for Stations in the Orange Line");System.out.println(" (-1) - Back to Search");
		
		int search_line = 0; // Prompt user for name of desired line
		//Initialize results and station id array
		ArrayList<Train_Station> result = new ArrayList<Train_Station>();
		
		//Sequential Search algorithm
		search_line = Integer.parseInt(search.next());
		switch(search_line) {
			case 1:
				// To Find All Red Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Red.equals(-1)||Station_List.get(counter).Wheelchair.equals(true)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						counter++;}	
				}
			
			case 2:
				// To Find All Green Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Green.equals(-1)||Station_List.get(counter).Wheelchair.equals(true)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						counter++;}	
					}
			case 3:
				// To Find All Blue Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Blue.equals(-1)||Station_List.get(counter).Wheelchair.equals(true)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						counter++;}	
					}
				
			case 4:
				// To Find All Brown Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Brown.equals(-1)||Station_List.get(counter).Wheelchair.equals(true)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						counter++;}	
					}
			case 5:
				// To Find All Purple Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Purple.equals(-1)||Station_List.get(counter).Wheelchair.equals(true)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						counter++;}	
					}
			case 6:
				// To Find All Pink Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Pink.equals(-1)||Station_List.get(counter).Wheelchair.equals(true)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						counter++;}	
					}
			case 7:
				// To Find All Orange Line Stations
				while(counter < Station_List.size()) {
					
					if (Station_List.get(counter).Orange.equals(-1)||Station_List.get(counter).Wheelchair.equals(true)) {
						counter++;}
					else {
						result.add(Station_List.get(counter));
						counter++;}	
					}
				}
		int count = 0;
		System.out.println(" ");
		while(count < result.size()) {
			//Print the results 
			System.out.println(result.get(count).toString());
		count++; 
		}
		}
	public static void sequentialSearch(){
		/*This method prompts the user to choose for the preferred search method, and uses a switch case to direct the logic for the searches. 
		 */
	
		System.out.println("*****************************************************************************");
		System.out.println("(1) All Stations: ");
		System.out.println("(2) Wheelchair Accessible Stations: ");
		System.out.println("(3) Non-Wheelchair Accessible Stations: ");
		System.out.println("Enter your preference below: ");
		Scanner search = new Scanner(System.in); //Initialize scanner input
		int search_wheelchair = Integer.parseInt(search.next()); // Prompt user for name of desired station
		switch(search_wheelchair) {
		
		case 1: 
			// All Stations, proceed to enter Line Color
			System.out.println("Displaying all stations from the following Line: ");
			sequentialSearchbyLine_All();
			break;
		case 2: 
			//Wheelchair = true and proceed to enter Line Color
			System.out.println("Displaying all Wheelchair Accessible stations from the following Line: ");
			sequentialSearchbyLine_WheelchairAccess();
			break;
		case 3:
			//Wheelchair = false and proceed to enter Line Color
			System.out.println("Displaying all Non-Wheelchair Accessible stations from the following Line: ");
			sequentialSearchbyLine_NoWheelchairAccess();
			break;
		}
		// This method performs iterative search using the Stations Name
		
	

	}
	
	public static void modifyStation() {
		//This method is used to modify an existing Train Station 
		//Prompt user to enter the station ID as shown after the user searches for a station
			System.out.println("*****************************************************************************");
			System.out.println(" "); System.out.println("To modify a station, please enter its ID as shown above: ");
			Scanner modStation = new Scanner(System.in); //Initialize scanner System.out.println("ID: ");
			modStation.useDelimiter("\n");//Change Delimiter so we can have multi worded strings and avoid an input mismatch exception
			int id = modStation.nextInt();
			// The code below will match the entered ID with the Station ID to ensure the right Station is being modified
			int id_iterator = 0;
			while (id!=Station_List.get(id_iterator).Id) {
				id_iterator++;
			}
			//The program will now prompt the user to enter the modifications for each field of the Train Station object
			//Prompt user with the Modification Menu
			modify_prompt();
			int choice = modStation.nextInt();
			while(choice!=-1) {
				switch(choice) {
				case 1:// Now modify the name of the station using the setName mutator method
					System.out.println("*****************************************************************************");
					System.out.println("Modify Name: ");
					String name = modStation.next();
					Station_List.get(id_iterator).setName(name);
					System.out.println("*****************************************************************************");break;
				case 2:// Now modify the name of the station using the setLatitude mutator method
					System.out.println("*****************************************************************************");
					System.out.println("Modify Latitude: ");
					Double lat = Double.parseDouble(modStation.next());
					Station_List.get(id_iterator).setLatitude(lat);
					System.out.println("*****************************************************************************");break;
				case 3:// Now modify the name of the station using the setLongitude mutator method
					System.out.println("*****************************************************************************");
					System.out.println("Modify Longitude: ");
					Double lon = Double.parseDouble(modStation.next());
					Station_List.get(id_iterator).setLongitude(lon);
					System.out.println("*****************************************************************************");break;
				case 4:// Now modify the name of the station using the setDescription mutator method
					System.out.println("*****************************************************************************");
					System.out.println("Modify Description: ");
					String description = modStation.next();
					Station_List.get(id_iterator).setDescription(description);
					System.out.println("*****************************************************************************");break;
				case 5:// Now modify the name of the station using the setWheelchair mutator method
					System.out.println("*****************************************************************************");
					System.out.println("Modify Wheelchair (true/false): ");
					Boolean wheelchair = Boolean.parseBoolean(modStation.next());
					Station_List.get(id_iterator).setWheelchair(wheelchair);
					System.out.println("*****************************************************************************");break;
				case 6:// Now modify the name of the station using the setRed mutator method
					System.out.println("*****************************************************************************");
					System.out.println("Modify Red Line Order: ");
					int red = Integer.parseInt(modStation.next());
					Station_List.get(id_iterator).setRed(red);
					System.out.println("*****************************************************************************");break;
				case 7:// Now modify the name of the station using the setGreen mutator method
					System.out.println("*****************************************************************************");
					System.out.println("Modify Green Line Order: ");
					int green = Integer.parseInt(modStation.next());
					Station_List.get(id_iterator).setGreen(green);
					System.out.println("*****************************************************************************");break;
				case 8:// Now modify the name of the station using the setBlue mutator method
					System.out.println("*****************************************************************************");
					System.out.println("Modify Blue Line Order: ");
					int blue = Integer.parseInt(modStation.next());
					Station_List.get(id_iterator).setBlue(blue);
					System.out.println("*****************************************************************************");break;
				case 9:// Now modify the name of the station using the setBrown mutator method
					System.out.println("*****************************************************************************");
					System.out.println("Modify Brown Line Order: ");
					int brown = Integer.parseInt(modStation.next());
					Station_List.get(id_iterator).setBrown(brown);System.out.println("*****************************************************************************");break;
				case 10:// Now modify the name of the station using the setPurple mutator method
					System.out.println("*****************************************************************************");
					System.out.println("Purple Line Order: ");
					int purple = Integer.parseInt(modStation.next());
					Station_List.get(id).setPurple(purple);System.out.println("*****************************************************************************");break;
				case 11:// Now modify the name of the station using the setPink mutator method
					System.out.println("*****************************************************************************");
					System.out.println("Pink Line Order: ");
					int pink = Integer.parseInt(modStation.next());
					Station_List.get(id_iterator).setPink(pink);System.out.println("*****************************************************************************");break;
				case 12:// Now modify the name of the station using the setOrange mutator method
					System.out.println("*****************************************************************************");
					System.out.println("Orange Line Order: ");
					int orange = Integer.parseInt(modStation.next());
					Station_List.get(id_iterator).setOrange(orange);System.out.println("*****************************************************************************");break;
				default://Default response for incorrect input
					System.out.println("*****************************************************************************");
					System.out.println("Incorrect Value, try again...");System.out.println("*****************************************************************************");break;}
				System.out.println("Station Succesfully Modified!"); choice = modStation.nextInt();}}
	public static void removeStation() {
		//This method is used to remove a particular station from the list based on its Id.
		//The program will prompt the user for an ID and delete that station from the Stations List
		System.out.println("*****************************************************************************");
		System.out.println(" "); System.out.println("To remove a station, please enter its ID as shown above: ");
		Scanner removeStation = new Scanner(System.in); //Initialize scanner System.out.println("ID: ");
		int id = removeStation.nextInt();
		int id_iterator = 0;
		while (id!=Station_List.get(id_iterator).Id) {
			id_iterator++;
		}
		Train_Station remove = Station_List.get(id_iterator);
		Station_List.remove(remove); //Remove Station
		System.out.println(remove.Name + " has been successfully removed");
	}
	//Find Nearest Station Method
	
	public static void nearMe() {
		/*
		 * This method prompts the user for his latitude and longitude, and uses the Haversine Formula for geographic distances on spheres to calculate the distance between the entered coordinates.
		 * Once this distance parameter is known, it is saved into a distance arraylist and a sorting function is used to minimize the distance and return the closest station to the user
		 */
	// Prompting user for their search name
		Scanner coordinates = new Scanner(System.in); //Initialize scanner input
		System.out.println("Latitude: ");
		Double lat1 = Double.parseDouble(coordinates.next());
		System.out.println("Longitude: ");
		Double lon1 = Double.parseDouble(coordinates.next());
		//The distance between the user entered coordinates and the coordinates from each station from the map will be calculated using the Haversine Formula
		//Δlat = lat2− lat1
		//Δlong = long2− long1
		//a = sin²(Δlat/2) + cos(lat1).cos(lat2).sin²(Δlong/2)
		//c = 2atan2(√a, √(1−a))
		//d = Rc
		int R = 6371; // Earth's Radius
		int counter = 0;
		ArrayList<Train_Station> result = new ArrayList<Train_Station>();//Initialize object ArrayList to store all resulting stations as objects
		ArrayList<Double> distance = new ArrayList<Double>();//Initialize Distance ArrayList
		//Calculating Distance between all points and the user input coordinates
		while(counter < Station_List.size()) {
			Double lat2 = Station_List.get(counter).getLatitude();
			Double lon2 = Station_List.get(counter).getLongitude();
			Double delta_lat = Math.toRadians(lat2-lat1);
			Double delta_lon = Math.toRadians(lon2-lon1);
			Double a = Math.sin(delta_lat/2)*Math.sin(delta_lat/2) + Math.cos(lat1)*Math.cos(lat2)*Math.sin(delta_lon/2)*Math.sin(delta_lon/2);
			Double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			Double d = R*c;
			distance.add(d);
			counter++;
			}
		// Finding the minimum distance value in the List
		Double minimum_distance = 0.0;
		int i = 0;
		int min = 0;
		//Minimizer algorithm for distance
		while(i<Station_List.size()){
			if(distance.get(i)<distance.get(min)) {
				min = i;
			}
			i++;
		}
		minimum_distance = distance.get(min);
		
		int id_iterator = 0;
		while (minimum_distance!=distance.get(id_iterator)) {
			id_iterator++;
		}
		// Printing Closest Station Data
		System.out.println(" ");
		System.out.println("The Closest Station to your coordinates: ( "+lat1+" , "+lon1+" ) is:");
		System.out.println(Station_List.get(id_iterator).toString());
	}
	
	
	
	

		
		
	}
	//Directions Method
	


