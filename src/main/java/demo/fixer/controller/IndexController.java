package demo.fixer.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Controller for currency rate service index page.
 */
public class IndexController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = ERROR_PATH)
    public String error() {
        return "error";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
