package myjava.my10;
import java.io.*;
///class to store and manipulate Patient values
public class Patient {
	public static final String parameters[] = {"height", "weight", "blood pressure", "temperature"}; ///<member to hold parameters of examination
	private int ID, ///<member to hold ID
		age; ///<member to hold age
	private String name, ///<member to hold name
		checkin, ///<member to hold checkin time
		doctor, ///<member to hold doctor
		vitals[]; ///<member to hold vitals
	private boolean examined; ///<member to hold examination state
	///constructor to initialize data members and validate class invariants
	public Patient(int pID, int a, String pNm, String chkin, String dctr) {
		ID = pID;
		name = pNm;
		age = a;
		checkin = chkin;
		doctor = dctr;
		examined = false;
		vitals = new String[parameters.length];
	}
	///getter function for ID
	public int getID() {
		return ID;
	}
	///getter function for age
	public int getAge() {
		return age;
	}
	///getter function for name
	public String getName() {
		return name;
	}
	///getter function for checkin
	public String getCheckin() {
		return checkin;
	}
	///getter function for doctor
	public String getDoctor() {
		return doctor;
	}
	///getter function for vitals
	public String[] getVitals() {
		return vitals;
	}
	///getter function for examined
	public boolean getExamined() {
		return examined;
	}
	///member function to examine a patient
	public void examine(String parameters[]) throws Exception {
		if(parameters.length != Patient.parameters.length)
			throw new Exception("insufficient data");
		for(int i = 0; i < Patient.parameters.length; ++i)
			vitals[i] = parameters[i];
		examined = true;
	}
}
