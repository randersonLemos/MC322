package mc322.lab05b;

public class Peao extends Peca
{	
	Peao(String simb, int lin, int col)
	{	
		super(simb, lin, col);		
		tipo = "peao";		
	}
	
	
	boolean tentaMovimento(String trajeto, int linF, int colF)
	{
		boolean permissao = false;
		
				
		if(trajeto.equals("b-"))	// Lance normal
			permissao = true;
		else if(trajeto.equals("bp-") || trajeto.equals("bP-"))	// Come peça adversaria
			permissao = true;
		else if(trajeto.equals("p-"))			// Lance normal
			permissao = true;
		else if(trajeto.equals("pb-") || trajeto.equals("pB-"))			// Come peça adversaria
			permissao = true;
		else
			System.out.println(tipo + " não faz esse trajeto: " + trajeto);
			
		if(permissao) 
			atulizaPosicao(linF, colF);
		
		return permissao;
	}
}