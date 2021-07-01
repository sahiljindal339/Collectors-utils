package codefx;

@SuppressWarnings("preview")
public record Author(String lastName) {
	/*
	 * Record : Transparent and Semantic way...!!!!!!
	 */
    public static Author of(String authorName) {
        return new Author(authorName);
    }

}
