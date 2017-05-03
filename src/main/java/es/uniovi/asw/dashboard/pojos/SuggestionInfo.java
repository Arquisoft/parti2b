package es.uniovi.asw.dashboard.pojos;

import es.uniovi.asw.dbmanagement.model.Suggestion;

/**
 * Created by karol on 03/05/2017.
 */
public class SuggestionInfo {

    private Long id;
    private int numberOfVotes;
    private String title;

    public SuggestionInfo(Suggestion s){
        this.id = s.getId();
        this.numberOfVotes = s.getPositiveVotes();
        this.title = s.getTitle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
