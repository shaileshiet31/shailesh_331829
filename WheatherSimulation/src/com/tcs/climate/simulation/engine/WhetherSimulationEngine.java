package com.tcs.climate.simulation.engine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.tcs.climate.simulation.bean.WheatherCondition;
import com.tcs.climate.simulation.util.ClimateSimulationCore;
/**
 * @author shailesh_331829
	May 7, 2017
 *
 */

public class WhetherSimulationEngine {
	
	
             
	/**  
	 * 
	 * Core Engine to generate whether simulation data
	 * header.
	 * @param noOfReords NO of Records to generate
	 * @param enteredLatitude on behalf of latitude provided ranges of +10 to -10 latitude data is generated
	 * @param enteredLongitude on behalf of longitude provided ranges of +10 to -10 longitude data is generated
	 * @param bw  
	 */
	
	public void executeEngineToGenerateRecords(int noOfReords,double enteredLatitude,double enteredLongitude, BufferedWriter bw ) throws Exception
	{
		ClimateSimulationCore climateSimulationUtility=new ClimateSimulationCore();
		
		
		for(int i=1;i<=noOfReords;i++)
		{
			StringBuilder sb=new StringBuilder();
			double latitude=climateSimulationUtility.generateRandomLatitude(enteredLatitude);
			double longitude=climateSimulationUtility.generateRandomLongitude(enteredLongitude);
			double altitude=climateSimulationUtility.generateRandomAltitude();
			Date randomDate=climateSimulationUtility.generateRandomDateTime();
			
			
			double tempreture=calculateTempretureByFactors(latitude, altitude, randomDate);
			
			double pressure=climateSimulationUtility.calculatePressureAtAltitude(altitude, tempreture);
			
			double humidity=climateSimulationUtility.generateRandomRelativeHumidity();
			
			WheatherCondition condition=climateSimulationUtility.calculateWhetherCondition(pressure, tempreture, humidity, altitude);
			
			sb.append( String.format( "%.2f", latitude ));
			sb.append(",");
			sb.append(String.format( "%.2f", longitude ));
			sb.append(",");
			sb.append(String.format( "%.2f", altitude));
			sb.append("|");
			sb.append(getISO8601DateFormat(randomDate));
			sb.append("|");
			sb.append(condition.toString());
			sb.append("|");
			String formattedTempreture=String.format( "%.2f", tempreture);
			sb.append(tempreture>0?"+"+formattedTempreture:formattedTempreture);
			sb.append("|");
			sb.append(String.format( "%.2f", pressure));
			sb.append("|");
			sb.append( String.format( "%.2f", humidity));
			System.out.println(sb);
			sb.append("\r\n");
			
			bw.write(sb.toString());
			
		}
		
	}
	/**  
	 * 
	 * Intitater point for Data generation it expect no of records ,lattitude,longitude on console to be entered when promted
	 * header.
	 * 
	 */
	public static void main(String[] args) throws Exception
	{
		Scanner scanner = new Scanner(System.in);
		int noOfReords;
		 BufferedWriter bw = null;
		 FileWriter fw =null;
		double enteredLatitude;
		double enteredLongitude;
		try
		{
		
			System.out.print("Enter number of records to genrate: ");
			
			
			String noOfReordsString = scanner.nextLine();
	
			try
			{
				noOfReords=Integer.valueOf(noOfReordsString);	
			}
			catch(NumberFormatException e)
			{
				throw new IllegalArgumentException("No Of Records to generate should be of numeric");
			}
			
			System.out.print("Enter Latitude (Note:-+10 range of latitude data will be generated): ");
			String latitudeString = scanner.nextLine();
			
			try
			{
				enteredLatitude=Double.valueOf(latitudeString);	
			}
			catch(NumberFormatException e)
			{
				throw new Exception("latitude should be a number");
			}
			if(enteredLatitude>80)
			{
				throw new IllegalArgumentException("Entered latitude +-10 will exceed 90 degree please enter a valid range");
			}
			else if(enteredLatitude<-80)
			{
				throw new IllegalArgumentException("Entered latitude +-10 will exceed 90 degree please enter a valid range");
			}
			
			
			
			System.out.print("Enter Longitude (Note:-+10 range of Longitude data will be generated): ");
			String longitudeString = scanner.nextLine();
			
			try
			{
				enteredLongitude=Double.valueOf(longitudeString);
			}
			catch(NumberFormatException e)
			{
				throw new IllegalArgumentException("longitude should be a number");
			}
			
			if(enteredLongitude>170)
			{
				throw new IllegalArgumentException("Entered longitude +-10 will exceed 180 degree please enter a valid range");
			}
			else if(enteredLongitude<-170)
			{
				throw new IllegalArgumentException("Entered longitude +-10 will exceed 180 degree please enter a valid range");
			}
			
			
			System.out.print("Enter File path(eg c:\\data.txt): ");
			String filePathString = scanner.nextLine();
			try 
			{
				File file = new File(filePathString);
			 fw = new FileWriter(file);
			 bw = new BufferedWriter(fw);
			 if (!file.exists()) {
			     file.createNewFile();
			  }
			}
			catch(Exception e) 
			{
				throw e;
			}
			
			WhetherSimulationEngine whetherSimulationEngine=new WhetherSimulationEngine();
			whetherSimulationEngine.executeEngineToGenerateRecords(noOfReords, enteredLatitude, enteredLongitude,bw);
		}
		finally
		{
			try 
			{
				scanner.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			try 
			{
				bw.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	/**  
	 * 
	 * It is used to calculate temperature on various factor .
	 * 1)latitude & altitude 
	 * 2)By factor of latitude falling in northern and southern hemisphere also 
	 * it will be using intensity factor of temperature by months
	 * taking account of summer and winter months of norther and southern hemisphere ie.summer months (April,May,June,July,August,Sep) on the Northern Hemisphere 
	 *	and the winter months are October through March (Oct,Nov,Dec,Jan,Feb,March) and vice versa foe southern hemisphere
	 * 3)By factor of timestamp hour in noon the tempreture intensity will be high in night it will less.
	 * header.
	 * @param lattitude 
	 * @param altitude 
	 * @param randomDate auto generated date
	 * @return temperature
	 */
	
	public double calculateTempretureByFactors(double lattitude,double altitude,Date randomDate) throws Exception
	{
		
		ClimateSimulationCore climateSimulationUtility=new ClimateSimulationCore();
		
		double tempreture=climateSimulationUtility.getAverageTempretureByLattitudeAndAltitude(lattitude, altitude);
		
		tempreture=tempreture+climateSimulationUtility.calculateTempretureIntensityFactorByMonth(lattitude,  altitude, randomDate);
		
		tempreture=(tempreture*	climateSimulationUtility.calculateTempretureIntensityFactorByCurrentHours(randomDate))/100;
		
		return tempreture;
	}
	/**  
	 * 
	 * this method is used to format the date into ISO8601 Format .
	 *its using joda time library
	 * header.
	 * @param date 
	 *
	 * @return formatted date String
	 */
	public String getISO8601DateFormat(Date date){
		
		
		DateTime dt = new DateTime(date);

		
		//DateTimeFormatter isoFormat = ISODateTimeFormat.dateParser();

		    
		//System.out.println(DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z"));
		
		/*TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); 
		df.setTimeZone(tz);
		String nowAsISO = df.format(date);*/
		return dt.toString(DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z"));
	}
	
}
