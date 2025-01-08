package com.spring.jwt.utils;

import com.spring.jwt.dto.AdministratorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponseDTO {

    private String code;
    private String message;

}
