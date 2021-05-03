package mc322.lab05a;

public class Tabuleiro {
	int maxLin = 8;
	int maxCol = 8;
	Peca matrixPeca[][] = new Peca[maxLin][maxCol];
	Dama matrixDama[][] = new Dama[maxLin][maxCol];
	String source = "Tabuleiro inicial:";
	String target = "";
	public boolean setomou = false;
	String jogadaAnterior ="";
	
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
		int colI = ((int)jogada.charAt(0) - 97); 
		int linI = 8-((int)jogada.charAt(1)- 48);
		int colF = ((int)jogada.charAt(3) - 97); 
		int linF = 8-((int)jogada.charAt(4)- 48);
		
		if(verificaJogada(linI, colI, linF, colF, jogada))
		{
			String trajeto = fazTrajeto(linI, colI, linF, colF); 
			String peixonauta = MinimizarTrajetoDama(trajeto);
			
			Peca peca = matrixPeca[linI][colI];
			Dama dama = matrixDama[linI][colI];
			
			if(peca != null)
			{		
				if(peca.movimentaPeca(trajeto, linF, colF))
				{
					source = "Source: " + jogada.charAt(0) + jogada.charAt(1);
					target = "Target: " + jogada.charAt(3) + jogada.charAt(4);
					
					if(trajeto.length() == 3) // Se for movimento de tomada de peca
					{
						matrixPeca[(linI+linF)/2][(colI+colF)/2] = null; // A média retorna a casa entre a inicial e a final
						matrixDama[(linI+linF)/2][(colI+colF)/2] = null;
						setomou = true;
					}
					else
					{
						setomou = false;
					}
					
					matrixPeca[linI][colI] = null;
					matrixPeca[linF][colF] = peca;
					jogadaAnterior = jogada;
					
					if(corDaPeca(linF, colF).equals("b") && linF == 0) 
					{
						matrixPeca[linF][colF] = null;
						matrixDama[linF][colF] = new Dama("B", linF, colF);
					}
					else if(corDaPeca(linF, colF).equals("p") && linF == 7)
					{
						matrixPeca[linF][colF] = null;
						matrixDama[linF][colF] = new Dama("P", linF, colF);
					}
				}
				else 	
				{
					setomou = false;		
				}			
			}
			
			else if(dama != null)
			{			
				if(dama.movimentaDama(peixonauta, linF, colF))
				{
					source = "source: " + jogada.charAt(0) + jogada.charAt(1);
					target = "target: " + jogada.charAt(3) + jogada.charAt(4);
					
					if(peixonauta.length() == 3 || peixonauta.length() == 4) // Se for movimento de tomada de peca
					{
						int colDelta = colF - colI;
						int linDelta = linF - linI;
						
						int colIncremento = colDelta/Math.abs(colDelta);
						int linIncremento = linDelta/Math.abs(linDelta);
						int linha = linI, coluna = colI;
						
						for(int i=1; i < trajeto.length(); i++) 
						{
							linha += linIncremento;
							coluna += colIncremento;
							
							if(trajeto.substring(i, i+1) != "-") 
							{
							    matrixDama[linha][coluna] = null;
							    matrixPeca[linha][coluna] = null;
							}
						}
						setomou = true;
					}
					else
					{
						setomou = false;
					}
					matrixDama[linI][colI] = null;
					matrixDama[linF][colF] = dama;
					jogadaAnterior = jogada;
				}
				else
				{
					setomou = false;
				}
			}
		}
	}
	
	boolean verificaJogada(int linI, int colI, int linF, int colF, String jogada)
	{
		Peca peca = matrixPeca[linI][colI];
		Dama dama = matrixDama[linI][colI];
		String cor = "";
		
		if(peca != null)
			cor = peca.verPeca();
		if(dama != null)
			cor = dama.verDama();
		
		if(peca == null && dama == null)
		{
			System.out.println("Não há peça na posição: " + jogada);
			System.out.println("Estado do tabuleiro não atualizado");
			return false;
		}
		
		int colDelta = Math.abs(colF - colI);
		int linDelta = Math.abs(linF - linI);
		
		// Verificação de movimento na diagonal
		if(colDelta != linDelta)
		{
			System.out.println("Apenas jogadas na diagonal são válidas: " + jogada);			
			System.out.println("Estado do tabuleiro não atualizado");
			return false;
		}
		
		String trajeto = fazTrajeto(linI, colI, linF, colF);
		String peixonauta = MinimizarTrajetoDama(trajeto); // Minimização do trajeto pra dama
		

		// Verificação de lance obrigatório
		for(int i=0; i<maxLin; i++)
		{
			for(int j=0; j<maxCol; j++)
			{	
				String[] trajetos = fazTrajetosAteBordaDoTabuleiro(i, j);		
		
				if(trajetos[0].charAt(0) != '-' &&	trajetos[0].toLowerCase().charAt(0) == cor.charAt(0))
				{
					if(peca != null)
					{
						//System.out.println("trajeto ref " + trajeto);
						for(int z=0; z<4; z++)
						{
					
							if(trajetos[z].length() >= 3)
							{
								// System.out.println("trajeto " + z + " " + trajetos[z]);
								if(trajetos[z].substring(0,3).equals(cor + outraCor(cor) + "-"))
								{
									//System.out.println("trajeto  : " + trajeto);
									//System.out.println("trajeto " + (z+1) + ": " + trajetos[z]);
									if(!trajeto.equals(trajetos[z].substring(0,3)))
									{
										System.out.println("Jogada não permita. Há uma jogada obrigatária pendente :" + jogada);
										System.out.println("Estado do tabuleiro não atualizado");
										return false;	
									}									
								}
								if(trajetos[z].substring(0,3).equals(cor.toUpperCase() + outraCor(cor) + "-"))
								{
									//System.out.println("trajeto " + z + " " + trajetos[z]);
									if(!trajeto.equals(trajetos[z].substring(0,3))) 
									{
										System.out.println("Jogada não permita. Há uma jogada obrigatória pendente :" + jogada);
										System.out.println("Estado do tabuleiro não atualizado");
										return false;
									}
								}
							}
						}
					}
					else if(dama != null)
					{
						//System.out.println("trajeto ref " + trajeto);
						for(int z=0; z<4; z++)
						{					
							if(trajetos[z].length() >= 3)
							{
								//System.out.println("trajeto " + z + " " + trajetos[z]);
								if(trajetos[z].substring(0,3).equals(cor + outraCor(cor) + "-") || (trajetos[z].substring(0,3).equals(cor + "-" + outraCor(cor) + "-")))
								{
									System.out.println("trajeto  : " + trajeto);
									System.out.println("trajeto " + (z+1) + ": " + trajetos[z]);
									if(!peixonauta.equals(trajetos[z].substring(0,3)))
									{
										System.out.println("Jogada não permita. Há uma jogada obrigatória pendente :" + jogada);
										System.out.println("Estado do tabuleiro não atualizado");
										return false;	
									}									
								}
								if(trajetos[z].substring(0,3).equals(cor.toUpperCase() + outraCor(cor) + "-"))
								{
									//System.out.println("trajeto " + z + " " + trajetos[z]);
									if(!peixonauta.equals(trajetos[z].substring(0,3))) 
									{
										System.out.println("Jogada não permita. Há uma jogada obrigatória pendente :" + jogada);
										System.out.println("Estado do tabuleiro não atualizado");
										return false;
									}
								}
							}
						}
					}				
				}
			}
		}
		return true;
	}	
	
	
	String fazTrajeto(int linI, int colI, int linF, int colF)
	{
		int colDelta = colF - colI;
		int linDelta = linF - linI;
		
		int colIncremento = colDelta/Math.abs(colDelta);
		int linIncremento = linDelta/Math.abs(linDelta);		
		
		String trajeto = "";
		
		int col = colI;
		int lin = linI;
		while(true)
		{
			trajeto += estadoPosicao(lin, col);
			
			col += colIncremento;
			lin += linIncremento;			
		
			if(col == colF)
			{
				trajeto += estadoPosicao(lin, col);
				break;
			}
		}
		return trajeto;
	}
	
	
	public String MinimizarTrajetoDama(String trajeto) {
		String auxT = trajeto; //string auxiliar de trajeto
	    String auxTI = "";     //substring inicial de auxT
	    String auxTF = "";     //substring inicial de auxT
	    int i = 0;
		int a = 0;
			
	    //System.out.println("comeco: " + auxT);
		while(i < auxT.length())
		{
			if(auxT.charAt(i) == '-') 
			{
		        auxTI = auxT.substring(0, i+1);
		        if(i != auxT.length()-1)
		        {
				  a = i+1;
				  
		          while(auxT.charAt(a) == '-')
		          {
		            if(a == auxT.length()-1) 
		            {
		            	break;
		            }
		            a++;
		          }
		          
		          auxTF = auxT.substring(a);
		          
		          if(auxTF.equals("-"))
		          {
		            auxTF = "";
		          }
		          
		          auxT = auxTI + auxTF;
		          i = a-1;
		        }
		        i++;
			}
			else
			{
				i++;
			}
		}	
		return auxT;
	}
	

	String[] fazTrajetosAteBordaDoTabuleiro(int lin, int col)
	{
		String[] trajetos = new String[4];
		trajetos[0] = "";
		trajetos[1] = "";
		trajetos[2] = "";
		trajetos[3] = "";		
		
		int l = lin;
		int c = col;		
		while(true)
		{
			trajetos[0] += estadoPosicao(l, c);
			l += 1;
			c += 1;
			if(l == maxLin || c == maxCol)
			{
				break;
			}
		}
		
		l = lin;
		c = col;
		while(true)
		{
			trajetos[1] += estadoPosicao(l, c);
			l -= 1;
			c += 1;
			if(l == -1 || c == maxCol)
			{
				break;
			}
		}
		
		l = lin;
		c = col;
		while(true)
		{
			trajetos[2] += estadoPosicao(l, c);
			l += 1;
			c -= 1;
			if(l == maxLin || c == -1)
			{
				break;
			}
		}
		
		l = lin;
		c = col;
		while(true)
		{
			trajetos[3] += estadoPosicao(l, c);
			l -= 1;
			c -= 1;
			if(l == -1 || c == -1)
			{
				break;
			}
		}		
		return trajetos;		
	}
	
	
	String corDaPeca(int lin, int col)
	{
		String cor = null;
		
		Peca peca = matrixPeca[lin][col];
		Dama dama = matrixDama[lin][col];
	
		if(peca != null)
		{
			cor = peca.cor.toLowerCase();			
		}
		if(dama != null)
		{
			cor = dama.cor.toLowerCase();
		}		
		return cor;	
	}
	
	String outraCor(String cor)
	{
		if(cor.toLowerCase().equals("b"))
		{
			return "p";
		}
		return "b";
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

		if(target.length() != 0)
		{
			estado += source + "\n";
			estado += target + "\n";
		}
		else
		{
			estado += source + "\n";
		}		
		
		for(int i=0; i<maxLin+1; i++)
		{
			for(int j=-1; j<maxCol; j++)
			{
				if(i >= 0 && j >= 0 && i < maxLin && j < maxCol)
				{
					estado += estadoPosicao(i,j);
					estado += " ";
				}
				if(i == maxLin && j != -1)
				{
					estado += String.valueOf((char)(j + 97));
					estado += " ";
				}
				if(i != maxLin && j == -1)
				{
					estado += (maxLin - i);
					estado += " ";
				}
				if(i == maxLin && j == -1)
				{
					estado += " ";
					estado += " ";
				}
			}
			estado += "\n";
		}
		return estado;
	}
}
