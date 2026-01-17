package _a;

import com.thomasmylonas.petstore_api_web_app.service_layer.models.response_status_models.ServerResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public abstract class AbstractController implements WebMvcConfigurer {

    public void setResponseStatus(ServerResponseStatus responseStatus, Exception e, HttpStatus httpStatus, String message) {

        responseStatus.setResponseDescription(httpStatus.toString() + ": " + message);
        String responseDescription = responseStatus.getResponseDescription();
        // responseStatus.setResponseDescription(Integer.parseInt(responseDescription.split(" ")[0]));
        responseStatus.setResponseCode(Integer.parseInt(responseDescription.substring(0, responseDescription.indexOf(" "))));
        responseStatus.setResponseMessage(message);
//        responseStatus.setResponseMessage(new ResponseEntity<>(e, httpStatus).getBody().getMessage());

        LOGGER.info(responseDescription);
    }

    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
