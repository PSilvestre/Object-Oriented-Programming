package events;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import comments.Comment;
import exceptions.CommentDoesNotExistException;
import user.Staff;
import user.User;

public class EventClass implements Event {
	private String name;
	private String description;
	private User staff;
	private ArrayList<User> visitors;
	// Possivelmente adicionar mapa de comments
	private ArrayList<Comment> comments;
	private int numLikes;
	private int numDislikes;

	public EventClass(String name, String description, User staff) {
		this.name = name;
		this.description = description;
		this.staff = staff;
		visitors = new ArrayList<User>();
		comments = new ArrayList<Comment>();
		numLikes = 0;
		numDislikes = 0;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public User getStaff() {
		return staff;
	}

	public List<User> getVisitorList() {
		return visitors;
	}

	public List<Comment> getCommentList() {
		return comments;
	}

	public int getNumLikes() {
		return numLikes;
	}

	public int getNumDislikes() {
		return numDislikes;
	}

	public int compareTo(Event o) {
		int result = ((Integer) this.getNumVisitors()).compareTo((Integer) o.getNumVisitors());
		if (result == 0) {
			result = ((Integer) this.getNumLikes()).compareTo((Integer) o.getNumLikes());
			if (result == 0) {
				result = ((Integer) o.getNumDislikes()).compareTo((Integer) this.getNumDislikes());
				if (result == 0)
					result = this.getName().compareTo(o.getName());
			}
		}

		return result;
	}

	public int getNumVisitors() {
		return this.visitors.size();
	}

	@Override
	public Integer getNumComments() {
		return comments.size();
	}

	@Override
	public void likeEvent() {
		numLikes++;
	}

	@Override
	public void dislikeEvent() {
		numDislikes++;
	}

	public boolean hasCommentBy(User user) {
		boolean result = false;

		for (Comment c : comments) {
			if (c.commentCreator().equals(user))
				result = true;
		}
		return result;
	}

	@Override
	public void likeCommentOfEvent(User author) throws CommentDoesNotExistException {
		if (!hasCommentBy(author))
			throw new CommentDoesNotExistException();

		for (Comment c : comments) {
			if (c.commentCreator().equals(author))
				c.likeComment();
		}

	}

	@Override
	public List<Comment> allComments() {
		return comments;
	}

	public void registerUser(User u) {
		if (!visitors.contains(u))
			visitors.add(u);
	}

	@Override
	public boolean hasRegisteredVisitor(User u) {
		return visitors.contains(u);
	}

	public String toString() {
		return this.getName() + "; " + this.getStaff().getEmail() + "; " + this.getNumVisitors() + "; "
				+ this.getNumComments() + "; " + this.getNumLikes() + "; " + this.getNumDislikes();
	}

	@Override
	public Comment getCommentBy(User u) throws CommentDoesNotExistException{
		Comment result = null;
		
		for (Comment c : comments) {
			if (c.commentCreator().equals(u))
				result = c;
		}
		if(result == null)
			throw new CommentDoesNotExistException();
		return result;
	}

	
}
