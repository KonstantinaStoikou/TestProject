package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the Post database table.
 * 
 */
@Entity
@Table(name = "Post")
@NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Postcol")
	private String postcol;

	// bi-directional many-to-one association to Comment
	@OneToMany(mappedBy = "post")
	private List<Comment> comments;

	// bi-directional many-to-many association to User
	@ManyToMany(mappedBy = "likedPosts")
	private List<User> likeUsers;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "User_id")
	private User user;

	public Post() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPostcol() {
		return this.postcol;
	}

	public void setPostcol(String postcol) {
		this.postcol = postcol;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setPost(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setPost(null);

		return comment;
	}

	public List<User> getLikeUsers() {
		return this.likeUsers;
	}

	public void setLikeUsers(List<User> likeUsers) {
		this.likeUsers = likeUsers;
	}

	public void addLikeUser(User likeUser) {
		getLikeUsers().add(likeUser);
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}