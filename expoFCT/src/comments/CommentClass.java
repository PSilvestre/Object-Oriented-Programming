package comments;

import events.Event;
import user.User;
import user.Visitor;

public class CommentClass implements Comment {

	Event event;
	String commentText;
	Visitor author;
	int numLikes;

	public CommentClass(Event event, String commentText, Visitor author) {
		this.event = event;
		this.commentText = commentText;
		this.author = author;
		this.numLikes = 0;
	}

	@Override
	public Event getAssociatedEvent() {
		return event;
	}

	@Override
	public String getCommentText() {
		return commentText;
	}

	@Override
	public Visitor commentCreator() {
		return author;
	}

	@Override
	public int numberOfLikes() {
		return numLikes;
	}

	@Override
	public void likeComment() {
		numLikes++;
	}
	
	public String toString() {
		return this.getCommentText() + "; " + this.commentCreator().getEmail() 
				+ "; " + this.numberOfLikes();
	}
}
