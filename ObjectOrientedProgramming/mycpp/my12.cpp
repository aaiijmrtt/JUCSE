#include <iostream>
#include <fstream>
#include <sstream>
#include <cstdlib>
#include <cstdio>
#include <string>
#include <stdexcept>
#include <limits>
#include <ctime>
using namespace std;
///enum to check state of transaction
enum STATE {issues, returns};
///enum to check state of file operation
enum CHECK {erase, replace};
///abstract class to act as INTERFACE for LIBRARY
class LIBRARYINTERFACE {
	public:
		virtual string recordIt() = 0;
		virtual int findIt(string, int) = 0;
		virtual void fixIt(string, string, STATE) = 0;
		///member function to read a file
		static void read(string filename) {
			ifstream file(filename, ios::in);
			string line;
			while(getline(file, line))
				cout << line << endl;
			file.close();
		}
		///member function to append to a file
		static void append(string filename, string record) {
			ofstream file(filename, ios::app);
			file << record << endl;
			file.close();
		}
		///member function to replace in a file
		static void replace(string filename, int number, string record, CHECK check) {
			ifstream fileIn(filename, ios::in);
			ofstream fileOut("temp.txt", ios::out);
			string line;
			for(int i = 0; i < number; ++i) {
				getline(fileIn, line);
				fileOut << line << endl;
			}
			getline(fileIn, line);
			if(check == CHECK::replace)
				fileOut << record << endl;
			while(getline(fileIn, line))
				fileOut << line << endl;
			fileIn.close();
			fileOut.close();
			remove(filename.c_str());
			rename("temp.txt",filename.c_str());
		}
};
///abstract class to act as INTERFACE for PAPER: books and journals
class PAPERINTERFACE : public LIBRARYINTERFACE {
	protected:
		int ID; ///<member to hold ID of books and journals
		clock_t issued; ///<member to hold time of issue
	public:
		///constructor to initialize data members and validate class invariants
		PAPERINTERFACE(int id=-1) : ID{id}, issued{clock()} {
		}
		///getter function for ID
		int getID() {
			return ID;
		}
		///member function to find book or journal and return period of issue
		int findIt(string filename, int query) override {
			ifstream in(filename);
			int match;
			clock_t tick;
			string line;
			while(getline(in, line)) {
				istringstream stream(line);
				stream >> match;
				if(match == query) {
					stream >> tick;
					in.close();
					return clock() - tick;
				}
			}
			in.close();
			return 0;
		}
		///member function to log issue or return of book or journal
		void fixIt(string filename, string record, STATE state) override {
			append(filename, record);
		}
};
///class to store and manipulate BOOK values
class BOOK : public PAPERINTERFACE {
	string author, ///<member to hold author of book
			title; ///<member to hold title of book
	///constructor to initialize data members and validate class invariants
	BOOK(int id=-1, string aut=string{}, string tit=string{}):
		PAPERINTERFACE{id}, author{aut}, title{tit} {
	}
	public:
		///member function to record transaction of book
		string recordIt() override {
			stringstream fields{};
			fields << ID << "\t" << issued << "\t" << author << "\t" << title;
			return fields.str();
		}
		///member function to input a book
		static BOOK* input(STATE state) {
			string title{}, author{};
			int ID;
			switch(state) {
				case issues:	cout << "enter book title: ";
								cin >> title;
								cout << "enter book author: ";
								cin >> author;
								srand(clock());
								ID = rand();
								cout << "book ID: " << ID << endl;
								return new BOOK{ID, author, title};
				case returns:	cout << "enter book ID: ";
								if(!(cin >> ID))
									throw invalid_argument("argument of invalid type");
								return new BOOK{ID};
			}
		}
};
///class to store and manipulate JOURNAL values
class JOURNAL : public PAPERINTERFACE {
	string author, ///<member to hold author of journal
			title; ///<member to hold title of journal
	int issue, ///<member to hold issue number of journal
		volume; ///<member to hold volume number of journal
	///constructor to initialize data members and validate class invariants
	JOURNAL(int id=-1, string aut=string{}, string tit=string{}, int is=0, int vol=0):
		PAPERINTERFACE{id}, author{aut}, title{tit}, issue{is}, volume{vol} {
	}
	public:
		///member function to record transaction of journal
		string recordIt() override {
			stringstream fields{};
			fields << ID << "\t" << issued << "\t" << author << "\t" << title
				<< "\t" << volume << "\t" << issue;
			return fields.str();
		}
		///member function to input a journal
		static JOURNAL* input(STATE state) {
			string title{}, author{};
			int issue, volume, ID;
			JOURNAL journal;
			switch(state) {
				case issues:	cout << "enter journal title: ";
								cin >> title;
								cout << "enter journal author: ";
								cin >> author;
								cout << "enter issue number: ";
								if(!(cin >> issue))
									throw invalid_argument("argument of invalid type");
								cout << "enter volume number: ";
								if(!(cin >> volume))
									throw invalid_argument("argument of invalid type");
								srand(clock());
								ID = rand();
								cout << "journal ID: " << ID << endl;
								return new JOURNAL{ID, author, title, issue, volume};
				case returns:	cout << "enter journal ID: ";
								if(!(cin >> ID))
									throw invalid_argument("argument of invalid type");
								return new JOURNAL{ID};
			}
		}
};
///abstract class to act as INTERFACE for PEOPLE: student and faculty
class PEOPLEINTERFACE : public LIBRARYINTERFACE {
	int max, ///<member to hold maximum issue limit
		fine; ///<member to hold fine applicable
	protected:
		int ID, ///<member to hold ID of student or faculty
			issues; ///<member to hold number of issues of student or faculty
	public:
		///constructor to initialize data members and validate class invariants
		PEOPLEINTERFACE(int mx, int fn, int id=-1, int is=0) : max{mx}, fine{fn}, ID{id}, issues{is} {
			if(ID == -1) {
				srand(clock());
				ID = rand();
			}
		}
		///getter function for ID
		int getID() {
			return ID;
		}
		///member function to find student or faculty and return number of issues
		int findIt(string filename, int query) override {
			ifstream in(filename);
			int match, i;
			string line;
			for(i = 0, issues = 0; getline(in, line); ++i) {
				istringstream stream(line);
				stream >> match;
				if(match == query) {
					stream >> issues;
					break;
				}
			}
			in.close();
			return i;
		}
		///member function to log issue or return of student or faculty
		void fixIt(string filename, string record, STATE state) override {
			stringstream fields(record);
			int ID, line;
			fields >> ID;
			line = findIt(filename, ID);
			switch(state) {
				case STATE::issues: issues++; break;
				case STATE::returns: issues--; break;
			}
			if((issues < 0) || (issues > max))
				throw invalid_argument("exceeded library limit");
			else if(issues == 0)
				replace(filename, line, recordIt(), CHECK::erase);
			else
				replace(filename, line, recordIt(), CHECK::replace);
		}
		///member function to record transaction of student or faculty
		string recordIt() {
			stringstream ss{};
			ss << ID << "\t" << issues;
			return ss.str();
		}
		///getter function for fine
		int getFine() {
			return fine;
		}
};
///class to store and manipulate STUDENT values
class STUDENT : public PEOPLEINTERFACE {
	///constructor to initialize data members and validate class invariants
	STUDENT(int id, int is=0) : PEOPLEINTERFACE{STUDENT::max, STUDENT::fine, id, is} {
	}
	public:
		static const int max = 2; ///<member to hold maximum issue limit for all faculty
		static const int fine = 1; ///<member to hold fine applicable for all faculty
		///member function to input a journal
		static STUDENT* input(STATE state) {
			int card;
			cout << "enter card number: ";
			if(!(cin >> card))
				throw invalid_argument("argument of invalid type");
			return new STUDENT{card};
		}
};
///class to store and manipulate FACULTY values
class FACULTY : public PEOPLEINTERFACE {
	///constructor to initialize data members and validate class invariants
	FACULTY(int id, int is=0) : PEOPLEINTERFACE{FACULTY::max, FACULTY::fine, id, is} {
	}
	public:
		static const int max = 10; ///<member to hold maximum issue limit for all faculty
		static const int fine = 0; ///<member to hold fine applicable for all faculty
		///member function to input a journal
		static FACULTY* input(STATE state) {
			int card;
			cout << "enter card number: ";
			if(!(cin >> card))
				throw invalid_argument("argument of invalid type");
			return new FACULTY{card};
		}
};
///helper function to provide issue and return slips for student and faculty
void slip(string filenames[], STATE state) {
	int c, line;
	cout << "<1> request student book slip" << endl
		<< "<2> request student journal slip" << endl
		<< "<3> request faculty book slip" << endl
		<< "<4> request faculty journal slip" << endl
		<< endl << "enter operation code: ";
	if(!(cin >> c))
		throw invalid_argument("arguments of invalid type");
	PAPERINTERFACE *paper;
	PEOPLEINTERFACE *people;
	switch(c) {
		case 1:	paper = BOOK::input(state);
				people = STUDENT::input(state);
				break;
		case 2:	paper = JOURNAL::input(state);
				people = STUDENT::input(state);
				break;
		case 3:	paper = BOOK::input(state);
				people = FACULTY::input(state);
				break;
		case 4:	paper = JOURNAL::input(state);
				people = FACULTY::input(state);
				break;
		default: cout << "invalid operation code" << endl;
	}
	people->fixIt(filenames[2 + (c - 1) / 2], people->recordIt(), state);
	paper->fixIt(filenames[(c - 1) % 2], paper->recordIt(), state);
	if(state == STATE::returns)
		cout << "fine: " << (people->getFine() * paper->findIt(filenames[(c - 1) % 2], paper->getID()) / (24 * 3600 * 1000));
	cout << endl;
}
int main() {
	int c;
	string filenames[] = {"books.txt", "journals.txt", "student.txt", "faculty.txt"};
	do {
		cout << endl << "MENU:" << endl
			<< endl << "<0> exit" << endl
			<< "<1> issues" << endl
			<< "<2> returns" << endl
			<< "<3> view log" << endl
			<< endl << "enter operation code: ";
		try {
			if(!(cin >> c))
				throw invalid_argument("arguments of invalid type");
			switch(c) {
				case 0: cout << "terminating program" << endl; break;
				case 1:	slip(filenames, STATE::issues); break;
				case 2: slip(filenames, STATE::returns); break;
				case 3: cout << "<1> view book log" << endl
							<< "<2> view journal log" << endl
							<< "<3> view student log" << endl
							<< "<4> view faculty log" << endl
							<< endl << "enter operation code: ";
						if(!(cin >> c))
							throw invalid_argument("arguments of invalid type");
						if((c > 0) && (c < 5)) {
							switch(c) {
								case 1: cout << "ID\tTime\tAuthor\tTitle" << endl; break;
								case 2: cout << "ID\tTime\tAuthor\tTitle\tVolume\tIssue" << endl; break;
								case 3: cout << "ID\tIssues" << endl; break;
								case 4: cout << "ID\tIssues" << endl; break;
							}
							LIBRARYINTERFACE::read(filenames[c - 1]);
						}
						break;
				default: cout << "invalid operation code" << endl;
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
