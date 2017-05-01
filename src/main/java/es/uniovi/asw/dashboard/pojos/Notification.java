package es.uniovi.asw.dashboard.pojos;

import es.uniovi.asw.dbmanagement.types.NotificationType;

public class Notification {

    private NotificationType type;
    private Long suggestionId;

    public Notification(){
    }

    public Notification(NotificationType type, Long suggestionId) {
        this.type = type;
        this.suggestionId = suggestionId;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public Long getSuggestionId() {
        return suggestionId;
    }

    public void setSuggestionId(Long suggestionId) {
        this.suggestionId = suggestionId;
    }
}
