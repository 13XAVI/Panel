package SmartPanel.panel.Reservation.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandling implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandling.class);

    public String GetErrorPath(){
    return "error";
    }
    @RequestMapping("/error")
    public String  HandleError(HttpServletRequest request){
    Object Status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String pageTitle = "Error";
        String errorPage = "error";
        if(Status != null){
           Integer statusCode = Integer.valueOf(Status.toString());
           if (statusCode == HttpStatus.NOT_FOUND.value()){
                pageTitle = "page Not Found";
                errorPage = "error/404";
                logger.error("Error 404");
           }
            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                pageTitle = "Internal Server Error";
                errorPage = "error/500";
                logger.error("Error 500");
            }
            if (statusCode == HttpStatus.FORBIDDEN.value()){
                pageTitle = "Forbiden";
                errorPage = "error/403";
                logger.error("Error 403");
            }
            if (statusCode == HttpStatus.BAD_REQUEST.value()){
                pageTitle = "Bad Request";
                errorPage = "error/400";
                logger.error("Error 400");
            }
            if (statusCode == HttpStatus.UNAUTHORIZED.value()){
                pageTitle = "UnAuthorized";
                errorPage = "error/401";
                logger.error("Error 401");
            }
            if (statusCode == HttpStatus.NOT_ACCEPTABLE.value()){
                pageTitle = "NotAcceptable";
                errorPage = "error/406";
                logger.error("Error 406");
            }
            if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()){
                pageTitle = "MethodNotAllowed";
                errorPage = "error/405";
                logger.error("Error 405");
            }
        }

        return errorPage;
    }
}
