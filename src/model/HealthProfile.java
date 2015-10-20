package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@XmlRootElement(name="healthprofile")
@XmlType(propOrder = { "weight", "height", "BMI", "lastUpdate" })
@XmlAccessorType(XmlAccessType.FIELD)
public class HealthProfile {
	private double weight; // in kg
	private double height; // in m
	private String lastUpdate;

	public void updateDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		lastUpdate = dateFormat.format(date);
	}

	public HealthProfile(double weight, double height) {
		this.weight = weight;
		this.height = height;
		updateDate();
		
	}

	public HealthProfile() {
		this.weight = 85.5;
		this.height = 1.72;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
		updateDate();
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
		updateDate();
	}
	public String toString() {
		return "Height="+height+", Weight="+weight;
	}

	@XmlElement(name="bmi")
	public double getBMI() {
		return this.weight/(Math.pow(this.height, 2));
	}
}
