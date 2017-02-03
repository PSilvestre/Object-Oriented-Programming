package events;

import java.util.List;

import comments.Comment;
import exceptions.CommentDoesNotExistException;
import user.User;

public interface Event extends Comparable<Event> {
	String getName();

	String getDescription();

	User getStaff();

	List<User> getVisitorList();

	List<Comment> getCommentList();

	List<Comment> allComments();

	int getNumLikes();

	int getNumDislikes();

	int getNumVisitors();

	Integer getNumComments();

	void likeEvent();

	void dislikeEvent();

	boolean hasCommentBy(User user);

	void likeCommentOfEvent(User author) throws CommentDoesNotExistException;

	void registerUser(User u);

	boolean hasRegisteredVisitor(User loggedInUser);

	String toString();
	
	Comment getCommentBy(User u) throws CommentDoesNotExistException;
}
