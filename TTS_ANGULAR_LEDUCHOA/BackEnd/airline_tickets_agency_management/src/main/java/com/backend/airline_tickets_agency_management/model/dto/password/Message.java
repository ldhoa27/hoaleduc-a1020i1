package com.backend.airline_tickets_agency_management.model.dto.password;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String msg;

    public Message(String msg) {
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

