package org.orion.avaya.servicetwo.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@Validated
public class CallDTO {
    @NotBlank
    private String locale;
    @NotBlank
    private String trunkName;
    @NotBlank
    private String timestamp;

}
