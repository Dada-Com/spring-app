package datamodels;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "admindata")
@Data //lombok annotation It take care of getter & setter & and to string method
@AllArgsConstructor //for creating a constructor for all private parameter
@NoArgsConstructor //for no parameter

public class AdminSecurityType {
	@Id
	private ObjectId id;
	@Indexed(unique = true)
	private String adminid;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getAdminid() {
		return adminid;
	}
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
}
