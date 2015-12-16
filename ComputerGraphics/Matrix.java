public class Matrix {

	int rows, columns;
	double values[][];

	public Matrix(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.values = new double[rows][columns];
	}

	//returns the sum of two matrices
	public static Matrix addition(Matrix mat1, Matrix mat2) {
		if((mat1.rows != mat2.rows) || (mat1.columns != mat2.columns))
			return null;
		Matrix mat = new Matrix(mat1.rows, mat1.columns);
		for(int i = 0; i < mat1.rows; ++i)
			for(int j = 0; j < mat1.columns; ++j)
				mat.values[i][j] = mat1.values[i][j] + mat2.values[i][j];
		return mat;
	}

	//returns the difference of two matrices
	public static Matrix subtraction(Matrix mat1, Matrix mat2) {
		if((mat1.rows != mat2.rows) || (mat1.columns != mat2.columns))
			return null;
		Matrix mat = new Matrix(mat1.rows, mat1.columns);
		for(int i = 0; i < mat1.rows; ++i)
			for(int j = 0; j < mat1.columns; ++j)
				mat.values[i][j] = mat1.values[i][j] - mat2.values[i][j];
		return mat;
	}

	//returns the product of two matrices
	public static Matrix multiplication(Matrix mat1, Matrix mat2) {
		if(mat1.columns != mat2.rows)
			return null;
		Matrix mat = new Matrix(mat1.rows, mat2.columns);
		for(int i = 0; i < mat1.rows; ++i)
			for(int j = 0; j < mat2.columns; ++j)
				for(int k = 0; k < mat1.columns; ++k)
					mat.values[i][j] += mat1.values[i][k] * mat2.values[k][j];
		return mat;
	}

	//prints a matrix
	public static void print(Matrix mat) {
		if(mat == null)
			return;
		for(int i = 0; i < mat.rows; ++i) {
			for(int j = 0; j < mat.columns; ++j)
				System.out.print(mat.values[i][j] + "\t");
			System.out.println();
		}
	}

}
