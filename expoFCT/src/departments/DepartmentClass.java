package departments;

public class DepartmentClass implements Department {
	private String name;
	private String initials;
	private String building;
	
	private int numStaffInDep;

	public DepartmentClass(String name, String initials, String building) {
		this.name = name;
		this.initials = initials;
		this.building = building;
		numStaffInDep = 0;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getInitials() {
		return initials;
	}

	@Override
	public String getBuilding() {
		return building;
	}

	public int getNumStaffInDep() {
		return numStaffInDep;
	}

	public void incNumStaffInDep() {
		numStaffInDep++;
	}
	
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null)
			return false;
		if(!(o instanceof Department))
			return false;
		Department other = (Department) o;
		
		return this.getName().equals(other.getName()) ||
				this.getInitials().equals(other.getInitials()) ||
				this.getBuilding().equals(other.getInitials());
		
	}

}
