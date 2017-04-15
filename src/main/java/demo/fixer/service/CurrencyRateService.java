package demo.fixer.service;

import demo.fixer.model.Result;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Interface declaring the REST API method for currency rate service.
 */
public interface CurrencyRateService {

    /**
     * Method to get currency rate info from Fixer.io.
     *
     * @param base name of base currency
     * @param target name/names of target currency/currencies
     * @param timestamp timestamp (date) for currency rate info
     * @return {@link Result} object encapsulating the result of calling Fixer.io API via currency rate service
     */
    Result getRates(String base, String target, String timestamp);

}
