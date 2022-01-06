package project;
import java.util.Scanner;
import project.sorting_line.BlueSorter;
import project.sorting_line.BrownSorter;
import project.sorting_line.GreenSorter;
import project.sorting_line.OrangeSorter;
import project.sorting_line.PinkSorter;
import project.sorting_line.PurpleSorter;
import project.sorting_line.RedSorter;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.File;  
import java.io.FileWriter;
import java.io.IOException;  

public class Route extends Train_Station{
//This class is created for the Route object, and it is directly inherited from the Train_Station class. We add the fields start and end as two distinct stations
//Create static myroute variable as an instance of the route, that is to be filled out with user data
Train_Station start;
Train_Station end;
static Route myroute;
static String file_name; //Declare file name as a static variable to be filled by user input and cross referenced between methods inside the Route class

//Basic Methods
	public Route() {
		//Default constructor method
		super();
	}
	public Route(Train_Station start, Train_Station end) {
		//Custom constructor method
		setStart(start);
		setEnd(end);
	}
	//Mutator Methods
	public void setStart(Train_Station start) {
		//Mutator Method for Start Station
		this.start = start;
	}
	public void setEnd(Train_Station end) {
		//Mutator Method for End Station
		this.end = end;
	}
	//Access Methods
	public Train_Station getStart() {
		//Use this method to Access Station Start
		return start;
	}
	public Train_Station getEnd() {
		//Use this method to Access Ending Station
		return end;
	}
	//ToString Method
	public String toString() {
		//This method converts the route to a string
		return "Your Route: || Start: " + start.getName() + " || End: "+ end.getName();
	}
	//equals method
	public boolean equals(Train_Station other) {
		//This method compares one station to another to determine whether it is equal to another one
		if(start.equals(other)) {
			return true;
		}
		else {
			return false;
		}
	}

//Two Node Connections
	public static void red_green_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is on both lines and saves it as a Train Station Object.
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the connection using the first colored line
		 * After this, the program generates a route between the connection and the ending station using the second colored line's route generator
		 * 
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getRed()!=-1 && myroute.end.getGreen()!=-1) {
			//Starting at a Red Line Station, Ending at a Green Line Station
			System.out.println("Start on Red Line, End on Green Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getGreen()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = redRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = greenRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Green Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}
		
		else if (myroute.start.getGreen()!=-1 && myroute.end.getRed()!=-1) {
			//Starting at a Green Line Station, Ending at a Red Line Station
			System.out.println("Start on Green Line, End on Red Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getGreen()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = greenRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = redRoute(connection_to_end);
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			int k = 0;
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Red Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}

	}
	public static void red_blue_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is on both lines and saves it as a Train Station Object.
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the connection using the first colored line
		 * After this, the program generates a route between the connection and the ending station using the second colored line's route generator
		 * 
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getRed()!=-1 && myroute.end.getBlue()!=-1 && myroute.end.getRed()!=1 ) {
			//Starting at a Red Line Station, Ending at a Blue Line Station
			System.out.println("Start on Red Line, End on Blue Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getBlue()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = redRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = blueRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Blue Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}
		
		else if (myroute.start.getBlue()!=-1 && myroute.end.getRed()!=-1 && myroute.end.getBlue()!=1 ) {
			//Starting at a Blue Line Station, Ending at a Red Line Station
			System.out.println("Start on Blue Line, End on Red Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getBlue()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = blueRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = redRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Red Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}
	}
	public static void red_brown_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is on both lines and saves it as a Train Station Object.
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the connection using the first colored line
		 * After this, the program generates a route between the connection and the ending station using the second colored line's route generator
		 * 
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getRed()!=-1 && myroute.end.getBrown()!=-1 && myroute.end.getRed()!=1 ) {
			//Starting at a Red Line Station, Ending at a Brown Line Station
			System.out.println("Start on Red Line, End on Brown Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getBrown()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = redRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = brownRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Brown Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}
		
		else if (myroute.start.getBrown()!=-1 && myroute.end.getRed()!=-1 && myroute.end.getBrown()!=1 ) {
			//Starting at a Blue Line Station, Ending at a Red Line Station
			System.out.println("Start on Brown Line, End on Red Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getBrown()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = brownRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = redRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Red Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}
	}
	public static void red_purple_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is on both lines and saves it as a Train Station Object.
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the connection using the first colored line
		 * After this, the program generates a route between the connection and the ending station using the second colored line's route generator
		 * 
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getRed()!=-1 && myroute.end.getPurple()!=-1) {
			//Starting at a Red Line Station, Ending at a Purple Line Station
			System.out.println("Start on Red Line, End on Purple Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getPurple()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = redRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = purpleRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Purple Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}
		
		else if (myroute.start.getPurple()!=-1 && myroute.end.getRed()!=-1 && myroute.end.getPurple()!=1 ) {
			//Starting at a Blue Line Station, Ending at a Red Line Station
			System.out.println("Start on Purple Line, End on Red Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getPurple()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = purpleRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = redRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Red Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}
	}
	public static void red_orange_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is on both lines and saves it as a Train Station Object.
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the connection using the first colored line
		 * After this, the program generates a route between the connection and the ending station using the second colored line's route generator
		 * 
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getRed()!=-1 && myroute.end.getOrange()!=-1 && myroute.end.getOrange()!=1 ) {
			//Starting at a Red Line Station, Ending at a Orange Line Station
			System.out.println("Start on Red Line, End on Orange Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getOrange()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = redRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = orangeRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Orange Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}
		
		else if (myroute.start.getOrange()!=-1 && myroute.end.getRed()!=-1 && myroute.end.getOrange()!=1 ) {
			//Starting at a Blue Line Station, Ending at a Red Line Station
			System.out.println("Start on Orange Line, End on Red Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getOrange()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = orangeRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = redRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Red Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}
	}
	//Green Connections to other Lines
	public static void green_blue_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is on both lines and saves it as a Train Station Object.
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the connection using the first colored line
		 * After this, the program generates a route between the connection and the ending station using the second colored line's route generator
		 * 
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getGreen()!=-1 && myroute.end.getBlue()!=-1 && myroute.end.getGreen()!=1 ) {
			//Starting at a Green Line Station, Ending at a Blue Line Station
			System.out.println("Start on Green Line, End on Blue Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getGreen()!=-1 && Station_List.get(i).getBlue()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = greenRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = blueRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Blue Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}
		
		else if (myroute.start.getBlue()!=-1 && myroute.end.getGreen()!=-1 && myroute.end.getBlue()!=1 ) {
			//Starting at a Blue Line Station, Ending at a Green Line Station
			System.out.println("Start on Blue Line, End on Green Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getGreen()!=-1 && Station_List.get(i).getBlue()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = blueRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = greenRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Green Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}

	}
	public static void green_brown_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is on both lines and saves it as a Train Station Object.
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the connection using the first colored line
		 * After this, the program generates a route between the connection and the ending station using the second colored line's route generator
		 * 
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getGreen()!=-1 && myroute.end.getBrown()!=-1 && myroute.end.getGreen()!=1 ) {
			//Starting at a Green Line Station, Ending at a Brown Line Station
			System.out.println("Start on Green Line, End on Brown Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getGreen()!=-1 && Station_List.get(i).getBrown()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = greenRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = brownRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Brown Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}
		
		else if (myroute.start.getBrown()!=-1 && myroute.end.getGreen()!=-1 && myroute.end.getBrown()!=1 ) {
			//Starting at a Brown Line Station, Ending at a Green Line Station
			System.out.println("Start on Brown Line, End on Green Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getGreen()!=-1 && Station_List.get(i).getBrown()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = brownRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = greenRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Green Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}

	}
	public static void green_pink_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is on both lines and saves it as a Train Station Object.
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the connection using the first colored line
		 * After this, the program generates a route between the connection and the ending station using the second colored line's route generator
		 * 
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getGreen()!=-1 && myroute.end.getPink()!=-1 && myroute.end.getGreen()!=1 ) {
			//Starting at a Green Line Station, Ending at a Pink Line Station
			System.out.println("Start on Green Line, End on Pink Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getGreen()!=-1 && Station_List.get(i).getPink()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = greenRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = pinkRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Pink Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}
		
		else if (myroute.start.getPink()!=-1 && myroute.end.getGreen()!=-1 && myroute.end.getPink()!=1 ) {
			//Starting at a Pink Line Station, Ending at a Green Line Station
			System.out.println("Start on Pink Line, End on Green Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getGreen()!=-1 && Station_List.get(i).getPink()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = pinkRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = greenRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Green Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}

	}
	public static void green_orange_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is on both lines and saves it as a Train Station Object.
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the connection using the first colored line
		 * After this, the program generates a route between the connection and the ending station using the second colored line's route generator
		 * 
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getGreen()!=-1 && myroute.end.getOrange()!=-1 && myroute.end.getGreen()!=1 ) {
			//Starting at a Green Line Station, Ending at a Orange Line Station
			System.out.println("Start on Green Line, End on Orange Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getGreen()!=-1 && Station_List.get(i).getOrange()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = greenRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = orangeRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Orange Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}
		
		else if (myroute.start.getOrange()!=-1 && myroute.end.getGreen()!=-1 && myroute.end.getOrange()!=1 ) {
			//Starting at a Orange Line Station, Ending at a Green Line Station
			System.out.println("Start on Orange Line, End on Green Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getGreen()!=-1 && Station_List.get(i).getOrange()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = orangeRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = greenRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Green Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}

	}
	//Blue Connections to other Lines
	public static void blue_brown_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is on both lines and saves it as a Train Station Object.
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the connection using the first colored line
		 * After this, the program generates a route between the connection and the ending station using the second colored line's route generator
		 * 
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getBlue()!=-1 && myroute.end.getBrown()!=-1 && myroute.end.getBlue()!=1 ) {
			//Starting at a Blue Line Station, Ending at a Brown Line Station
			System.out.println("Start on Blue Line, End on Brown Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getBlue()!=-1 && Station_List.get(i).getBrown()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = blueRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = brownRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Brown Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}
		
		else if (myroute.start.getBrown()!=-1 && myroute.end.getBlue()!=-1 && myroute.end.getBrown()!=1 ) {
			//Starting at a Brown Line Station, Ending at a Blue Line Station
			System.out.println("Start on Brown Line, End on Blue Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getBlue()!=-1 && Station_List.get(i).getBrown()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = brownRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = blueRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Blue Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}

	}
	public static void blue_pink_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is on both lines and saves it as a Train Station Object.
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the connection using the first colored line
		 * After this, the program generates a route between the connection and the ending station using the second colored line's route generator
		 * 
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getBlue()!=-1 && myroute.end.getPink()!=-1 && myroute.end.getBlue()!=1 ) {
			//Starting at a Blue Line Station, Ending at a Pink Line Station
			System.out.println("Start on Blue Line, End on Pink Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getBlue()!=-1 && Station_List.get(i).getPink()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = blueRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = pinkRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Pink Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}
		
		else if (myroute.start.getPink()!=-1 && myroute.end.getBlue()!=-1 && myroute.end.getPink()!=1 ) {
			//Starting at a Pink Line Station, Ending at a Blue Line Station
			System.out.println("Start on Pink Line, End on Blue Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getBlue()!=-1 && Station_List.get(i).getPink()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = pinkRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = blueRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Blue Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}

	}
	public static void blue_orange_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is on both lines and saves it as a Train Station Object.
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the connection using the first colored line
		 * After this, the program generates a route between the connection and the ending station using the second colored line's route generator
		 * 
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getBlue()!=-1 && myroute.end.getOrange()!=-1 && myroute.end.getBlue()!=1 ) {
			//Starting at a Blue Line Station, Ending at a Orange Line Station
			System.out.println("Start on Blue Line, End on Orange Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getBlue()!=-1 && Station_List.get(i).getOrange()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = blueRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = orangeRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Orange Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}
		
		else if (myroute.start.getOrange()!=-1 && myroute.end.getBlue()!=-1 && myroute.end.getOrange()!=1 ) {
			//Starting at a Orange Line Station, Ending at a Blue Line Station
			System.out.println("Start on Orange Line, End on Blue Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getBlue()!=-1 && Station_List.get(i).getOrange()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = orangeRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = blueRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Blue Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}

	}
	//Brown Connections to other Lines
	public static void brown_pink_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is on both lines and saves it as a Train Station Object.
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the connection using the first colored line
		 * After this, the program generates a route between the connection and the ending station using the second colored line's route generator
		 * 
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getBrown()!=-1 && myroute.end.getPink()!=-1 && myroute.end.getBrown()!=1 ) {
			//Starting at a Brown Line Station, Ending at a Pink Line Station
			System.out.println("Start on Brown Line, End on Pink Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getBrown()!=-1 && Station_List.get(i).getPink()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = brownRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = pinkRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Pink Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}
		
		else if (myroute.start.getPink()!=-1 && myroute.end.getBrown()!=-1 && myroute.end.getPink()!=1 ) {
			//Starting at a Pink Line Station, Ending at a Brown Line Station
			System.out.println("Start on Pink Line, End on Brown Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getBrown()!=-1 && Station_List.get(i).getPink()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = pinkRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = brownRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Brown Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}

	}
	public static void brown_orange_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is on both lines and saves it as a Train Station Object.
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the connection using the first colored line
		 * After this, the program generates a route between the connection and the ending station using the second colored line's route generator
		 * 
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getBrown()!=-1 && myroute.end.getOrange()!=-1 && myroute.end.getBrown()!=1 ) {
			//Starting at a Brown Line Station, Ending at a Orange Line Station
			System.out.println("Start on Brown Line, End on Orange Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getBrown()!=-1 && Station_List.get(i).getOrange()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = brownRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = orangeRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Orange Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}
		
		else if (myroute.start.getOrange()!=-1 && myroute.end.getBrown()!=-1 && myroute.end.getOrange()!=1 ) {
			//Starting at a Orange Line Station, Ending at a Brown Line Station
			System.out.println("Start on Orange Line, End on Brown Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getBrown()!=-1 && Station_List.get(i).getOrange()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = orangeRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = brownRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Brown Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}

	}
	//Pink Connections to other Lines
	public static void pink_orange_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is on both lines and saves it as a Train Station Object.
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the connection using the first colored line
		 * After this, the program generates a route between the connection and the ending station using the second colored line's route generator
		 * 
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getPink()!=-1 && myroute.end.getOrange()!=-1 && myroute.end.getPink()!=1 ) {
			//Starting at a Pink Line Station, Ending at a Pink Line Station
			System.out.println("Start on Pink Line, End on Pink Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getPink()!=-1 && Station_List.get(i).getPink()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = pinkRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = orangeRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Orange Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}
		
		else if (myroute.start.getOrange()!=-1 && myroute.end.getPink()!=-1 && myroute.end.getOrange()!=1 ) {
			//Starting at a Orange Line Station, Ending at a Pink Line Station
			System.out.println("Start on Orange Line, End on Pink Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			
			//Find the common Station between both Lines
			int i = 0;
			Train_Station connection = new Train_Station();

			while(i<Station_List.size()) {
				if(Station_List.get(i).getPink()!=-1 && Station_List.get(i).getOrange()!=-1) {
					connection = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}
			}
			Route start_to_connection = new Route(myroute.start, connection);
			ArrayList<Train_Station> StartToConnectionRoute = orangeRoute(start_to_connection);
			Route connection_to_end = new Route(connection, myroute.end);
			ArrayList<Train_Station> ConnectionToEndRoute = pinkRoute(connection_to_end);
			int k = 0;
			Route_List.addAll(StartToConnectionRoute);
			Route_List.addAll(ConnectionToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Pink Line Trains at: "+ connection.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
		}

	}
	//Two Transfer Connections	
	public static void red_blue_pink_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is between the starting line and the connecting line and saves its value as a Train Station object called connection1
		 * The program finds a station that is between connection 1's  line and the ending line and saves its value as a Train Station object called connection2
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the first connection using the first colored line
		 * After this, the program generates a route between the first connection and the second connection station using the first connection's line color
		 * After this, the program generates a route between the second connection and the ending station using the second connection's colored line's route generator
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getRed()!=-1 && myroute.end.getPink()!=-1) {
			//Starting at a Red Line Station, Transferring to Blue Line Station and then transferring to Pink Line Station
			System.out.println("Start on Red Line, End on Pink Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			//Find the first common Station between both Lines
			int i = 0;
			Train_Station connection_1 = new Train_Station();
			while(i<Station_List.size()) {
				if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getBlue()!=-1) {
					connection_1 = Station_List.get(i);
					i++;}
				else {
					i++;}}
			//Find second common station between second and third line
			i = 0;
			Train_Station connection_2 = new Train_Station();
			while(i<Station_List.size()) {
				if(Station_List.get(i).getBlue()!=-1 && Station_List.get(i).getPink()!=-1) {
					connection_2 = Station_List.get(i);
					i++;
				}
				else {
					i++;}}
			
			Route start_to_connection_1 = new Route(myroute.start, connection_1);
			ArrayList<Train_Station> StartToConnection1Route = redRoute(start_to_connection_1);
			Route connection_1_to_connection2 = new Route(connection_1, connection_2);
			ArrayList<Train_Station> Connection1ToConnection2Route = blueRoute(connection_1_to_connection2);
			Route connection_2_to_end = new Route(connection_2, myroute.end);
			ArrayList<Train_Station> Connection2ToEndRoute = pinkRoute(connection_2_to_end);
			
			int k = 0;
			Route_List.addAll(StartToConnection1Route);
			Route_List.addAll(Connection1ToConnection2Route);
			Route_List.addAll(Connection2ToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection_1.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Blue Line Trains at: "+ connection_1.getName());
				}
				else if(Route_List.get(k).getName()==connection_2.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Pink Line Trains at: "+ connection_2.getName());
				}
				k++;
			}System.out.println("***********************************************");}
			
		else if (myroute.start.getPink()!=-1 && myroute.end.getRed()!=-1) {
			//Starting at a Red Line Station, Transferring to Blue Line Station and then transferring to Pink Line Station
			System.out.println("Start on Pink Line, End on Red Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			//Find the first common Station between both Lines
			int i = 0;
			Train_Station connection_1 = new Train_Station();
			while(i<Station_List.size()) {
				if(Station_List.get(i).getPink()!=-1 && Station_List.get(i).getBlue()!=-1) {
					connection_1 = Station_List.get(i);
					i++;
				}
				else {
					i++;
				}}//Find second common station between second and third line
			i = 0;
			Train_Station connection_2 = new Train_Station();
			while(i<Station_List.size()) {
				if(Station_List.get(i).getBlue()!=-1 && Station_List.get(i).getRed()!=-1) {
					connection_2 = Station_List.get(i);
					i++;}
				else {
					i++;}}
			Route start_to_connection_1 = new Route(myroute.start, connection_1);
			ArrayList<Train_Station> StartToConnection1Route = pinkRoute(start_to_connection_1);
			Route connection_1_to_connection2 = new Route(connection_1, connection_2);
			ArrayList<Train_Station> Connection1ToConnection2Route = blueRoute(connection_1_to_connection2);
			Route connection_2_to_end = new Route(connection_2, myroute.end);
			ArrayList<Train_Station> Connection2ToEndRoute = redRoute(connection_2_to_end);
			int k = 0;
			Route_List.addAll(StartToConnection1Route);
			Route_List.addAll(Connection1ToConnection2Route);
			Route_List.addAll(Connection2ToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection_1.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Blue Line Trains at: "+ connection_1.getName());
				}
				else if(Route_List.get(k).getName()==connection_2.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Red Line Trains at: "+ connection_2.getName());
				}
				k++;
			}
			System.out.println("***********************************************");
			
		}
	}
//Three Node Connections
	public static void blue_red_purple_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is between the starting line and the connecting line and saves its value as a Train Station object called connection1
		 * The program finds a station that is between connection 1's  line and the ending line and saves its value as a Train Station object called connection2
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the first connection using the first colored line
		 * After this, the program generates a route between the first connection and the second connection station using the first connection's line color
		 * After this, the program generates a route between the second connection and the ending station using the second connection's colored line's route generator
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getBlue()!=-1 && myroute.end.getPurple()!=-1) {
			//Starting at a Blue Line Station, Transferring to Red Line Station and then transferring to Purple Line Station
			System.out.println("Start on Blue Line, End on Purple Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			//Find the first common Station between both Lines
			int i = 0;
			Train_Station connection_1 = new Train_Station();
			while(i<Station_List.size()) {
				if(Station_List.get(i).getBlue()!=-1 && Station_List.get(i).getRed()!=-1) {
					connection_1 = Station_List.get(i);
					i++;}
				else {
					i++;}}//Find second common station between second and third line
			i = 0;
			Train_Station connection_2 = new Train_Station();
			while(i<Station_List.size()) {
				if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getPurple()!=-1) {
					connection_2 = Station_List.get(i);
					i++;
				}
				else {
					i++;}}
			Route start_to_connection_1 = new Route(myroute.start, connection_1);
			ArrayList<Train_Station> StartToConnection1Route = blueRoute(start_to_connection_1);
			Route connection_1_to_connection2 = new Route(connection_1, connection_2);
			ArrayList<Train_Station> Connection1ToConnection2Route = redRoute(connection_1_to_connection2);
			Route connection_2_to_end = new Route(connection_2, myroute.end);
			ArrayList<Train_Station> Connection2ToEndRoute = purpleRoute(connection_2_to_end);
			int k = 0;
			Route_List.addAll(StartToConnection1Route);
			Route_List.addAll(Connection1ToConnection2Route);
			Route_List.addAll(Connection2ToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection_1.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Red Line Trains at: "+ connection_1.getName());
				}
				else if(Route_List.get(k).getName()==connection_2.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Purple Line Trains at: "+ connection_2.getName());}
				k++;}
			System.out.println("***********************************************");}
		else if (myroute.start.getPurple()!=-1 && myroute.end.getBlue()!=-1) {
			//Starting at a Red Line Station, Transferring to Blue Line Station and then transferring to Pink Line Station
			System.out.println("Start on Purple Line, End on Blue Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			//Find the first common Station between both Lines
			int i = 0;
			Train_Station connection_1 = new Train_Station();
			while(i<Station_List.size()) {
				if(Station_List.get(i).getPurple()!=-1 && Station_List.get(i).getRed()!=-1) {
					connection_1 = Station_List.get(i);
					i++;
				}
				else {
					i++;}}
			//Find second common station between second and third line
			i = 0;
			Train_Station connection_2 = new Train_Station();
			while(i<Station_List.size()) {
				if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getBlue()!=-1) {
					connection_2 = Station_List.get(i);
					i++;}
				else {
					i++;}}
			Route start_to_connection_1 = new Route(myroute.start, connection_1);
			ArrayList<Train_Station> StartToConnection1Route = purpleRoute(start_to_connection_1);
			Route connection_1_to_connection2 = new Route(connection_1, connection_2);
			ArrayList<Train_Station> Connection1ToConnection2Route = redRoute(connection_1_to_connection2);
			Route connection_2_to_end = new Route(connection_2, myroute.end);
			ArrayList<Train_Station> Connection2ToEndRoute = blueRoute(connection_2_to_end);
			int k = 0;
			Route_List.addAll(StartToConnection1Route);
			Route_List.addAll(Connection1ToConnection2Route);
			Route_List.addAll(Connection2ToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection_1.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Red Line Trains at: "+ connection_1.getName());
				}
				else if(Route_List.get(k).getName()==connection_2.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Blue Line Trains at: "+ connection_2.getName());
				}k++;}System.out.println("***********************************************");}
	}
	public static void brown_red_purple_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is between the starting line and the connecting line and saves its value as a Train Station object called connection1
		 * The program finds a station that is between connection 1's  line and the ending line and saves its value as a Train Station object called connection2
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the first connection using the first colored line
		 * After this, the program generates a route between the first connection and the second connection station using the first connection's line color
		 * After this, the program generates a route between the second connection and the ending station using the second connection's colored line's route generator
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
			if(myroute.start.getBrown()!=-1 && myroute.end.getPurple()!=-1) {
				//Starting at a Brown Line Station, Transferring to Red Line Station and then transferring to Purple Line Station
				System.out.println("Start on Brown Line, End on Purple Line");
				ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
				//Find the first common Station between both Lines
				int i = 0;
				Train_Station connection_1 = new Train_Station();
				while(i<Station_List.size()) {
					if(Station_List.get(i).getBrown()!=-1 && Station_List.get(i).getRed()!=-1) {
						connection_1 = Station_List.get(i);
						i++;
					}
					else {
						i++;
					}
				}
				//Find second common station between second and third line
				i = 0;
				Train_Station connection_2 = new Train_Station();
				while(i<Station_List.size()) {
					if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getPurple()!=-1) {
						connection_2 = Station_List.get(i);
						i++;
					}
					else {
						i++;
					}
				}
				
				Route start_to_connection_1 = new Route(myroute.start, connection_1);
				ArrayList<Train_Station> StartToConnection1Route = brownRoute(start_to_connection_1);
				Route connection_1_to_connection2 = new Route(connection_1, connection_2);
				ArrayList<Train_Station> Connection1ToConnection2Route = redRoute(connection_1_to_connection2);
				Route connection_2_to_end = new Route(connection_2, myroute.end);
				ArrayList<Train_Station> Connection2ToEndRoute = purpleRoute(connection_2_to_end);
				
				int k = 0;
				Route_List.addAll(StartToConnection1Route);
				Route_List.addAll(Connection1ToConnection2Route);
				Route_List.addAll(Connection2ToEndRoute);
				RouteToFile = Route_List;
				System.out.println("***********************************************");
				System.out.println("Your Route is: ");
				while(k<Route_List.size()) {
					System.out.println(Route_List.get(k).getName());
					if(Route_List.get(k).getName()==connection_1.getName()) {
						Route_List.remove(k);
						System.out.println("******************Connection********************");
						System.out.println("Connect to Red Line Trains at: "+ connection_1.getName());
					}
					else if(Route_List.get(k).getName()==connection_2.getName()) {
						Route_List.remove(k);
						System.out.println("******************Connection********************");
						System.out.println("Connect to Purple Line Trains at: "+ connection_2.getName());
					}
					k++;
				}
				System.out.println("***********************************************");
			}
			
			else if (myroute.start.getPurple()!=-1 && myroute.end.getBrown()!=-1) {
				//Starting at a Purple Line Station, Transferring to Red Line Station and then transferring to Brown Line Station
				System.out.println("Start on Purple Line, End on Brown Line");
				ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
				//Find the first common Station between both Lines
				int i = 0;
				Train_Station connection_1 = new Train_Station();
				while(i<Station_List.size()) {
					if(Station_List.get(i).getPurple()!=-1 && Station_List.get(i).getRed()!=-1) {
						connection_1 = Station_List.get(i);
						i++;
					}
					else {
						i++;
					}
				}
				//Find second common station between second and third line
				i = 0;
				Train_Station connection_2 = new Train_Station();
				while(i<Station_List.size()) {
					if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getBrown()!=-1) {
						connection_2 = Station_List.get(i);
						i++;
					}
					else {
						i++;
					}
				}
				
				Route start_to_connection_1 = new Route(myroute.start, connection_1);
				ArrayList<Train_Station> StartToConnection1Route = purpleRoute(start_to_connection_1);
				Route connection_1_to_connection2 = new Route(connection_1, connection_2);
				ArrayList<Train_Station> Connection1ToConnection2Route = redRoute(connection_1_to_connection2);
				Route connection_2_to_end = new Route(connection_2, myroute.end);
				ArrayList<Train_Station> Connection2ToEndRoute = brownRoute(connection_2_to_end);
				
				int k = 0;
				Route_List.addAll(StartToConnection1Route);
				Route_List.addAll(Connection1ToConnection2Route);
				Route_List.addAll(Connection2ToEndRoute);
				RouteToFile = Route_List;
				System.out.println("***********************************************");
				System.out.println("Your Route is: ");
				while(k<Route_List.size()) {
					System.out.println(Route_List.get(k).getName());
					if(Route_List.get(k).getName()==connection_1.getName()) {
						Route_List.remove(k);
						System.out.println("******************Connection********************");
						System.out.println("Connect to Red Line Trains at: "+ connection_1.getName());
					}
					else if(Route_List.get(k).getName()==connection_2.getName()) {
						Route_List.remove(k);
						System.out.println("******************Connection********************");
						System.out.println("Connect to Brown Line Trains at: "+ connection_2.getName());
					}
					k++;
				}
				System.out.println("***********************************************");
				
			}
		}
	public static void green_red_purple_connection() {	
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is between the starting line and the connecting line and saves its value as a Train Station object called connection1
		 * The program finds a station that is between connection 1's  line and the ending line and saves its value as a Train Station object called connection2
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the first connection using the first colored line
		 * After this, the program generates a route between the first connection and the second connection station using the first connection's line color
		 * After this, the program generates a route between the second connection and the ending station using the second connection's colored line's route generator
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getGreen()!=-1 && myroute.end.getPurple()!=-1&& myroute.end.getRed()==-1) {
			//Starting at a Green Line Station, Transferring to Red Line Station and then transferring to Purple Line Station
			System.out.println("Start on Green Line, End on Purple Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			//Find the first common Station between both Lines
			int i = 0;
			Train_Station connection_1 = new Train_Station();
			while(i<Station_List.size()) {
				if(Station_List.get(i).getGreen()!=-1 && Station_List.get(i).getRed()!=-1) {
					connection_1 = Station_List.get(i);
					i++;
				}
				else {
					i++;}} //Find second common station between second and third line
			i = 0;
			Train_Station connection_2 = new Train_Station();
			while(i<Station_List.size()) {
				if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getPurple()!=-1) {
					connection_2 = Station_List.get(i);
					i++;}
				else {
					i++;}}
			Route start_to_connection_1 = new Route(myroute.start, connection_1);
			ArrayList<Train_Station> StartToConnection1Route = greenRoute(start_to_connection_1);
			Route connection_1_to_connection2 = new Route(connection_1, connection_2);
			ArrayList<Train_Station> Connection1ToConnection2Route = redRoute(connection_1_to_connection2);
			Route connection_2_to_end = new Route(connection_2, myroute.end);
			ArrayList<Train_Station> Connection2ToEndRoute = purpleRoute(connection_2_to_end);
			int k = 0;
			Route_List.addAll(StartToConnection1Route);
			Route_List.addAll(Connection1ToConnection2Route);
			Route_List.addAll(Connection2ToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection_1.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Red Line Trains at: "+ connection_1.getName());
				}
				else if(Route_List.get(k).getName()==connection_2.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Purple Line Trains at: "+ connection_2.getName());
				}k++;}System.out.println("***********************************************");}
		else if (myroute.start.getPurple()!=-1 && myroute.end.getGreen()!=-1) {
			//Starting at a Purple Line Station, Transferring to Red Line Station and then transferring to Green Line Station
			System.out.println("Start on Purple Line, End on Green Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			//Find the first common Station between both Lines
			int i = 0;
			Train_Station connection_1 = new Train_Station();
			while(i<Station_List.size()) {
				if(Station_List.get(i).getPurple()!=-1 && Station_List.get(i).getRed()!=-1) {
					connection_1 = Station_List.get(i);
					i++;}
				else {
					i++;}}
			//Find second common station between second and third line
			i = 0;
			Train_Station connection_2 = new Train_Station();
			while(i<Station_List.size()) {
				if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getGreen()!=-1) {
					connection_2 = Station_List.get(i);
					i++;}
				else {
					i++;}}
			Route start_to_connection_1 = new Route(myroute.start, connection_1);
			ArrayList<Train_Station> StartToConnection1Route = purpleRoute(start_to_connection_1);
			Route connection_1_to_connection2 = new Route(connection_1, connection_2);
			ArrayList<Train_Station> Connection1ToConnection2Route = redRoute(connection_1_to_connection2);
			Route connection_2_to_end = new Route(connection_2, myroute.end);
			ArrayList<Train_Station> Connection2ToEndRoute = greenRoute(connection_2_to_end);
			int k = 0;
			Route_List.addAll(StartToConnection1Route);
			Route_List.addAll(Connection1ToConnection2Route);
			Route_List.addAll(Connection2ToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection_1.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Red Line Trains at: "+ connection_1.getName());}
				else if(Route_List.get(k).getName()==connection_2.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Green Line Trains at: "+ connection_2.getName());}k++;}
			System.out.println("***********************************************");}
	}
	public static void orange_red_purple_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is between the starting line and the connecting line and saves its value as a Train Station object called connection1
		 * The program finds a station that is between connection 1's  line and the ending line and saves its value as a Train Station object called connection2
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the first connection using the first colored line
		 * After this, the program generates a route between the first connection and the second connection station using the first connection's line color
		 * After this, the program generates a route between the second connection and the ending station using the second connection's colored line's route generator
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getOrange()!=-1 && myroute.end.getPurple()!=-1) {
				//Starting at a Orange Line Station, Transferring to Red Line Station and then transferring to Purple Line Station
				System.out.println("Start on Orange Line, End on Purple Line");
				ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
				//Find the first common Station between both Lines
				int i = 0;
				Train_Station connection_1 = new Train_Station();
				while(i<Station_List.size()) {
					if(Station_List.get(i).getOrange()!=-1 && Station_List.get(i).getRed()!=-1) {
						connection_1 = Station_List.get(i);
						i++;}
					else {
						i++;}}//Find second common station between second and third line
				i = 0;
				Train_Station connection_2 = new Train_Station();
				while(i<Station_List.size()) {
					if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getPurple()!=-1) {
						connection_2 = Station_List.get(i);
						i++;}
					else {
						i++;}}
				Route start_to_connection_1 = new Route(myroute.start, connection_1);
				ArrayList<Train_Station> StartToConnection1Route = orangeRoute(start_to_connection_1);
				Route connection_1_to_connection2 = new Route(connection_1, connection_2);
				ArrayList<Train_Station> Connection1ToConnection2Route = redRoute(connection_1_to_connection2);
				Route connection_2_to_end = new Route(connection_2, myroute.end);
				ArrayList<Train_Station> Connection2ToEndRoute = purpleRoute(connection_2_to_end);
				int k = 0;
				Route_List.addAll(StartToConnection1Route);
				Route_List.addAll(Connection1ToConnection2Route);
				Route_List.addAll(Connection2ToEndRoute);
				RouteToFile = Route_List;
				System.out.println("***********************************************");
				System.out.println("Your Route is: ");
				while(k<Route_List.size()) {
					System.out.println(Route_List.get(k).getName());
					if(Route_List.get(k).getName()==connection_1.getName()) {
						Route_List.remove(k);
						System.out.println("******************Connection********************");
						System.out.println("Connect to Red Line Trains at: "+ connection_1.getName());}
					else if(Route_List.get(k).getName()==connection_2.getName()) {
						Route_List.remove(k);
						System.out.println("******************Connection********************");
						System.out.println("Connect to Purple Line Trains at: "+ connection_2.getName());}k++;}
				System.out.println("***********************************************");}
			
			else if (myroute.start.getPurple()!=-1 && myroute.end.getOrange()!=-1) {
				//Starting at a Purple Line Station, Transferring to Red Line Station and then transferring to Orange Line Station
				System.out.println("Start on Purple Line, End on Orange Line");
				ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
				//Find the first common Station between both Lines
				int i = 0;
				Train_Station connection_1 = new Train_Station();
				while(i<Station_List.size()) {
					if(Station_List.get(i).getPurple()!=-1 && Station_List.get(i).getRed()!=-1) {
						connection_1 = Station_List.get(i);
						i++;}
					else {
						i++;}}
				//Find second common station between second and third line
				i = 0;
				Train_Station connection_2 = new Train_Station();
				while(i<Station_List.size()) {
					if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getOrange()!=-1) {
						connection_2 = Station_List.get(i);
						i++;}
					else {
						i++;}}
				Route start_to_connection_1 = new Route(myroute.start, connection_1);
				ArrayList<Train_Station> StartToConnection1Route = purpleRoute(start_to_connection_1);
				Route connection_1_to_connection2 = new Route(connection_1, connection_2);
				ArrayList<Train_Station> Connection1ToConnection2Route = redRoute(connection_1_to_connection2);
				Route connection_2_to_end = new Route(connection_2, myroute.end);
				ArrayList<Train_Station> Connection2ToEndRoute = orangeRoute(connection_2_to_end);
				int k = 0;
				Route_List.addAll(StartToConnection1Route);
				Route_List.addAll(Connection1ToConnection2Route);
				Route_List.addAll(Connection2ToEndRoute);
				RouteToFile = Route_List;
				System.out.println("***********************************************");
				System.out.println("Your Route is: ");
				while(k<Route_List.size()) {
					System.out.println(Route_List.get(k).getName());
					if(Route_List.get(k).getName()==connection_1.getName()) {
						Route_List.remove(k);
						System.out.println("******************Connection********************");
						System.out.println("Connect to Red Line Trains at: "+ connection_1.getName());}
					else if(Route_List.get(k).getName()==connection_2.getName()) {
						Route_List.remove(k);
						System.out.println("******************Connection********************");
						System.out.println("Connect to Orange Line Trains at: "+ connection_2.getName());}
					k++;}
				System.out.println("***********************************************");}}
	//Three Transfer Connection
	public static void pink_blue_red_purple_connection() {
		/*
		 * This method creates a list of stations/instructions to follow if the route entered by the user starts and ends on different lines.
		 * Two if statements are inside this method, to account for the directionality of this route.
		 * The program finds a station that is between the starting line and the connecting line and saves its value as a Train Station object called connection1
		 * The program finds a station that is between connection 1's  line and connection 2's line and saves its value as a Train Station object called connection2
		 * The program finds a station that is between connection 2's  line and the ending line and saves its value as a Train Station object called connection3
		 * 
		 * It then calls the route making methods such as redRoute(a,b) and greenRoute(b,c), and generates a route between the starting point and the first connection using the first colored line
		 * After this, the program generates a route between the first connection and the second connection station using the first connection's line color
		 * After this, the program generates a route between the second connection and the third connection using the second connection's colored line's route generator
		 * After this, the program generates a route between the third connection and the ending station using the third connection's colored line's route generator
		 * 
		 * Finally, this method saves the route to the RouteToFile arraylist, which will allow us to save the route to a file or just print it.
		 */
		if(myroute.start.getPink()!=-1 && myroute.end.getPurple()!=-1) {
			//Starting at a Pink Line Station, Transferring to Blue Line Station, Transferring to Red Line Station and then transferring to Purple Line Station
			System.out.println("Start on Pink Line, End on Purple Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			//Find the first common Station between both Lines
			int i = 0;
			Train_Station connection_1 = new Train_Station();
			while(i<Station_List.size()) {
				if(Station_List.get(i).getPink()!=-1 && Station_List.get(i).getBlue()!=-1) {
					connection_1 = Station_List.get(i);
					i++;}
				else {
					i++;}}
			//Find second common station between second and third line
			i = 0;
			Train_Station connection_2 = new Train_Station();
			while(i<Station_List.size()) {
				if(Station_List.get(i).getBlue()!=-1 && Station_List.get(i).getRed()!=-1) {
					connection_2 = Station_List.get(i);
					i++;}
				else {
					i++;}}
			i = 0;
			//Find second common station between third and fourth line
			Train_Station connection_3 = new Train_Station();
			while(i<Station_List.size()) {
				if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getPurple()!=-1) {
					connection_3 = Station_List.get(i);
					i++;}
				else {
					i++;}}
			Route start_to_connection_1 = new Route(myroute.start, connection_1);
			ArrayList<Train_Station> StartToConnection1Route = pinkRoute(start_to_connection_1);
			Route connection_1_to_connection2 = new Route(connection_1, connection_2);
			ArrayList<Train_Station> Connection1ToConnection2Route = blueRoute(connection_1_to_connection2);
			Route connection_2_to_connection3 = new Route(connection_2, connection_3);
			ArrayList<Train_Station> Connection2ToConnection3Route = redRoute(connection_2_to_connection3);
			Route connection_3_to_end = new Route(connection_3, myroute.end);
			ArrayList<Train_Station> Connection3ToEndRoute = purpleRoute(connection_3_to_end);
			int k = 0;
			Route_List.addAll(StartToConnection1Route);
			Route_List.addAll(Connection1ToConnection2Route);
			Route_List.addAll(Connection2ToConnection3Route);
			Route_List.addAll(Connection3ToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection_1.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Blue Line Trains at: "+ connection_1.getName());}
				else if(Route_List.get(k).getName()==connection_2.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Red Line Trains at: "+ connection_2.getName());}
				else if(Route_List.get(k).getName()==connection_3.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Purple Line Trains at: "+ connection_3.getName());}k++;}
			System.out.println("***********************************************");}
		else if (myroute.start.getPurple()!=-1 && myroute.end.getPink()!=-1) {
			//Starting at a Purple Line Station, Transferring to Red Line Station, Transferring to Blue Line Station, Transferring to Pink Station
			System.out.println("Start on Purple Line, End on Pink Line");
			ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
			//Find the first common Station between both Lines
			int i = 0;
			Train_Station connection_1 = new Train_Station();
			while(i<Station_List.size()) {
				if(Station_List.get(i).getPurple()!=-1 && Station_List.get(i).getRed()!=-1) {
					connection_1 = Station_List.get(i);
					i++;}
				else {
					i++;
				}}
			//Find second common station between second and third line
			i = 0;
			Train_Station connection_2 = new Train_Station();
			while(i<Station_List.size()) {
				if(Station_List.get(i).getRed()!=-1 && Station_List.get(i).getBlue()!=-1) {
					connection_2 = Station_List.get(i);
					i++;}
				else {
					i++;}}//Find second common station between third and fourth line
			i = 0; Train_Station connection_3 = new Train_Station();
			while(i<Station_List.size()) {
				if(Station_List.get(i).getBlue()!=-1 && Station_List.get(i).getPink()!=-1) {
					connection_2 = Station_List.get(i);
					i++;}
				else {
					i++;}}
			Route start_to_connection_1 = new Route(myroute.start, connection_1);
			ArrayList<Train_Station> StartToConnection1Route = purpleRoute(start_to_connection_1);
			Route connection_1_to_connection2 = new Route(connection_1, connection_2);
			ArrayList<Train_Station> Connection1ToConnection2Route = redRoute(connection_1_to_connection2);
			Route connection_2_to_connection3 = new Route(connection_2, connection_3);
			ArrayList<Train_Station> Connection2ToConnection3Route = blueRoute(connection_2_to_connection3);
			Route connection_3_to_end = new Route(connection_2, myroute.end);
			ArrayList<Train_Station> Connection2ToEndRoute = pinkRoute(connection_3_to_end);
			int k = 0;
			Route_List.addAll(StartToConnection1Route);
			Route_List.addAll(Connection1ToConnection2Route);
			Route_List.addAll(Connection2ToConnection3Route);
			Route_List.addAll(Connection2ToEndRoute);
			RouteToFile = Route_List;
			System.out.println("***********************************************");
			System.out.println("Your Route is: ");
			while(k<Route_List.size()) {
				System.out.println(Route_List.get(k).getName());
				if(Route_List.get(k).getName()==connection_1.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Red Line Trains at: "+ connection_1.getName());
				}
				else if(Route_List.get(k).getName()==connection_2.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Blue Line Trains at: "+ connection_2.getName());
				}
				else if(Route_List.get(k).getName()==connection_3.getName()) {
					Route_List.remove(k);
					System.out.println("******************Connection********************");
					System.out.println("Connect to Pink Line Trains at: "+ connection_3.getName());}k++;}
			System.out.println("***********************************************");}}

//Route Generators
	public static ArrayList<Train_Station> redRoute(Route route) {
		/*
		 * This method will take in two train stations as a route object and compare the directionality of these stations assuming they are on the same line
		 * If the start index is greater than the ending index then the data is sorted in a descending pattern
		 * If the start index is lower than the ending index then the data is sorted in an ascending pattern. 
		 * 
		 * After the data is sorted, A Train_station ArrayList is populated with the values that lie between the start and the end, based on their index for this color line
		 * The program finally returns the populated ArrayList
		 */
		ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
		//If statement determines the directionality of the user based on its starting point and end
		if (route.start.getRed()<route.end.getRed()){
			//To 95th/Dan Ryan
			sortByRed_95th();
			int i = 0;
			while(i<Station_List.size() && Station_List.get(i).getRed()!= route.end.getRed()) {
				//If statement used to encompass the stations between the start and the end.
				if(Station_List.get(i).getRed()>=route.start.getRed() && Station_List.get(i).getRed()<=route.end.getRed()){
					Route_List.add(Station_List.get(i));
					i++;
				}
				
				else {
					i++;
				}
			}
			//Add destination to list
			Route_List.add(route.getEnd());
		}
		else {
			//To Howard
			sortByRed_Howard();
			int i = 0;
			while(i<Station_List.size() && Station_List.get(i).getRed()!= route.end.getRed()) {
				//If statement used to encompass the stations between the start and the end.
				if(Station_List.get(i).getRed()<=route.start.getRed() && Station_List.get(i).getRed()>=route.end.getRed()){
					Route_List.add(Station_List.get(i));
					i++;
				}
				
				else {
					i++;
				}
			}
			//Add destination to list
			Route_List.add(route.getEnd());
			
		}
		return Route_List;
	}
	public static ArrayList<Train_Station> greenRoute(Route myroute) {
		/*
		 * This method will take in two train stations as a route object and compare the directionality of these stations assuming they are on the same line
		 * If the start index is greater than the ending index then the data is sorted in a descending pattern
		 * If the start index is lower than the ending index then the data is sorted in an ascending pattern. 
		 * 
		 * After the data is sorted, A Train_station ArrayList is populated with the values that lie between the start and the end, based on their index for this color line
		 * The program finally returns the populated ArrayList
		 */
		ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
		//If statement determines the directionality of the user based on its starting point and end
		if (myroute.start.getGreen()<myroute.end.getGreen()){
			//To Ashland
			sortByGreen_Ashland();
			int i = 0;
			while(i<Station_List.size() && Station_List.get(i).getGreen()!= myroute.end.getGreen()) {
				//If statement used to encompass the stations between the start and the end.
				if(Station_List.get(i).getGreen()>=myroute.start.getGreen() && Station_List.get(i).getGreen()<=myroute.end.getGreen()){
					Route_List.add(Station_List.get(i));
					i++;
				}
				
				else {
					i++;
				}
			}
			//Add destination to list
			Route_List.add(myroute.getEnd());
		}
		else {
			//To Harlem
			sortByGreen_Harlem();
			int i = 0;
			while(i<Station_List.size() && Station_List.get(i).getGreen()!= myroute.end.getGreen()) {
				//If statement used to encompass the stations between the start and the end.
				if(Station_List.get(i).getGreen()<=myroute.start.getGreen() && Station_List.get(i).getGreen()>=myroute.end.getGreen()){
					Route_List.add(Station_List.get(i));
					i++;
				}
				
				else {
					i++;
				}
			}
			//Add destination to list
			Route_List.add(myroute.getEnd());
		}
		return Route_List;
	}
	public static ArrayList<Train_Station> blueRoute(Route myroute) {
		/*
		 * This method will take in two train stations as a route object and compare the directionality of these stations assuming they are on the same line
		 * If the start index is greater than the ending index then the data is sorted in a descending pattern
		 * If the start index is lower than the ending index then the data is sorted in an ascending pattern. 
		 * 
		 * After the data is sorted, A Train_station ArrayList is populated with the values that lie between the start and the end, based on their index for this color line
		 * The program finally returns the populated ArrayList
		 */
		ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
		//If statement determines the directionality of the user based on its starting point and end
		if (myroute.start.getBlue()<myroute.end.getBlue()){
			//To Forest Park
			sortByBlue_ForestPark();
			int i = 0;
			while(i<Station_List.size() && Station_List.get(i).getBlue()!= myroute.end.getBlue()) {
				//If statement used to encompass the stations between the start and the end.
				if(Station_List.get(i).getBlue()>=myroute.start.getBlue() && Station_List.get(i).getBlue()<=myroute.end.getBlue()){
					Route_List.add(Station_List.get(i));
					i++;
				}
				
				else {
					i++;
				}
			}
			//Add destination to list
			Route_List.add(myroute.getEnd());
		}
		else {
			//To O'Hare
			sortByBlue_OHare();
			int i = 0;
			while(i<Station_List.size() && Station_List.get(i).getBlue()!= myroute.end.getBlue()) {
				//If statement used to encompass the stations between the start and the end.
				if(Station_List.get(i).getBlue()<=myroute.start.getBlue() && Station_List.get(i).getBlue()>=myroute.end.getBlue()){
					Route_List.add(Station_List.get(i));
					i++;
				}
				
				else {
					i++;
				}
			}
			//Add destination to list
			Route_List.add(myroute.getEnd());
		}
		return Route_List;
	}
	public static ArrayList<Train_Station> brownRoute(Route myroute) {
		/*
		 * This method will take in two train stations as a route object and compare the directionality of these stations assuming they are on the same line
		 * If the start index is greater than the ending index then the data is sorted in a descending pattern
		 * If the start index is lower than the ending index then the data is sorted in an ascending pattern. 
		 * 
		 * After the data is sorted, A Train_station ArrayList is populated with the values that lie between the start and the end, based on their index for this color line
		 * The program finally returns the populated ArrayList
		 */
		ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
		//If statement determines the directionality of the user based on its starting point and end
		if (myroute.start.getBrown()<myroute.end.getBrown()){
			//To Clark/Lake
			sortByBrown_ClarkLake();
			int i = 0;
			while(i<Station_List.size() && Station_List.get(i).getBrown()!= myroute.end.getBrown()) {
				//If statement used to encompass the stations between the start and the end.
				if(Station_List.get(i).getBrown()>=myroute.start.getBrown() && Station_List.get(i).getBrown()<=myroute.end.getBrown()){
					Route_List.add(Station_List.get(i));
					i++;
				}
				
				else {
					i++;
				}
			}
			//Add destination to list
			Route_List.add(myroute.getEnd());
			
		}
		else {
			//To Kimball
			sortByBrown_Kimball();
			int i = 0;
			while(i<Station_List.size() && Station_List.get(i).getBrown()!= myroute.end.getBrown()) {
				//If statement used to encompass the stations between the start and the end.
				if(Station_List.get(i).getBrown()<=myroute.start.getBrown() && Station_List.get(i).getBrown()>=myroute.end.getBrown()){
					Route_List.add(Station_List.get(i));
					
					i++;
				}
				
				else {
					i++;
				}
			}
			//Add destination to list
			Route_List.add(myroute.getEnd());
		}
		return Route_List;
	}
	public static ArrayList<Train_Station> purpleRoute(Route myroute) {
		ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
		//If statement determines the directionality of the user based on its starting point and end
		if (myroute.start.getPurple()<myroute.end.getPurple()){
			//To Washington/Wells
			sortByPurple_WashingtonWells();
			int i = 0;
			while(i<Station_List.size() && Station_List.get(i).getPurple()!= myroute.end.getPurple()) {
				//If statement used to encompass the stations between the start and the end.
				if(Station_List.get(i).getPurple()>=myroute.start.getPurple() && Station_List.get(i).getPurple()<=myroute.end.getPurple()){
					Route_List.add(Station_List.get(i));
					i++;
				}
				
				else {
					i++;
				}
			}
			//Add destination to list
			Route_List.add(myroute.getEnd());
		}
		else {
			//To Linden
			sortByPurple_Linden();
			int i = 0;
			while(i<Station_List.size() && Station_List.get(i).getPurple()!= myroute.end.getPurple()) {
				//If statement used to encompass the stations between the start and the end.
				if(Station_List.get(i).getPurple()<=myroute.start.getPurple() && Station_List.get(i).getPurple()>=myroute.end.getPurple()){
					Route_List.add(Station_List.get(i));
					i++;
				}
				
				else {
					i++;
				}
			}
			//Add destination to list
			Route_List.add(myroute.getEnd());
		}
		return Route_List;
	}
	public static ArrayList<Train_Station> pinkRoute(Route myroute) {
		ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
		//If statement determines the directionality of the user based on its starting point and end
		if (myroute.start.getPink()<myroute.end.getPink()){
			//To Harold Washington Library
			sortByPink_HaroldWashington();
			int i = 0;
			while(i<Station_List.size() && Station_List.get(i).getPink()!= myroute.end.getPink()) {
				//If statement used to encompass the stations between the start and the end.
				if(Station_List.get(i).getPink()>=myroute.start.getPink() && Station_List.get(i).getPink()<=myroute.end.getPink()){
					Route_List.add(Station_List.get(i));
					i++;
				}
				else {
					i++;
				}
			}
			//Add destination to list
			Route_List.add(myroute.getEnd());
			
		}
		else {
			//To 54th/Cermak
			sortByPink_54thCermak();
			int i = 0;
			while(i<Station_List.size() && Station_List.get(i).getPink()!= myroute.end.getPink()) {
				//If statement used to encompass the stations between the start and the end.
				if(Station_List.get(i).getPink()<=myroute.start.getPink() && Station_List.get(i).getPink()>=myroute.end.getPink()){
					Route_List.add(Station_List.get(i));
					i++;
				}
				else {
					i++;
				}
			}
			//Add destination to list
			Route_List.add(myroute.getEnd());
		}
		return Route_List;
	}
	public static ArrayList<Train_Station> orangeRoute(Route myroute) {
		ArrayList<Train_Station> Route_List = new ArrayList<Train_Station>();
		//If statement determines the directionality of the user based on its starting point and end
		if (myroute.start.getOrange()<myroute.end.getOrange()){
			//To Adams/Wabash
			sortByOrange_AdamsWabash();
			int i = 0;
			while(i<Station_List.size() && Station_List.get(i).getOrange()!= myroute.end.getOrange()) {
				//If statement used to encompass the stations between the start and the end.
				if(Station_List.get(i).getOrange()>=myroute.start.getOrange() && Station_List.get(i).getOrange()<=myroute.end.getOrange()){
					Route_List.add(Station_List.get(i));
					i++;
				}
				else {
					i++;
				}
			}
			//Add destination to list
			Route_List.add(myroute.getEnd());

		}
		else {
			//To Midway
			sortByOrange_Midway();
			int i = 0;
			while(i<Station_List.size() && Station_List.get(i).getOrange()!= myroute.end.getOrange()) {
				//If statement used to encompass the stations between the start and the end.
				if(Station_List.get(i).getOrange()<=myroute.start.getOrange() && Station_List.get(i).getOrange()>=myroute.end.getOrange()){
					Route_List.add(Station_List.get(i));
					i++;
				}
				else {
					i++;
				}
			}
			//Add destination to list
			Route_List.add(myroute.getEnd());
		}
		return Route_List;
	}
		
//Route Sorting Methods
	//Red Line
	public static void sortByRed_95th() {
		//Sort for Southbound trains with destination to 95th Dan Ryan
		Station_List.sort(new RedSorter().reversed());
	}
	public static void sortByRed_Howard() {
		//Sort for Northbound trains with destination to Howard
		Station_List.sort(new RedSorter());
	}
	//Green Line
	public static void sortByGreen_Ashland() {
		//Sort for Trains headed to Ashland
		Station_List.sort(new GreenSorter().reversed());
			
		}
	public static void sortByGreen_Harlem() {
		//Sort for trains headed to  Harlem
		Station_List.sort(new GreenSorter());
			
		}
	//Blue Line
	public static void sortByBlue_ForestPark() {
		//Sort for trains headed to Forest Park
		Station_List.sort(new BlueSorter().reversed());
	}
	public static void sortByBlue_OHare() {
		//Sort for trains headed to OHare
		Station_List.sort(new BlueSorter());
		}
	//Brown Line
	public static void sortByBrown_ClarkLake() {
		//Sort for trains headed to Clark/Lake/Loop
		Station_List.sort(new BrownSorter().reversed());
	}
	public static void sortByBrown_Kimball() {
		//Sort for trains headed to Kimball
		Station_List.sort(new BrownSorter());
	}
	//Purple Line
	public static void sortByPurple_WashingtonWells() {
		//Sort for trains headed to WashingtonWells/Loop
		Station_List.sort(new PurpleSorter().reversed());
	}
	public static void sortByPurple_Linden() {
		//Sort for trains headed to Linden
		Station_List.sort(new PurpleSorter());
	}
	//Pink Line
	public static void sortByPink_HaroldWashington() {
		//Sort for trains headed to Harold Washington Library
		Station_List.sort(new PinkSorter().reversed());
	}
	public static void sortByPink_54thCermak() {
		//Sort for trains headed to 54th/Cermak
		Station_List.sort(new PinkSorter());
	}
	//Orange Line
	public static void sortByOrange_AdamsWabash() {	
		//Sort for trains headed to Adams/Wabash/Loop
		Station_List.sort(new OrangeSorter().reversed());
		}
	public static void sortByOrange_Midway() {
		//Sort for trains headed to Midway
		Station_List.sort(new OrangeSorter());
	}
	
	
//Other Methods
	public static void saveFileMenu() {
		/*
		 * This method prompts the user to choose whether to save a file or not
		 */
		System.out.println("*****************************************************************************");
		System.out.println("Save to File?: ");
		System.out.println(" (1) - Yes");
		System.out.println("(-1) - No");
		System.out.println("*****************************************************************************");
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please enter your choice below: ");
		int choice = input.nextInt();
		
		switch(choice) {
		case 1:
			Write();
			break;
		default:
			System.out.println("Incorrect Value, try again...");
			break;
			}
		choice = input.nextInt();
	}
	
	public static void promptForRoute() {
		//This method prompts the user for a starting station and an ending station using the sequentialSearchBName() method from Train_Stations class
		//It then saves it as a route object
		Scanner input = new Scanner(System.in);
		//Prompt user for the Name of the Starting Station
		System.out.println("Please enter the Name of the Starting Station: ");
		sequentialSearchbyName();
		System.out.println("Please enter the id of your selected Station: ");
		int id1 = input.nextInt();
		int id_iterator1 = 0;
		while (id1!=Station_List.get(id_iterator1).Id) {
			id_iterator1++;
		}
		Train_Station mystart = Station_List.get(id_iterator1);
		System.out.println(mystart.getName()+ " Selected!");
		//Prompt user for the Name of the Starting Station
		System.out.println("Please enter the Name of the Ending Station: ");
		sequentialSearchbyName();
		System.out.println("Please enter the id of your selected Station: ");
		int id2 = input.nextInt();
		int id_iterator2 = 0;
		while (id2!=Station_List.get(id_iterator2).Id) {
			id_iterator2++;
		}
		Train_Station myend = Station_List.get(id_iterator2);
		System.out.println(myend.getName()+ " Selected!");
		// Construct Route Object
		myroute = new Route(mystart,myend);
		System.out.println(myroute.toString());
			
			
		}
	public static void directionsMenu() {
		/*
		 * This method creates a menu for the generation of directions, giving the user the choice to exit the directions menu or to enter two stations as in the promptRoute() method
		 */
		System.out.println("Directions Menu: ");
		System.out.println(" (1) - Enter Starting and Ending Stations");
		System.out.println("(-1) - Exit");
		System.out.println("*****************************************************************************");
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your choice below: ");
		int choice = input.nextInt();
		
		while(choice != -1) {
			switch(choice) {
			case 1:
				directions();
				saveFileMenu();

				break;
			default:
				System.out.println("Incorrect Value, try again...");
				break;
				}
			System.out.println("*****************************************************************************");
			System.out.println("Directions Menu: ");
			System.out.println(" (1) - Enter Starting and Ending Stations");
			System.out.println("(-1) - Exit");
			System.out.println("*****************************************************************************");
			System.out.println("Please enter your choice below: ");
			choice = input.nextInt();
			}
		}
	public static void routeLogic() {
			//This method will guide the logic behind the directions making method. It considers all the different combinations of routes, including connecting routes.
			//We Start off with the obvious cases where both the start and end stops are on the same line
			ArrayList<Train_Station> Route_List = redRoute(myroute);
			if(myroute.start.getRed()!=-1 && myroute.end.getRed()!=-1) {
				System.out.println("Both Stops on the Red Line");
				Route_List = redRoute(myroute);
				RouteToFile = Route_List;
				int iterator = 0;
				System.out.println("******************************************");
				System.out.println("Your Route:");
				while(iterator<Route_List.size()) {
					System.out.println(Route_List.get(iterator).getName());
					iterator++;
				}
				System.out.println("******************************************");
			}
			else if (myroute.start.getGreen()!=-1 && myroute.end.getGreen()!=-1) {
				System.out.println("Both Stops on the Green Line");
				Route_List = greenRoute(myroute);
				RouteToFile = Route_List;
				int iterator = 0;
				System.out.println("******************************************");
				System.out.println("Your Route:");
				while(iterator<Route_List.size()) {
					System.out.println(Route_List.get(iterator).getName());
					iterator++;
				}
				System.out.println("******************************************");
			}
			else if(myroute.start.getBlue()!=-1 && myroute.end.getBlue()!=-1) {
				System.out.println("Both Stops on the Blue Line");
				Route_List = blueRoute(myroute);
				RouteToFile = Route_List;
				int iterator = 0;
				System.out.println("******************************************");
				System.out.println("Your Route:");
				while(iterator<Route_List.size()) {
					System.out.println(Route_List.get(iterator).getName());
					iterator++;
				}
				System.out.println("******************************************");
				
			}
			else if(myroute.start.getBrown()!=-1 && myroute.end.getBrown()!=-1) {
				System.out.println("Both Stops on the Brown Line");
				Route_List = brownRoute(myroute);
				RouteToFile = Route_List;
				int iterator = 0;
				System.out.println("******************************************");
				System.out.println("Your Route:");
				while(iterator<Route_List.size()) {
					System.out.println(Route_List.get(iterator).getName());
					iterator++;
				}
				System.out.println("******************************************");
			}
			else if(myroute.start.getPurple()!=-1 && myroute.end.getPurple()!=-1) {
				System.out.println("Both Stops on the Purple Line");
				Route_List = purpleRoute(myroute);
				RouteToFile = Route_List;
				int iterator = 0;
				System.out.println("******************************************");
				System.out.println("Your Route:");
				while(iterator<Route_List.size()) {
					System.out.println(Route_List.get(iterator).getName());
					iterator++;
				}
				System.out.println("******************************************");
			}
			else if(myroute.start.getPink()!=-1 && myroute.end.getPink()!=-1) {
				System.out.println("Both Stops on the Pink Line");
				Route_List = pinkRoute(myroute);
				RouteToFile = Route_List;
				int iterator = 0;
				System.out.println("******************************************");
				System.out.println("Your Route:");
				while(iterator<Route_List.size()) {
					System.out.println(Route_List.get(iterator).getName());
					iterator++;
				}
				System.out.println("******************************************");
			}
			else if(myroute.start.getOrange()!=-1 && myroute.end.getOrange()!=-1) {
				System.out.println("Both Stops on the Orange Line");
				Route_List = orangeRoute(myroute);
				RouteToFile = Route_List;
				int iterator = 0;
				System.out.println("******************************************");
				System.out.println("Your Route:");
				while(iterator<Route_List.size()) {
					System.out.println(Route_List.get(iterator).getName());
					iterator++;
				}
				System.out.println("******************************************");
			}
			else {
				System.out.println("Stations are on different lines");
				connectionLogic();
				
				
			}
			
		}
	public static void connectionLogic() {
			//This method puts together all different route connection possibilities. Given every one of these methods already has logic arguments within them to determine what method will dominate, it is just a matter of adding them to a simple voided method to save some code
			red_green_connection();
			red_blue_connection();
			red_brown_connection();
			red_purple_connection();
			red_orange_connection();
			green_blue_connection();
			green_brown_connection();
			green_pink_connection();
			green_orange_connection();
			blue_brown_connection();
			blue_pink_connection();
			blue_orange_connection();
			brown_pink_connection();
			brown_orange_connection();
			pink_orange_connection();
			red_blue_pink_connection();
			blue_red_purple_connection();
			brown_red_purple_connection();
			green_red_purple_connection();
			orange_red_purple_connection();
			pink_blue_red_purple_connection();
		}
	public static void directions() {
		//This method just saves up some coding so it can be called by the Train_Station.choice() method when the user selects "Get Directions"
			promptForRoute();
			routeLogic();
		}

	
	
	public static void CreateFile() {
		//This method is a voided method that creates a file based on user input. The file is saved within the project's "User_Routes" folder
		//The User_Route's folder was specifically created to store all routes the user wants to store. 
		//This program modifies the file_name string to create the path of the file including its name, which is chosen by the user
		Scanner name = new Scanner(System.in); //Prompt user
		System.out.println("Enter a your desired file name: ");
		file_name = "src/project/User_Routes/" + name.next() + ".txt"; //Modify String to create filepath
		
		try { 
			//Implement exceptions for file already existing or invalid names
		      File myObj = new File(file_name);
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}

	static ArrayList<Train_Station> RouteToFile = new ArrayList<Train_Station>();
	
	public static void WriteToFile() {
		/*
		 * This method parses through the values of the train stations saved in the static variable RouteToFile to save them to the file the user created
		 * The name of the file created is prompted to the user on the method CreateFile()
		 * This program writes the route instructions as well as all the characteristics of the desired route for the user to save in a .txt file
		 */
		
	    try {
	        FileWriter myWriter = new FileWriter(file_name);
	        myWriter.write("**************************************" + "\n");
	        myWriter.write("ROUTE: " + "\n"+"\n");
	        myWriter.write(myroute.toString() + "\n");
	        
			for(int i=0; i<RouteToFile.size();i++) {
				myWriter.write(RouteToFile.get(i).toString() + "\n"+"\n");
			}
	        myWriter.close();
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
	

	
	public static void Write() {
		//This method just assembles the pieces for the creation and writing of the route's instructions into the file. 
		CreateFile();
		WriteToFile();
		Train_Station.choice(); //Take the user back to main menu
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
