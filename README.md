# Weather Simulation Project

to run-->WeatherSimulationEngine.java  main method is entry point to simulation engine

This project is used to simulate Weather data at the entry point of the program it will ask for fallowing inputs:

1)no of records to generate 

2)latitude-->+-10 range of random latitudes lower and upper range will be created on behalf of entered latitude

3)longitude-->+-10 range of random longitudes lower and upper range will be created on behalf of entered longitude

4)file path-->to genrate the results to particular file.

output format:location(latitude,longitude,altitude)|datetime|condition|temperature|pressure|humidity

output generation alogorithms

latitude-->+-10 range of random latitudes lower and upper range will be created on behalf of entered latitude

longitude-->+-10 range of random longitudes lower and upper range will be created on behalf of entered longitude

altitude-->random value from a range of 2 to 200 will be picked

date-->any random date from a range of current date to past 3 years can be picked 

tempereture-->
	a)tempreture of any place is affected by its latitude and altitude there is a temperature relationship between latitude and altitude for more details  visit http://webinquiry.org/examples/temps/
	
		Temperature relation ship with altitude -->Temperature = -0.0026* (Elevation in feet) 
		Temperature relation ship with latitude -->
		For locations below 20°N: Temperature = 80°F.
		For locations between 20°N and 60°N: Temperature = -0.988 *(latitude) + 96.827°F.
		
	b)The tempreture result from step 'a' will be then anlaysed by tempreture intensity by months ,The summer months are April through September (April,May,June,July,August,Sep) on the Northern Hemisphere (positive latiudes) and the winter months are October through March (Oct,Nov,Dec,Jan,Feb,March), and VICE VERSA for the Southern Hemisphere(negitive latiudes).
		also the mid months intensity will be more than other months, so every months is having there own intensity level which will be furher	act on old tempreture from step 'a'
		
	c)The tempreture result from step 'b' will be then anlaysed by tempreture intensity by hours in noon we experience more temperature intensity then in evening and in night the weigtage scores are given for all individual hours of a day 
	
pressure-->pressure at given altitude can be also calculated by below formula
	   formula for calculation is 
	   P=P0e^((-mu*g*h)\RT)
		where
		p0- pressure at see level -101325
		mu - Molar mass of Earth's air, 0.0289644 kg/mol
		g - Gravitational acceleration, 9.80665 m/(s*s)
		h - Height difference, meters
		R - Universal gas constant for air, 8.31432 N·m /(mol·K)
		T - Air temperature
		
humidity-->random humidity ranging between  5 to 98
	   I have used humidity as random because relative humidity is calculate by formula rh=p0/P1  where p0 is pressure of atmoshphere where as p1 is dew point pressure which is more calculated  by scientific measures (eg wet bulb measure test).
Condition --> condition can be determine as sunny,rain  and snow on behalf pressure, temperature,humidity,alititude as all factors
	 *  snow->it is assumed that altitudes with higher values , less pressure and less temperature can cause of snow
	 
	 * rain->with high humidity,moderate range of altitudes and mild temperature
	 
	 * sunny-->else all conditions
	 
WeatherSimulationEngine.java  main method is 
entry point to simulation engine
