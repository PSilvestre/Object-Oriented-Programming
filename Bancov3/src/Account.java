
public class Account {
	public static final int FINE = 200;
	
	public static final int CATEGORY1 = 200000;
	public static final int CATEGORY2 = 10000000;
	
	public static final float RATE_CATEGORY1 = 0.01f;
	public static final float RATE_CATEGORY2 = 0.02f;
	public static final float RATE_CATEGORY3 = 0.03f;
	
	
	private int balance; //in cents
	
	public Account(){
		balance = 0;
	}
	
	public Account(int initialMoney) {
		balance = initialMoney;
	}
	
	public int getBalance(){
		return balance;
	}
	
	public int getBalanceInEuros(){
		return balance / 100;
	}
	
	/** 
	 * Pre: amount > 0
	 * @param amount
	 */
	public void withdraw(int amount){
		if(balance >= amount)
			balance -= amount;
		else
			balance -= FINE;
	}
	
	/** 
	 * Pre: amount > 0
	 * @param amount
	 */
	public void deposit(int amount){
		balance += amount;
	}
	
	public int computeInterest(){
		float interestRate;
		if(balance <= CATEGORY1) {
			interestRate = RATE_CATEGORY1;
		} else if(balance < CATEGORY2){
			interestRate = RATE_CATEGORY2;
		} else {
			interestRate = RATE_CATEGORY3;
		}
		return Math.round(balance * interestRate);
	}
	
	public void applyInterest() {
		balance = balance + this.computeInterest();
	}
	
	
}
