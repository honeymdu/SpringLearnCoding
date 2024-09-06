package com.example.SpringLearnH2db.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtTokenDto {

    private String token;

}
