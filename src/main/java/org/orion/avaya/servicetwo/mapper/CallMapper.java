package org.orion.avaya.servicetwo.mapper;

import org.mapstruct.*;
import org.orion.avaya.servicetwo.api.dto.CallDTO;
import org.orion.avaya.servicetwo.model.Call;
import org.orion.avaya.servicetwo.model.CallStatus;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CallMapper {
    Call toEntity(CallDTO callDTO);

    @AfterMapping
    default void afterToEntity(@MappingTarget Call call) {
        call.setCallStatus(CallStatus.INVITED);
    }
}
