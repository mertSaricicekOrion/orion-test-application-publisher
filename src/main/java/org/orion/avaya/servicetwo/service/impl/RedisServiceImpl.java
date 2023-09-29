package org.orion.avaya.servicetwo.service.impl;


import org.orion.avaya.servicetwo.model.Call;
import org.orion.avaya.servicetwo.model.CallStatus;
import org.orion.avaya.servicetwo.service.RedisService;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
    private final RedissonClient redissonClient;
    private final RMap<String, Call> callMap;

    @Autowired
    public RedisServiceImpl(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
        this.callMap = redissonClient.getMap("callMap");
    }

    @Override
    public void save(Call call) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong("unique_id_counter");
        call.setId(atomicLong.incrementAndGet());
        callMap.fastPut(String.valueOf(atomicLong), call);
    }

    @Override
    public void update(String callId, CallStatus callStatus) {
            Call call = callMap.get(callId);
        if (call != null) {
            call.setCallStatus(callStatus);
            callMap.fastPut(callId, call);
        } else {
          //TODO: handle if callId doesn't exists
        }
    }

}
