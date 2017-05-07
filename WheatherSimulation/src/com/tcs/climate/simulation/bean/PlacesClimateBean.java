package com.tcs.climate.simulation.bean;

import java.util.Date;
/**
 * @author shailesh_331829
	May 7, 2017
 *
 */

public class PlacesClimateBean
{
	
	String locationName=null;
	double lattitude=0.0d;
	double longitude=0.0d;
	double altitude=0.0d;
	double tempreture=0.0d;
	double pressure=0.0d;
	Date localTime=null;
	WheatherCondition condition;
	
	
	public PlacesClimateBean() {
		super();
	}

	public PlacesClimateBean(double lattitude, double longitude,
			double altitude, Date localTime) {
		super();
		this.lattitude = lattitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.localTime = localTime;
	}
	
	public String getLocationName() {
		return locationName;
	}
	
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public double getLattitude() {
		return lattitude;
	}
	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getAltitude() {
		return altitude;
	}
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
	public double getTempreture() {
		return tempreture;
	}
	public void setTempreture(double tempreture) {
		this.tempreture = tempreture;
	}
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	public Date getLocalTime() {
		return localTime;
	}
	public void setLocalTime(Date localTime) {
		this.localTime = localTime;
	}
	public WheatherCondition getCondition() {
		return condition;
	}
	public void setCondition(WheatherCondition condition) {
		this.condition = condition;
	}
	

}
 