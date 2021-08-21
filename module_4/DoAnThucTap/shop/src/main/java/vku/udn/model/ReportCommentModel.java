package vku.udn.model;

public class ReportCommentModel extends abstractModel<ReportCommentModel> {
	private int CommentID;
	private CommentModel comment;
	private int UsersID;
	private vn.vku.model.UserModel users;
	private int BookID;
	private BookModel books;
	private String content;
	
	public ReportCommentModel() {
	}
	
	public ReportCommentModel(int commentID, CommentModel comment, int usersID, vn.vku.model.UserModel users, int bookID,
                              BookModel books, String content) {
		CommentID = commentID;
		this.comment = comment;
		UsersID = usersID;
		this.users = users;
		BookID = bookID;
		this.books = books;
		this.content = content;
	}

	public int getCommentID() {
		return CommentID;
	}
	public void setCommentID(int commentID) {
		CommentID = commentID;
	}
	public CommentModel getComment() {
		return comment;
	}
	public void setComment(CommentModel comment) {
		this.comment = comment;
	}
	public int getUsersID() {
		return UsersID;
	}
	public void setUsersID(int usersID) {
		UsersID = usersID;
	}
	public vn.vku.model.UserModel getUsers() {
		return users;
	}
	public void setUsers(vn.vku.model.UserModel users) {
		this.users = users;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public int getBookID() {
		return BookID;
	}

	public void setBookID(int bookID) {
		BookID = bookID;
	}

	public BookModel getBooks() {
		return books;
	}

	public void setBooks(BookModel books) {
		this.books = books;
	}
	
}
