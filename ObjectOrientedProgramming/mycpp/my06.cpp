#include <iostream>
#include <string>
#include <cstdlib>
#include <stdexcept>
#include <limits>
#include <ctime>
using namespace std;
///class to store and manipulate STUDENT values
class STUDENT {
	int roll, ///<member to hold roll number
		*marks; ///<member to hold marks obtained
	string name, ///<member to hold name
		course, ///<member to hold course
		admission; ///<member to hold time of admission
	bool validMarks; ///<member to hold validity of marks
	public:
		static const int maxSubjects = 5; ///<member to hold maximum number of subjects
		///constructor to initialize data members and validate class invariants
		STUDENT(int rl=0, string nm=string{}, string cr=string{}) : roll{rl}, marks{new int[maxSubjects]}, name{nm}, course{cr}, validMarks{false} {
			for(int i = 0; i < maxSubjects; ++i)
				marks[i] = 0;
			time_t t = time(0);
			admission = ctime(&t);
		}
		///getter function for roll
		int getRoll() {
			return roll;
		}
		///getter function for marks
		int* getMarks() {
			return marks;
		}
		///setter function for marks
		void setMarks(int *mks) {
			validMarks = true;
			for(int i = 0; i < maxSubjects; ++i)
				marks[i] = mks[i];
		}
		///getter function for setMarks
		bool getValidMarks() {
			return validMarks;
		}
		///getter function name
		string getName() {
			return name;
		}
		///getter function for course
		string getCourse() {
			return course;
		}
		///getter function for admission
		string getAdmission() {
			return admission;
		}
};
///class to store and manipulate BATCH values
class BATCH {
	int students, ///<member to hold students admitted
		capacity; ///<member to hold student capacity
	STUDENT* list; ///<member to hold array of students
	public:
		///constructor to initialize data members and validate class invariants
		BATCH(int cap=10) : students{0}, capacity{cap}, list{new STUDENT[cap]} {
		}
		///member function to admit a student
		void admission(string name, string course) {
			if(students == capacity)
				throw invalid_argument("capacity exceeded");
			list[students] = STUDENT{students + 1, name, course};
			students++;
		}
		///member function to examine student
		void examination(int roll, int *marks) {
			list[roll - 1].setMarks(marks);
		}
		///member function to check student roll validity
		bool checkStudent(int roll) {
			return (roll > 0) && (roll <= students);
		}
		///getter function for students
		int getStudents() {
			return students;
		}
		///getter function for list
		STUDENT* getList() {
			return list;
		}
};
///helper function to input and admit a student to batch
void admitStudent(BATCH& batch) {
	string name, course;
	cout << "enter name of student: ";
	cin >> name;
	cout << "enter course of admission: ";
	cin >> course;
	batch.admission(name, course);	
}
///helper function to input and examine a student in batch
void prepareMarksheet(BATCH& batch) {
	int roll, *marks = new int[STUDENT::maxSubjects];
	cout << "enter roll: ";
	if(!(cin >> roll) || !batch.checkStudent(roll))
		throw invalid_argument("invalid roll");
	cout << "enter marks in " << STUDENT::maxSubjects << " subjects: ";
	for(int i = 0; i < STUDENT::maxSubjects; ++i)
		if(!(cin >> marks[i]))
			throw invalid_argument("argument of invalid type");
	batch.examination(roll, marks);
}
///helper function to output student report
void printReport(STUDENT& student) {
	cout << "name: " << student.getName() << endl
		<< "roll: " << student.getRoll() << endl
		<< "course: " << student.getCourse() << endl
		<< "admission date: " << student.getAdmission()
		<< "marks: ";
	if(!student.getValidMarks())
		cout << "not set" << endl;
	else {
		int *marks = student.getMarks();
		for(int i = 0; i < STUDENT::maxSubjects; ++i)
			cout << marks[i] << " ";
		cout << endl;
	}
}
///helper function to output report of student in batch
void printStudentReport(BATCH& batch) {
	int roll;
	cout << "enter roll: ";
	if(!(cin >> roll) || !batch.checkStudent(roll))
		throw invalid_argument("invalid roll");
	STUDENT* list = batch.getList();
	printReport(list[roll - 1]);
}
///helper function to output report of batch
void printBatchReport(BATCH& batch) {
	STUDENT* list = batch.getList();
	for(int i = 0; i < batch.getStudents(); ++i) {
		printReport(list[i]);
		cout << endl;
	}
}
int main() {
	int c;
	BATCH batch;
	cout << endl << "MENU:" << endl << endl << "<0> exit" << endl
		<< "<1> create a new batch" << endl
		<< "<2> admit a student" << endl
		<< "<3> prepare student marksheet" << endl
		<< "<4> print student report" << endl
		<< "<5> print batch report" << endl;
	do {
		cout << endl << "enter operation code: ";
		try {
			if(!(cin >> c))
				throw invalid_argument("arguments of invalid type");
			switch(c) {
				case 0: cout << "terminating program" << endl; break;
				case 1: int n; cout << "enter batch size: ";
						if(!(cin >> n))  throw invalid_argument("arguments of invalid type");
						batch = BATCH{n}; break;
				case 2:	admitStudent(batch);
						cout << "roll no: " << batch.getStudents() << endl; break;
				case 3: prepareMarksheet(batch); break;
				case 4: printStudentReport(batch); break;
				case 5: printBatchReport(batch); break;
				default: throw invalid_argument("arguments of invalid type");
			}
		}
		catch(invalid_argument e) {
			cin.clear();
			cin.ignore(numeric_limits<streamsize>::max(), '\n');
			cout << e.what() << ": try again" << endl;
		}
	}	while(c);
	return 0;
}
