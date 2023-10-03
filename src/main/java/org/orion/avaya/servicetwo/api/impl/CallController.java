package org.orion.avaya.servicetwo.api.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.orion.avaya.servicetwo.api.CallApi;
import org.orion.avaya.servicetwo.api.dto.CallDTO;
import org.orion.avaya.servicetwo.service.CallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class CallController implements CallApi {
    private final CallService callService;


    @Override
    public ResponseEntity<String> startCall(String telephoneNumber, @Valid CallDTO callDTO) {
        String callId = String.valueOf(callService.recieveCall(telephoneNumber, callDTO));
        return ResponseEntity.ok().body(callId);
    }

    @Override
    public ResponseEntity<Void> endCall(String callId, CallEndEvent callEndEvent) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> cancelCall(String callId, CallCancelEvent callCancelEvent) {
        return ResponseEntity.ok().build();
    }
}
