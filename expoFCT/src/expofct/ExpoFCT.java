package expofct;

import java.util.ArrayList;
import java.util.List;

import comments.Comment;
import events.Activity;
import events.Event;
import exceptions.*;
import user.User;

public interface ExpoFCT {

	/**
	 * Returns an Iterator of elements ordered by the number of registered
	 * people, then likes, then minimal dislikes and lastly alphabetical order.
	 * 
	 * @return an ordered Iterator.
	 */
	List<Event> getAllEvents();

	/**
	 * Returns an Iterator of elements ordered by the number of comments, then
	 * likes, then minimal dislikes and lastly alphabetical order. Only
	 * commented Events.
	 * 
	 * @return an ordered Iterator of commented Events.
	 */
	List<Event> getAllEventsOrderedByComments();

	List<Activity> getAllActivitiesByTheme(String tag);

	List<Comment> getCommentsOfEvent(String name) throws EventDoesNotExistException;

	void registerVisitor(String email) throws SomeUserLoggedInException, UserAlreadyExistsException;

	void registerStaff(String email, String department)
			throws SomeUserLoggedInException, UserAlreadyExistsException, DepartmentDoesNotExistException;

	void registerAdmin(String email) throws SomeUserLoggedInException, UserAlreadyExistsException;

	int likeEvent(String nameOfEvent) throws EventDoesNotExistException;

	int dislikeEvent(String nameOfEvent) throws EventDoesNotExistException;

	int likeCommentOfEvent(String nameOfEvent, String nameOfAuthor)
			throws EventDoesNotExistException, UserDoesNotExistException, CommentDoesNotExistException;

	Comment getHighestRatedComment() throws NoCommentsRegisteredException;

	void login(String email, String password) throws UserDoesNotExistException, UserAlreadyLoggedInException,
			AnotherUserLoggedInException, WrongPasswordException;

	void logout() throws NoUserLoggedInException;

	void createEvent(String name, String description)
			throws UserCannotExecuteCommandException, EventNameAlreadyTakenException;

	void createActivity(String name, String description, ArrayList<String> tags)
			throws UserCannotExecuteCommandException, EventNameAlreadyTakenException;

	void registerUserInEvent(String nameOfEvent)
			throws UserCannotExecuteCommandException, EventDoesNotExistException;

	void writeComment(String nameOfEvent, String comment)
			throws UserCannotExecuteCommandException, EventDoesNotExistException, NotRegisteredInEventException;

	void addDepartment(String nameOfDep, String initials, String building)
			throws UserCannotExecuteCommandException, DepartmentAlreadyExistsException;

	List<User> getAllUsers() throws UserCannotExecuteCommandException;

	String getEmailOfLoggedInUser() throws NoUserLoggedInException;

	String getPassWordOfUser(String email) throws NoUserLoggedInException;

	User getUser(String email) throws UserDoesNotExistException;
}
