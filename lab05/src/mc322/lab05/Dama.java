package mc322.lab05;

public class Dama {
	String cor = "";
	
	Dama(String cor)
	{
		this.cor = cor;
	}		

	String verDama()
	{
		return cor;
	}
	
	boolean movimentaDama(String trajeto)
	{
		System.out.println("movimentaDama(): Tem que implementar");
		return true;
	}	
}
