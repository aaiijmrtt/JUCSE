#include <iostream>
#include <stdexcept>
#include <string>
#include <limits>
using namespace std;
///class to store and manipulate TIME values
class TIME {
	int hours, ///<member to hold hours
		minutes, ///<member to hold minutes
		seconds; ///<member to hold seconds 
	public:
		static const int maxHours = 24, ///<member to hold upper limit of hours
						maxMinutes = 60, ///<member to hold upper limit of minutes
						maxSeconds = 60; ///<member to hold upper limit of seconds
		///constructor to initialize data members and validate class invariants
		TIME(int hr=0, int min=0, int sec=0) : hours{hr}, minutes{min}, seconds{sec} {
			if(!((hours < maxHours) && (minutes < maxMinutes) && (seconds < maxSeconds) && (hours >= 0) && (minutes >= 0) && (seconds >= 0))) {
				hours = minutes = seconds = 0;
				throw invalid_argument("arguments out of range");
			}
		}
		///getter function for hours
		int getHours() {
			return hours;
		}
		///getter function for minutes
		int getMinutes() {
			return minutes;
		}
		///getter function for seconds
		int getSeconds() {
			return seconds;
		}
		///member function to add minutes
		void addMinutes(int min) {
			minutes += min;
			hours += minutes / maxMinutes;
			minutes %= maxMinutes;
			hours %= maxHours;
		}
};
///helper function to output TIME object in 24 hour format
void get24(TIME t) {
	cout << "the time is " << t.getHours() << ":" << t.getMinutes() << ":" << t.getSeconds() << endl;
}
///helper function to output TIME object in 12 hour format
void get12(TIME t) {
	if(t.getHours() < TIME::maxHours / 2)
		cout << "the time is " << t.getHours() << ":" << t.getMinutes() << ":" << t.getSeconds() << " AM" << endl;
	else
		cout << "the time is " << t.getHours() - TIME::maxHours / 2 << ":" << t.getMinutes() << ":" << t.getSeconds() << " PM" << endl;
}
///helper function to input TIME object in 24 hour format
TIME set24() {
	cout << "what time is it? ";
	int hr, min, sec;
	((cin >> hr).ignore(numeric_limits<streamsize>::max(), ':') >> min).ignore(numeric_limits<streamsize>::max(), ':') >> sec;
	if(!cin)
		throw invalid_argument("arguments of invalid type");
	TIME t{hr, min, sec};
	return t;
}
///helper function to input TIME object in 12 hour format
TIME set12() {
	cout << "what time is it? ";
	int hr, min, sec;
	string ampm;
	((cin >> hr).ignore(numeric_limits<streamsize>::max(), ':') >> min).ignore(numeric_limits<streamsize>::max(), ':') >> sec >> ampm;
	if(!cin)
		throw invalid_argument("arguments of invalid type");
	if((ampm == "AM") || (ampm == "am"))
		return TIME{hr, min, sec};
	else if((ampm == "PM") || (ampm == "pm"))
		return TIME{hr + 12, min, sec};
}
///helper function to add minutes to TIME object
void addTime(TIME& t) {
	cout << "how much time would you like to add? ";
	int min;
	if(!(cin >> min) || (min < 0))
		throw invalid_argument("arguments of invalid type");
	t.addMinutes(min);
}
int main() {
	int c;
	TIME time;
	cout << endl << "MENU:" << endl << endl << "<0> exit" << endl
		<< "<1> set a time in 24 hour format" << endl
		<< "<2> set a time in 12 hour format" << endl
		<< "<3> get a time in 24 hour format" << endl
		<< "<4> get a time in 12 hour format" << endl
		<< "<5> add minutes to time" << endl << endl
		<< "note: all inputs are delimited by the ':' character" << endl;
	do {
		cout << endl << "enter operation code: ";
		try {
			if(!(cin >> c))
				throw invalid_argument("arguments of invalid type");
			switch(c) {
				case 0: cout << "terminating program" << endl; break;
				case 1:	time = set24(); break;
				case 2: time = set12(); break;
				case 3: get24(time); break;
				case 4: get12(time); break;
				case 5: addTime(time); break;
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
