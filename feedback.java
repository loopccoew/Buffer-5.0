package Buffer;

public class feedback {
	
	    private int rating;
	    private String comments;

	    // Constructor
	    public feedback(int rating, String comments) {
	        this.rating = rating;
	        this.comments = comments;
	    
}
	    public void displayFeedback() {
	        System.out.println("Rating: " + rating);
	        System.out.println("Comments: " + comments);
}
}

