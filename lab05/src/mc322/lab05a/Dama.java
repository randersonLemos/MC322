package mc322.lab05a;

public class Dama {
	String cor = "";
	int posLin = -1;
	int posCol = -1;
	
	Dama(String cor, int posLin, int posCol)
	{
		this.cor = cor.toUpperCase();
		this.posLin = posLin;
		this.posCol = posCol;
	}		

	String verDama()
	{
		return cor;
	}
	
	boolean movimentaDama(String trajeto, int linF, int colF)
	{
		boolean permissao = false;
		if(trajeto.toLowerCase().equals("b-"))
			permissao = true;
		else if(trajeto.toLowerCase().equals("bp-") || trajeto.toLowerCase().equals("b-p-"))
			permissao = true;
		else if(trajeto.toLowerCase().equals("p-"))
			permissao = true;
		else if(trajeto.toLowerCase().equals("pb-") || trajeto.toLowerCase().equals("p-b-"))
			permissao = true;
		
		return permissao;
	}	
}
