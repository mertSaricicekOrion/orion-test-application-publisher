package org.orion.avaya.servicetwo.service;

import org.orion.avaya.servicetwo.model.Call;
import org.orion.avaya.servicetwo.model.CallStatus;

public interface RedisService {

    void save(Call call);

    void update(String callId, CallStatus callStatus);
}
