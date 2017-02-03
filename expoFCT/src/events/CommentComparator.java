package events;

import java.util.Comparator;

public class CommentComparator implements Comparator<Event> {

	public int compare(Event e1, Event e2) {
		int result = e1.getNumComments() - e2.getNumComments();
		if (result == 0) {
			result = e1.getNumLikes() - e2.getNumLikes();
			if (result == 0) {
				result = e2.getNumDislikes() - e1.getNumDislikes();
				if (result == 0)
					result = e1.getName().compareTo(e2.getName());
			}
		}

		return result;
	}
}