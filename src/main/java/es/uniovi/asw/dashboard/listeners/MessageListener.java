//package es.uniovi.asw.dashboard.listeners;
//
//import es.uniovi.asw.dashboard.controllers.SSEController;
//import org.apache.log4j.Logger;
//import org.springframework.http.MediaType;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
//
//import javax.annotation.ManagedBean;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * Created by herminio on 28/12/16.
// */
//@ManagedBean
//@RestController
//public class MessageListener {
//
//    private static final Logger logger = Logger.getLogger(MessageListener.class);
//    private final List<SseEmitter> emitters = new ArrayList<>();
//
//    @KafkaListener(topics = "exampleTopic")
//    public void listen(String data) {
//
//        List<SseEmitter> sseEmitterListToRemove = new ArrayList<>();
//        SSEController.emitters.forEach((SseEmitter emitter) -> {
//            try {
//                emitter.send(data, MediaType.APPLICATION_JSON);
//            } catch (IOException e) {
//                emitter.complete();
//                sseEmitterListToRemove.add(emitter);
//                e.printStackTrace();
//            }
//        });
//        SSEController.emitters.removeAll(sseEmitterListToRemove);
//
//        logger.info("New message received: " + data);
//    }
//
//
//
//}
