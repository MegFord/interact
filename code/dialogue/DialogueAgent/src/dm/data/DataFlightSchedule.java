package dm.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataFlightSchedule {

	public static String[][] flightSchedule;

	public static void importFlights(String filename)
	{
		// Some change
		try
		{
			File file = new File(filename);
			Scanner in = new Scanner(file);
			int x = in.nextInt();
			in.nextLine();
			int y = in.nextInt();
			in.nextLine();
			String[][] flights = new String[x][y];
			int i = 0;
			while (in.hasNext()) 
			{
				flights[i] = in.nextLine().split(",");
				i++;
			}
			flightSchedule = flights;
		}
		catch (FileNotFoundException fnfe) 
		{
			System.out.println("File not found.");
		}
	
	}
	
}
