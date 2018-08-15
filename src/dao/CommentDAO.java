package dao;

import java.util.List;

import model.Comment;

public interface CommentDAO {
	public Comment find(int id);

	public List<Comment> list();

	public void create(Comment com);

	public void delete(Comment com);

}
