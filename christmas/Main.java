package christmas;

import java.util.Scanner;
//media custo cada bilhete entre 4 e 7 inclusive
public class Main {   
	public static final int NUM_OF_SALES = 20;

	public static void main(String[] args) {
		ChristmasCircus myCircus = null;
		Scanner in = new Scanner(System.in);
	
		System.out.println("Qual deve ser o preco dos bilhetes? ");
		float ticketPrice = in.nextFloat();
		
	
		System.out.println("Quantas vezes se deve poder reduzir o preço? ");
		int reduceTimes = in.nextInt();
	
		myCircus = new ChristmasCircus(ticketPrice, reduceTimes);
		
		for(int c = 0; c < NUM_OF_SALES; c++){
			System.out.println("Valor a pagar: " + myCircus.buyTicket());
		}
		
		System.out.println( "\nValor da media entre os bilhetes 4 e 7: " + myCircus.getAverageBetween(4,7));
	
		System.out.println("\nValor em caixa: " + myCircus.moneyInCash());
		System.out.println("Numero de bilhetes vendidos: " + myCircus.numberOfTickets());
	
		in.close();
	}
	
}
