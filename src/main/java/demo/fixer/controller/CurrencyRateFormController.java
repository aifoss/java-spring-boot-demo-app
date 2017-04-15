package demo.fixer.controller;

import demo.fixer.model.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Controller for currency rate form page.
 */
@Controller
public class CurrencyRateFormController {

    @RequestMapping(value = "/currencyRateForm")
    public String createCurrencyRateForm(Model model) {
        model.addAttribute("request", new Request());
        return "currencyRateForm";
    }

}
