package notification.Configuration;


import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	private QuestionRepository qRepo;
	
	@Autowired
	private TopicRepository tRepo;
	
   @MessageMapping("/question")
    public void notifyQuestion( Question q) throws Exception {
        Thread.sleep(1000); // simulated delay
        //save question
        
        qRepo.save(q);
        System.out.println("data saved");
        //Extract tags
        
        List<Topic> tags = q.getTags();
        //send to topics
        
        Iterator<Topic> tIterator = tags.iterator();
		while (tIterator.hasNext())
		{

	        System.out.println("fetching tag");
			Topic t =tIterator.next();

	        System.out.println(t);
			Topic topic = tRepo.findOne(t.getId());
			

	        System.out.println(topic.getTitle());
			String topicname = topic.getTitle();

	        System.out.println("Sending notification to "+topicname);
	        simpMessagingTemplate.convertAndSend("/topic/"+topicname, q);
	     }
    } 
    
}