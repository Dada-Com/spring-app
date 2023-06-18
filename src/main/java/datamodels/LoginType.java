package datamodels;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "userdata")
@Data //lombok annotation It take care of getter & setter & and to string method
@AllArgsConstructor //for creating a constructor for all private parameter
@NoArgsConstructor //for no parameter
public class LoginType {
	@Id
	private ObjectId id;
	 @Indexed(unique = true)
	private String username;
	private String password;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}  	
}
