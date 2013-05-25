package dm.tasks;
import java.util.Random;

public class FlightGenerator {
	public static void main(String[] args)
	{
		String[] places = {"Chicago","San Francisco","Austin","Miami","Memphis"};
		String time = "";
		String date = "";
		
		for (int i = 1; i < 31; i++)
			for (String s1 : places)
			{
				for (String s2: places)
				{
					if (!s1.equals(s2))
					{
						time = genTime();
						date = genDate(i);
						System.out.println(s1 + "," + s2 + "," + date + "," + time);
					}
				}
			}
			
	}
	
	public static String genDate(int day) 
	{
		String month = "06";
		String date = day+"";
		String year = "2013";
		
		if (date.length() < 2)
			date = "0" + date;
		return (month +"/"+date+"/"+year); 
	}
	
	public static String genTime()
	{
		Random rand = new Random();
		int hour = rand.nextInt(24);
		String strHour = hour+"";
		if (hour == 0)
		strHour = "00";
		int minutes = (rand.nextInt(60) + 4) / 5 * 5;
		String strMinutes = "";
		if (minutes < 10)
			strMinutes = "0";
		strMinutes += minutes;
		return (strHour + ":" + strMinutes);
	}
}