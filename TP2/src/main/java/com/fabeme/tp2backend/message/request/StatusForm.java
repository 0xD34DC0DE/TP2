package com.fabeme.tp2backend.message.request;

import com.fabeme.tp2backend.model.Status;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusForm {
    private Status status;

    @JsonCreator
    StatusForm(@JsonProperty("status") String status) {
        if(status.equals(Status.ACTIVE.toString())) {
            this.status = Status.ACTIVE;
        } else if(status.equals(Status.INACTIVE.toString())) {
            this.status = Status.INACTIVE;
        } else {
            throw new RuntimeException("Status Form created with invalid argument");
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isStatusActive() {
        if(this.status == Status.ACTIVE)
            return true;
        else if (this.status == Status.INACTIVE)
            return false;
        else
            throw new RuntimeException("status is not in a valid state");
    }
}
