package mc322.lab05;

public class Tabuleiro {
	int maxLin = 8;
	int maxCol = 8;
	Peca matrixPeca[][] = new Peca[maxLin][maxCol];
	Dama matrixDama[][] = new Dama[maxLin][maxCol];
	
	Tabuleiro()	
	{
		for(int i=0; i<maxLin; i++)
		{
			for(int j=0; j<maxCol; j++)
			{
				if(i < 3)
				{
					if((i+j)%2 != 0)
					{
						matrixPeca[i][j] = new Peca("p", i, j);	
					}					
				}
				if(i >= 5)
				{
					if((i+j)%2 != 0)
					{
						matrixPeca[i][j] = new Peca("b", i, j);	
					}
				}
			}			
		}		
	}
	
	void fazJogada(String jogada)
	{
		int colI = (int)jogada.charAt(0) - 97; int linI = 49 + maxLin - 1 - (int)jogada.charAt(1);
		int colF = (int)jogada.charAt(3) - 97; int linF = 49 + maxLin - 1 - (int)jogada.charAt(4);
		
		String trajeto = fazTrajeto(linI, colI, linF, colF); 
		
		Peca peca = matrixPeca[linI][colI];
		Dama dama = matrixDama[linI][colI];
		
		if(peca != null)
		{
			peca.movimentaPeca(trajeto);
		}
		if(dama != null)
		{
			dama.movimentaDama(trajeto);
		}
		if(peca == null && dama == null)
		{
			System.out.println("Não há peça na posição: " + jogada);
		}
		
		
		System.out.println(colI + " " + linI + " " + colF + " " + linF + " " + trajeto);
	}
	
	String fazTrajeto(int linI, int colI, int linF, int colF)
	{
		int colDelta = colF - colI;
		int linDelta = linF - linI;
		
		int colIncremento = colDelta/Math.abs(colDelta);
		int linIncremento = linDelta/Math.abs(linDelta);		
		
		String trajeto ="";
		
		int col = colI;
		int lin = linI;
		while(true)
		{
			trajeto += estadoPosicao(col, lin);
			
			col += colIncremento;
			lin += linIncremento;
			
			System.out.println("l: " + lin + " c: " + col);
			
			if(col == colF)
			{
				trajeto += estadoPosicao(col, lin);
				break;
			}
		}
		return trajeto;
	}
	
	String estadoPosicao(int lin, int col)
	{
		String estado = "";
		
		Peca peca = matrixPeca[lin][col];
		Dama dama = matrixDama[lin][col];
		if(peca != null)
		{
			estado += peca.verPeca();
		}
		if(dama != null)
		{
			estado += dama.verDama();
		}
		if(peca == null && dama == null)
		{
			estado += "-";
		}
		
		return estado;
	}
	
	String estadoTabuleiro()
	{
		String estado = "";
		for(int i=0; i<maxLin; i++)
		{
			for(int j=0; j<maxCol; j++)
			{
				estado += estadoPosicao(i,j);
			}
			estado += "\n";
		}
		return estado;
	}
	
	String estadoComBordasTabuleiro()
	{
		String estado = "";
		for(int i=0; i<maxLin+1; i++)
		{
			for(int j=-1; j<maxCol; j++)
			{
				if(i >= 0 && j >= 0 && i < maxLin && j < maxCol)
				{
					estado += estadoPosicao(i,j);
				}
				if(i == maxLin && j != -1)
				{
					estado += String.valueOf((char)(j + 97));
				}
				if(i != maxLin && j == -1)
				{
					estado += (maxLin - i);
				}
				if(i == maxLin && j == -1)
				{
					estado += " "; 
				}
			}
			estado += "\n";
		}
		return estado;
	}
	
	public static void main(String[] args){		
		CSVReader csv = new CSVReader();	
		csv.setDataSource("./src/mc322/lab05/entrada.csv");
		
		String jogadas[] = csv.requestCommands();
		
		Tabuleiro tabuleiro = new Tabuleiro();
		
		System.out.println(tabuleiro.estadoTabuleiro());
		
		for(int i=0; i<jogadas.length; i++)
		{
			tabuleiro.fazJogada(jogadas[i]);;
		}
	}

}
