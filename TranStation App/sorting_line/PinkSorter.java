package project.sorting_line;
import java.util.Comparator;
import project.Train_Station;

public class PinkSorter implements Comparator<Train_Station>{
	//This method is used to create the sorting comparison for the pink line. 
	//It implements the Comparator interface and overrides it to sort the values of Station_List based on their order in the pinkline station
	@Override
    public int compare(Train_Station o1, Train_Station o2) {
        return o2.getPink().compareTo(o1.getPink());
    }
}
