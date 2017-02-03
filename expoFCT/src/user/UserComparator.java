package user;

import java.util.Comparator;

public class UserComparator implements Comparator<User> {

	public int compare(User u1, User u2) {
		int result;
		if (u1.getClass().equals(u2.getClass())) {
			result = u1.getEmail().compareTo(u2.getEmail());
		} else {
			if (u1 instanceof Admin)
				result = -1;
			else if (u2 instanceof Admin)
				result = 1;
			else{
				if (u1 instanceof Staff)
					result = -1;
				else 
					result = 1;
			}

		}
		return result;
	}

}
