package myjava.my02;
import myjava.my01.Student;
import myjava.my01.Batch;
///class to store and manipulate Depart values
public class Department extends Batch {
	private String name; ///<member to hold department name
	///constructor to initialize data members and validate class invariants
	public Department(int cap, String nm) {
		super(cap);
		name = nm;
	}
	///getter function for name
	public String getName() {
		return name;
	}
	///member function to calculate total marks of student
	private int studentTotal(Student student) {
		if(!student.getValidMarks())
			return -1;
		int i, total, marks[] = student.getMarks();
		for(i = total = 0; i < Student.maxSubjects; ++i)
			total += marks[i];
		return total;
	}
	///member function to sort students by marks
	public void sortList() {
		int i, j, begin = 0, end = getStudents();
		Student swap, list[] = getList();
		for(i = 0; i < getStudents() - 1; ++i) {
			for(j = 0; j < end; ++j)
				if(studentTotal(list[j]) < studentTotal(list[j + 1])) {
					swap = list[j];
					list[j] = list[j + 1];
					list[j + 1] = swap;
					begin = j;
				}
			end = begin;
			begin = 0;
		}
	}
}
