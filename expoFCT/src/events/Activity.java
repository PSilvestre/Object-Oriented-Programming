package events;

import java.util.Iterator;
import java.util.List;

public interface Activity extends Event {

	String responsibleDepartment();

	List tags();

	boolean hasTag(String tag);
}
