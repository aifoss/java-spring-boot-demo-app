package demo.fixer.controller;

import demo.fixer.model.Result;
import demo.fixer.service.CurrencyRateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Controller for currency rate service.
 */
@RestController
public class CurrencyRateServiceController {

    private final CurrencyRateService currencyRateService;

    public CurrencyRateServiceController(CurrencyRateService currencyRateService) {

        this.currencyRateService = currencyRateService;
    }

    /**
     * Method mapping /rates URI to getRates() method in currency rate service.
     *
     * @param base name of base currency
     * @param target name/names of target currency/currencies
     * @param timestamp timestamp (date) for currency rate info
     * @return {@link Result} object encapsulating the result of calling Fixer.io API via currency rate service
     */
    @RequestMapping(value = "/rates", method = RequestMethod.GET, produces = "application/json")
    public Result getRates(@RequestParam(value = "base", required = false) String base,
                           @RequestParam(value = "target", required = false) String target,
                           @RequestParam(value = "timestamp", required = false) String timestamp) {

        return currencyRateService.getRates(base, target, timestamp);
    }

}
