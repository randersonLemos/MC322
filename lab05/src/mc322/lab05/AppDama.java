  
package mc322.lab05;

public class AppDama {

	public static void executaJogo(String caminho_para_entrada_csv)
	{
		CSVReader csv = new CSVReader();	
		csv.setDataSource(caminho_para_entrada_csv);
		
		String jogadas[] = csv.requestCommands();
		
		Tabuleiro tabuleiro = new Tabuleiro();
		
		for(int i=0; i<jogadas.length; i++)
		{
			tabuleiro.fazJogada(jogadas[i]);;
		}
	}

	public static void main(String[] args) {
		executaJogo("./src/mc322/lab05/entrada.csv");	
	}

}