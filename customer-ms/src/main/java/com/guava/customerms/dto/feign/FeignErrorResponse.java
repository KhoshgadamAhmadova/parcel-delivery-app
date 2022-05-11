package com.guava.customerms.dto.feign;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeignErrorResponse {

    private String type;
    private String message;
    private Integer status;
    private String error;
    private String errorDetail;
    private String path;
    private String timestamp;
}
