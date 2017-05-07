package com.tcs.climate.simulation.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.tcs.climate.simulation.bean.WheatherCondition;


/**
 * @author shailesh_331829
	May 7, 2017
 *
 */

public class ClimateSimulationCore 
{
	private static Map<Integer,Double>summerMonthsForNorthernHemishphereMap=new HashMap<Integer,Double>();
	private static Map<Integer,Double>winterrMonthsForNorthernHemishphereMap=new HashMap<Integer,Double>();
	private static	Map<Integer,Double>summerMonthsForSouthernHemishphereMap=new HashMap<Integer,Double>();
	private static Map<Integer,Double>winterrMonthsForSouthernHemishphereMap=new HashMap<Integer,Double>();
	
	private static Map<Integer,Double>tempIntensityKeyedByHour=new HashMap<Integer,Double>();
	
	
	/**  
	 * 
	 * Initializing credit weightage score for individual summer and cold months for northern and southern hemisphere
	 * Also initialization of credit weightage score for tempreture intensity for day hours 
	 */
	static
	{
		summerMonthsForNorthernHemishphereMap.put(3,5.0);//april and its weigtage out of of 100 % intensity range
		summerMonthsForNorthernHemishphereMap.put(4,10.0);//may and its weigtage out of of 100 % intensity range
		summerMonthsForNorthernHemishphereMap.put(5,20.0);//june and its weigtage out of of 100 % intensity range
		summerMonthsForNorthernHemishphereMap.put(6,15.0);//july and its weigtage out of of 100 % intensity range
		summerMonthsForNorthernHemishphereMap.put(7,10.0);//august and its weigtage out of of 100 % intensity range
		summerMonthsForNorthernHemishphereMap.put(8,0.0);//sep and its weigtage out of of 100 % intensity range
		
		
		winterrMonthsForNorthernHemishphereMap.put(9,0.0);//october and its weigtage out of of 100 % intensity range
		winterrMonthsForNorthernHemishphereMap.put(10,-5.0);//november and its weigtage out of of 100 % intensity range
		winterrMonthsForNorthernHemishphereMap.put(11,-15.0);//december and its weigtage out of of 100 % intensity range
		winterrMonthsForNorthernHemishphereMap.put(0,-10.0);//january and its weigtage out of of 100 % intensity range
		winterrMonthsForNorthernHemishphereMap.put(1,-8.0);//februry and its weigtage out of of 100 % intensity range
		winterrMonthsForNorthernHemishphereMap.put(2,-5.0);//march and its weigtage out of of 100 % intensity range
		
		
		
		
		summerMonthsForSouthernHemishphereMap.put(9,5.0);//october and its weigtage out of of 100 % intensity range
		summerMonthsForSouthernHemishphereMap.put(10,10.0);//november and its weigtage out of of 100 % intensity range
		summerMonthsForSouthernHemishphereMap.put(11,20.0);//december and its weigtage out of of 100 % intensity range
		summerMonthsForSouthernHemishphereMap.put(0,15.0);//january and its weigtage out of of 100 % intensity range
		summerMonthsForSouthernHemishphereMap.put(1,10.0);//februry and its weigtage out of of 100 % intensity range
		summerMonthsForSouthernHemishphereMap.put(2,8.0);//march and its weigtage out of of 100 % intensity range
		
	
		winterrMonthsForSouthernHemishphereMap.put(3,0.0);//april and its weigtage out of of 100 % intensity range
		winterrMonthsForSouthernHemishphereMap.put(4,-5.0);//may and its weigtage out of of 100 % intensity range
		winterrMonthsForSouthernHemishphereMap.put(5,-15.0);//june and its weigtage out of of 100 % intensity range
		winterrMonthsForSouthernHemishphereMap.put(6,-12.0);//july and its weigtage out of of 100 % intensity range
		winterrMonthsForSouthernHemishphereMap.put(7,-8.0);//august and its weigtage out of of 100 % intensity range
		winterrMonthsForSouthernHemishphereMap.put(8,-5.0);//sep and its weigtage out of of 100 % intensity range
		
		
		
		tempIntensityKeyedByHour.put(12, 100.0);
		tempIntensityKeyedByHour.put(13, 100.0);
		tempIntensityKeyedByHour.put(14, 92.0);
		tempIntensityKeyedByHour.put(15, 82.0);
		tempIntensityKeyedByHour.put(16, 75.0);
		tempIntensityKeyedByHour.put(17, 67.0);
		tempIntensityKeyedByHour.put(18, 60.0);
		tempIntensityKeyedByHour.put(19, 56.0);
		tempIntensityKeyedByHour.put(20, 54.0);
		tempIntensityKeyedByHour.put(21, 53.0);
		tempIntensityKeyedByHour.put(22, 53.0);
		tempIntensityKeyedByHour.put(23, 53.0);
		tempIntensityKeyedByHour.put(0, 52.0);
		tempIntensityKeyedByHour.put(1, 52.0);
		tempIntensityKeyedByHour.put(2, 51.0);
		tempIntensityKeyedByHour.put(3, 51.0);
		tempIntensityKeyedByHour.put(4, 49.0);
		tempIntensityKeyedByHour.put(5, 49.0);
		tempIntensityKeyedByHour.put(6, 52.0);
		tempIntensityKeyedByHour.put(7, 67.0);
		tempIntensityKeyedByHour.put(8, 75.0);
		tempIntensityKeyedByHour.put(9, 82.0);
		tempIntensityKeyedByHour.put(10, 92.0);
		tempIntensityKeyedByHour.put(11, 95.0);
	}
	/**  
	 * This method is used to calculate temperature from latitude and altitude
	 *  there is a temperature relationship between latitude and altitude for more details  visit http://webinquiry.org/examples/temps/
		Temperature relation ship with altitude -->Temperature = -0.0026* (Elevation in feet) 
		Temperature relation ship with latitude -->
		For locations below 20°N: Temperature = 80°F.
		For locations between 20°N and 60°N: Temperature = -0.988 *(latitude) + 96.827°F.
		
	 * @param lattitude 
	 * @param altitude 
	 *
	 * @return temperature
	 */
	public double getAverageTempretureByLattitudeAndAltitude(double lattitude,double altitude) throws Exception
	{
		/*if lattitude contains -ve val than change to positive value as we assume that the 
		formula will be applied for both northern hemisphere and southern hemisphere*/
		
		lattitude=lattitude<0?(lattitude*-1):lattitude;
		
		double returnTempretureFareignhight=0.0;
		if(lattitude<20)
		{
			returnTempretureFareignhight=80;
		}
		else if(lattitude>=20 && lattitude<60)
		{
			returnTempretureFareignhight=(-0.988 *lattitude) + 96.827;
		}
		else if(lattitude>=60 && lattitude<=90)
		{
			returnTempretureFareignhight= (-2.5826*lattitude) + 193.33;
		}
		else
		{
			throw new Exception("Latitude can't be greater then 90 degree");
		}
		
		//tempreture is inversely propartional to altitude 
		//conversion of altitude to feet 1:3.28084
		
		returnTempretureFareignhight=returnTempretureFareignhight -(0.0026 *(altitude*3.28084) );
		
		return convertFareignhigthToCelcius(returnTempretureFareignhight);
		
	}
	
	
	/**  
	 * This method is used to calculate pressure at a given  altitude and temperature
	 *  formula for calculation is 
	 *  P=P0e^((-mu*g*h)\RT)
		where
		p0- pressure at see level -101325
		mu - Molar mass of Earth's air, 0.0289644 kg/mol
		g - Gravitational acceleration, 9.80665 m/(s*s)
		h - Height difference, meters
		R - Universal gas constant for air, 8.31432 N·m /(mol·K)
		T - Air temperature*
	 * @param altitude
	 * @param tempreture 
	 *  
	 *
	 * @return pressure
	 */
	
	public double calculatePressureAtAltitude(double altitude ,double tempreture)
	{
		
		double pressure=	(101325*(Math.exp((-0.0289644*9.80665*altitude)/(8.31432*(tempreture+273.15))))/100);
		
		//System.out.println(pressure);
		return pressure;
	}
	/**  
	 * This method is used to calculate whether condition as sunny,rain  and snow on behalf pressure, temperature,humidity,alititude
	 *  snow->it is assumed that altitudes with higher values , less pressure and less temperature can cause of snow
	 * rain->with high humidity,moderate range of altitudes and mild temperature
	 * sunny-->else all conditions
	 * @param pressure
	 * @param tempreture 
	 * @param humidity 
	 * @param altitude 
	 *  
	 *
	 * @return WheatherCondition
	 */
	public WheatherCondition calculateWhetherCondition(double pressure,double tempreture, double humidity,double altitude)
	{
		
		if(pressure<1000.2 && altitude>50 && tempreture<8   )
		{
			if((humidity>=50 && humidity<=75))
			{
				return WheatherCondition.Snow;
			}
			else if( humidity>85)
			{
				return WheatherCondition.Rain;
			}
			else {
				return WheatherCondition.Sunny;
			}
		}
		else if(humidity>=85)
		{
			return WheatherCondition.Rain;
		}
		else
		{
			return WheatherCondition.Sunny;
		}
		
	}
	/**  
	 * This method is used to generate dates in range of 3 years from current date  
	 *  
	 *
	 * @return Date
	 */
	
	public  Date generateRandomDateTime()
	{
		Calendar lowerRangeDate=Calendar.getInstance();
		lowerRangeDate.setTimeInMillis(System.currentTimeMillis());
		lowerRangeDate.add(Calendar.YEAR, -3);
		
		Calendar upperRangeDate=Calendar.getInstance();
		upperRangeDate.setTimeInMillis(System.currentTimeMillis());
		
		
		Date randomDate = new Date(ThreadLocalRandom.current().nextLong(
				lowerRangeDate.getTimeInMillis(), upperRangeDate.getTimeInMillis()));
		
		return randomDate;

	}
	/**  
	 * to  generate random altitude of 20 to 500 meters
	 *  
	 *  
	 *
	 * @return altitude
	 */
	
	public  double generateRandomAltitude()
	{
		double altitude = ThreadLocalRandom.current().nextDouble(20, 200);
		
		return altitude;
	}
	/**  
	 *  to  generate humidity ranging between  5 to 98
	 *  I have used humidity as random because relative humidity is calculate by formula rh=p0/P1
	 *  where p0 is pressure of atmoshphere where as p1 is dew point pressure which is more calculated 
	 *  by scientific measures (eg wet bulb measure test)
	 * 
	 *  
	 *
	 * @return relativeHumidity
	 */
	
	public  double generateRandomRelativeHumidity()
	{
		double relativeHumidity = ThreadLocalRandom.current().nextDouble(5, 98);
	
		return relativeHumidity;
	}
	
	/**  
	 *  to  generate latitude ranging between  +-10 of given latitude
	 *  
	 *
	 * @return latitude
	 */
	
	public  double generateRandomLatitude(double latitude)
	{
		return ThreadLocalRandom.current().nextDouble(latitude-10, latitude+10);
	
	}
	/**  
	 *  to  generate longitude ranging between  +-10 of given longitude
	 *  
	 *
	 * @return longitude
	 */
	
	public  double generateRandomLongitude(double longitude)
	{
		return ThreadLocalRandom.current().nextDouble(longitude-10, longitude+10);
	}
	/**  
	 * This method is used to calculate temperature intensity weitage by months 
	 *  The summer months are April through September (April,May,June,July,August,Sep) on the Northern Hemisphere 
		and the winter months are October through March (Oct,Nov,Dec,Jan,Feb,March), and VICE VERSA for the Southern Hemisphere
		also the mid months intensity will be more than other months
	 * 
	 * @param lattitude
	 * @param altitude 
	 * @param atDateTime 
	 * 
	 *  
	 *
	 * @return wheitageOfIntensity
	 */
	
	public double calculateTempretureIntensityFactorByMonth(double lattitude, double altitude,Date atDateTime)
	{
		Calendar passedDate= Calendar.getInstance();
		passedDate.setTime(atDateTime);
		int currentMonth=passedDate.get(Calendar.MONTH);
		boolean inNorthernHemishphere=true;
		double wheitageOfIntensity=0.0d;
		
		//if location falls under southern hemishphere
		if(lattitude<0 )
		{
			inNorthernHemishphere=false;
			//to check current month is in summer
			if(summerMonthsForSouthernHemishphereMap.keySet().contains(currentMonth))
			{
				//since location is falled under southern hemishephere then winter months will be summer months
				wheitageOfIntensity=summerMonthsForSouthernHemishphereMap.get(currentMonth);
			}
			//current month falls in winter
			else
			{
				wheitageOfIntensity=winterrMonthsForSouthernHemishphereMap.get(currentMonth);
			}
		}
		//if location falls under northern hemishphere
		else 
		{
			//to check current month is in summer
			if(summerMonthsForNorthernHemishphereMap.keySet().contains(currentMonth))
			{
				//since location is falled under southern hemishephere then winter months will be summer months
				wheitageOfIntensity=summerMonthsForNorthernHemishphereMap.get(currentMonth);
			}
			//current month falls in winter
			else
			{
				wheitageOfIntensity=winterrMonthsForNorthernHemishphereMap.get(currentMonth);
			}
		}
		return wheitageOfIntensity;
	}
	
	/**  
	 * This method is used to calculate temperature intensity weigtage by day hours  
	 *  in noon we experience more temperature intensity then in evening and in night the weigtage scores are given for 
	 *  all individual hours of a day 
	 * 
	 * @param atDateTime
	 * @return wheitageOfIntensity
	 */
	
	public double calculateTempretureIntensityFactorByCurrentHours(Date atDateTime)
	{
		
		Calendar passedDate= Calendar.getInstance();
		passedDate.setTime(atDateTime);
		int currentHour=passedDate.get(Calendar.HOUR_OF_DAY);
		return tempIntensityKeyedByHour.get(currentHour);
		
	}
	/**  
	 * This method is used to convert farenhiegth to celcius by formula
	 *  (F - 32) / 1.8 = C
	 * @param farenhieghtDegree
	 * @return celciusDegree
	 */
	
	 public static double convertFareignhigthToCelcius(double farenhieghtDegree)
	 {
		 return (farenhieghtDegree-32)/1.8;
	 }
	 
	
	
	 /**  
		 * This method is used to convert farenhiegth to celcius by formula
		 *  (F - 32) / 1.8 = C
		 * @param farenhieghtDegree
		 * @return celciusDegree
		 */
	 public double calculateTempretureIntensityFactorByDay(Date atDateTime)
		{
			
			List<Integer>collectionOfDAys=new ArrayList<>();
			for(int i=0;i<31;i++){
				collectionOfDAys.add(i);
			}
			
			
			Calendar passedDate= Calendar.getInstance();
			passedDate.setTime(atDateTime);
			int currentDay=passedDate.get(Calendar.DAY_OF_MONTH);
			
			return getCyclicWiegtage(collectionOfDAys, 100, 10, 1, currentDay);
		}
	 /**  
		 *(Note: this method is incomplete) it will be used for calculation of tempreture intensity weigtage in cyclic graph 
		if we take 24 hour period tempreture at 12' clock noon will be very high then 3  then there will less instesity in
		evening and in nightso the the tempreture intensity can be defined as 12 noon>3pm>6pm>9pm>24
		then there will be reverse occuring of rise in tempreture ie 2am<4am<8am<10am<11am this algorithm is desinged to
		claculate this intenisty changes in cyclic manner .
		this alogrithm will be used defined the intensity of tempreture of a particular day in 30 days of month also
		 * @param
		 * @return wheitageOfIntensity
		 */
		
		
		public double getCyclicWiegtage(List<Integer>collectionList,double higherWietage,
				double lowerWeigtage,int elementOfHigherWietage,int item)
		{
			
			int midSizeOFCollection=collectionList.size()/2;
			int itemIndex=collectionList.indexOf(item);
			int elementOfHigherWietageIndex=collectionList.indexOf(elementOfHigherWietage);
			int midPointIndexRefrenceToMainElement=elementOfHigherWietageIndex;
			List<Integer> elementsOfMidRange= new ArrayList<Integer>();
			for(int inc=0;inc<midSizeOFCollection;inc++)
			{
				if(midPointIndexRefrenceToMainElement!=collectionList.size())
				{
					midPointIndexRefrenceToMainElement++;
					elementsOfMidRange.add(midPointIndexRefrenceToMainElement);
				}
				else
				{
					midPointIndexRefrenceToMainElement=0;
					elementsOfMidRange.add(midPointIndexRefrenceToMainElement);
				}
			}
			double midWiegtageVal=(higherWietage-((higherWietage-lowerWeigtage)/collectionList.size())*midSizeOFCollection);
			double weightedReturnVal=0.0;
			if(!elementsOfMidRange.contains(itemIndex))
			{
				int distance=0;
				if(midPointIndexRefrenceToMainElement>itemIndex)
				{
					distance=collectionList.size()-midPointIndexRefrenceToMainElement+itemIndex;
				}
				else
				{
					distance=itemIndex-midPointIndexRefrenceToMainElement;
				}
				weightedReturnVal=(midWiegtageVal+((higherWietage-lowerWeigtage)/collectionList.size())*distance);
			}
			else 
			{
				int distance=0;
				if(elementOfHigherWietageIndex<itemIndex)
				{
					distance=itemIndex-elementOfHigherWietageIndex;
				}
				else
				{
					distance=collectionList.size()-elementOfHigherWietageIndex+itemIndex;
				}
				weightedReturnVal=(higherWietage-((higherWietage-lowerWeigtage)/collectionList.size())*distance);
			}
			
			
			return weightedReturnVal;
		}
		
}
