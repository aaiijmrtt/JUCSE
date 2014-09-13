#include<stdio.h>
#include<math.h>
#include<limits.h>
#define PI 3.1415926
#define PAUSE	getchar();	printf("\nreturning to menu. press Enter...");	getchar();
#define WIDTH(field)	log10(field)>0?(int)(1+log10(field)):(int)(1-log10(field))
void one()	{
	int number,length,i;
	printf("enter number (int), length (int) of table: ");
	scanf("%d%d",&number,&length);
	printf("\ngenerated table of %d of length %d:\n\n",number,length);
	for(i=1;i<=length;++i)
		printf("%d * %*d = %*d\n",number,WIDTH(length),i,WIDTH(length*number),(number*i));
	PAUSE
}
void two()	{
	char numerals[9]={'I','V','X','X','L','C','C','D','M'};
	int values[9]={1,5,10,10,50,100,100,500,1000},number,i,j;
	printf("enter number (int): ");
	scanf("%d",&number);
	printf("\ngenerated roman representation: ");
	for(i=2;i>=0;--i)
		for(j=2;j>=0;--j)	{
			while(number>=values[3*i+j])	{
				printf("%c",numerals[3*i+j]);
				number-=values[3*i+j];
			}
			if(j==0)
				break;
			if(number>=(5*j-1)*values[3*i])	{
				printf("%c%c",numerals[3*i],numerals[3*i+j]);
				number-=values[3*i+j]-values[3*i];
			}
		}
	printf("\n");
	PAUSE
}
void three()	{
	float x,new_x,fx,dfx,accuracy;
	printf("enter initial solution (int), accuracy (float): ");
	scanf("%f%f",&new_x,&accuracy);
	int w1=WIDTH(new_x),w2=WIDTH(accuracy);
	printf("\ngenerating solution of equation x^4=81:\n%*.*f\n\n",w1,w2,new_x);
	do	{
		x=new_x;
		fx=x*x*x*x-81;
		dfx=4*x*x*x;
		new_x=x-fx/dfx;
		printf("%*.*f\n",w1+w2+1,w2,new_x);
	}	while((x-new_x)>accuracy||(new_x-x)>accuracy);
	PAUSE
}
void four()	{
	int i,j,height,width;
	float accuracy;
	printf("enter height (int), skipwidth (int), accuracy (float): ");
	scanf("%d%d%f",&height,&width,&accuracy);
	printf("\ngenerated sine curve with specified parameters:\n");
	for(i=height;i>=-height;--i)	{
		for(j=0;j<=360;j+=width)
			if((height*sin(PI*j/180)<i+height*accuracy)&&(height*sin(PI*j/180)+height*accuracy>i))
				printf("*");
			else
				printf(" ");
		printf("\n");
	}
	PAUSE
}
void five()	{
	int count=0;
	float number,sum=0,sum_square=0,mean,variance,deviation;
	printf("enter numbers (float) and a single character to exit:\n\n");
	while(scanf("%f",&number))	{
		sum+=number;
		sum_square+=number*number;
		count++;
	}
	mean=sum/count;
	variance=sum_square/count-mean*mean;
	deviation=sqrt(variance);
	printf("\nevaluated mean\t\t\t= %9.2f\nevaluated variance\t\t= %9.2f\nevaluated standard deviation\t= %9.2f\n",mean,variance,deviation);
	getchar();
	PAUSE
}
void six()	{
	int number,divisor=2,n;
	printf("enter number (int): ");
	scanf("%d",&number);
	printf("\ngenerated prime factorization: ");
	n=number;
	while(number%divisor==0)	{
		number/=divisor;
		printf("%d * ",divisor);
	}
	for(divisor=3;divisor<=sqrt(number);divisor+=2)
		while(number%divisor==0)	{
			number/=divisor;
			printf("%d * ",divisor);
		}
	printf("%d\n",number);
	if(number==n)
		printf("note: the number is prime\n");
	PAUSE
}
void seven()	{
	int date,day,month,year,days[13]={0,31,28,31,30,31,30,31,31,30,31,30,31};
	char months[13][10]={"","January","February","March","April","May","June","July","August","September","October","November","December"};
	printf("enter date (int) in ddmmyy format: ");
	scanf("%d",&date);
	day=date/10000;
	month=(date/100)%100;
	year=date%100;
	days[2]+=(year%4==0)?1:0;
	if((month<1)||(month>12)||(day<1)||(day>days[month]))
		printf("\ninvalid date\n");
	else	{
			printf("\nformatted date: %d",day);
			if((day/10)==1)
				printf("th ");
			else
				switch(day%10)	{
					case 1:	printf("st ");	break;
					case 2:	printf("nd ");	break;
					case 3:	printf("rd ");	break;
					default:printf("th ");	break;
				}
			printf("%s '%d\n",months[month],year);
		}
	PAUSE
}
void eight()	{
	int limit,i,j;
	float sum,term;
	printf("enter limit (int): ");
	scanf("%d",&limit);
	printf("\ngenerated limits of accuracy:\n\n");
	for(i=1;i<=limit;++i)	{
		term=1/(float)i;
		sum=0;
		for(j=0;j<i;++j)
			sum+=term;
		printf("sum of %*d 1/%*d's is %f\n",WIDTH(limit),i,WIDTH(limit),i,sum);
	}
	PAUSE
}
void nine()	{
	int decimal,length,i;
	printf("enter number (int): ");
	scanf("%d",&decimal);
	length=(int)(1+log(decimal)/log(2));
	char binary[length];
	for(i=0;decimal>0;++i,decimal/=2)
		binary[i]='0'+decimal%2;
	printf("\ngenerated binary number: ");
	for(--i;i>=0;--i)
		printf("%c",binary[i]);
	printf("\n");
	PAUSE
}
void ten()	{
	int i;
	float angle,accuracy,term,sum;
	printf("enter angle (float), accuracy (float): ");
	scanf("%f%f",&angle,&accuracy);
	angle*=PI/180;
	sum=term=angle;
	for(i=2;(term>=accuracy)||(term<=-accuracy);++i)	{
		term=-angle*angle*term/((2*i-1)*(2*i-2));
		sum+=term;
	}
	printf("\ncomputed sine\t\t= %+*.*f\ncomputer generated sine\t= %+*.*f\n",(WIDTH(accuracy)+3),WIDTH(accuracy),sum,(WIDTH(accuracy)+3),WIDTH(accuracy),sin(angle));
	PAUSE
}
void eleven()	{
	int last,i,number,w1=WIDTH(INT_MAX);
	printf("enter number of elements (int): ");
	scanf("%d",&last);
	int numbers[last+1],w2=WIDTH(last);
	printf("\nrandomized array:\n\n");
	for(i=1;i<=last;++i)	{
		numbers[i]=rand(INT_MAX);
		printf("numbers [%*d] = %*d\n",w2,i,w1,numbers[i]);
	}
	printf("\nenter search number: ");
	scanf("%d",&number);
	numbers[0]=number;
	for(i=last;number!=numbers[i];--i)	;
	if(i==0)
		printf("%d is not present in the array\n",number);
	else
		printf("%d is present at position %d\n",number,i);
	PAUSE
}
void twelve()	{
	int last,i,j,k,number,w1=WIDTH(INT_MAX);
	printf("enter number of elements (int): ");
	scanf("%d",&last);
	int numbers[last+1],w2=WIDTH(last);
	numbers[0]=INT_MIN;
	printf("\nunsorted array:\n\n");
	for(i=1;i<=last;++i)	{
		numbers[i]=rand(INT_MAX);
		printf("numbers [%*d] = %*d\n",w2,i,w1,numbers[i]);
	}
	for(i=2;i<=last;++i)	{
		j=numbers[i];
		for(k=i-1;j<numbers[k];--k)
			numbers[k+1]=numbers[k];
		numbers[k+1]=j;
	}
	printf("\nsorted array:\n\n");
	for(i=1;i<=last;++i)
		printf("numbers [%*d] = %*d\n",w2,i,w1,numbers[i]);
	PAUSE
}
void thirteen()	{
	struct complex	{	float x,y;	}	z1,z2;
	printf("enter real, imaginary coefficients of 2 complex numbers (float): ");
	scanf("%f%f%f%f",&z1.x,&z1.y,&z2.x,&z2.y);
	printf("\ngenerated sum\t\t= % 09.2f %+09.2f i\n",(z1.x+z2.x),(z1.y+z2.y));
	printf("generated difference\t= % 09.2f %+09.2f i\n",(z1.x-z2.x),(z1.y-z2.y));
	printf("generated product\t= % 09.2f %+09.2f i\n",(z1.x*z2.x-z1.y*z2.y),(z1.x*z2.y+z1.y*z2.x));
	printf("generated quotient\t= % 09.2f %+09.2f i\n",(z1.x*z2.x+z1.y*z2.y)/(z2.x*z2.x+z2.y*z2.y),(-z1.x*z2.y+z1.y*z2.x)/(z2.x*z2.x+z2.y*z2.y));
	PAUSE
}
void fourteen()	{
	int given,guess,term,i,j;
	printf("enter number of terms given (int), number of terms generated (int): ");
	scanf("%d%d",&given,&guess);						//accepting generation parameters from user
	int series[given+guess+1],differences[given+guess+1],nCr[given+guess][given+guess+1];
	for(i=0;i<given+guess;++i)	{
		nCr[i][0]=1;									//generating and storing the binomial coefficients (Pascal's triangle)
		for(j=1;j<=i;++j)
			nCr[i][j]=nCr[i-1][j-1]+nCr[i-1][j];		//generating and storing the binomial coefficients (Pascal's triangle)
		for(;j<=given+guess;++j)
			nCr[i][j]=0;								//generating and storing the binomial coefficients (Pascal's triangle)
	}
	printf("\nenter %d terms (int): ",given);
	for(i=0;i<given+guess+1;++i)	{
		if(i<given)
			scanf("%d",&series[i]);						//accepting initial terms from user
		else
			series[i]=0;								//setting remaining terms to 0
		differences[i]=0;								//initializing array of differences (to be used while calculating terms using method of differences)
	}
	for(i=0;i<given;++i)	{
		differences[i]=series[0];						//storing differences
		for(j=0;i+j+1<given;++j)
			series[j]=series[j+1]-series[j];			//calculating differences
	}
	printf("\ngenerated terms: ");
	for(i=given;i<given+guess;++i)	{
		term=0;											//initializing generated term
		for(j=0;j<=i;++j)
			term+=nCr[i][j]*differences[j];				//generating subsequent term as a linear combination of binomial coefficients and differences
		printf("%d ",term);								//printing generated term
	}
	printf("\n");
	PAUSE												//pausing to display output, before returning to Main Menu
}
int main()	{
	int index;
	do	{
		printf("\nMAIN MENU\n\n");
		printf(" 0.\texit\n");
		printf(" 1.\tprogram to generate a mathematical table\n");
		printf(" 2.\tprogram to generate the roman numeral representation\n");
		printf(" 3.\tprogram to solve a biquadratic equation\n");
		printf(" 4.\tprogram to generate the sine curve\n");
		printf(" 5.\tprogram to generate statistical markers\n");
		printf(" 6.\tprogram to generate the prime factorisation\n");
		printf(" 7.\tprogram to generate a formatted date\n");
		printf(" 8.\tprogram to gauge the accuracy of computation\n");
		printf(" 9.\tprogram to generate the binary equivalent\n");
		printf("10.\tprogram to generate the sine\n");
		printf("11.\tprogram to demostrate linear search\n");
		printf("12.\tprogram to demostrate insertion sort\n");
		printf("13.\tprogram to manipulate complex numbers\n");
		printf("14.\tprogram to generate terms of a polynomial series\n");
		printf("\nselect option (int): ");
		scanf("%d",&index);
		printf("\n");
		switch(index)	{
			case 0:				break;
			case 1:	one();		break;
			case 2:	two();		break;
			case 3:	three();	break;
			case 4:	four();		break;
			case 5:	five();		break;
			case 6:	six();		break;
			case 7: seven();	break;
			case 8:	eight();	break;
			case 9:	nine();		break;
			case 10:ten();		break;
			case 11:eleven();	break;
			case 12:twelve();	break;
			case 13:thirteen();	break;
			case 14:fourteen();	break;
			default:	printf("invalid input\n");
		}
	}	while(index);
	return 0;
}