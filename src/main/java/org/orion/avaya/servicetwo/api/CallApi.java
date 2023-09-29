package org.orion.avaya.servicetwo.api;

import org.orion.avaya.servicetwo.api.dto.CallDTO;
import org.orion.avaya.servicetwo.model.CallCancelEvent;
import org.orion.avaya.servicetwo.model.CallEndEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface CallApi {
    @PostMapping("/start-call/{telephoneNumber}")
    ResponseEntity<String> startCall(
            @PathVariable String telephoneNumber,
            @RequestBody CallDTO callDTO
    );

    @PostMapping("/end/{callId}")
    ResponseEntity<Void> endCall(@PathVariable String callId, @RequestBody CallEndEvent callEndEvent);

    @PostMapping("/cancel/{callId}")
    ResponseEntity<Void> cancelCall(@PathVariable String callId, @RequestBody CallCancelEvent callCancelEvent);

}
