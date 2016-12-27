package cz.muni.pa165.sem.util;

/**
 * @author Kamil Triscik
 */
public class InviteRequest {
    private Long event_id;
    private String inputEmail;

    public InviteRequest() {
        super();
    }

    public InviteRequest(Long event_id, String inputEmail) {
        this.event_id = event_id;
        this.inputEmail = inputEmail;
    }

    public Long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Long event_id) {
        this.event_id = event_id;
    }

    public String getInputEmail() {
        return inputEmail;
    }

    public void setInputEmail(String inputEmail) {
        this.inputEmail = inputEmail;
    }
}
