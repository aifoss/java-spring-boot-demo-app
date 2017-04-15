package demo.fixer.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Controller for error page.
 */
@Controller
public class ErrorPageController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    public String error() {
        return "error";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
