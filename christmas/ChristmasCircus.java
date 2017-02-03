package christmas;
/* Pretende-se a construção de uma classe ChristmasCircus para gerir os bilhetes de um circo de Natal. 
 * O circo de Natal tem lotação ilimitada. É sempre possível comprar um bilhete para o circo de Natal, 
 * consultar o número de bilhetes vendidos e o dinheiro em caixa. 
 * O preço do bilhete pode ser reduzido à medida que se aproxima o Natal, no entanto o número de vezes 
 * que se reduz o preço é limitado. Note que também é possível consultar a que valor foi vendido o 
 * i-ésimo bilhete, pelo que é necessário guardar o valor do bilhete de todos os bilhetes vendidos.
 * Quando se cria o circo de Natal pode-se indicar o preço máximo do bilhete (em Euros) e o número 
 * de vezes que é possível reduzir o preço. Quando não se indica nada, o circo de Natal é criado com o 
 * preço de bilhete de 10 Euros e com o número de reduções do bilhete a zero, ou seja não se pode 
 * reduzir o preço do bilhete.
 */

public class ChristmasCircus {
        /* Constantes */  
	public static final float DEFAULTPRICE = 10.00f;
	public static final int REDUPRICE = 0;
	public static final int HALF = 2;
	public static final int GROWTH_FACTOR = 2;
	public static final int DEFAULT_SIZE_OF_SALES_ARRAY = 100;
	
        /* Variáveis de Instância */ 
	private int numberReduzPrice;
	private float cash;
	private float currentPrice;
	private float[] sales;
	private int salesCounter;
	//FAZER -- vector acompanhado para guardar os valores do bilhetes vendidos/registados
	// Em cada posição i do vector registará o valor do i-1 ésimo bilhete vendido 
	

/* Construtor
 * @param ticketPrice: preço do bilhete em euros
 * @param maxRed: número de vezes que se pode reduzir o preço do bilhete
 * @pre ticketPrice > 0 && maxRed >= 0
 */
        public ChristmasCircus(float ticketPrice, int maxRed){            
        	numberReduzPrice = maxRed;
        	currentPrice = ticketPrice;
        	cash = 0;
        	sales = new float[DEFAULT_SIZE_OF_SALES_ARRAY];
        	salesCounter = 0;
        	
        }

/* Construtor que coloca o preço do bilhete em 10 Euros e não permite redução de preço
*/
        public ChristmasCircus(){                
        	numberReduzPrice = REDUPRICE;
        	currentPrice = DEFAULTPRICE;
        	cash = 0;
        	sales = new float[DEFAULT_SIZE_OF_SALES_ARRAY];
        	salesCounter = 0;
        	
        }

/*
 * Reduz o preço do bilhete a metade, caso ainda seja possível reduzir o preço.
 * É possível reduzir se ainda não foi alcançado o número máximo de reduções.
 * Note que não se reduz caso não seja possível.
 * @return true se for possível reduzir; false caso contrário
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
        	  //FAZER -- guarda na primeira posição livre do vector o valor do bilhete
        	  // CUIDADO com a capacidade do vector
        	  
        	  return currentPrice;
         }

/*
  * Consulta o número de tickets vendidos até ao momento
  * @return numero de tickets vendidos
  */
        public int numberOfTickets(){
        	return salesCounter;
       }  
        /*
         * Consulta o valor da i-ésimo bilhete registado.
         * @param i: ordem de venda do bilhete
         * @pre i > 0 && i <= numberOfTickets()
         */
          public float iTicketPrice(int i){
             return sales[i-1];
          }

        /*
         * Consulta o valor médio dos preços de bilhetes vendidos
         * @pre numberOfTickets() > 0
         * @return valor médio em Euros do preço dos bilhetes
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