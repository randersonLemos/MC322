package mc322.lab03;

public class Animacao {
	   AquarioLombriga al;
	   int tamAquario;
	   int tamLombriga;
	   int posLombriga;
	   String acoes;
	   
	   
	   Animacao(String stg){
	      this.tamAquario = Integer.parseInt(stg.substring(0, 2));
	      this.tamLombriga = Integer.parseInt(stg.substring(2, 4));
	      this.posLombriga = Integer.parseInt(stg.substring(4, 6));
	      
	      this.acoes = stg.substring(6);
	      
	      this.al = new AquarioLombriga(this.tamAquario, this.tamLombriga, this.posLombriga);
	   }   
	   
	   
	   String apresenta()
	   {
	      return this.al.lombrigaNoAquario();      
	   }
	   
	   
	   void passo()
	   {
	      if(this.acoes.length() != 0)
	      {
	         String acao = this.acoes.substring(0, 1);
	         this.acoes = this.acoes.substring(1);

	         if(acao.compareTo("M") == 0)
	         {
	            this.al.mover();
	         }
	         if(acao.compareTo("C") == 0)
	         {
	            this.al.crescer();         
	         }
	         if(acao.compareTo("V") == 0)
	         {
	            this.al.virar();
	         }       
	      }
	   }


	   public static void main(String[] args) {
	      Animacao anim = new Animacao("080403MCMVM");
	      System.out.println(anim.apresenta());
	      anim.passo(); System.out.println(anim.apresenta());
	      anim.passo(); System.out.println(anim.apresenta());
	      anim.passo(); System.out.println(anim.apresenta());
	      anim.passo(); System.out.println(anim.apresenta());
	      anim.passo(); System.out.println(anim.apresenta());
	      }
	}