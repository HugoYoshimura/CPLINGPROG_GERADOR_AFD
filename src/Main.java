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
		
		System.out.println("Olá! Bem vindo ao gerador de AFDs!\nInsira os dados solicitados!");
		System.out.println("= = = = = = = = = = = = = = = = =");
		System.out.println("Quanto símbolos há no alfabeto?");
		
		do {
			numSimbolos = Integer.parseInt(scan.nextLine());
			if(numSimbolos <=0) {
				System.out.println("Quantidade de símbolos do alfabeto deve ser maior que zero.\n Insira novamente o número de símbolos.");
			}
		} while(numSimbolos <= 0);
		alfabeto = new char[numSimbolos];
		for(int i = 0; i < numSimbolos; i++) {
			System.out.println("Qual o símbolo " + i + "?");
			alfabeto[i] = scan.nextLine().charAt(0);
		}
		
		System.out.println("Qual o número de estados?");
		do {
			numEstados = Integer.parseInt(scan.nextLine());
			if(numEstados <=0) {
				System.out.println("Quantidade de estados deve ser maior que zero.\n Insira novamente o número de estados.");
			}
		} while(numEstados <= 0);
		
		System.out.println("Quantos estados são finais?");
		do {
			numEstadosFinais = Integer.parseInt(scan.nextLine());
			if(numEstadosFinais <= 0 || numEstadosFinais > numEstados) {
				System.out.println("Quantidade de estados finais deve ser no mínimo um e não pode ser superior que o número de estados (" + numEstados + ").\n Insira novamente o número de estados finais.");
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
				System.out.println("Estado inicial deve ser maior que zero e menor que " + numEstados + ".\n Insira novamente o número de estados.");
			}
		} while(estadoInicial < 0 || estadoInicial > numEstados);
		
		acao = new int[numEstados][numSimbolos];
		
		for(int i = 0; i < numEstados; i++) {
			for(int j = 0; j < numSimbolos; j++) {
				System.out.println("Quando estiver no estado e" + i + " e a entrada for \"" + alfabeto[j] + "\", para qual estado irá?\n Caso não haja, insira -1");
				do {
					novoEstado = Integer.parseInt(scan.nextLine());
					if(novoEstado < -1 || novoEstado > (numEstados - 1)) {
						System.out.println("Estado não pode ser menor que -1 e maior que " + (numEstados - 1) + "!\nInsira novamente o estado que irá quando estiver no estado e" + i + " e a entrada for \"" + alfabeto[j] + "\"\n Caso não haja, insira -1");
					}
				} while(novoEstado < -1 || novoEstado > (numEstados - 1));
				acao[i][j] = novoEstado;
			}
		}
		
		System.out.println("Qual o nome do Autômato?");
		nome = scan.nextLine();
		char nomeTemp[] = nome.toCharArray();
		nomeTemp[0] = Character.toUpperCase(nomeTemp[0]);
		
		
		Gerador obj = new Gerador(alfabeto, acao, estadoInicial, estadosFinais, numEstados, new String(nomeTemp));
		System.out.println(obj.gerarCodigo());
				
		GeradorMatriz object = new GeradorMatriz(alfabeto, acao, estadoInicial, estadosFinais, numEstados, new String(nomeTemp));
		System.out.println(object.gerarCodigo());
		
	}

}
