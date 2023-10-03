package org.orion.avaya.servicetwo.service;

import org.orion.avaya.servicetwo.model.CallEvent;
import org.orion.avaya.servicetwo.model.CallStatus;

public interface RedisService {

    void save(CallEvent call);

    void update(String callId, CallStatus callStatus);
}
