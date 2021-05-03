package mc322.lab05b;

public class AppDama {

	public static void executaJogo(String caminho_para_comandos_csv)
	{
			System.out.println("Tem que implementar.\n\n");
	}
	
	static void testaPecas()
	{
		System.out.println("Testando peças.\n\n");
		
		Peca peca = new Peca("a", 2, 2);	
		
		System.out.println("Tipo: " + peca.getTipo() + ", cor: " + peca.getCor());
		
		peca = new Peao("b", 2, 2);
		
		System.out.println("Tipo: " + peca.getTipo() + ", cor: " + peca.getCor());
		
		peca = new Dama("b", 2, 2);
		
		System.out.println("Tipo: " + peca.getTipo() + ", cor: " + peca.getCor());	
		
		System.out.println("\n\n---");
	}
	
	static void testaTabuleiro()
	{
		System.out.println("Testando Tabuleiro.\n\n");
		
		Tabuleiro tab = new Tabuleiro();
		
		tab.imprimirTabuleiro();
		
		System.out.println("\n\n---");
	}
	
	static void testaJogo()
	{
		System.out.println("Testando Jogo.\n\n");
		
		Tabuleiro tab = new Tabuleiro();
		
		CSVHandling csv = new CSVHandling(); 
		
		csv.setDataSource("./comandos.csv");
		
		String[] comandos = csv.requestCommands();
		
		for(int i=0; i<comandos.length; i++)
		{
			tab.imprimirTabuleiro();
			tab.solicitaMovimento(comandos[i]);
		}
		
		System.out.println("\n\n---");
	}

	public static void main(String[] args)
	{		
		//testaPecas();
		//testaTabuleiro();
		testaJogo();
	}

}
