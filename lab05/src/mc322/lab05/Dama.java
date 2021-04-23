package mc322.lab05;

public class Dama {
	String cor = "";
	int posLin = -1;
	int posCol = -1;
	
	Dama(String cor, int posLin, int posCol)
	{
		this.cor = cor;
		this.posLin = posLin;
		this.posCol = posCol;
	}		

	String verDama()
	{
		return cor;
	}
	
	boolean movimentaDama(String trajeto, int linF, int colF)
	{
		System.out.println("movimentaDama(): Tem que implementar");
		return true;
	}	
}
