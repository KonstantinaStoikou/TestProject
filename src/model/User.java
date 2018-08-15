package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the User database table.
 * 
 */
@Entity
@Table(name = "User")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String password;

	private String phone;

	@Lob
	private byte[] photo;

	// bi-directional many-to-many association to Job
	@ManyToMany
	@JoinTable(name = "Application", joinColumns = { @JoinColumn(name = "User_id") }, inverseJoinColumns = {
			@JoinColumn(name = "Job_id") })
	private List<Job> appliedJobs;

	// bi-directional many-to-one association to Comment
	@OneToMany(mappedBy = "user")
	private List<Comment> comments;

	// bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(name = "Connection", joinColumns = { @JoinColumn(name = "Users_id2") }, inverseJoinColumns = {
			@JoinColumn(name = "Users_id1") })
	private List<User> friends;

	// bi-directional many-to-one association to Education
	@OneToMany(mappedBy = "user")
	private List<Education> educations;

	// bi-directional many-to-one association to Experience
	@OneToMany(mappedBy = "user")
	private List<Experience> experiences;

	// bi-directional many-to-one association to Job
	@OneToMany(mappedBy = "user")
	private List<Job> jobs;

	// bi-directional many-to-many association to Post
	@ManyToMany
	@JoinTable(name = "LikePost", joinColumns = { @JoinColumn(name = "User_id") }, inverseJoinColumns = {
			@JoinColumn(name = "Post_id") })
	private List<Post> likedPosts;

	// bi-directional many-to-one association to Message
	@OneToMany(mappedBy = "sender")
	private List<Message> sentMessages;

	// bi-directional many-to-one association to Message
	@OneToMany(mappedBy = "receiver")
	private List<Message> receivedMessages;

	// bi-directional many-to-one association to Post
	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	// bi-directional many-to-one association to Skill
	@OneToMany(mappedBy = "user")
	private List<Skill> skills;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public List<Job> getAppliedJobs() {
		return this.appliedJobs;
	}

	public void setAppliedJobs(List<Job> appliedJobs) {
		this.appliedJobs = appliedJobs;
	}

	public void addAppliedJob(Job job) {
		getAppliedJobs().add(job);
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setUser(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		// comment.setUser(null);

		return comment;
	}

	public List<User> getFriends() {
		return this.friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public void addFriends(User user) {
		getFriends().add(user);
		user.getFriends().add(this);
	}

	public List<Education> getEducations() {
		return this.educations;
	}

	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}

	public Education addEducation(Education education) {
		getEducations().add(education);
		education.setUser(this);

		return education;
	}

	public Education removeEducation(Education education) {
		getEducations().remove(education);
//		education.setUser(null);

		return education;
	}

	public List<Experience> getExperiences() {
		return this.experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public Experience addExperience(Experience experience) {
		getExperiences().add(experience);
		experience.setUser(this);

		return experience;
	}

	public Experience removeExperience(Experience experience) {
		getExperiences().remove(experience);
//		experience.setUser(null);

		return experience;
	}

	public List<Job> getJobs() {
		return this.jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Job addJob(Job job) {
		getJobs().add(job);
		job.setUser(this);

		return job;
	}

	public Job removeJob(Job job) {
		getJobs().remove(job);
		// job.setUser(null);

		return job;
	}

	public List<Post> getLikedPosts() {
		return this.likedPosts;
	}

	public void setLikedPosts(List<Post> likedPost) {
		this.likedPosts = likedPost;
	}

	public void addLikedPost(Post likedPost) {
		getLikedPosts().add(likedPost);
	}

	public List<Message> getSentMessages() {
		return this.sentMessages;
	}

	public void setSentMessages(List<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public Message addSentMessages(Message sent) {
		getSentMessages().add(sent);
		sent.setSender(this);

		return sent;
	}

	public Message removeSendedMessages(Message sent) {
		getSentMessages().remove(sent);
		// send.setUser1(null);

		return sent;
	}

	public List<Message> getReceivedMessages() {
		return this.receivedMessages;
	}

	public void setReceivedMessages(List<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	public Message addReceivedMessages(Message received) {
		getReceivedMessages().add(received);
		received.setReceiver(this);

		return received;
	}

	public Message removeReceivedMessages(Message received) {
		getReceivedMessages().remove(received);
		// received.setUser2(null);

		return received;
	}

	// get users with whom the argument user has exchanged messages
	public List<User> getConversations() {
		// set is used so that there are no duplicates from
		// received and sent messages
		Set<User> conversationsSet = new HashSet<User>();

		// add to set users that this user has sent messages to
		for (Message msg : this.sentMessages) {
			conversationsSet.add(msg.getReceiver());
		}
		// add to set users that this user has received messages from
		for (Message msg : this.receivedMessages) {
			conversationsSet.add(msg.getSender());
		}
		List<User> conversations = new ArrayList<User>();
		conversations.addAll(conversationsSet);

		return conversations;
	}

	// get messages from conversation with given user sorted by id
	public List<Message> getConversationMessages(int id) {
		// add to list all messages exchanged between this user and given user
		List<Message> allUserMessages = new ArrayList<Message>();
		for (Message msg : this.sentMessages) {
			if (msg.getReceiver().getId() == id) {
				allUserMessages.add(msg);
			}
		}
		for (Message msg : this.receivedMessages) {
			if (msg.getSender().getId() == id) {
				allUserMessages.add(msg);
			}
		}

		// sort messages in this conversation by id
		// because id is auto incremented, sorting by id
		// means sorting by most recent message
		// (more recent messages will have the larger ids)
		Comparator<Message> comparator = new Comparator<Message>() {
			@Override
			public int compare(Message left, Message right) {
				return left.getId() - right.getId();
			}
		};

		Collections.sort(allUserMessages, comparator);
		return allUserMessages;
	}

	// find user with whom the current user exchanged the last message
	public User getLastConversationUser() {
		// merge received and sent messages into one list
		List<Message> allMessages = new ArrayList<Message>(this.getReceivedMessages());
		allMessages.addAll(this.getSentMessages());

		// sort messages by most recent using their ids (largest id -> most recent)
		Comparator<Message> comparator = new Comparator<Message>() {
			@Override
			public int compare(Message left, Message right) {
				return left.getId() - right.getId();
			}
		};
		Collections.sort(allMessages, comparator);
		if (allMessages.isEmpty()) {
			return null;
		}
		Message lastMessage = allMessages.get(allMessages.size() - 1);
		User user = new User();
		if (this != lastMessage.getReceiver()) {
			user = lastMessage.getReceiver();
		} else {
			user = lastMessage.getSender();
		}

		return user;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post addPosts(Post posts) {
		getPosts().add(posts);
		posts.setUser(this);

		return posts;
	}

	public Post removePosts(Post posts) {
		getPosts().remove(posts);
		// posts.setUser(null);

		return posts;
	}

	public List<Skill> getSkills() {
		return this.skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public Skill addSkill(Skill skill) {
		getSkills().add(skill);
		skill.setUser(this);

		return skill;
	}

	public Skill removeSkill(Skill skill) {
		getSkills().remove(skill);
		// skill.setUser(null);

		return skill;
	}

}