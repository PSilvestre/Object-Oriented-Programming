package user;

public class VisitorClass extends AbstractUserClass implements Visitor {
	
	private static final String USERTYPE = "VISITOR";
	
	public static int numberOfVisitors = 0;
	private int numberOfComments;
	private int numberOfActiviesParticipatingIn;

	public VisitorClass(String email, String password) {
		super(email, password);
		numberOfComments = 0;
		numberOfVisitors++;
		
	}

	@Override
	public int getNumComments() {
		return numberOfComments;
	}

	public void incNumComments() {
		numberOfComments++;
	}
	
	public String toString(){
		return super.getEmail() + "; " + USERTYPE + "; " + this.getNumberOfActiviesParticipatingIn();
	}

	public int getNumberOfActiviesParticipatingIn() {
		return numberOfActiviesParticipatingIn;
	}

	public void incNumberOfActiviesParticipatingIn() {
		numberOfActiviesParticipatingIn++;
	}
}
