import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		char alfabeto[];
		int acao[][], estadoInicial, estadosFinais[];
		int numSimbolos, numEstados, numEstadosFinais, novoEstado;
		String nome;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Ol�! Bem vindo ao gerador de AFDs!\nInsira os dados solicitados!");
		System.out.println("= = = = = = = = = = = = = = = = =");
		System.out.println("Quanto s�mbolos h� no alfabeto?");
		
		do {
			numSimbolos = Integer.parseInt(scan.nextLine());
			if(numSimbolos <=0) {
				System.out.println("Quantidade de s�mbolos do alfabeto deve ser maior que zero.\n Insira novamente o n�mero de s�mbolos.");
			}
		} while(numSimbolos <= 0);
		alfabeto = new char[numSimbolos];
		for(int i = 0; i < numSimbolos; i++) {
			System.out.println("Qual o s�mbolo " + i + "?");
			alfabeto[i] = scan.nextLine().charAt(0);
		}
		
		System.out.println("Qual o n�mero de estados?");
		do {
			numEstados = Integer.parseInt(scan.nextLine());
			if(numEstados <=0) {
				System.out.println("Quantidade de estados deve ser maior que zero.\n Insira novamente o n�mero de estados.");
			}
		} while(numEstados <= 0);
		
		System.out.println("Quantos estados s�o finais?");
		do {
			numEstadosFinais = Integer.parseInt(scan.nextLine());
			if(numEstadosFinais <= 0 || numEstadosFinais > numEstados) {
				System.out.println("Quantidade de estados finais deve ser no m�nimo um e n�o pode ser superior que o n�mero de estados (" + numEstados + ").\n Insira novamente o n�mero de estados finais.");
			}
		} while(numEstadosFinais <= 0 || numEstadosFinais > numEstados);
		estadosFinais = new int[numEstadosFinais];
		for(int i = 0; i < numEstadosFinais; i++) {
			System.out.println("Qual o estado final " + i + "?");
			estadosFinais[i] = Integer.parseInt(scan.nextLine());
		}
		
		System.out.println("Qual estado e o inicial?");
		do {
			estadoInicial = Integer.parseInt(scan.nextLine());
			if(estadoInicial < 0 || estadoInicial >= numEstados) {
				System.out.println("Estado inicial deve ser maior que zero e menor que " + numEstados + ".\n Insira novamente o n�mero de estados.");
			}
		} while(estadoInicial < 0 || estadoInicial > numEstados);
		
		acao = new int[numEstados][numSimbolos];
		
		for(int i = 0; i < numEstados; i++) {
			for(int j = 0; j < numSimbolos; j++) {
				System.out.println("Quando estiver no estado e" + i + " e a entrada for \"" + alfabeto[j] + "\", para qual estado ir�?\n Caso n�o haja, insira -1");
				do {
					novoEstado = Integer.parseInt(scan.nextLine());
					if(novoEstado < -1 || novoEstado > (numEstados - 1)) {
						System.out.println("Estado n�o pode ser menor que -1 e maior que " + (numEstados - 1) + "!\nInsira novamente o estado que ir� quando estiver no estado e" + i + " e a entrada for \"" + alfabeto[j] + "\"\n Caso n�o haja, insira -1");
					}
				} while(novoEstado < -1 || novoEstado > (numEstados - 1));
				acao[i][j] = novoEstado;
			}
		}
		
		System.out.println("Qual o nome do Aut�mato?");
		nome = scan.nextLine();
		char nomeTemp[] = nome.toCharArray();
		nomeTemp[0] = Character.toUpperCase(nomeTemp[0]);
		
		
		Gerador obj = new Gerador(alfabeto, acao, estadoInicial, estadosFinais, numEstados, new String(nomeTemp));
		System.out.println(obj.gerarCodigo());
				
		GeradorMatriz object = new GeradorMatriz(alfabeto, acao, estadoInicial, estadosFinais, numEstados, new String(nomeTemp));
		System.out.println(object.gerarCodigo());
		
	}

}
