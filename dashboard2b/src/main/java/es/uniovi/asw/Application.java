package es.uniovi.asw;


import es.uniovi.asw.dashboard.controllers.SuggestionController;
import es.uniovi.asw.dbmanagement.model.Suggestion;
import es.uniovi.asw.util.ProducerDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(
            Application.class);
    private static final int NUMBER_OF_SUGGESTIONS = 99;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner demo(ProducerDemo producerDemo, SuggestionController
            controller) {
        return (args) -> {
            String demo = System.getenv("DEMO_KAFKA");

            if (demo == null) {
                Thread.sleep(3000);
                List<Suggestion> suggestions = new ArrayList<>();
                for (int i = 0; i < NUMBER_OF_SUGGESTIONS; i++) {
                    suggestions.add(new Suggestion("Propuesta" + i));
                    Thread.sleep(1500);
                    producerDemo.insertSuggestion(suggestions.get(i));

                    for (Suggestion s : suggestions) {
                        Thread.sleep(1000);
                        producerDemo.simulateVotes(s.getId());
                    }
                }
            }
        };
    }
}