import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class GeradorMatriz {
	
	char alfabeto[], palavra[];
	int acao[][], estadoInicial, estadosFinais[], estados[];
	int numEstados;
	String nome;
		
	public GeradorMatriz() {
		
	}
	
	public GeradorMatriz(char alfabeto[], int acao[][], int estadoInicial, int estadosFinais[], int numEstados, String nome) {
		this.alfabeto = alfabeto;
		this.acao = acao;
		this.estadoInicial = estadoInicial;
		this.estadosFinais = estadosFinais;
		this.numEstados = numEstados;
		this.nome = nome;
		this.estados = new int[numEstados];
		for(int i = 0; i < numEstados; i++) {
			estados[i] = i;
		}
	}
	
	
	public String gerarCodigo() {
		boolean status = false;
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(nome + "Matriz.java", "UTF-8");
			
			// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =  = = = = = = = = = = = = =
			// Declaração da classe e método construtor
			writer.println(
					"import java.util.Scanner; \r\n" +
					"\r\n" +
					"\r\n" +
					"public class " + nome + "Matriz {\r\n" + 
					"	char alfabeto[], palavra[];\r\n" + 
					"	int estados[], acao[][], estadoInic, estadoFin[], estadoAtual, simbolo, estado;\r\n" + 
					"	\r\n" + 
					"	public " + nome + "Matriz(String palavra) {\r\n" + 
					"		this.alfabeto = new char[]" + vetorCharString(alfabeto) + ";\r\n" + 
					"		this.estados = new int[] " + vetorIntString(estados) + ";\r\n" + 
					"		this.acao = new int[][] " + acaoString() + ";\r\n" + 
					"		this.estadoInic = 0;\r\n" + 
					"		this.estadoFin = new int[]" + vetorIntString(estadosFinais) + ";\r\n" + 
					"		this.palavra = palavra.toCharArray();\r\n" + 
					"		this.estadoAtual = estadoInic;\r\n" + 
					"		this.simbolo = 0;\r\n" + 
					"		this.estado = 0;\r\n" + 
					"	}\r\n" + 
					"	\r\n" + 
					"	public void automato() {\r\n" + 
					"		for(int i = 0; i < this.palavra.length; i++) {\r\n" + 
					"			if(!conferirAlfabeto(this.palavra[i])) {\r\n" + 
					"				rejeitar();\r\n" + 
					"			} else  if (acao[estado][simbolo] < 0) {\r\n" + 
					"				rejeitar();\r\n" + 
					"			} else {\r\n" + 
					"				estadoAtual = acao[estado][simbolo];\r\n" + 
					"				estado = estadoAtual;\r\n" + 
					"			}\r\n" + 
					"			if(i == (this.palavra.length - 1)) {\r\n" + 
					"				if(conferirEstadoFinal(estadoAtual)) {\r\n" + 
					"					aceitar();\r\n" + 
					"				} else {\r\n" + 
					"					rejeitar();\r\n" + 
					"				}\r\n" + 
					"			}\r\n" + 
					"		}\r\n" + 
					"	}\r\n" + 
					"	\r\n" + 
					"	public void rejeitar() {\r\n" + 
					"		System.out.println(\"Rejeita \\\"\" + new String(palavra) + \"\\\"\");\r\n" + 
					"		System.exit(0);\r\n" + 
					"	}\r\n" + 
					"	\r\n" + 
					"	public void aceitar() {\r\n" + 
					"		System.out.println(\"Aceita \\\"\" + new String(palavra) + \"\\\"\");\r\n" + 
					"		System.exit(0);\r\n" + 
					"	}\r\n" + 
					"	\r\n" + 
					"	public boolean conferirAlfabeto(char entrada) {\r\n" + 
					"		boolean result = false;\r\n" + 
					"		for(int i = 0; i < alfabeto.length; i++) {\r\n" + 
					"			if(alfabeto[i] == entrada) {\r\n" + 
					"				simbolo = i;\r\n" + 
					"				result = true;\r\n" + 
					"				break;\r\n" + 
					"			}\r\n" + 
					"			else\r\n" + 
					"				result = false;\r\n" + 
					"		}\r\n" + 
					"		return result;\r\n" + 
					"	}\r\n" + 
					"	\r\n" + 
					"	public boolean conferirEstadoFinal(int atual) {\r\n" + 
					"		boolean result = false;\r\n" + 
					"		for(int i = 0; i < estadoFin.length; i++) {\r\n" + 
					"			if(atual == estadoFin[i]) {\r\n" + 
					"				estado = i;\r\n" + 
					"				result = true;\r\n" + 
					"				break;\r\n" + 
					"			} else {\r\n" + 
					"				result = false;\r\n" + 
					"			}\r\n" + 
					"		}\r\n" + 
					"		return result;\r\n" + 
					"	}\r\n" +
					"	\r\n" + 
					"	public static void main(String[] args) {\r\n" + 
					"		Scanner scan = new Scanner(System.in);\r\n" +
					"		System.out.println(\"Insira a palavra:\");\r\n" +
					"		String palavra = scan.nextLine();\r\n" +
					"		" + nome + "Matriz obj = new " + nome + "Matriz(palavra);\r\n" + 
					"		obj.automato();\r\n" +
					"	}\r\n" +
					"	\r\n" + 
					"}");
			
			writer.close();
			
			status = true;
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(status) {
			return "Gerado arquivo " + nome + "Matriz.java!";
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
	
	public String acaoString() {
		String result = "{";
		for(int i = 0; i < acao.length; i++) {
			result += "{";
			for (int j = 0; j < acao[0].length; j++) {
				result += acao[i][j];
				if(j < acao[0].length-1) {
					result += ", ";
				}
			}
			result += "}";
			if(i < acao.length-1) {
				result += ", ";
			}
		}
		result += "}";
		
		return result;
	}
	
	public String vetorIntString(int[] vet) {
		String result = "{";
		for (int j = 0; j < vet.length; j++) {
			result += vet[j];
			if(j < vet.length-1) {
				result += ", ";
			}
		}
		result += "}";
		
		return result;
	}
	
	public String vetorCharString(char[] vet) {
		String result = "{";
		for (int j = 0; j < vet.length; j++) {
			result += "\'" + vet[j] + "\'";
			if(j < vet.length-1) {
				result += ", ";
			}
		}
		result += "}";
		
		return result;
	}
	

}
