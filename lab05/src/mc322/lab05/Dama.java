  
package mc322.lab05;

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
		
		//olhar se ha alguma peca da mesma cor durante o caminho
		//se ha 2 pecas adversarias consecutivas no caminho
		//se ha 1 peca adversaria n casa final
			permissao = true;
		
		return permissao;
	}	
}
