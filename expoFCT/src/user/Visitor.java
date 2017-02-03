package user;

public interface Visitor extends User {
	int getNumComments();

	void incNumComments();
	
	int getNumberOfActiviesParticipatingIn();
	
	void incNumberOfActiviesParticipatingIn();
}
