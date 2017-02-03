import java.util.Scanner;

public class Main {
	
	private static final String EXIT = "S";
	private static final String APPLY_TAX = "ATA";
	private static final String TAX_CHECK = "CTA";
	private static final String BALANCE_CHECK = "CS";
	private static final String WITHDRAW = "L";
	private static final String DEPOSIT = "D";
	
	private static void printMenu(){
		System.out.println("Escolha uma opcao: ");
		System.out.println("D - Depositar.");
		System.out.println("L - Levantar.");
		System.out.println("CS - Consulta saldo.");
		System.out.println("CTA - Consultar taxa anual.");
		System.out.println("ATA - Aplicar taxa anual.");
		System.out.println("S - Sair.");
		System.out.println();
	}
	
	private static void processDeposit(Account acc, Scanner in){
		System.out.print("Quanto quer depositar(cents)? ");
		int amount = readInt(in);
		System.out.println();
		if(amount > 0)
			acc.deposit(amount);
		else
			System.out.println("Essa quantia é negativa!");
	}
	
	private static void processWithdraw(Account acc, Scanner in) {
		System.out.print("Quanto quer levantar(cents)? ");
		int amount = readInt(in);
		System.out.println();
		if(amount > 0)
			acc.withdraw(amount);
		else
			System.out.println("Essa quantia é negativa!");
		
	}
	
	private static void procesApplyTax(Account acc, Scanner in) {
		acc.applyInterest();
		System.out.println("Taxa aplicada! O seu novo saldo é: " + acc.getBalance());
		System.out.println();
	}

	private static void processAnualTaxCheck(Account acc, Scanner in) {
		System.out.println("A sua taxa é: " + acc.computeInterest());
		System.out.println();
	}

	private static void processBalanceCheck(Account acc, Scanner in) {
		System.out.println("O seu saldo é: " +  acc.getBalance());
		System.out.println();
	}

	private static String readOption(Scanner in){
		String s = in.nextLine();
		
		return s;
		
	}
	
	private static int readInt(Scanner in) {
		int amount = in.nextInt();
		in.nextLine();
		
		return amount;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Account acc = new Account();
		String option;
		boolean done = false;
		
		do{
			printMenu();
			option = readOption(in);
			switch(option){
			case DEPOSIT:
				processDeposit(acc,in);
				break;
				
			case WITHDRAW:
				processWithdraw(acc,in);
				break;
				
			case BALANCE_CHECK:
				processBalanceCheck(acc,in);
				break;
				
			case TAX_CHECK:
				processAnualTaxCheck(acc,in);
				break;
				
			case APPLY_TAX:
				procesApplyTax(acc,in);
				break;
				
			case EXIT: 
				System.out.println("Adeus!");
				done = true;
				break;
				
			default:
				System.out.println("Esse comando nao existe.");
				System.out.println();
				
			}
		}while(!done);
	}
}
