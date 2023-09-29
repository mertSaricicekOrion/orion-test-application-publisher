package org.orion.avaya.servicetwo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Call {

    private Long id;
    private String telephoneNumber;
    private String locale;
    private String trunkName;
    private String timestamp;
    private CallStatus callStatus;

}
