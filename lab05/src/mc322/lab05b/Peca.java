package mc322.lab05b;

public class Peca
{	
	String tipo = "";	
	String cor = "";
	int lin = -1;
	int col = -1;
	
	
	Peca(String cor, int lin, int col)
	{	
		this.cor = cor;
		this.lin = lin;
		this.col = col;
		this.tipo = "peca";
	}
	
	
	String getCor()
	{
		return cor;
	}
	
	
	String getTipo()
	{
		return tipo;
	}	
	
	
	void atulizaPosicao(int lin, int col)
	{
		this.lin = lin;
		this.col = col;
	}
	
	
	boolean tentaMovimento(String trajeto, int linF, int colF)
	{
		System.out.println("Método a ser implementado nas sub-classes.");
		return false;
	}
}