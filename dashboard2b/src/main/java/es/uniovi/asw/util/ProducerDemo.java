package es.uniovi.asw.util;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import es.uniovi.asw.Application;
import es.uniovi.asw.dashboard.pojos.Notification;
import es.uniovi.asw.dashboard.producers.KafkaProducer;
import es.uniovi.asw.dbmanagement.SuggestionRepository;
import es.uniovi.asw.dbmanagement.model.Suggestion;
import es.uniovi.asw.dbmanagement.types.NotificationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;

@ManagedBean
public class ProducerDemo {

    @Autowired
    private SuggestionRepository repository;
    @Autowired
    private KafkaProducer producer;

    private Logger log = LoggerFactory.getLogger(Application.class);

    public Suggestion insertSuggestion(Suggestion s) {
        s = repository.save(s);
        Notification n = new Notification(NotificationType.CREATION, s.getId());
        log.info(
                "Saved new suggestion - id:" + s.getId() + ", title:" + s
                        .getTitle());
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        json.put("type", n.getType().ordinal());
        json.put("suggestionId",n.getSuggestionId());
        producer.send("exampleTopic", json.toString());
        return s;
    }


    public void simulateVotes(Long id) {
        Suggestion s = repository.findOne(id);
        int numberOfVotes = s.vote();
        repository.save(s);
        log.info(
                "Suggestion voted - id:" + s.getId() + ", title:" + s
                        .getTitle() + ", votes:" + s.getNumberOfVotes());
        Notification n = new Notification(NotificationType.VOTING, s.getId());
        ObjectNode json = JsonNodeFactory.instance.objectNode();
        json.put("type", n.getType().ordinal());
        json.put("suggestionId",n.getSuggestionId());
        producer.send("exampleTopic", json.toString());
    }
}
