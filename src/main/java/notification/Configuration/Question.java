package notification.Configuration;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name = "WebSocketQuestion")
public class Question implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	String sender;
	String content;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<Topic> tags;

	
	public List<Topic> getTags() {
		return tags;
	}
	public void setTags(List<Topic> tags) {
		this.tags = tags;
	}
	
	public String getFrom() {
		return sender;
	}
	public void setFrom(String from) {
		this.sender = from;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
