package datamodels;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "workdata")
@Data //lombok annotation It take care of getter & setter & and to string method
@AllArgsConstructor //for creating a constructor for all private parameter
@NoArgsConstructor //for no parameter
public class WorkType {
	@Id
	private ObjectId id;
	 @Indexed(unique = true)
	private String workno;
	private String tittle;
	private String descrip;
	private String imgsrc;
	private String githublink;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getWorkno() {
		return workno;
	}
	public void setWorkno(String workno) {
		this.workno = workno;
	}
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public String getGithublink() {
		return githublink;
	}
	public void setGithublink(String githublink) {
		this.githublink = githublink;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
    
	
    
	

}
