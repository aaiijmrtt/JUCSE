package myjava.my01;
import java.util.*;
///class to store and manipulate Student values
public class Student {
	private int roll, ///<member to hold roll number
		marks[]; ///<member to hold marks obtained
	private String name, ///<member to hold name
		course, ///<member to hold course
		admission; ///<member to hold time of admission
	private boolean validMarks; ///<member to hold validity of marks
	public static final int maxSubjects = 5; ///<member to hold maximum number of subjects
	///constructor to initialize data members and validate class invariants
	public Student(int rl, String nm, String cr) {
		roll = rl;
		marks = new int[maxSubjects];
		name = nm;
		course = cr;
		validMarks = false;
		Date admit = new Date();
		admission = admit.toString();
	}
	///getter function for roll
	public int getRoll() {
		return roll;
	}
	///getter function for marks
	public int[] getMarks() {
		return marks;
	}
	///setter function for marks
	public void setMarks(int mks[]) {
		validMarks = true;
		for(int i = 0; i < maxSubjects; ++i)
			marks[i] = mks[i];
	}
	///getter function for setMarks
	public boolean getValidMarks() {
		return validMarks;
	}
	///getter function name
	public String getName() {
		return name;
	}
	///getter function for course
	public String getCourse() {
		return course;
	}
	///getter function for admission
	public String getAdmission() {
		return admission;
	}
}
