package myjava.my01;
//class to store and manipulate Batch values
public class Batch {
	private int students, ///<member to hold students admitted
		capacity; ///<member to hold student capacity
	private Student list[]; ///<member to hold array of students
	///constructor to initialize data members and validate class invariants
	public Batch(int cap) {
		students = 0;
		capacity = cap;
		list = new Student[cap];
	}
	///member function to admit a student
	public void admission(String name, String course) {
		list[students] = new Student(students + 1, name, course);
		students++;
	}
	///member function to examine student
	public void examination(int roll, int marks[]) {
		list[roll - 1].setMarks(marks);
	}
	///member function to check student roll validity
	public boolean checkStudent(int roll) {
		return (roll > 0) && (roll <= students);
	}
	///getter function for students
	public int getStudents() {
		return students;
	}
	///getter function for list
	public Student[] getList() {
		return list;
	}
}
