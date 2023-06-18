package datamodels;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "homedata")
@Data //lombok annotation It take care of getter & setter & and to string method
@AllArgsConstructor //for creating a constructor for all private parameter
@NoArgsConstructor //for no parameter
public class HomeType {
	@Id
	private ObjectId id;
	
	 @Indexed(unique = true)
	private String pid;
	private String name;
	private String descrp; 
	private String tlink;
	private String llink;
	private String glink;
	private String imgsrc;
	private String logo;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescrp() {
		return descrp;
	}
	public void setDescrp(String descrp) {
		this.descrp = descrp;
	}
	public String getTlink() {
		return tlink;
	}
	public void setTlink(String tlink) {
		this.tlink = tlink;
	}
	public String getLlink() {
		return llink;
	}
	public void setLlink(String llink) {
		this.llink = llink;
	}
	public String getGlink() {
		return glink;
	}
	public void setGlink(String glink) {
		this.glink = glink;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	
    
	

}
