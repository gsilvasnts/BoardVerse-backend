package br.com.boardverse.boardverse_backend.exception;

import java.util.Map;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    
    private Integer status;
    private String message;
    private Map<String, String> errors;

}
