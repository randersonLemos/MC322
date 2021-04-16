package mc322.lab04;

public class Peca {
	String estado;
	
	Peca(String stg)
	{
		estado = stg;		
	}
	
	String retornaEstado()
	{
		return estado;
	}
	
	void mudaEstado(String stg)
	{
		estado = stg;
	}
}
