package org.orion.avaya.servicetwo.service;

import org.orion.avaya.servicetwo.api.dto.CallDTO;

public interface CallService {
    Long recieveCall(String telephoneNumber, CallDTO callDTO);
}
