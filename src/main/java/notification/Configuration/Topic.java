package notification.Configuration;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "WebSocketTopic")
public class Topic implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "topic_id", updatable = false, nullable = false)
	private long id;

	String title;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
