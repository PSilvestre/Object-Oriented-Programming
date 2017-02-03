import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import comments.Comment;
import events.Activity;
import events.Event;
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
import expofct.*;
import user.Staff;
import user.User;

public class Main {
	private static final String ALL_EVENTS_HEADER = "All events:";
	private static final String ALL_USERS = "All users:";
	private static final String USER_REGISTERED = "User was registered: ";
	private static final String MOST_COMMENTED_EVENTS_HEADER = "Most commented events:";
	private static final String ACTIVITIES_WITH_TAG = "Activities with tag ";
	private static final String COMMENTS = " comments:";
	private static final String LIKES = "Likes: ";
	private static final String DISLIKES = "Dislikes: ";
	private static final String COMMENT_LIKES = "Comment likes: ";
	private static final String WELCOME = "Welcome ";
	private static final String GOODBYE = "Goodbye ";
	private static final String EVENT_REGISTERED = "Event registered.";
	private static final String ACTIVITY_RESGISTERED = "Activity registered.";
	
	private static final String DEPT_SUCCESS = "Department registered.";
	private static final String COMMENT_SUCCESS = "Comment registered.";
	private static final String REGISTER_IN_EVENT_SUCCESS = "Registered in event.";
	
	private enum Command {
		EVENTS, LISTBYCOMMENTS, BYTAG, COMMENTS, REGISTER, LIKE, DISLIKE, LIKECOMMENT, TOPCOMMENT, LOGIN, LOGOUT, NEWEVENT, NEWACTIVITY, PARTICIPATE, COMMENT, DEPT, USERS, EXIT, UNKNOWN
	}

	public static void main(String[] args) {
		commands();
	}

	private static void commands() {
		Scanner in = new Scanner(System.in);
		ExpoFCT expo = new ExpoFCTClass();
		Command comm = getCommand(in);

		while (!comm.equals(Command.EXIT)) {
			switch (comm) {
			case EVENTS:
				commandEvents(expo, in);
				break;
			case LISTBYCOMMENTS:
				commandListByComments(expo, in);
				break;
			case BYTAG:
				commandByTag(expo, in);
				break;
			case COMMENTS:
				commandComments(expo, in);
				break;
			case REGISTER:
				commandRegister(expo, in);
				break;
			case LIKE:
				commandLike(expo, in);
				break;
			case DISLIKE:
				commandDislike(expo, in);
				break;
			case LIKECOMMENT:
				commandLikeComment(expo, in);
				break;
			case TOPCOMMENT:
				commandTopComment(expo);
				break;
			case LOGIN:
				commandLogin(expo, in);
				break;
			case LOGOUT:
				commandLogout(expo);
				break;
			case NEWEVENT:
				commandNewEvent(expo, in);
				break;
			case NEWACTIVITY:
				commandNewActivity(expo, in);
				break;
			case PARTICIPATE:
				commandParticipate(expo, in);
				break;
			case COMMENT:
				commandComment(expo, in);
				break;
			case DEPT:
				commandDept(expo, in);
				break;
			case USERS:
				commandUsers(expo);
				break;
			default:
				System.out.println("ERROR");
				break;
			}
			System.out.println();
			comm = getCommand(in);
			
		}
		System.out.println("Exiting.");
		System.out.println();
		in.close();
	}

	private static void commandUsers(ExpoFCT expo) {
		try {
			List<User> users = expo.getAllUsers();
			System.out.println(ALL_USERS);
			for(User u : users)
				System.out.println(u.toString());
			
		} catch (UserCannotExecuteCommandException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void commandDept(ExpoFCT expo, Scanner in) {
		String name = in.nextLine();
		String initials = in.nextLine();
		String building = in.nextLine();
		try {
			expo.addDepartment(name, initials, building);
			System.out.println(DEPT_SUCCESS);
		} catch (UserCannotExecuteCommandException e) {
			System.out.println(e.getMessage());
		} catch (DepartmentAlreadyExistsException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private static void commandComment(ExpoFCT expo, Scanner in) {
		String eventName = in.nextLine();
		String comment = in.nextLine();
		
		try {
			expo.writeComment(eventName, comment);
			System.out.println(COMMENT_SUCCESS);
		} catch (UserCannotExecuteCommandException e) {
			System.out.println(e.getMessage());
		} catch (EventDoesNotExistException e) {
			System.out.println(e.getMessage());
		} catch (NotRegisteredInEventException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void commandParticipate(ExpoFCT expo, Scanner in) {
		String eventName = in.nextLine();
		try {
			expo.registerUserInEvent(eventName);
			System.out.println(REGISTER_IN_EVENT_SUCCESS);
		} catch (UserCannotExecuteCommandException e) {
			System.out.println(e.getMessage());
		} catch (EventDoesNotExistException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void commandNewActivity(ExpoFCT expo, Scanner in) {
		String name = in.nextLine();
		String description = in.nextLine();
		String numStr = in.nextLine();
		ArrayList<String> tags = new ArrayList<String>();
		try {
			int num = Integer.parseInt(numStr);
			for (int i = 0; i < num; i++)
				tags.add(in.nextLine());

			expo.createActivity(name, description, tags);
			System.out.println(ACTIVITY_RESGISTERED);
		} catch (UserCannotExecuteCommandException e) {
			System.out.println(e.getMessage());
		} catch (EventNameAlreadyTakenException e) {
			System.out.println(e.getMessage());
		} catch(NumberFormatException e){
			System.out.println("Not a number.");
		}
	}

	private static void commandNewEvent(ExpoFCT expo, Scanner in) {
		String name = in.nextLine();
		String description = in.nextLine();
		try {
			expo.createEvent(name, description);
			System.out.println(EVENT_REGISTERED);
		} catch (UserCannotExecuteCommandException e) {
			System.out.println(e.getMessage());
		} catch (EventNameAlreadyTakenException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void commandLogout(ExpoFCT expo) {
		try{
			String loggedInEmail = expo.getEmailOfLoggedInUser();
			expo.logout();
			System.out.println("Goodbye " + loggedInEmail + ".");
		}catch(NoUserLoggedInException e){
			System.out.println(e.getMessage());
		}
	}

	private static void commandLogin(ExpoFCT expo, Scanner in) {
		String email = in.nextLine();
		String password = in.nextLine();
		
		try{
			expo.login(email, password);
			System.out.println("Welcome " + email + ".");
		}catch(UserDoesNotExistException e){
			System.out.println(e.getMessage());
		} catch (UserAlreadyLoggedInException e) {
			System.out.println(e.getMessage());
		} catch (AnotherUserLoggedInException e) {
			System.out.println(e.getMessage());
		} catch (WrongPasswordException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void commandTopComment(ExpoFCT expo) {
		try {
			Comment comment = expo.getHighestRatedComment();
			System.out.println(comment.getCommentText() + " " + (comment.commentCreator()).getEmail() + " "
					+ comment.numberOfLikes());
		} catch (NoCommentsRegisteredException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void commandLikeComment(ExpoFCT expo, Scanner in) {
		String nameEvent = in.nextLine();
		String email = in.nextLine();
		try {
			System.out.println(COMMENT_LIKES + expo.likeCommentOfEvent(nameEvent, email));
		} catch (EventDoesNotExistException e) {
			System.out.println(e.getMessage());
		} catch (UserDoesNotExistException e) {
			System.out.println(e.getMessage());
		} catch (CommentDoesNotExistException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void commandDislike(ExpoFCT expo, Scanner in) {
		String nameEvent = in.nextLine();
		try {		
			System.out.println(DISLIKES + expo.dislikeEvent(nameEvent));
		} catch (EventDoesNotExistException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void commandLike(ExpoFCT expo, Scanner in) {
		String nameEvent = in.nextLine();
		try {		
			System.out.println(LIKES + expo.likeEvent(nameEvent));
		} catch (EventDoesNotExistException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void commandRegister(ExpoFCT expo, Scanner in) {
		String type = in.nextLine().toUpperCase();
		String email = in.nextLine();
		String dept = null;
		try {
			if (type.equals("ADMIN")) {
				expo.registerAdmin(email);
			}
			if (type.equals("STAFF")) {
				dept = in.nextLine();
				expo.registerStaff(email, dept);
			}
			if (type.equals("VISITOR")) {
				expo.registerVisitor(email);
			}
			User user = expo.getUser(email);
			System.out.println(USER_REGISTERED + user.getPassword() + ".");
		} catch (UserAlreadyExistsException e) {
			System.out.println(e.getMessage());
		} catch (DepartmentDoesNotExistException e) {
			System.out.println(e.getMessage());
		} catch (UserDoesNotExistException e) {
			System.out.println(e.getMessage());
		} catch (SomeUserLoggedInException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void commandComments(ExpoFCT expo, Scanner in) {
		String event = in.nextLine();
		try{
			List<Comment> commentsOfEvent = expo.getCommentsOfEvent(event);
			System.out.println(event + COMMENTS);
			for(Comment c : commentsOfEvent)
				System.out.println(c.toString());
		}catch(EventDoesNotExistException e){
			System.out.println(e.getMessage());
		}
	}

	private static void commandByTag(ExpoFCT expo, Scanner in) {
		String tag = in.nextLine();
		List<Activity> activities = expo.getAllActivitiesByTheme(tag);
		System.out.println(ACTIVITIES_WITH_TAG + tag + ":");
		for(Activity a : activities)
			System.out.println(a.toString());
	}

	private static void commandListByComments(ExpoFCT expo, Scanner in) {
		System.out.println(MOST_COMMENTED_EVENTS_HEADER);

		List<Event> eventsOrdered = expo.getAllEventsOrderedByComments();
		for(Event e : eventsOrdered)
			System.out.println(e.toString());
	}

	private static void commandEvents(ExpoFCT expo, Scanner in) {
		System.out.println(ALL_EVENTS_HEADER);
		List<Event> eventsOrdered = expo.getAllEvents();
		for(Event e : eventsOrdered)
			System.out.println(e.toString());
	}

	private static Command getCommand(Scanner in) {
		try {
			return Command.valueOf(in.nextLine().toUpperCase());
		} catch (IllegalArgumentException e) {
			return Command.UNKNOWN;
		}
	}
}
