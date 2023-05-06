package ir.tehranluster.paano.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Data
public class ApiSuccessResponse {
    private int statusCode;
    private HttpStatus status;

    public ApiSuccessResponse(int statusCode, HttpStatus status) {
        this.statusCode = statusCode;
        this.status = status;
    }
}
