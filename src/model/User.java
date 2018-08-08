package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the User database table.
 * 
 */
@Entity
@Table(name="User")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String password;

	private String phone;

	@Lob
	private byte[] photo;

	//bi-directional many-to-one association to Education
	@OneToMany(mappedBy="user")
	private List<Education> educations;

	//bi-directional many-to-one association to Experience
	@OneToMany(mappedBy="user")
	private List<Experience> experiences;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="sender")
	private List<Message> sentMessages;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="receiver")
	private List<Message> receivedMessages;

	//bi-directional many-to-one association to Skill
	@OneToMany(mappedBy="user")
	private List<Skill> skills;

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="Connection"
		, joinColumns={
			@JoinColumn(name="Users_id1")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Users_id")
			}
		)
	private List<User> friends;

	//bi-directional many-to-many association to User
//	@ManyToMany(mappedBy="users1")
//	private List<User> users2;

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
		//education.setUser(null);

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
		//experience.setUser(null);

		return experience;
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
		//send.setUser1(null);

		return sent;
	}

	public List<Message> getReceivesMessages() {
		return this.receivedMessages;
	}

	public void setReceivedMessages(List<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	public Message addReceivedMessages(Message received) {
		getReceivesMessages().add(received);
		received.setReceiver(this);

		return received;
	}

	public Message removeReceivedMessages(Message received) {
		getReceivesMessages().remove(received);
		//received.setUser2(null);

		return received;
	}
	

    //get users with whom the argument user has exchanged messages
    public List<User> getConversations() {
    	//set is used so that there are no duplicates from 
    	//received and sent messages
    	Set<User> conversationsSet = new HashSet<User>();
    	
    	//add to set users that this user has sent messages to
    	for (Message msg : this.sentMessages) {
    	    conversationsSet.add(msg.getReceiver());
    	}
    	//add to set users that this user has received messages from
    	for (Message msg : this.receivedMessages) {
    	    conversationsSet.add(msg.getSender());
    	}
    	List<User> conversations = new ArrayList<User>();
    	conversations.addAll(conversationsSet);
    	
    	return conversations;
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
		//skill.setUser(null);

		return skill;
	}

	public List<User> getFriends() {
		return this.friends;
	}

	public void setFriends(List<User> users1) {
		this.friends = users1;
	}

//	public List<User> getFriendOf() {
//		return this.friendOf;
//	}
//
//	public void setFriendOf(List<User> friendOf) {
//		this.friendOf = friendOf;
//	}
	
	public void addFriends(User user)
    {
        getFriends().add(user);
        user.getFriends().add(this);
    }

//    public void addFriendOf(User user)
//    {
//        this.friendOf.add(user);
//        user.friends.add(this);
//    }

}