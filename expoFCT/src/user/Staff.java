package user;

import departments.Department;

public interface Staff extends User {

	Department getDepartment();
	
	int getEventsResponsibleFor();
	
	void incEventsResponsibleFor();
	
}
