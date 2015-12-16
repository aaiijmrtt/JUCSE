public class Transforms {

	//returns transformation matrix for 2D scaling
	public Matrix scaling(double scaleX, double scaleY) {
		Matrix trans = new Matrix(3, 3);
		trans.values[0][0] = scaleX;
		trans.values[1][1] = scaleY;
		trans.values[2][2] = 1.0;
		return trans;
	}

	//returns transformation matrix for 2D shearing
	public Matrix shearing(double shearX, double shearY) {
		Matrix trans = new Matrix(3, 3);
		trans.values[0][0] = 1.0;
		trans.values[0][1] = shearY;
		trans.values[1][0] = shearX;
		trans.values[1][1] = 1.0;
		trans.values[2][2] = 1.0;
		return trans;
	}

	//returns transformation matrix for 2D rotation
	public Matrix rotation(double clockwise) {
		Matrix trans = new Matrix(3, 3);
		trans.values[0][0] = Math.cos(clockwise);
		trans.values[0][1] = Math.sin(clockwise);
		trans.values[1][0] = -trans.values[0][1];
		trans.values[1][1] = trans.values[0][0];
		trans.values[2][2] = 1.0;
		return trans;
	}

	//returns transformation matrix for 2D reflection
	public Matrix reflection_xaxis() {
		Matrix trans = new Matrix(3, 3);
		trans.values[0][0] = 1.0;
		trans.values[1][1] = -1.0;
		trans.values[2][2] = 1.0;
		return trans;
	}

	//returns transformation matrix for 2D reflection
	public Matrix reflection_yaxis() {
		Matrix trans = new Matrix(3, 3);
		trans.values[0][0] = -1.0;
		trans.values[1][1] = 1.0;
		trans.values[2][2] = 1.0;
		return trans;
	}

	//returns transformation matrix for 2D reflection
	public Matrix reflection_yequaltox() {
		Matrix trans = new Matrix(3, 3);
		trans.values[0][1] = 1.0;
		trans.values[1][0] = 1.0;
		trans.values[2][2] = 1.0;
		return trans;
	}

	//returns transformation matrix for 2D reflection
	public Matrix reflection_yequaltominusx() {
		Matrix trans = new Matrix(3, 3);
		trans.values[0][1] = -1.0;
		trans.values[1][0] = -1.0;
		trans.values[2][2] = 1.0;
		return trans;
	}

	//returns transformation matrix for 2D translation
	public Matrix translation(double translateX, double translateY) {
		Matrix trans = new Matrix(3, 3);
		trans.values[0][0] = 1.0;
		trans.values[1][1] = 1.0;
		trans.values[2][0] = translateX;
		trans.values[2][1] = translateY;
		trans.values[2][2] = 1.0;
		return trans;
	}

	//returns transformation matrix for 2D rotation about a point
	public Matrix rotationaboutpoint(double pointX, double pointY, double counterclockwise) {
		Matrix trans = new Matrix(3, 3);
		trans.values[0][0] = Math.cos(counterclockwise);
		trans.values[0][1] = Math.sin(counterclockwise);
		trans.values[1][0] = -trans.values[0][1];
		trans.values[1][1] = trans.values[0][0];
		trans.values[2][0] = - pointX * (trans.values[0][0] - 1.0) + pointY * trans.values[0][1];
		trans.values[2][1] = - pointX * trans.values[0][1] - pointY * (trans.values[0][0] - 1.0);
		trans.values[2][2] = 1.0;
		return trans;
	}

	//returns transformation matrix for 3D scaling
	public Matrix scaling(double scaleX, double scaleY, double scaleZ) {
		Matrix trans = new Matrix(4, 4);
		trans.values[0][0] = scaleX;
		trans.values[1][1] = scaleY;
		trans.values[2][2] = scaleZ;
		trans.values[3][3] = 1.0;
		return trans;
	}

	//returns transformation matrix for 3D rotation
	public Matrix rotationX(double counterclockwise) {
		Matrix trans = new Matrix(4, 4);
		trans.values[0][0] = 1.0;
		trans.values[1][1] = Math.cos(counterclockwise);
		trans.values[1][2] = Math.sin(counterclockwise);
		trans.values[2][1] = -trans.values[1][2];
		trans.values[2][2] = trans.values[1][1];
		trans.values[3][3] = 1.0;
		return trans;
	}

	//returns transformation matrix for 3D rotation
	public Matrix rotationY(double counterclockwise) {
		Matrix trans = new Matrix(4, 4);
		trans.values[1][1] = 1.0;
		trans.values[2][2] = Math.cos(counterclockwise);
		trans.values[2][0] = Math.sin(counterclockwise);
		trans.values[0][2] = -trans.values[2][0];
		trans.values[0][0] = trans.values[2][2];
		trans.values[3][3] = 1.0;
		return trans;
	}

	//returns transformation matrix for 3D rotation
	public Matrix rotationZ(double counterclockwise) {
		Matrix trans = new Matrix(4, 4);
		trans.values[0][0] = Math.cos(counterclockwise);
		trans.values[0][1] = Math.sin(counterclockwise);
		trans.values[1][0] = -trans.values[0][1];
		trans.values[1][1] = trans.values[0][0];
		trans.values[2][2] = 1.0;
		trans.values[3][3] = 1.0;
		return trans;
	}

	//returns transformation matrix for 3D rotation
	public Matrix rotation(double counterX, double counterY, double counterZ) {
		return Matrix.multiplication(Matrix.multiplication(rotationX(counterX), rotationY(counterY)), rotationZ(counterZ));
	}

	//returns transformation matrix for 3D reflection
	public Matrix reflectionXY() {
		Matrix trans = new Matrix(4, 4);
		trans.values[0][0] = 1.0;
		trans.values[1][1] = 1.0;
		trans.values[2][2] = -1.0;
		trans.values[3][3] = 1.0;
		return trans;
	}

	//returns transformation matrix for 3D reflection
	public Matrix reflectionYZ() {
		Matrix trans = new Matrix(4, 4);
		trans.values[0][0] = -1.0;
		trans.values[1][1] = 1.0;
		trans.values[2][2] = 1.0;
		trans.values[3][3] = 1.0;
		return trans;
	}

	//returns transformation matrix for 3D reflection
	public Matrix reflectionZX() {
		Matrix trans = new Matrix(4, 4);
		trans.values[0][0] = 1.0;
		trans.values[1][1] = -1.0;
		trans.values[2][2] = 1.0;
		trans.values[3][3] = 1.0;
		return trans;
	}

	//returns transformation matrix for 3D translation
	public Matrix translation(double translateX, double translateY, double translateZ) {
		Matrix trans = new Matrix(4, 4);
		trans.values[0][0] = 1.0;
		trans.values[1][1] = 1.0;
		trans.values[2][2] = 1.0;
		trans.values[3][0] = translateX;
		trans.values[3][1] = translateY;
		trans.values[3][2] = translateZ;
		trans.values[3][3] = 1.0;
		return trans;
	}

	//returns transformation matrix for 3D perspective transformation
	public Matrix perspectiveX(double pointofprojection) {
		Matrix trans = new Matrix(4, 4);
		trans.values[0][0] = 1.0;
		trans.values[1][1] = 1.0;
		trans.values[2][2] = 1.0;
		trans.values[3][3] = 1.0;
		trans.values[0][3] = -1 / pointofprojection;
		return trans;
	}

	//returns transformation matrix for 3D perspective transformation
	public Matrix perspectiveY(double pointofprojection) {
		Matrix trans = new Matrix(4, 4);
		trans.values[0][0] = 1.0;
		trans.values[1][1] = 1.0;
		trans.values[2][2] = 1.0;
		trans.values[3][3] = 1.0;
		trans.values[1][3] = -1 / pointofprojection;
		return trans;
	}

	//returns transformation matrix for 3D perspective transformation
	public Matrix perspectiveZ(double pointofprojection) {
		Matrix trans = new Matrix(4, 4);
		trans.values[0][0] = 1.0;
		trans.values[1][1] = 1.0;
		trans.values[2][2] = 1.0;
		trans.values[3][3] = 1.0;
		trans.values[2][3] = -1 / pointofprojection;
		return trans;
	}

	//returns transformation matrix for 3D projection
	public Matrix projectionXY() {
		Matrix trans = new Matrix(4, 4);
		trans.values[0][0] = 1.0;
		trans.values[1][1] = 1.0;
		trans.values[3][3] = 1.0;
		return trans;
	}

	//returns transformation matrix for 3D projection
	public Matrix projectionYZ() {
		Matrix trans = new Matrix(4, 4);
		trans.values[1][1] = 1.0;
		trans.values[2][2] = 1.0;
		trans.values[3][3] = 1.0;
		return trans;
	}

	//returns transformation matrix for 3D projection
	public Matrix projectionZX() {
		Matrix trans = new Matrix(4, 4);
		trans.values[0][0] = 1.0;
		trans.values[2][2] = 1.0;
		trans.values[3][3] = 1.0;
		return trans;
	}

	//returns point matrix mat after applying transformation trans
	public Matrix transform(Matrix trans, Matrix mat) {
		Matrix intermediate = Matrix.multiplication(mat, trans);
		int rows = intermediate.rows, columns = intermediate.columns;
		for(int i = 0; i < rows; ++i)
			for(int j = 0; j < columns; ++j)
				intermediate.values[i][j] /= intermediate.values[i][columns - 1];
		return intermediate;
	}
}
