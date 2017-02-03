package comments;

import java.util.Comparator;

public class HighestRatedComparator implements Comparator<Comment> {

	public int compare(Comment c1, Comment c2) {
		int result = c1.numberOfLikes() - c2.numberOfLikes();
		if (result == 0) {
			result = c1.commentCreator().getNumComments() - c2.commentCreator().getNumComments();
			if (result == 0) {
				result = c1.getCommentText().compareTo(c2.getCommentText());
			}
		}
		return result;
	}
}