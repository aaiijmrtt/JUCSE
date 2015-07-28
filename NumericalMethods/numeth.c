#include<stdio.h>
#include<stdlib.h>
#include<math.h>

double abslt(double x) {
	if(x > 0)
		return x;
	return -x;
}

double f1(double x) { return log(4) * 2 * log(x); }
double f2(double x) { return x * sin(x) + cos(x); }
double f3(double x) { return exp(x) - (2 * x + 1); }
double df3(double x) { return exp(x) - 2; }
double f4(double x) { return x * x * x- 2 * x * x - 4 * x + 8; }
double df4(double x) { return 3 * x * x- 4 * x - 4; }
double df5(double x, double y) { return 2 * x * x + 2 * y; }
double f6(double x) { return 1 / (1 + x); }

double iterative(double initial, double accuracy, double (*f)(double)) {
	int i = 1;
	double xi, ximinus1 = initial, fxi, ei=exp(1);
	printf("ITERATIVE METHOD:\n\ni\txi\t\t|g(xi)|\t\t|f(xi)|\t\t|ei|\t\t|log(ei)/log(e(i-1))|\n");
	do {
		xi = ximinus1;
		fxi = f(xi);
		ximinus1 = fxi;
		printf("%02d\t%+09g\t%+09g\t%+09g\t%+09g\t%+09g\n", i++, xi, abslt(ximinus1), abslt(xi - ximinus1), abslt(xi - ximinus1), abslt(log(abslt(xi - ximinus1))/log(ei)));
		ei = abslt(xi - ximinus1);
	} while(abslt(xi - ximinus1) > accuracy);
	return xi;
}

double bisection(double initiala, double initialb, double accuracy, double (*f)(double)) {
	int i = 1;
	double a = initiala, b = initialb, c, fa = f(a), fb = f(b), fc, ei = (b - a) / 2;
	printf("BISECTION METHOD:\n\ni\ta\t\tb\t\tc\t\tf(c)\t\t|f(c)|\t\t|ei|\t\t|log(ei)/log(e(i-1))|\n");
	do {
		c = (a + b) / 2;
		if(!(fc = f(c)))
			break;
		printf("%02d\t%+09g\t%+09g\t%+09g\t%+09g\t%+09g\t%+09g\t%+09g\n", i++, a, b, c, fc, abslt(fc), (b - a) / 2, abslt(log((b - a) / 2) / log(ei)));
		ei = (b - a) / 2;
		if(fa * fc > 0)
			fa = fc, a = c;
		else
			fb = fc, b = c;
	} while((b - a) / 2 > accuracy);
	return c;
}

double regulafalsi(double initiala, double initialb, double accuracy, double (*f)(double)) {
	int i = 1;
	double a = initiala, b = initialb, c = (a + b) /2, cminus1, fa = f(a), fb = f(b), fc, ei = exp(1);
	printf("REGULA FALSI METHOD:\n\ni\ta\t\tb\t\tc\t\tf(c)\t\t|f(c)|\t\t|ei|\t\t|log(ei)/log(e(i-1))|\n");
	do {
		cminus1 = c;
		if(fa == fb)
			c = (a + b) / 2;
		else
			c = (a * fb - b * fa) / (fb - fa);
		if(!(fc = f(c)))
			break;
		printf("%02d\t%+09g\t%+09g\t%+09g\t%+09g\t%+09g\t%+09g\t%+09g\n", i++, a, b, c, fc, abslt(fc), b - a, abslt(log(abslt(b - a)) / ei));
		ei = (b - a);
		if(fa * fc > 0)
			fa = fc, a = c;
		else
			fb = fc, b = c;
	} while(abslt(c - cminus1) > accuracy);
	return c;
}

double newtonraphson(double initial, double accuracy, double (*f)(double), double (*df)(double)) {
	int i = 1;
	double xi, ximinus1 = initial, fxi, dfxi, ei = exp(1);
	printf("NEWTON RAPHSON METHOD:\n\ni\txi\t\tf(xi)\t\t|ei|\t\t|log(ei)/log(e(i-1))|\n");
	do {
		xi = ximinus1;
		fxi = f(xi);
		if(!(dfxi = df(xi)))
			break;
		ximinus1 = xi - fxi / dfxi;
		printf("%02d\t%+09g\t%+09g\t%+09g\t%+09g\n", i++, xi, fxi, abslt(xi - ximinus1), abslt(log(abslt(xi - ximinus1)) / log(ei)));
		ei = abslt(xi - ximinus1);
	} while(abslt(xi - ximinus1) > accuracy);
	return xi;
}

double secant(double initialminus1, double initial0, double accuracy, double (*f)(double)) {
	int i = 1;
	double xiplus1, xi=initial0, ximinus1 = initialminus1, fxi, fximinus1, ei = exp(1);
	printf("SECANT METHOD:\n\ni\txi\t\tf(xi)\t\t|ei|\t\t|log(ei)/log(e(i-1))|\n");
	do {
		fxi = f(xi);
		if(!(fximinus1 = f(ximinus1)))
			break;
		xiplus1 = (ximinus1 * fxi - xi * fximinus1) / (fxi - fximinus1);
		printf("%02d\t%+09g\t%+09g\t%+09g\t%+09g\n", i++, xi, fxi, abslt(xi - ximinus1), abslt(log(abslt(xi - ximinus1)) / log(ei)));
		ei = abslt(xi - ximinus1);
		ximinus1 = xi;
		xi = xiplus1;
	} while(abslt(xi - ximinus1) > accuracy);
	return xi;
}

double acceleratednewtonraphson(double initial, double accuracy, double (*f)(double), double (*df)(double), int degree) {
	int i = 1;
	double xi, ximinus1 = initial, fxi, dfxi, ei = exp(1);
	printf("ACCELERATED NETWON RAPHSON METHOD:\n\ni\txi\t\t|ei|\t\t|log(ei)/log(e(i-1))|\n");
	do {
		xi = ximinus1;
		fxi = f(xi);
		if(!(dfxi = df(xi)))
			break;
		ximinus1 = xi - degree * fxi / dfxi;
		printf("%02d\t%+09g\t%+09g\t%+09g\n", i++, xi, abslt(xi - ximinus1), abslt(log(abslt(xi - ximinus1)) / log(ei)));
		ei = abslt(xi - ximinus1);
	} while(abslt(xi - ximinus1) > accuracy);
	return xi;
}

double *gauss(int rank, double augmentedmatrix[rank][rank + 1], double accuracy) {
	int i, j, k = 1;
	double temp;
	do {
		i = k + 1;
		do {
			temp = augmentedmatrix[i-1][k-1] / augmentedmatrix[k-1][k-1];
			j = k;
			do
				augmentedmatrix[i-1][j-1] -= augmentedmatrix[k-1][j-1] * temp;
			while(++j <= rank + 1);
		} while(++i <= rank);
	} while(++k <= rank - 1);
	double *solutionvector=(double *)malloc(rank * sizeof(double));
	solutionvector[rank-1] = augmentedmatrix[rank-1][rank] / augmentedmatrix[rank-1][rank-1];
	i = rank - 1;
	do {
		j = i + 1;
		temp = augmentedmatrix[i-1][rank];
		do
			temp -= augmentedmatrix[i-1][j-1] * solutionvector[j-1];
		while(++j <= rank);
		solutionvector[i-1] = temp / augmentedmatrix[i-1][i-1];
	} while(--i >= 1);
	i = 1;
	printf("GAUSS METHOD:\n\n");
	do {
		printf("%+09g\t",*(solutionvector+i-1));
	} while(++i <= rank);
	return solutionvector;
}

double **gaussjordan(int rank, double matrix[rank][rank], double accuracy) {
	int i = 1, j, k = 1;
	double temp;
	double **inversematrix = (double **)malloc(rank * rank * sizeof(double));
	do {
		*(inversematrix+i-1) = (double *)malloc(rank * sizeof(double));
		j = 1;
		do {
			if(j == i)
				*(*(inversematrix+i-1)+j-1) = 1;
			else
				*(*(inversematrix+i-1)+j-1) = 0;
		} while(++j <=rank);
	} while(++i <=rank);
	do {
		i = k + 1;
		do {
			temp = matrix[i-1][k-1] / matrix[k-1][k-1];
			j = 1;
			do {
				matrix[i-1][j-1] -= matrix[k-1][j-1] * temp;
				*(*(inversematrix+i-1)+j-1) -= *(*(inversematrix+k-1)+j-1) * temp;
			} while(++j <= rank);
		} while(++i <= rank);
	} while(++k <= rank - 1);
	k = rank;
	do {
		i = k - 1;
		do {
			temp = matrix[i-1][k-1] / matrix[k-1][k-1];
			j = 1;
			do {
				matrix[i-1][j-1] -= matrix[k-1][j-1] * temp;
				*(*(inversematrix+i-1)+j-1) -= *(*(inversematrix+k-1)+j-1) * temp;
			} while(++j <= rank);
		} while(--i > 0);
	} while(--k > 1);
	i = 1;
	do {
		j = 1;
		temp = matrix[i-1][i-1];
		do {
			matrix[i-1][j-1] /= temp;
			*(*(inversematrix+i-1)+j-1) /= temp;
		} while(++j <= rank);
	} while(++i <= rank);
	i = 1;
	printf("GAUSS JORDAN METHOD:\n\n");
	do {
		j = 1;
		do {
			printf("%+09g\t",*(*(inversematrix+i-1)+j-1));
		} while(++j <= rank);
		printf("\n");
	} while(++i <= rank);
	return inversematrix;
}

double *jacobi(int rank, double augmentedmatrix[rank][rank+1], double accuracy) {
	double *solutionvectornew = (double *)malloc(rank * sizeof(double));
	double *solutionvectorold = (double *)malloc(rank * sizeof(double));
	int i = 1, j, k = 1, check;
	do
		solutionvectornew[i-1] = 0;
	while(++i <= rank);
	printf("JACOBI METHOD:\n\n");
	do {
		check = 0;
		i = 1;
		do
			solutionvectorold[i-1] = solutionvectornew[i-1];
		while(++i <= rank);
		i = 1;
		printf("%02d\t", k++);
		do {
			solutionvectornew[i-1] = augmentedmatrix[i-1][rank];
			j = 1;
			do
				if(i != j)
					solutionvectornew[i-1] -= augmentedmatrix[i-1][j-1] * solutionvectorold[j-1];
			while(++j <= rank);
			solutionvectornew[i-1] /= augmentedmatrix[i-1][i-1];
			check |= abslt(solutionvectornew[i-1] - solutionvectorold[i-1]) > accuracy;
			printf("%+09g\t",solutionvectornew[i-1]);
		} while(++i <= rank);
		printf("\n");
	} while(check);
	free(solutionvectorold);
	return solutionvectornew;
}

double *gaussseidel(int rank, double augmentedmatrix[rank][rank+1], double accuracy) {
	double *solutionvectornew = (double *)malloc(rank * sizeof(double));
	double *solutionvectorold = (double *)malloc(rank * sizeof(double));
	int i = 1, j, k = 1, check;
	do
		solutionvectornew[i-1] = 0;
	while(++i <= rank);
	printf("GAUSS SEIDEL METHOD:\n\n");
	do {
		check = 0;
		i = 1;
		do
			solutionvectorold[i-1] = solutionvectornew[i-1];
		while(++i <= rank);
		i = 1;
		printf("%02d\t", k++);
		do {
			solutionvectornew[i-1] = augmentedmatrix[i-1][rank];
			j = 1;
			do
				if(j < i)
					solutionvectornew[i-1] -= augmentedmatrix[i-1][j-1] * solutionvectornew[j-1];
				else if(j > i)
					solutionvectornew[i-1] -= augmentedmatrix[i-1][j-1] * solutionvectorold[j-1];
			while(++j <= rank);
			solutionvectornew[i-1] /= augmentedmatrix[i-1][i-1];
			check |= abslt(solutionvectornew[i-1] - solutionvectorold[i-1]) > accuracy;
			printf("%+09g\t",solutionvectornew[i-1]);
		} while(++i <= rank);
		printf("\n");
	} while(check);
	free(solutionvectorold);
	return solutionvectornew;
}

double euler(double initialx, double finalx, double initialy, double accuracy, double (*df)(double x, double y)) {
	double interval = finalx - initialx, finalynminus1, finalyn = 0, ei = exp(1);
	int i = 1, j;
	printf("EULER METHOD:\n\ni\t|dx|\t\ty(x)\t\t|ei|\t\t|log(ei)/log(e(i-1))|\n");
	do {
		finalynminus1 = finalyn;
		finalyn = initialy;
		interval /= 2;
		j = 1;
		do
			finalyn += interval * df(initialx + j * interval, finalyn);
		while (initialx + (++j) * interval <= finalx);
		printf("%02d\t%+09g\t%+09g\t%+09g\t%+09g\n", i++, interval, finalyn, abslt(finalyn - finalynminus1), abslt(log(abslt(finalyn - finalynminus1)) / log(ei)));
		ei = abslt(finalyn - finalynminus1);
	} while(abslt(finalyn - finalynminus1) > accuracy);
	return finalyn;
}

double modifiedeuler(double initialx, double finalx, double initialy, double accuracy, double (*df)(double x, double y)) {
	double interval = finalx - initialx, finalynminus1, finalyn = 0, tempy, ei = exp(1);
	int i = 1, j;
	printf("MODIFIED EULER METHOD:\n\ni\t|dx|\t\ty(x)\t\t|ei|\t\t|log(ei)/log(e(i-1))|\n");
	do {
		finalynminus1 = finalyn;
		finalyn = initialy;
		tempy = initialy;
		interval /= 2;
		j = 1;
		do {
			tempy += interval * df(initialx + j * interval, tempy);
			finalyn += interval / 2 * (df(initialx + j * interval, finalyn) + df(initialx + (j + 1) * interval, tempy));
		} while (initialx + (++j) * interval <= finalx);
		printf("%02d\t%+09g\t%+09g\t%+09g\t%+09g\n", i++, interval, finalyn, abslt(finalyn - finalynminus1), abslt(log(abslt(finalyn - finalynminus1)) / log(ei)));
		ei = abslt(finalyn - finalynminus1);
	} while(abslt(finalyn - finalynminus1) > accuracy);
	return finalyn;
}

double trapezoidal(double a, double b, double accuracy, double (*f)(double x)) {
	double integraln = 0, integralnminus1, interval = b - a, ei = exp(1);
	int i = 1, j;
	printf("TRAPEZOIDAL METHOD:\n\ni\t|dx|\t\tI(dx)\t\t|ei|\t\t|log(ei)/log(e(i-1))|\n");
	do {
		integralnminus1 = integraln;
		integraln = f(a) / 2 + f(b) / 2;
		interval /= 2;
		j = 1;
		do
			integraln += f(a + j * interval);
		while (a + (++j) * interval < b);
		integraln = interval * integraln;
		printf("%02d\t%+09g\t%+09g\t%+09g\t%+09g\n", i++, interval, integraln, abslt(integraln - integralnminus1), abslt(log(abslt(integraln - integralnminus1)) / log(ei)));
		ei = abslt(integraln - integralnminus1);
	} while (abslt(integraln - integralnminus1) > accuracy);
	return integraln;
}

double simpsons(double a, double b, double accuracy, double (*f)(double x)) {
	double integraln = 0, integralnminus1, interval = b - a, ei = exp(1);
	int i = 1, j;
	printf("SIMPSON ONE-THIRD METHOD:\n\ni\t|dx|\t\tI(dx)\t\t|ei|\t\t|log(ei)/log(e(i-1))|\n");
	do {
		integralnminus1 = integraln;
		integraln = f(a) + f(b);
		interval /= 3;
		j = 1;
		do {
			if(j % 2 == 0)
				integraln += 2 * f(a + j * interval);
			else
				integraln += 4 * f(a + j * interval);
		} while (a + (++j) * interval < b);
		integraln = interval * integraln / 3;
		printf("%02d\t%+09g\t%+09g\t%+09g\t%+09g\n", i++, interval, integraln, abslt(integraln - integralnminus1), abslt(log(abslt(integraln - integralnminus1)) / log(ei)));
		ei = abslt(integraln - integralnminus1);
	} while (abslt(integraln - integralnminus1) > accuracy);
	return integraln;
}

int main(int argc, char **argv) {
	printf("\n");
	iterative(4, 0.0001, &f1);
	printf("\n");
	bisection(0, 7.2, 0.0001, &f2);
	printf("\n");
	regulafalsi(0, 7.2, 0.0001, &f2);
	printf("\n");
	newtonraphson(1, 0.0001, &f3, &df3);
	printf("\n");
	secant(1, 1.1, 0.0001, &f3);
	printf("\n");
	acceleratednewtonraphson(1, 0.0001, &f4, &df4, 2);
	printf("\n");
	int i, j;
	double gaussonly[4][5] = {{1, 1, -1, 1, 2},{2, 1, 1, -3, 1},{3, -1, -1, 1, 2},{5, 1, 3, -2, 7}};
	gauss(4, gaussonly, 0.0001);
	printf("\n");
	printf("\n");
	double jordan[3][3] = {{1, 5, 3},{1, 3, 2},{2, 4, -6}};
	gaussjordan(3, jordan, 0.0001);
	printf("\n");
	double jacobionly[3][4] = {{5, 1, -1, 10},{2, 8, -1, 11},{-1, 1, 4, 3}};
	jacobi(3, jacobionly, 0.0001);
	printf("\n");
	double seidel[3][4] = {{5, 1, -1, 10},{2, 8, -1, 11},{-1, 1, 4, 3}};
	gaussseidel(3, seidel, 0.0001);
	printf("\n");
	euler(0, 0.1, 1, 0.000001, &df5);
	printf("\n");
	modifiedeuler(0, 0.1, 1, 0.000001, &df5);
	printf("\n");
	trapezoidal(0, 1, 0.0001, &f6);
	printf("\n");
	simpsons(0, 1, 0.0001, &f6);
	printf("\n");
	return 0;
}
