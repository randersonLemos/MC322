package mc322.lab05b;

public class Dama extends Peca
{
	Dama(String simb, int lin, int col)
	{
		super(simb.toUpperCase(), lin, col);
		tipo = "dama";		
	}		

	
	boolean tentaMovimento(String trajeto, int linF, int colF)
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
		else
			System.out.println(tipo + " não faz esse trajeto: " + trajeto);
			
		if(permissao) 
			atulizaPosicao(linF, colF);
		
		return permissao;
	}	
}