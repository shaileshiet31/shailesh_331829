/**
 * 
 */
package com.tcs.climate.simulation.test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import junit.framework.TestCase;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tcs.climate.simulation.bean.PlacesClimateBean;
import com.tcs.climate.simulation.engine.WhetherSimulationEngine;

/**
 * @author shailesh_331829
	May 7, 2017
 *
 */
public class WhetherSimulationEngineTest extends TestCase {

	/**
	 * @throws java.lang.Exception
	 */
	List<PlacesClimateBean>placesClimateBeanList=null;
	@Before
	public void setUp() throws Exception 
	{
		DateTimeFormatter df=DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z");
	
		
		
		placesClimateBeanList=new ArrayList<PlacesClimateBean>();
		
		PlacesClimateBean  placesClimateBean=new PlacesClimateBean();
		placesClimateBean.setLattitude(-39.00);
		placesClimateBean.setAltitude(57.15);
		placesClimateBean.setLocalTime(df.parseDateTime("2016-07-06T02:31:52Z").toDate());
		placesClimateBean.setTempreture(1.19);
		placesClimateBeanList.add(placesClimateBean);
		
		PlacesClimateBean  placesClimateBean1=new PlacesClimateBean();
		placesClimateBean1.setLattitude(-31.29);
		placesClimateBean1.setAltitude(154.31);
		placesClimateBean1.setLocalTime(df.parseDateTime("2015-03-06T22:17:31Z").toDate());
		placesClimateBean1.setTempreture(13.84);
		placesClimateBeanList.add(placesClimateBean1);
		
		PlacesClimateBean  placesClimateBean2=new PlacesClimateBean();
		placesClimateBean2.setLattitude(-42.34);
		placesClimateBean2.setAltitude(45.33);
		placesClimateBean2.setLocalTime(df.parseDateTime("2015-05-14T06:56:17Z").toDate());
		placesClimateBean2.setTempreture(3.93);
		placesClimateBeanList.add(placesClimateBean2);
		
		PlacesClimateBean  placesClimateBean3=new PlacesClimateBean();
		placesClimateBean3.setLattitude(-38.84);
		placesClimateBean3.setAltitude(187.09);
		placesClimateBean3.setLocalTime(df.parseDateTime("2016-01-11T23:34:57Z").toDate());
		placesClimateBean3.setTempreture(15.27);
		placesClimateBeanList.add(placesClimateBean3);
		
		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		placesClimateBeanList=null;
	}

	@Test
	public void testcalculateTempretureByFactors() throws Exception 
	{
		WhetherSimulationEngine whetherSimulationEngine=new WhetherSimulationEngine();
		for(PlacesClimateBean placesClimateBean:placesClimateBeanList)
		{
			double acutalTempreture=whetherSimulationEngine.calculateTempretureByFactors(placesClimateBean.getLattitude(), placesClimateBean.getAltitude(), placesClimateBean.getLocalTime());
			
			assertEquals(new Double(placesClimateBean.getTempreture()).intValue(), new Double(acutalTempreture).intValue());
			
		}
		
		
	}

}
