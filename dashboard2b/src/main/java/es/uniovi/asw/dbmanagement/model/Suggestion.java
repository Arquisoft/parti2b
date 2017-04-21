package es.uniovi.asw.dbmanagement.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Propuestas")
public class Suggestion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int numberOfVotes;

    Suggestion() {
    }

    public Suggestion(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public int vote() {
        return ++numberOfVotes;
    }
}
