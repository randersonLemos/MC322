package mc322.lab05;

public class Peca {
	String cor = "";
	int posLin = -1;
	int posCol = -1;
	
	Peca(String cor, int posLin, int posCol)
	{	
		this.cor = cor;
		this.posLin = posLin;
		this.posCol = posCol;
	}
	
	String verPeca()
	{
		return cor;
	}
	
	boolean movimentaPeca(String trajeto, int linF, int colF)
	{
		System.out.println("movimentaPeca(): Tem que implementar");
		return true;
	}
}