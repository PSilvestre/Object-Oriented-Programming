package user;

import departments.Department;

public class StaffClass extends AbstractUserClass implements Staff {
	private static final String USERTYPE = "STAFF";
	private Department department;
	private int eventsResponsibleFor;

	public StaffClass(String email, String password, Department department) {
		super(email, password);
		this.department = department;
		eventsResponsibleFor = 0;
	}

	@Override
	public Department getDepartment() {
		return department;
	}
	
	public String toString(){
		return super.getEmail() + "; " + USERTYPE + "; " + this.getEventsResponsibleFor();
	}

	public int getEventsResponsibleFor() {
		return eventsResponsibleFor;
	}

	public void incEventsResponsibleFor() {
		eventsResponsibleFor++;
	}

}
