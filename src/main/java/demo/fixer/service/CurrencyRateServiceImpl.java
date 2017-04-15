package demo.fixer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.fixer.constant.Constants;
import demo.fixer.datamodel.CurrencyRate;
import demo.fixer.model.FixerError;
import demo.fixer.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Implementation of currency rate service.
 */
@Service("currencyRateService")
public class CurrencyRateServiceImpl implements CurrencyRateService, Constants {

    private static final Logger LOG = LoggerFactory.getLogger(CurrencyRateService.class);

    private final CurrencyRateServiceHandler handler;
    private final CurrencyRateServiceHelper helper;

    public CurrencyRateServiceImpl(CurrencyRateServiceHandler handler, CurrencyRateServiceHelper helper) {
        this.handler = handler;
        this.helper = helper;
    }


    @Override
    public Result getRates(String base, String target, String timestamp) {
        // compose request URL
        String requestUrl = helper.getRequestUrl(base, target, timestamp);
        LOG.info("requestUrl="+requestUrl);

        // validate request params
        String errorMessage = handler.validateGetRatesParams(base, target, timestamp);
        if (!StringUtils.isEmpty(errorMessage)) {
            LOG.info(errorMessage);
            return Result.createErrorResult(requestUrl, errorMessage);
        }

        // compose corresponding Fixer.io API URL
        String fixerApiUrl = helper.getFixerApiUrl(base, target, timestamp);
        LOG.info("fixerApiUrl="+fixerApiUrl);

        String res = "";
        ObjectMapper mapper = new ObjectMapper();

        try {
            // connect to Fixer.io API
            res = handler.connectToFixerApi(fixerApiUrl);
            LOG.info(res);

            // wrap and return currency rate info together with status info
            CurrencyRate currencyRate = mapper.readValue(res, CurrencyRate.class);
            return Result.createSuccessResult(requestUrl, fixerApiUrl, OK_MESSAGE, currencyRate);

        } catch (IOException e) {
            if (!StringUtils.isEmpty(res)) {
                try {
                    FixerError fixerError = mapper.readValue(res, FixerError.class);
                    LOG.info(fixerError.getError());
                    return Result.createExceptionResult(requestUrl, fixerApiUrl, fixerError.getError());
                } catch (IOException ioe) {
                    LOG.info(ioe.getMessage());
                    return Result.createExceptionResult(requestUrl, fixerApiUrl, ioe.getMessage());
                }
            } else {
                LOG.info(e.getMessage());
                return Result.createExceptionResult(requestUrl, fixerApiUrl, e.getMessage());
            }
        }
    }

}
