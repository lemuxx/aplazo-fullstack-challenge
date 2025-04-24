package mx.aplazo.demo.exception.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String error;
    private Long timestamp;
    private String message;
    private String path;
}
