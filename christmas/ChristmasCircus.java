package christmas;
/* Pretende-se a constru��o de uma classe ChristmasCircus para gerir os bilhetes de um circo de Natal. 
 * O circo de Natal tem lota��o ilimitada. � sempre poss�vel comprar um bilhete para o circo de Natal, 
 * consultar o n�mero de bilhetes vendidos e o dinheiro em caixa. 
 * O pre�o do bilhete pode ser reduzido � medida que se aproxima o Natal, no entanto o n�mero de vezes 
 * que se reduz o pre�o � limitado. Note que tamb�m � poss�vel consultar a que valor foi vendido o 
 * i-�simo bilhete, pelo que � necess�rio guardar o valor do bilhete de todos os bilhetes vendidos.
 * Quando se cria o circo de Natal pode-se indicar o pre�o m�ximo do bilhete (em Euros) e o n�mero 
 * de vezes que � poss�vel reduzir o pre�o. Quando n�o se indica nada, o circo de Natal � criado com o 
 * pre�o de bilhete de 10 Euros e com o n�mero de redu��es do bilhete a zero, ou seja n�o se pode 
 * reduzir o pre�o do bilhete.
 */

public class ChristmasCircus {
        /* Constantes */  
	public static final float DEFAULTPRICE = 10.00f;
	public static final int REDUPRICE = 0;
	public static final int HALF = 2;
	public static final int GROWTH_FACTOR = 2;
	public static final int DEFAULT_SIZE_OF_SALES_ARRAY = 100;
	
        /* Vari�veis de Inst�ncia */ 
	private int numberReduzPrice;
	private float cash;
	private float currentPrice;
	private float[] sales;
	private int salesCounter;
	//FAZER -- vector acompanhado para guardar os valores do bilhetes vendidos/registados
	// Em cada posi��o i do vector registar� o valor do i-1 �simo bilhete vendido 
	

/* Construtor
 * @param ticketPrice: pre�o do bilhete em euros
 * @param maxRed: n�mero de vezes que se pode reduzir o pre�o do bilhete
 * @pre ticketPrice > 0 && maxRed >= 0
 */
        public ChristmasCircus(float ticketPrice, int maxRed){            
        	numberReduzPrice = maxRed;
        	currentPrice = ticketPrice;
        	cash = 0;
        	sales = new float[DEFAULT_SIZE_OF_SALES_ARRAY];
        	salesCounter = 0;
        	
        }

/* Construtor que coloca o pre�o do bilhete em 10 Euros e n�o permite redu��o de pre�o
*/
        public ChristmasCircus(){                
        	numberReduzPrice = REDUPRICE;
        	currentPrice = DEFAULTPRICE;
        	cash = 0;
        	sales = new float[DEFAULT_SIZE_OF_SALES_ARRAY];
        	salesCounter = 0;
        	
        }

/*
 * Reduz o pre�o do bilhete a metade, caso ainda seja poss�vel reduzir o pre�o.
 * � poss�vel reduzir se ainda n�o foi alcan�ado o n�mero m�ximo de redu��es.
 * Note que n�o se reduz caso n�o seja poss�vel.
 * @return true se for poss�vel reduzir; false caso contr�rio
 */
          public boolean isPossibleReduzPrice(){ 
        	  boolean result=false;
        	  if (numberReduzPrice >0){
        		  result = true;
        		  numberReduzPrice--;
        		  currentPrice = currentPrice / HALF;
        	  }
        	  return result;
         }  

/*
 * Regista a compra de um bilhete e retorna o valor a pagar.
 * @return valor em euros da compra a realizar
 */
          public float buyTicket(){
        	  isPossibleReduzPrice();
        	  cash = cash + currentPrice;
        	  if(salesCounter != DEFAULT_SIZE_OF_SALES_ARRAY - 1){
        		  sales[salesCounter] = currentPrice;
        		  salesCounter++;
        	  }else{
        		  resize();
        		  sales[salesCounter] = currentPrice;
        		  salesCounter++;
        	  }
        	  //FAZER -- guarda na primeira posi��o livre do vector o valor do bilhete
        	  // CUIDADO com a capacidade do vector
        	  
        	  return currentPrice;
         }

/*
  * Consulta o n�mero de tickets vendidos at� ao momento
  * @return numero de tickets vendidos
  */
        public int numberOfTickets(){
        	return salesCounter;
       }  
        /*
         * Consulta o valor da i-�simo bilhete registado.
         * @param i: ordem de venda do bilhete
         * @pre i > 0 && i <= numberOfTickets()
         */
          public float iTicketPrice(int i){
             return sales[i-1];
          }

        /*
         * Consulta o valor m�dio dos pre�os de bilhetes vendidos
         * @pre numberOfTickets() > 0
         * @return valor m�dio em Euros do pre�o dos bilhetes
         */
           public float averageCostOfTickets(){
               float total = 0.0f;
               for(int i = 0; i < salesCounter; i++){
            	   total += iTicketPrice(i);
               }
        	   
               return total / salesCounter;
           }
/*
  * Consulta o valor em Euros na caixa
  * @return valor em Euros
  */
        public float moneyInCash(){
        	return cash;
       }    
        
        public float getAverageBetween(int lowerBound, int upperBound){
        	float total = 0.0f;
        	for(int i = lowerBound; i < upperBound+1; i++){
        		total += iTicketPrice(i);
        	}
        	return total / ((upperBound+1) - lowerBound);
        }
        
       private void resize(){
    	   float[] temp = sales;
    	   sales = new float[sales.length * GROWTH_FACTOR];
    	   for(int i = 0; i < temp.length; i++){
    		   sales[i] = temp[i];
    	   }
       }
}         