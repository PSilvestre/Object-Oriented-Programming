package events;

import java.util.Comparator;

import events.Event;

public class BasicComparator implements Comparator<Event> {

	public int compare(Event e1, Event e2) {
		int result = e1.getNumVisitors() - e2.getNumVisitors();
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
