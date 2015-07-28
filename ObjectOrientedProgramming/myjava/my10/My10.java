package myjava.my10;
import java.util.*;
import java.io.*;

public class My10 {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private ArrayList<Patient> list = new ArrayList<Patient>();
	///helper function to input and admit a patient to hospital
	public void admit() throws IOException {
		System.out.print("enter patient details:\nsocial security ID: ");
		int pID = Integer.parseInt(br.readLine());
		System.out.print("name: ");
		String pNm = br.readLine();
		System.out.print("age: ");
		int a = Integer.parseInt(br.readLine());
		System.out.print("doctor: ");
		String dctr = br.readLine();
		String chckin = new Date().toString();
		list.add(new Patient(pID, a, pNm, chckin, dctr));
	}
	///helper function to find a patient in hospital
	public int find() throws IOException {
		System.out.print("enter social security ID: ");
		int ID = Integer.parseInt(br.readLine()), i;
		for(i = 0; i < list.size(); ++i)
			if(list.get(i).getID() == ID)
				return i;
		return -1;
	}
	///helper function to discharge a patient from hospital
	public void discharge() throws IOException {
		int ID = check();
		if(ID == -1)
			return;
		list.remove(list.get(ID));
		System.out.println("discharge time: " + new Date().toString());
	}
	///helper function to examine a patient in hospital
	public void examine() throws Exception {
		int ID = find();
		System.out.println();
		if(ID == -1) {
			System.out.println("patient not found");
			return;
		}
		String vitals[] = new String[Patient.parameters.length];
		for(int i = 0; i < Patient.parameters.length; ++i) {
			System.out.print("enter " + Patient.parameters[i] + ": ");
			vitals[i] = br.readLine();
		}
		list.get(ID).examine(vitals);
	}
	///helper function to check if a patient is in hospital
	public int check() throws IOException {
		int ID = find();
		System.out.println();
		if(ID == -1) {
			System.out.println("patient not found");
			return -1;
		}
		Patient patient = list.get(ID);
		System.out.println("patient details:\nsocial security ID: " + patient.getID() + "\nname: " + patient.getName() + "\nage: " + patient.getAge() + "\ndoctor assigned: " + patient.getDoctor() + "\ncheckin time: " + patient.getCheckin());
		if(patient.getExamined()) {
			String vitals[] = patient.getVitals();
			for(int i = 0; i < Patient.parameters.length; ++i)
				System.out.println(Patient.parameters[i] + ": " + vitals[i]);
		}
		else
			System.out.println("not yet examined");
		return ID;
	}
	public static void main(String args[]) {
		int c = 1;
		double amount;
		My10 obj = new My10();
		System.out.println("\nMENU:\n\n<0> exit\n<1> admit patient\n<2> discharge\n<3> examine\n<4> check");
		do {
			System.out.print("\nenter operation code: ");
			try {
				c = Integer.parseInt(obj.br.readLine());
				switch(c) {
					case 0: System.out.println("terminating program"); break;
					case 1: obj.admit(); break;
					case 2:	obj.discharge(); break;
					case 3: obj.examine(); break;
					case 4: obj.check(); break;
					default: throw new Exception("arguments of invalid type");
				}
			}
			catch(Exception e) {
				System.out.println(e.toString() + ": try again");
			}
		}	while(c != 0);
	}
}
