package events;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import user.Staff;
import user.User;

public class ActivityClass extends EventClass implements Activity {

	private ArrayList<String> tags;

	public ActivityClass(String name, String description, User staff, ArrayList<String> tags) {
		super(name, description, staff);
		this.tags =  tags;
	}

	public String responsibleDepartment() {
		return ((Staff) this.getStaff()).getDepartment().getName();
	}

	public List tags() {
		return tags;
	}

	public boolean hasTag(String tag) {
		return tags.contains(tag);
	}

	public String toString() {
		return this.getName() + "; " + this.getStaff().getEmail() + "; "
				+ ((Staff) this.getStaff()).getDepartment().getInitials() + "; " + this.getNumVisitors() + "; "
				+ this.getNumComments() + "; " + this.getNumLikes() + "; " + this.getNumDislikes();

	}

}
