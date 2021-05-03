package mc322.lab05a;

public class AppDama {

	public static String[] executaJogo(String caminho_para_comandos_csv)
	{
		CSVReader csv = new CSVReader();	
		csv.setDataSource(caminho_para_comandos_csv);		
		
		Tabuleiro tabuleiro = new Tabuleiro();
		
		System.out.println(tabuleiro.estadoComBordasTabuleiro());
		
		String jogadas[] = csv.requestCommands();
		String vec[] = new String[jogadas.length+1];
		vec[0] = tabuleiro.estadoTabuleiro();

		for(int i=0; i<jogadas.length; i++)
		{
			tabuleiro.fazJogada(jogadas[i]);
			System.out.println(tabuleiro.estadoComBordasTabuleiro());
			vec[i+1] = tabuleiro.estadoTabuleiro();
		}
		
		return vec;
	}

	public static void main(String[] args) {		
		executaJogo("./comandos.csv");	

	}

}
