package org.orion.avaya.servicetwo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CallCancelEvent {
    private String reason;
    private String timestamp;
}
