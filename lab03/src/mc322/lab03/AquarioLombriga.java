package mc322.lab03;

public class AquarioLombriga {
	   int tailAquario;
	   int headAquario;
	   int tailLombriga;
	   int headLombriga;
	   String sentido = "LR"; // LR -> Left to Right; RL -> Right to Left 
	   
	   
	   AquarioLombriga(int tamAquario, int tamLombriga, int posLombriga){
	      this.tailAquario = 0;
	      this.headAquario = tamAquario;
	      this.tailLombriga = 0 + posLombriga - 1;
	      this.headLombriga = tamLombriga + posLombriga - 1;
	      
	      this.trataSeLombrigaMaiorQueAquario();
	      this.trataSeLombrigaNaoCabeNoAquarioNaPosicaoEspecificada();
	      
	   }   
	   

	   void crescer()
	   {
	      if(this.sentido == "LR")
	      {
	         if(this.tailLombriga > this.tailAquario)
	         {
	            this.tailLombriga -= 1;
	         }
	      }
	      else
	      {
	         if(this.headLombriga < this.headAquario)
	         {
	            this.headLombriga += 1;
	         }
	      }
	   }
	   
	   
	   void mover()
	   {
	      if(this.sentido == "LR")
	      {
	         if(this.headLombriga == this.headAquario)
	         {
	            this.virar();            
	         }

	         if(this.headLombriga < this.headAquario)
	         {
	            this.headLombriga += 1;
	            this.tailLombriga += 1;
	         }
	      }
	      else
	      {
	         if(this.tailLombriga == this.tailAquario)
	         {
	            this.virar();
	         }

	         
	         if(this.tailLombriga > this.tailAquario)
	         {
	            this.headLombriga -= 1;
	            this.tailLombriga -= 1;
	         }
	      
	      }

	   }
	   
	   
	   void virar()
	   {
	      if(this.sentido == "LR")
	      {
	         this.sentido = "RL";
	      }
	      else
	      {
	         this.sentido = "LR";         
	      }
	   }

	   
	   int sizeLombriga() 
	   {
	      return this.headLombriga - this.tailLombriga;      
	   }
	   
	   
	   void trataSeLombrigaMaiorQueAquario()
	   {      
	      if(this.headAquario < this.sizeLombriga())
	      {
	         this.headAquario = this.sizeLombriga();
	         this.headLombriga = this.sizeLombriga();
	         
	         this.tailAquario = 0;
	         this.tailLombriga = 0;
	      }

	   }
	   
	   
	   void trataSeLombrigaNaoCabeNoAquarioNaPosicaoEspecificada()
	   {
	      if(this.tailLombriga < 0)
	      {
	         this.headLombriga = this.sizeLombriga();
	         this.tailLombriga = 0;
	      }
	      if(this.headLombriga > this.headAquario)
	      {
	         this.headLombriga = this.sizeLombriga();
	         this.tailLombriga = 0;
	      }
	   }
	   
	   
	   String lombrigaNoAquario() 
	   {
	      String stg = "";
	      for(int i = this.tailAquario; i < this.headAquario; i++)
	      {
	         if(i >= this.tailLombriga && i < this.headLombriga) 
	         {
	            if(this.sentido == "LR")
	            {
	               if(i == this.headLombriga - 1)
	               {
	                  //System.out.print('O');
	                  stg += "O";
	               }
	               else
	               {
	                  //System.out.print('@');   
	                  stg += "@";
	               }
	            }
	            else
	            {
	               if(i == this.tailLombriga)
	               {
	                  //System.out.print('O');
	                  stg += "O";
	               }
	               else
	               {
	                  //System.out.print('@');
	                  stg += "@";
	               }     
	            }            
	         }
	         else
	         {
	            //System.out.print("#");
	            stg += "#";
	         }
	      }   
	      return stg;
	   }
	   
	   
	   public static void main(String[] args) {
	      AquarioLombriga al = new AquarioLombriga(10, 4, 5);
	      al.lombrigaNoAquario();  
	      System.out.println(al.lombrigaNoAquario());
	      al.mover(); System.out.println(al.lombrigaNoAquario());
	      al.mover(); System.out.println(al.lombrigaNoAquario());
	      al.mover(); System.out.println(al.lombrigaNoAquario());
	      al.mover(); System.out.println(al.lombrigaNoAquario());
	      al.mover(); System.out.println(al.lombrigaNoAquario());
	      al.mover(); System.out.println(al.lombrigaNoAquario());
	      al.mover(); System.out.println(al.lombrigaNoAquario());
	      al.mover(); System.out.println(al.lombrigaNoAquario());

	   }
	}