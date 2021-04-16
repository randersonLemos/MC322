package mc322.lab04;

public class AppRestaUm {
	
	public static String[] executaJogo(String caminho_para_entrada_csv)
	{
		CSVReader csv = new CSVReader();	
		csv.setDataSource(caminho_para_entrada_csv);
		
		String comandos[] = csv.requestCommands();
		
		Tabuleiro tabuleiro = new Tabuleiro();
		for(int i=0; i<comandos.length; i++)
		{
			tabuleiro.aplicaComando(comandos[i]);
		}
		
		String estados[] = new String[tabuleiro.count];
		for(int i=0; i<tabuleiro.count; i++)
		{
			estados[i] = tabuleiro.estados[i];
 		}
			
		for(int i=0; i<estados.length; i++)
		{
			System.out.print(estados[i]);
			System.out.println();
		}
		
		return estados;
		
	}

	public static void main(String[] args) {
		executaJogo("./src/mc322/lab04/entrada.csv");	
	}

}
