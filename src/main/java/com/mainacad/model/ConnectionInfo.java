package com.mainacad.model;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ConnectionInfo implements Serializable {
    private Integer sessionId;
    private Long connectionTime;
    private String ip;

    @Override
    public String toString() {
        return sessionId + " " + connectionTime + " " + ip;
    }
}
