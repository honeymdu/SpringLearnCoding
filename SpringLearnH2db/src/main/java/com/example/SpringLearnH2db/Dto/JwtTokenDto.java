package com.example.SpringLearnH2db.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenDto {

    private Long id;
    private String accessToken;
    private String refreshToken;

}
