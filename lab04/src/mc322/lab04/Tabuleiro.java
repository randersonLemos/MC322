package mc322.lab04;

public class Tabuleiro {
	int maxRow = 8;
	int maxCol = 9;
	Peca matrix[][] = new Peca[maxRow][maxCol];
	String primeiraLinha = "Tabuleiro inicial:";
	int count = 0;
	int maxCount = 1000;
	String estados[] = new String[1000]; // Guarda numeração, letras, soucer and targe além do tabuleiro
	String estadosTabuleiro[] = new String[1000]; // Guarda apenas o tabuleiro
	
	Tabuleiro()
	{
		for(int i=0; i<maxRow; i++)
		{
			for(int j=0; j<maxCol; j++)
			{
				matrix[i][j] = new Peca("P");
				
				if(i < 2 && j < 3)
				{
					matrix[i][j] = new Peca(" ");
				}
				
				if (i < 2 && j > 5)					
				{
					matrix[i][j] = new Peca(" ");
				}

				if (j < 3 && i > 4)					
				{
					matrix[i][j] = new Peca(" ");
				}

				if (j > 5 && i > 4)					
				{
					matrix[i][j] = new Peca(" ");
				}
				
				if (j == 4 && i == 3)					
				{
					matrix[i][j] = new Peca("-");
				}
				
				if(i == maxRow - 1)
				{
					char ch = (char)(j - 1 + 97);
					
					matrix[i][j] = new Peca(String.valueOf(ch));
				}

				if(j == 0)
				{					
					matrix[i][j] = new Peca(String.valueOf(maxRow - 1 - i));
					if(i == maxRow - 1)
					{
						matrix[i][j] = new Peca(" ");
					}					
				}
				
				
				if(j == maxCol -1)
				{
					matrix[i][j] = new Peca("\n");
				}
			}
		}	
		
		salvaEstado();
		
	}
	
	void salvaEstado()
	{
		if(count < maxCount)
		{
			String conteudo = "";
			String conteudoTabuleiro = "";
			
			conteudo += primeiraLinha + "\n";
			
			for(int i=0; i<maxRow; i++)
			{
				for(int j=0; j<maxCol; j++)
				{
					conteudo += matrix[i][j].retornaEstado();
					if(i < maxRow - 1 && j > 0)
					{
						conteudoTabuleiro += matrix[i][j].retornaEstado();
					}
				}
			}
			
			estados[count] = conteudo;
			estadosTabuleiro[count] = conteudoTabuleiro;
			
			count += 1;			
		}
	}
	
	void verUltimoEstado()
	{
		System.out.println(estados[count - 1]);
	}
	
	void aplicaComando(String comando)
	{		
		
		if(count < maxCount) // Verifica se cabe no vetor de Strings estados
		{
			int xi = (int)comando.charAt(0) - 96; int yi = (int)comando.charAt(1) - 49;
			int xf = (int)comando.charAt(3) - 96; int yf = (int)comando.charAt(4) - 49;
			
			xi = 8 - xi; yi = 6 - yi;
			xf = 8 - xf; yf = 6 - yf;

			if(    (yi < 2 && xi < 3) // Verifica se está dentro do domínio
				|| (yi < 2 && xi > 5)
				|| (xi < 3 && yi > 4)	
				|| (xi > 5 && yi > 4)
				|| (xi < 1 || xi > 7)
				|| (yi < 0 || yi > 6)
				|| (yf < 2 && xf < 3)
				|| (yf < 2 && xf > 5)
				|| (xf < 3 && yf > 4)	
				|| (xf > 5 && yf > 4)
				|| (xf < 1 || xf > 7)
				|| (yf < 0 || yf > 6)
				)
			{
				System.out.println("Posição fora do dominínio");
				return;
			}
					
			int dx = xf - xi;
			int dy = yf - yi;
			int xx = -1; int yy = -1;
			
			if(dx == 0) // Verifica e contabiliza direção da movimentação
			{
				int ddy = dy/2;
				xx = xi;
				yy = yi+ddy;					
			}
			
			else if(dy == 0)
			{
				int ddx = dx/2;					
				xx = xi+ddx;
				yy = yi;					
			}
			
			else
			{
				System.out.println("Movimento na diagonal não é permitido: " + comando);
				return;
			}
			
			if(    matrix[yi][xi].retornaEstado() == "P" // Verifica se a movimentação é permitida
				&& matrix[yf][xf].retornaEstado() == "-"
				&& matrix[yy][xx].retornaEstado() == "P"
			  )
			{
				primeiraLinha = "Source: " + comando.substring(0,2) + "\n" + "Target: " + comando.substring(3,5);		
				
				matrix[yi][xi].mudaEstado("-");
				matrix[yf][xf].mudaEstado("P");
				matrix[yy][xx].mudaEstado("-");
				
				salvaEstado();
			}
			else
			{
				System.out.println("Movimento não permitido: " + comando);
			}						
		}			
	}
}
