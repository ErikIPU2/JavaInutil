import java.util.Scanner;
public class Index {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("1. Codificar uma mensagem\n2. Decodificar uma mensagem");
		int menu = input.nextInt();

		if (menu==1) {
			System.out.println("Digite a sua mensagem: (Use \"#\" Para espacos)");

			char[] msg = input.next().toCharArray();
			int dif = (msg.length%2==0) ? msg.length:msg.length+1;
			// int dif = msg.length;
			int[] msgVal = new int[(msg.length%2==0) ? msg.length:msg.length+1];

			for (int i = 0; i<msg.length; i++) {
				msgVal[i] = charConverter(msg[i]);
			}

			int[][] msgMat = new int[2][msgVal.length/2];


			int aux = 0;

			for (int i = 0; i<msgMat.length; i++) {
				for (int j = 0; j<msgMat[i].length; j++) {
					msgMat[i][j] = msgVal[aux];
					aux++;
				}
			}

			matConst(msgMat);
			System.out.println();


			System.out.println("Digite a sua chave (quatro numeros separados por 'enter')");
			int[][] chave = new int[2][2];
			for (boolean Ctes = true;Ctes ;) {
				for (int i = 0; i<chave.length; i++) {
					for (int j = 0; j<chave[i].length; j++) {
						chave[i][j] = input.nextInt();
					}
				}
				Ctes = ((chave[0][0]*chave[1][1])-(chave[0][1]*chave[1][0])==0);

				if (Ctes) {
					System.out.println("Voce digitou uma chave errada, tente novamente");			
				}			
			}

			int[][] msgCript = Lib.mtpMatrizes(chave, msgMat);

			System.out.println("Sua mensagem secreta: ");

			matConst(msgCript);


		}
		else if (menu==2) {
			System.out.println("Digite a mensagem criptograda: (separe por \".\") ");
			String msg = input.next();

			int[][] msgsep = sepString(msg);
			float[][] chave = new float[2][2];

			System.out.println("Digite a sua chave: (separados por enter)");
			for (int i = 0; i<chave.length; i++) {
				for (int j = 0; j<chave.length; j++) {
					chave[i][j] = input.nextInt();
				}
			}

			chave = inversor(chave);

			for (int i = 0; i<chave.length; i++) {
				for (int j = 0; j<chave[i].length; j++) {
					System.out.print(chave[i][j]+" ");
				}
				System.out.println();
			}

			float[][] msgsep2 = mtpMatrizes(chave, msgsep);
			System.out.println("\n\n");

			for (int i = 0; i<msgsep2.length; i++) {
				for (int j = 0; j<msgsep2[i].length; j++) {
					System.out.print(msgsep2[i][j]+" ");
				}
				System.out.println();
			}

			System.out.println("Sua mensagem: ");

			revelador(msgsep2);

		}
	}








	public static int charConverter(char val){
		if (val=='#') {
			return 0;
		}
		else {
			//int tes = (int) val;
			return ((int)val-96);
		}
	}


	public static void matConst(int[][] val){
		for (int i = 0; i<val.length; i++) {
			for (int j = 0; j<val[i].length; j++) {
				System.out.print(val[i][j]);
				if (j!=val[i].length-1 || i!=val.length-1) {
					System.out.print(".");
				}
			}
		}
	}


	public static int[][] sepString(String msg){
		msg += '.';
		int[] msgSep = new int[30];
		int aux = -1;
		int aux2 = 0;


		for (int i = 0; i<msg.length(); i++) {
			if (msg.charAt(i)=='.') {
				String preArray = "";
				for (int j = 0; j<msg.length(); j++) {
					if (j>aux && j<i) {
						preArray += msg.charAt(j);
					}
				}
				msgSep[aux2] = Integer.parseInt(preArray);
				aux = i;
				aux2++;
			}
		}

		msgSep[aux2] = 977785652;
		int aauxx = 0;

		boolean paranaousaebreak = true;
		for (int i = 0; i<msgSep.length && paranaousaebreak; i++) {
			if (msgSep[i]==977785652) {
				paranaousaebreak = false;
			}
			if (msgSep[i]!=977785652) {
				aauxx++;
			}
		}

		int[] msgFinal = new int[aauxx];
		for (int i = 0; i<msgFinal.length; i++) {
			msgFinal[i] = msgSep[i];
		}

		int[][] msgMat = new int[2][msgFinal.length/2];

			int auxxxxx = 0;
			for (int i = 0; i<msgMat.length; i++) {
				for (int j = 0; j<msgMat[i].length; j++) {
					msgMat[i][j] = msgFinal[auxxxxx];
					auxxxxx++;
				}
			}

		return msgMat;

	}

	public static float[][] inversor(float[][] chave){
		float det = (chave[0][0]*chave[1][1])-(chave[0][1]*chave[1][0]);
		float[][] chac = new float[2][2];
		chac[0][0] = (chave[1][1]/det);
		chac[1][1] = (chave[0][0]/det);
		chac[0][1] = (chave[0][1]/-det);
		chac[1][0] = (chave[1][0]/-det);

		return chac;
	}

	public static void revelador(float[][] mat){
		String rev = "";
		for (int i = 0; i<mat.length; i++) {
			for (int j = 0; j<mat[i].length; j++) {
				if (mat[i][j]==0) {
					rev += ' ';
				}
				else{
					float fa = mat[i][j];
					rev += (char)(fa+96);
				}
				
			}
		}
		System.out.println(rev);
	}

	public static float[][] mtpMatrizes(float[][] mat1, int[][] mat2){
		float[][] fa = new float[mat1.length][mat2[0].length];
		mat2 = Lib.trpMatriz(mat2);
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

}
