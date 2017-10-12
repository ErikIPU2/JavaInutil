public class Lib{

	public static boolean ehMatriz(int[][] mat){
		boolean fa = true;
		for (int i = 0; i<mat.length; i++) {
			fa = fa && (mat[0].length == mat[i].length);
		}
		return fa;
	}

	public static boolean ehMatrizQdr(int[][] mat){
		if (ehMatriz(mat)) {
			return mat.length==mat[0].length;
		}
		else {
			return false;
		}
	}

	public static int ordMatriz(int[][] mat){
		if (ehMatrizQdr(mat)) {
			return mat.length;
		}
		else {
			return 0;
		}
	}

	public static boolean ehMatrizId(int[][] mat) {
		boolean fa = true;
		if (ehMatrizQdr(mat)) {
			for (int i = 0; i<mat.length; i++) {
				for (int j = 0; j<mat[i].length; j++) {
					if (i==j && mat[i][j]!=1){
							fa = false;
					}
					else if (i!=j && mat[i][j]!=0) {
							fa = false;
					}
				}
			}
			return fa;	
		}
		else {
			return false;
		}
	}

	public static boolean ehMatrizNula(int[][] mat) {
		boolean fa = true;
		for (int i = 0; i<mat.length; i++) {
			for (int j = 0; j<mat.length; j++) {
				if (mat[i][j]!=0) {
					fa = false;
				}
			}
		}
		return fa;
	}

	public static boolean saoIguais(int[][] mat1, int[][] mat2) {
		boolean fa = true;
		for (int i = 0; i<mat1.length; i++) {
			for (int j = 0; j<mat1.length; j++) {
				if (mat1[i][j]!=mat2[i][j]) {
					fa = false;
				}
			}
		}
		return fa;
	}

	public static int[][] optMatriz(int[][] mat){
		for (int i = 0; i<mat.length; i++) {
			for (int j = 0; j<mat.length; j++) {
				mat[i][j] *= -1;
			}
		}
		return mat;
	}

	public static int[][] adcMatrizes(int[][] mat1, int[][] mat2){
		for (int i = 0; i<mat1.length; i++) {
			for (int j = 0; j<mat1[0].length; j++) {
				mat1[i][j] = mat1[i][j]+mat2[i][j];
			}
		}
		return mat1;
	}

	public static int[][] subMatrizes(int[][] mat1, int[][]mat2){
		for (int i = 0; i<mat1.length; i++) {
			for (int j = 0; j<mat1[0].length; j++) {
				mat1[i][j] = mat1[i][j]-mat2[i][j];
			}
		}
		return mat1;
	}

	public static int[][] numXMatriz(int num, int[][] mat){
		for (int i = 0; i<mat.length; i++) {
			for (int j = 0; j<mat[0].length; j++) {
				mat[i][j] = mat[i][j]*num;
			}
		}
		return mat;
	}

	public static double[][] numXMatriz(double num, double[][] mat){
		for (int i = 0; i<mat.length; i++) {
			for (int j = 0; j<mat[0].length; j++) {
				mat[i][j] = mat[i][j]*num;
			}
		}
		return mat;
	}

	public static int[][] trpMatriz(int[][] mat){
		int[][] fa = new int[mat[0].length][mat.length];
		for (int i = 0; i<mat.length; i++) {
			for (int j = 0; j<mat[i].length; j++) {
				fa[j][i] = mat[i][j];
			}
		}
		return fa;
	}

	public static int[][] mtpMatrizes(int[][] mat1, int[][] mat2){
		int[][] fa = new int[mat1.length][mat2[0].length];
		mat2 = trpMatriz(mat2);
		for (int i = 0; i<mat1.length; i++) {
			for (int i2 = 0; i2<mat2.length; i2++) {
				int aux = 0;
				for (int j = 0; j<mat1[i].length; j++) {
					aux += mat1[i][j]*mat2[i2][j];
				}
				fa[i][i2] = aux;
			}
		}
		return fa;
	}

	public static int det2Matriz(int[][] mat){
		if (ehMatrizQdr(mat) && ordMatriz(mat)==2) {
			return (mat[0][0]*mat[1][1])-(mat[0][1]*mat[1][0]);
		}
		else {
			return 0;
		}
	}

	public static int det3Matriz(int[][] mat){
		if (ehMatrizQdr(mat) && ordMatriz(mat)==3) {
			return ((mat[0][0]*mat[1][1]*mat[2][2])+(mat[0][1]*mat[1][2]*mat[2][0])+(mat[0][2]*mat[1][0]*mat[2][1]))-((mat[0][2]*mat[1][1]*mat[2][0])+(mat[0][0]*mat[1][2]*mat[2][1])+(mat[0][1]*mat[1][0]*mat[2][2]));
		}
		else {
			return 0;
		}
	}

	public static boolean ehInversa(int[][] mat1, int[][] mat2){
		int[][] aux1 = mtpMatrizes(mat1, mat2);
		int[][] aux2 = mtpMatrizes(mat2, mat1);
		boolean fa = true;
		
		for (int i = 0; i<aux1.length; i++) {
			for (int j = 0; j<aux1.length; j++) {
				fa = fa && (aux1[i][j]==aux2[i][j]);
			}
		}

		fa = fa && (ehMatrizId(aux1) && ehMatrizId(aux2));

		return fa;
	}

	
	
	
}