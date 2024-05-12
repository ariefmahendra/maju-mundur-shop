package com.ariefmahendra.majumundurshop.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WebResponse<T> {
    private HttpStatus code;

    private String message;

    private T data;
}
