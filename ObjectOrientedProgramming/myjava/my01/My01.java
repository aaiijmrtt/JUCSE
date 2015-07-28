package myjava.my01;
import java.io.*;

public class My01 {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private Batch batch;
	///helper function to input and admit a student to batch
	public void admitStudent(Batch batch) throws IOException {
		String name, course;
		System.out.print("enter name of student: ");
		name = br.readLine();
		System.out.print("enter course of admission: ");
		course = br.readLine();
		batch.admission(name, course);	
	}
	///helper function to input and examine a student in batch
	public void prepareMarksheet(Batch batch) throws IOException {
		int roll, marks[] = new int[Student.maxSubjects];
		String temp[];
		System.out.print("enter roll: ");
		roll = Integer.parseInt(br.readLine());
		System.out.print("enter marks in " + Student.maxSubjects + " subjects: ");
		temp = br.readLine().split(" ");
		for(int i = 0; i < Student.maxSubjects; ++i)
			marks[i] = Integer.parseInt(temp[i]);
		batch.examination(roll, marks);
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
	///helper function to output report of student in batch
	public void printStudentReport(Batch batch) throws IOException {
		int roll;
		System.out.print("enter roll: ");
		roll = Integer.parseInt(br.readLine());
		Student list[] = batch.getList();
		this.printReport(list[roll - 1]);
	}
	///helper function to output report of batch
	public void printBatchReport(Batch batch) {
		Student list[] = batch.getList();
		for(int i = 0; i < batch.getStudents(); ++i) {
			System.out.println();
			this.printReport(list[i]);
		}
	}
	public static void main(String[] args) {
		int c = 1;
		My01 obj = new My01();
		System.out.println("MENU:\n\n<0> exit\n<1> create a new batch\n<2> admit a student\n<3> prepare student marksheet\n<4> print student report\n<5> print batch report");
		do {
			System.out.print("\nenter operation code: ");
			try {
				c = Integer.parseInt(obj.br.readLine());
				switch(c) {
					case 0: System.out.println("terminating program"); break;
					case 1: int n; System.out.print("enter batch size: ");
							n = Integer.parseInt(obj.br.readLine());
							obj.batch = new Batch(n); break;
					case 2:	obj.admitStudent(obj.batch);
							System.out.println("roll no: " + obj.batch.getStudents()); break;
					case 3: obj.prepareMarksheet(obj.batch); break;
					case 4: obj.printStudentReport(obj.batch); break;
					case 5: obj.printBatchReport(obj.batch); break;
					default: throw new Exception("arguments of invalid type");
				}
			}
			catch(Exception e) {
				System.out.println(e.toString() + ": try again");
			}
		}	while(c != 0);
	}
}
