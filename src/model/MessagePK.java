package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Message database table.
 * 
 */
@Embeddable
public class MessagePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int id;

	@Column(insertable=false, updatable=false)
	private int senderId;

	@Column(name="User_id1", insertable=false, updatable=false)
	private int user_id1;

	public MessagePK() {
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSenderId() {
		return this.senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getUser_id1() {
		return this.user_id1;
	}
	public void setUser_id1(int user_id1) {
		this.user_id1 = user_id1;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MessagePK)) {
			return false;
		}
		MessagePK castOther = (MessagePK)other;
		return 
			(this.id == castOther.id)
			&& (this.senderId == castOther.senderId)
			&& (this.user_id1 == castOther.user_id1);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.senderId;
		hash = hash * prime + this.user_id1;
		
		return hash;
	}
}