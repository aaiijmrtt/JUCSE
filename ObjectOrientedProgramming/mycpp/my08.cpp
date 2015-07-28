#include <iostream>
#include <stdexcept>
#include <limits>
using namespace std;
///class to store and manipulate COMPLEX values
class COMPLEX {
	double real, ///<member to hold real part
		imag; ///<member to hold imaginary part
	public:
		///constructor to initialize data members and validate class invariants
		COMPLEX(double r=0, double i=0) : real{r}, imag{i} {
		}
		///getter function for real
		double getReal() {
			return real;
		}
		///getter function for imag
		double getImag() {
			return imag;
		}
		///member operator overload to add a complex number
		COMPLEX& operator+=(COMPLEX c) {
			real += c.real;
			imag += c.imag;
			return *this;
		}
		///member operator overload to subtract a complex number
		COMPLEX& operator-=(COMPLEX c) {
			real -= c.real;
			imag -= c.imag;
			return *this;
		}
		///member operator overload to multiply a complex number
		COMPLEX& operator*=(COMPLEX c) {
			double copyReal = this->real, copyImag = this->imag;
			real = copyReal * c.real - copyImag * c.imag;
			imag = copyReal * c.imag + copyImag * c.real;
			return *this;
		}
		///member operator overload to divide a complex number
		COMPLEX& operator/=(COMPLEX c) {
			if((c.real == 0) && (c.imag == 0))
				throw invalid_argument("divisor is zero");
			double copyReal = this->real, copyImag = this->imag;
			real = (copyReal * c.real + copyImag * c.imag) / (c.real * c.real + c.imag * c.imag);
			imag = (copyImag * c.real - copyReal * c.imag) / (c.real * c.real + c.imag * c.imag);
			return *this;
		}
		friend istream& ::operator>>(istream&, COMPLEX&);
		friend ostream& ::operator<<(ostream&, COMPLEX);
};
///operator overload to add a complex number
COMPLEX operator+(COMPLEX a, COMPLEX b) {
	return a += b;
}
///operator overload to subtract a complex number
COMPLEX operator-(COMPLEX a, COMPLEX b) {
	return a -= b;
}
///operator overload to multiply a complex number
COMPLEX operator*(COMPLEX a, COMPLEX b) {
	return a *= b;
}
///operator overload to divide a complex number
COMPLEX operator/(COMPLEX a, COMPLEX b) {
	return a /= b;
}
///friend function to input a complex number
istream& operator>>(istream& in,COMPLEX& c) {
	double re, im;
	char sign, i;
	in >> re >> sign >> im >> i;
	if((!in) || ((sign != '+') && (sign != '-')) || (i != 'i'))
		throw invalid_argument("complex format not recognised");
	if(sign == '-')
		im = -im;
	c.real = re;
	c.imag = im;
	return in;
}
///friend function to output a complex number
ostream& operator<<(ostream& out,COMPLEX c) {
	if(c.getImag() > 0.0)
		out << c.getReal() << "+" << c.getImag() << "i";
	else if(c.getImag() < 0.0)
		out << c.getReal() << "-" << -c.getImag() << "i";
	else
		out << c.getReal();
	return out;
}
int main() {
	int c;
	COMPLEX complex, temp;
	cout << endl << "MENU:" << endl << endl << "0. exit" << endl
		<< "1. enter a complex number" << endl
		<< "2. display a complex number" << endl
		<< "3. add to it another complex number" << endl
		<< "4. subtract from it another complex number" << endl
		<< "5. multiply with it another complex number" << endl
		<< "6. divide it by another complex number" << endl;
	do {
		cout << endl << "enter operation code: ";
		try {
			if(!(cin >> c))
				throw invalid_argument("arguments of invalid type");
			switch(c) {
				case 1:	cin >> complex; break;
				case 2: cout << complex << endl; break;
				case 3: cin >> temp; complex += temp; break;
				case 4: cin >> temp; complex -= temp; break;
				case 5: cin >> temp; complex *= temp; break;
				case 6: cin >> temp; complex /= temp; break;
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
