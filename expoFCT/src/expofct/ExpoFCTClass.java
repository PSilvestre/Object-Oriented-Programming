package expofct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import comments.Comment;
import comments.HighestRatedComparator;
import departments.Department;
import departments.DepartmentClass;
import events.Activity;
import events.ActivityClass;
import events.BasicComparator;
import events.CommentComparator;
import events.Event;
import events.EventClass;
import exceptions.AnotherUserLoggedInException;
import exceptions.CommentDoesNotExistException;
import exceptions.DepartmentAlreadyExistsException;
import exceptions.DepartmentDoesNotExistException;
import exceptions.EventDoesNotExistException;
import exceptions.EventNameAlreadyTakenException;
import exceptions.NoCommentsRegisteredException;
import exceptions.NoUserLoggedInException;
import exceptions.NotRegisteredInEventException;
import exceptions.SomeUserLoggedInException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserAlreadyLoggedInException;
import exceptions.UserCannotExecuteCommandException;
import exceptions.UserDoesNotExistException;
import exceptions.WrongPasswordException;
import user.Admin;
import user.AdminClass;
import user.Staff;
import user.StaffClass;
import user.User;
import user.UserComparator;
import user.Visitor;
import user.VisitorClass;

public class ExpoFCTClass implements ExpoFCT {

	private static final String ADMIN_PASSWORD_PREFIX = "admin";
	private static final String VISITOR_PASSWORD_PREFIX = "visitor";
	// Colecoes para ExpoFCT
	private Map<String, Event> events;
	private Map<String, User> users;
	private Map<String, Department> departments;

	// Adicionar Lista de todos os comentarios

	private User loggedInUser;

	public ExpoFCTClass() {
		events = new HashMap<String, Event>();
		users = new HashMap<String, User>();
		departments = new HashMap<String, Department>();
		
		loggedInUser = null;

	}

	@Override
	public List<Event> getAllEvents() {
		List<Event> allEvents = new ArrayList<Event>(events.size());
		allEvents.addAll(events.values());
		Collections.sort(allEvents, new BasicComparator());
		return allEvents;
	}

	@Override
	public List<Event> getAllEventsOrderedByComments() {
		// Using LinkedList because of remove operation.
		List<Event> allCommentedEvents = new LinkedList<Event>();
		for (Event e : allCommentedEvents) {
			if (e.getNumComments() != 0)
				allCommentedEvents.add(e);
		}

		Collections.sort(allCommentedEvents, new CommentComparator());
		return allCommentedEvents;

	}

	@Override
	public List<Activity> getAllActivitiesByTheme(String tag) {
		List<Activity> activitiesWithTag = new ArrayList<Activity>();
		for (Event e : events.values())
			if (e instanceof Activity)
				if (((Activity) e).hasTag(tag))
					activitiesWithTag.add((Activity) e);

		return activitiesWithTag;

	}

	@Override
	public List<Comment> getCommentsOfEvent(String name) throws EventDoesNotExistException {
		if (!events.containsKey(name))
			throw new EventDoesNotExistException();
		return events.get(name).getCommentList();
	}

	@Override
	public void registerVisitor(String email) throws SomeUserLoggedInException, UserAlreadyExistsException {
		if(loggedInUser != null)
			throw new SomeUserLoggedInException();
		if (users.containsKey(email))
			throw new UserAlreadyExistsException();
		String password = VISITOR_PASSWORD_PREFIX + (VisitorClass.numberOfVisitors + 1);
		users.put(email, new VisitorClass(email, password));

	}

	@Override
	public void registerStaff(String email, String initials)
			throws SomeUserLoggedInException, UserAlreadyExistsException, DepartmentDoesNotExistException {
		if(loggedInUser != null)
			throw new SomeUserLoggedInException();
		if (users.containsKey(email))
			throw new UserAlreadyExistsException();
		if (!departments.containsKey(initials))
			throw new DepartmentDoesNotExistException();
		Department dep = departments.get(initials);
		dep.incNumStaffInDep();
		String password = dep.getInitials() + dep.getNumStaffInDep();
		users.put(email, new StaffClass(email, password, dep));
	}

	@Override
	public void registerAdmin(String email) throws SomeUserLoggedInException, UserAlreadyExistsException {
		if(loggedInUser != null)
			throw new SomeUserLoggedInException();
		if (users.containsKey(email))
			throw new UserAlreadyExistsException();
		String password = ADMIN_PASSWORD_PREFIX + (AdminClass.numberOfAdmins + 1);
		users.put(email, new AdminClass(email, password));
	}

	@Override
	public int likeEvent(String nameOfEvent) throws EventDoesNotExistException {
		if (!events.containsKey(nameOfEvent))
			throw new EventDoesNotExistException();
		Event e = events.get(nameOfEvent);
		e.likeEvent();
		return e.getNumLikes();
	}

	public int dislikeEvent(String nameOfEvent) throws EventDoesNotExistException {
		if (!events.containsKey(nameOfEvent))
			throw new EventDoesNotExistException();
		Event e = events.get(nameOfEvent);
		e.dislikeEvent();
		return e.getNumDislikes();
	}

	@Override
	public int likeCommentOfEvent(String nameOfEvent, String nameOfAuthor)
			throws EventDoesNotExistException, UserDoesNotExistException, CommentDoesNotExistException {
		if (!events.containsKey(nameOfEvent))
			throw new EventDoesNotExistException();
		Event e = events.get(nameOfEvent);
		if (!users.containsKey(nameOfAuthor))
			throw new UserDoesNotExistException();
		User u = users.get(nameOfAuthor);
		if (!e.hasCommentBy(u))
			throw new CommentDoesNotExistException();

		Comment c = e.getCommentBy(u);
		c.likeComment();
		return e.getNumLikes();
	}

	@Override
	public Comment getHighestRatedComment() throws NoCommentsRegisteredException {
		List<Comment> allComments = new ArrayList<Comment>();
		for (Event e : events.values()) {
			allComments.addAll(e.allComments());
		}
		if (allComments.isEmpty())
			throw new NoCommentsRegisteredException();
		Collections.sort(allComments, new HighestRatedComparator());
		// return last one (biggest)
		return allComments.get(allComments.size());
	}

	@Override
	public void login(String email, String password) throws UserDoesNotExistException, UserAlreadyLoggedInException,
			AnotherUserLoggedInException, WrongPasswordException {
		if (!users.containsKey(email))
			throw new UserDoesNotExistException();
		User u = users.get(email);
		if (u.equals(loggedInUser))
			throw new UserAlreadyLoggedInException();
		if (loggedInUser != null)
			throw new AnotherUserLoggedInException();
		if (!u.getPassword().equals(password))
			throw new WrongPasswordException();

		loggedInUser = u;
	}

	@Override
	public void logout() throws NoUserLoggedInException {
		if (loggedInUser == null)
			throw new NoUserLoggedInException();
		loggedInUser = null;
	}

	@Override
	public void createEvent(String name, String description)
			throws UserCannotExecuteCommandException, EventNameAlreadyTakenException {
		if (!(loggedInUser instanceof Staff))
			throw new UserCannotExecuteCommandException();
		if (events.containsKey(name))
			throw new EventNameAlreadyTakenException();
		((Staff)loggedInUser).incEventsResponsibleFor();
		events.put(name, new EventClass(name, description, loggedInUser));
	}

	@Override
	public void createActivity(String name, String description, ArrayList<String> tags)
			throws UserCannotExecuteCommandException, EventNameAlreadyTakenException {
		if (!(loggedInUser instanceof Staff))
			throw new UserCannotExecuteCommandException();
		if (events.containsKey(name))
			throw new EventNameAlreadyTakenException();
		((Staff)loggedInUser).incEventsResponsibleFor();
		events.put(name, new ActivityClass(name, description, loggedInUser, tags));
	}

	@Override
	public void registerUserInEvent(String nameOfEvent)
			throws UserCannotExecuteCommandException, EventDoesNotExistException {
		if (!(loggedInUser instanceof Visitor))
			throw new UserCannotExecuteCommandException();
		if (!events.containsKey(nameOfEvent))
			throw new EventDoesNotExistException();
		((Visitor)loggedInUser).incNumberOfActiviesParticipatingIn();;
		events.get(nameOfEvent).registerUser(loggedInUser);
	}

	@Override
	public void writeComment(String nameOfEvent, String comment)
			throws UserCannotExecuteCommandException, EventDoesNotExistException, NotRegisteredInEventException {
		if (!(loggedInUser instanceof Visitor))
			throw new UserCannotExecuteCommandException();
		if (!events.containsKey(nameOfEvent))
			throw new EventDoesNotExistException();
		if (!events.get(nameOfEvent).hasRegisteredVisitor(loggedInUser))
			throw new NotRegisteredInEventException();
	}

	@Override
	public void addDepartment(String nameOfDep, String initials, String building)
			throws UserCannotExecuteCommandException, DepartmentAlreadyExistsException {
		if (!(loggedInUser instanceof Admin))
			throw new UserCannotExecuteCommandException();
		Department newDep = new DepartmentClass(nameOfDep, initials, building);
		if(departments.containsValue(newDep))
			throw new DepartmentAlreadyExistsException();
		departments.put(initials, newDep);
	}

	@Override
	public List<User> getAllUsers() throws UserCannotExecuteCommandException {
		if (!(loggedInUser instanceof Admin))
			throw new UserCannotExecuteCommandException();
		List<User> orderedUsers = new ArrayList<User>(users.size());
		orderedUsers.addAll(users.values());
		Collections.sort(orderedUsers, new UserComparator());
		return orderedUsers;
	}

	@Override
	public String getEmailOfLoggedInUser() throws NoUserLoggedInException {
		if (loggedInUser == null)
			throw new NoUserLoggedInException();
		return loggedInUser.getEmail();
	}

	@Override
	public String getPassWordOfUser(String email) throws NoUserLoggedInException {
		if (loggedInUser == null)
			throw new NoUserLoggedInException();
		return loggedInUser.getPassword();
	}

	@Override
	public User getUser(String email) throws UserDoesNotExistException {
		if (!users.containsKey(email))
			throw new UserDoesNotExistException();
		return users.get(email);
	}

}
