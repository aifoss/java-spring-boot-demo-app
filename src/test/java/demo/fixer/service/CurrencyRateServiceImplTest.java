package demo.fixer.service;

import demo.fixer.TestHelper;
import demo.fixer.handler.CurrencyRateServiceHandler;
import demo.fixer.helper.CurrencyRateServiceHelper;
import demo.fixer.model.Result;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Test for {@link CurrencyRateServiceImpl}.
 */
@RunWith(SpringRunner.class)
public class CurrencyRateServiceImplTest {

    private CurrencyRateService service;
    private CurrencyRateServiceHandler handler;
    private CurrencyRateServiceHelper helper;

    @Before
    public void setUp() {
        handler = new CurrencyRateServiceHandler();
        helper = new CurrencyRateServiceHelper();
        service = new CurrencyRateServiceImpl(handler, helper);
    }

    @Test
    public void testServiceGetRatesWithNoParamOK() {
        Result result = this.service.getRates(null, null, null);
        TestHelper.validateGetRatesResultWithNoParamOK(result);
    }

    @Test
    public void testServiceGetRatesWithBaseParamOK() {
        Result result = this.service.getRates("USD", null, null);
        TestHelper.validateGetRatesResultWithBaseParamOK(result);
    }

    @Test
    public void testServiceGetRatesWithBaseAndTargetOK() {
        Result result = this.service.getRates("USD", "CAD,EUR,GBP", null);
        TestHelper.validateGetRatesResultWithBaseAndTargetOK(result);
    }

    @Test
    public void testServiceGetRatesWithBaseTargetAndTimestampOK() {
        Result result = this.service.getRates("USD", "CAD,EUR,GBP", "2017-01-10T00:00:01Z");
        TestHelper.validateGetRatesResultBaseTargetAndTimestampOK(result);
    }

    @Test
    public void testServiceGetRatesWithInvalidBaseParamError() {
        Result result = this.service.getRates("XXX", null, null);
        TestHelper.validateGetRatesResultWithInvalidBaseParamError(result);
    }

    @Test
    public void testServiceGetRatesWithInvalidTargetParamError() {
        Result result = this.service.getRates(null, "CAD,YYY", null);
        TestHelper.validateGetRatesResultWithInvalidTargetParamError(result);
    }

    @Test
    public void testServiceGetRatesWithInvalidTimestampParamError() {
        Result result = this.service.getRates(null, null, "ZZZ");
        TestHelper.validateGetRatesResultWithInvalidTimestampParamError(result);
    }

    @Test
    public void testServiceGetRatesWithInvalidTimestampParamException() {
        Result result = this.service.getRates(null, null, "1900-01-01T00:00:01Z");
        TestHelper.validateGetRatesResultWithInvalidTimestampParamException(result);
    }

}
