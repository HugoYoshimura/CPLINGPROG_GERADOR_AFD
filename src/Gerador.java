import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Gerador {
	
	char alfabeto[], palavra[];
	int acao[][], estadoInicial, estadosFinais[];
	int numEstados;
	String nome;
		
	public Gerador() {
		
	}
	
	public Gerador(char alfabeto[], int acao[][], int estadoInicial, int estadosFinais[], int numEstados, String nome) {
		this.alfabeto = alfabeto;
		this.acao = acao;
		this.estadoInicial = estadoInicial;
		this.estadosFinais = estadosFinais;
		this.numEstados = numEstados;
		this.nome = nome;
	}
	
	
	public String gerarCodigo() {
		boolean status = false;
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(nome + ".java", "UTF-8");
			
			// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =  = = = = = = = = = = = = =
			// Declaração da classe e método construtor
			writer.println(
					"import java.util.Scanner; \r\n" +
							"\r\n" +
							"\r\n" +
							"public class " + nome + " {\r\n" + 
					"	char palavra[];\r\n" + 
					"	int pos;\r\n" + 
					"	\r\n" + 
					"	public " + nome + "(char[] palavra) {\r\n" + 
					"		this.palavra = palavra;\r\n" + 
					"		this.pos = 0;\r\n" + 
					"	}\r\n" + 
					"	\r\n");
			
			// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =  = = = = = = = = = = = = =
			// Criação dos métodos de estados
			String codeElse = "";
			
			for(int i = 0; i < numEstados; i++) {
				writer.println(
						"	public void e" + i + "() {\r\n");
				
				writer.println(
						"		if(pos >= palavra.length) {\r\n");
				
				if(conferirEstadoFinal(i)) {
					writer.println(	
							"			aceitar();\r\n");
				} else {
					writer.println(	
							"			rejeitar();\r\n");
				}
				writer.println(
						"		}\r\n");
				
				// ações
				for(int j = 0; j < alfabeto.length; j++) {
					if(acao[i][j] >=0) {
						writer.println(
								"		" + codeElse + "if(palavra[pos] == \'" + alfabeto[j] + "\') {\r\n" +
										"			pos++;\r\n" +
										"			e" + acao[i][j] + "();\r\n" +
								"		}\r\n");
					} else {
						writer.println(
								"		" + codeElse + "if(palavra[pos] == \'" + alfabeto[j] + "\') {\r\n" +
								"			rejeitar();\r\n" +
								"		}\r\n");
					}
					if(j == 0) {
						codeElse = "else ";
					}
				}
				writer.println(
						"		else {\r\n" +
						"			rejeitar();\r\n" +
						"		}\r\n");
				writer.println(
						"	}\r\n" + 
						"	\r\n");
			}
			
			// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =  = = = = = = = = = = = = =
			// Escreve método rejeitar
			writer.println(
					"	public void rejeitar() {\r\n" + 
					"		System.out.println(\"Rejeita \\\"\" + new String(palavra) + \"\\\"\");\r\n" + 
					"		System.exit(0);\r\n" + 
					"	}\r\n" + 
					"	\r\n");

			// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =  = = = = = = = = = = = = =
			// Escreve método aceitar
			writer.println(
					"	public void aceitar() {\r\n" + 
					"		System.out.println(\"Aceita \\\"\" + new String(palavra) + \"\\\"\");\r\n" + 
					"		System.exit(0);\r\n" + 
					"	}\r\n" + 
					"	\r\n");
			
			// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =  = = = = = = = = = = = = =
			// Declaração da classe main
			writer.println(
					"	public static void main(String[] args) {\r\n" + 
					"		Scanner scan = new Scanner(System.in);\r\n" +
					"		System.out.println(\"Insira a palavra:\");\r\n" +
					"		String palavra = scan.nextLine();\r\n" +
					"		" + nome + " obj = new " + nome + "(palavra.toCharArray());\r\n" + 
					"		obj.e0();\r\n" +
					"	}\r\n" +
					"	\r\n" +
					"}\r\n");

			writer.close();
			
			status = true;
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(status) {
			return "Gerado arquivo " + nome + ".java!";
		} else {
			return "Arquivo não gerado";
		}
		
	}
	
	public boolean conferirEstadoFinal(int atual) {
		boolean result = false;
		for(int i = 0; i < estadosFinais.length; i++) {
			if(atual == estadosFinais[i]) {
				result = true;
				break;
			}
		}
		return result;
	}
}
