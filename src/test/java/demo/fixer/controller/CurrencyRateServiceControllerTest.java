package demo.fixer.controller;

import demo.fixer.TestHelper;
import demo.fixer.model.Result;
import demo.fixer.service.CurrencyRateService;
import demo.fixer.handler.CurrencyRateServiceHandler;
import demo.fixer.helper.CurrencyRateServiceHelper;
import demo.fixer.service.CurrencyRateServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Test for {@link CurrencyRateServiceController}.
 */
@RunWith(SpringRunner.class)
public class CurrencyRateServiceControllerTest {

    private CurrencyRateServiceController controller;
    private CurrencyRateService service;
    private CurrencyRateServiceHandler handler;
    private CurrencyRateServiceHelper helper;

    @Before
    public void setUp() {
        handler = new CurrencyRateServiceHandler();
        helper = new CurrencyRateServiceHelper();
        service = new CurrencyRateServiceImpl(handler, helper);
        controller = new CurrencyRateServiceController(service);
    }

    @Test
    public void testControllerGetRatesWithNoParamOK() {
        Result result = this.controller.getRates(null, null, null);
        TestHelper.validateGetRatesResultWithNoParamOK(result);
    }

    @Test
    public void testControllerGetRatesWithBaseParamOK() {
        Result result = this.controller.getRates("USD", null, null);
        TestHelper.validateGetRatesResultWithBaseParamOK(result);
    }

    @Test
    public void testControllerGetRatesWithBaseAndTargetOK() {
        Result result = this.controller.getRates("USD", "CAD,EUR,GBP", null);
        TestHelper.validateGetRatesResultWithBaseAndTargetOK(result);
    }

    @Test
    public void testControllerGetRatesWithBaseTargetAndTimestampOK() {
        Result result = this.controller.getRates("USD", "CAD,EUR,GBP", "2017-01-10T00:00:01Z");
        TestHelper.validateGetRatesResultBaseTargetAndTimestampOK(result);
    }

    @Test
    public void testControllerGetRatesWithInvalidBaseParamError() {
        Result result = this.controller.getRates("XXX", null, null);
        TestHelper.validateGetRatesResultWithInvalidBaseParamError(result);
    }

    @Test
    public void testControllerGetRatesWithInvalidTargetParamError() {
        Result result = this.controller.getRates(null, "CAD,YYY", null);
        TestHelper.validateGetRatesResultWithInvalidTargetParamError(result);
    }

    @Test
    public void testControllerGetRatesWithInvalidTimestampParamError() {
        Result result = this.controller.getRates(null, null, "ZZZ");
        TestHelper.validateGetRatesResultWithInvalidTimestampParamError(result);
    }

    @Test
    public void testControllerGetRatesWithInvalidTimestampParamException() {
        Result result = this.controller.getRates(null, null, "1900-01-01T00:00:01Z");
        TestHelper.validateGetRatesResultWithInvalidTimestampParamException(result);
    }

}
