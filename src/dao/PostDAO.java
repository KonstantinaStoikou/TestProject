package dao;

import java.util.List;

import model.Post;

public interface PostDAO {
	public Post find(int id);

	public List<Post> list();

	public void create(Post post);

	public void delete(Post post);

}
