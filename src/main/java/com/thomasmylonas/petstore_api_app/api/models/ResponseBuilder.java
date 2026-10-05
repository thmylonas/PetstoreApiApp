package com.thomasmylonas.petstore_api_app.api.models;

import com.thomasmylonas.petstore_api_app.helpers.HelperClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Service
public class ResponseBuilder {

    /**
     * This default value only in DEV environment/profile. Set "false" in PROD environment/profile.
     * The client to see the stacktrace, must add in the request URI the "?trace=true"
     */
    @Value(value = "${stacktrace.print:true}")
    private boolean printStacktrace;

    public ResponseEntity<ResponseSuccess> buildResponseSuccess(HttpStatus httpStatus, String message, Map<String, ?> data) {
        return buildResponseSuccess(httpStatus, message, ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString(), data);
    }

    public ResponseEntity<ResponseSuccess> buildResponseSuccess(HttpStatus httpStatus, String message, String path, Map<String, ?> data) {

        ResponseSuccess responseSuccess = ResponseSuccess.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(httpStatus.toString())
                .message(message)
                .path(path)
                .data(data)
                .build();
        return ResponseEntity.status(httpStatus).header(HttpHeaders.LOCATION, path).body(responseSuccess);
    }

    public ResponseEntity<ResponseError> buildResponseError(Exception e, HttpStatus httpStatus, Map<String, ?> errorMessages, WebRequest webRequest) {

        ResponseError.ResponseErrorBuilder responseErrorBuilder = ResponseError.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(httpStatus.toString())
                .errorMessages(errorMessages)
                .path(ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString());
        if (printStacktrace && isTraceParameterEnabled(webRequest)) {
            responseErrorBuilder.stacktrace(HelperClass.stacktrace(e));
        }
        return ResponseEntity.status(httpStatus).body(responseErrorBuilder.build());
    }

    private boolean isTraceParameterEnabled(WebRequest webRequest) {
        String[] traceValues = webRequest.getParameterValues("trace");
        return Objects.nonNull(traceValues) && traceValues.length > 0 && Arrays.asList(traceValues).contains("true");
    }
}
