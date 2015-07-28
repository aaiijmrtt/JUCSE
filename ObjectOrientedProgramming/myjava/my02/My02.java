package myjava.my02;
import myjava.my01.Student;
import java.io.*;

public class My02 {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private Department department;
	///helper function to input and admit a student to department
	public void admitStudent(Department department) throws IOException {
		String name, course;
		System.out.print("enter name of student: ");
		name = br.readLine();
		System.out.print("enter course of admission: ");
		course = br.readLine();
		department.admission(name, course);	
	}
	///helper function to input and examine a student in department
	public void prepareMarksheet(Department department) throws IOException {
		int roll, marks[] = new int[Student.maxSubjects];
		String temp[];
		System.out.print("enter roll: ");
		roll = Integer.parseInt(br.readLine());
		System.out.print("enter marks in " + Student.maxSubjects + " subjects: ");
		temp = br.readLine().split(" ");
		for(int i = 0; i < Student.maxSubjects; ++i)
			marks[i] = Integer.parseInt(temp[i]);
		department.examination(roll, marks);
	}
	///helper function to output student report
	public void printReport(Student student) {
		System.out.print("name: " + student.getName() + "\n"
			+ "roll: " + student.getRoll() + "\n"
			+ "course: " + student.getCourse() + "\n"
			+ "admission date: " + student.getAdmission() + "\n"
			+ "marks: ");
		if(!student.getValidMarks())
			System.out.println("not set");
		else {
			int marks[] = student.getMarks();
			for(int i = 0; i < Student.maxSubjects; ++i)
				System.out.print(marks[i] + " ");
			System.out.println();
		}
	}
	///helper function to output report of student in department
	public void printStudentReport(Department department) throws IOException {
		int roll;
		System.out.print("enter roll: ");
		roll = Integer.parseInt(br.readLine());
		Student list[] = department.getList();
		this.printReport(list[roll - 1]);
	}
	///helper function to output report of department
	public void printDepartmentReport(Department department) {
		System.out.println("department name: " + department.getName());
		Student list[] = department.getList();
		for(int i = 0; i < department.getStudents(); ++i) {
			System.out.println();
			this.printReport(list[i]);
		}
	}
	public static void main(String args[]) {
		int c = 1;
		My02 obj = new My02();
		System.out.println("MENU:\n\n<0> exit\n<1> create a new department\n<2> admit a student\n<3> prepare student marksheet\n<4> print student report\n<5> print batch report\n<6> sort students in department by results");
		do {
			System.out.print("\nenter operation code: ");
			try {
				c = Integer.parseInt(obj.br.readLine());
				switch(c) {
					case 0: System.out.println("terminating program"); break;
					case 1: int n; System.out.print("enter department size: ");
							n = Integer.parseInt(obj.br.readLine());
							String nm; System.out.print("enter department name: ");
							nm = obj.br.readLine();
							obj.department = new Department(n,nm); break;
					case 2:	obj.admitStudent(obj.department);
							System.out.println("roll no: " + obj.department.getStudents()); break;
					case 3: obj.prepareMarksheet(obj.department); break;
					case 4: obj.printStudentReport(obj.department); break;
					case 5: obj.printDepartmentReport(obj.department); break;
					case 6:	obj.department.sortList(); break;
					default: throw new Exception("arguments of invalid type");
				}
			}
			catch(Exception e) {
				System.out.println(e.toString() + ": try again");
			}
		}	while(c != 0);
	}
}
