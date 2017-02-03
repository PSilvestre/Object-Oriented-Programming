package comments;

import events.Event;
import user.User;
import user.Visitor;

public interface Comment {
	Event getAssociatedEvent();

	String getCommentText();

	Visitor commentCreator();

	int numberOfLikes();

	void likeComment();
	
	
}
