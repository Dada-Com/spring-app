package datamodels;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "contactresdata")
@Data //lombok annotation It take care of getter & setter & and to string method
@AllArgsConstructor //for creating a constructor for all private parameter
@NoArgsConstructor //for no parameter
public class ContactResponseType {
	@Id
	private ObjectId id;
	 @Indexed(unique = true)
	private String contactresid;
	private String name;
	private String email;
	private String message;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getContactresid() {
		return contactresid;
	}
	public void setContactresid(String contactresid) {
		this.contactresid = contactresid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
   
}
