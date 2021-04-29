package mc322.lab05;

public class AppDama {

	public static void executaJogo(String caminho_para_entrada_csv)
	{
		CSVReader csv = new CSVReader();	
		csv.setDataSource("./src/mc322/lab05/entrada.csv");		
		
		Tabuleiro tabuleiro = new Tabuleiro();
		
		System.out.println(tabuleiro.estadoComBordasTabuleiro());
		
		String jogadas[] = csv.requestCommands();
		System.out.println(jogadas.length);
		
		for(int i=0; i<jogadas.length; i++)
		{
			tabuleiro.fazJogada(jogadas[i]);
			System.out.println("Lance: " + (i+1));
			System.out.println(tabuleiro.estadoComBordasTabuleiro());
		}
	}

	public static void main(String[] args) {
		executaJogo("./src/mc322/lab05/entrada.csv");	
	}

}