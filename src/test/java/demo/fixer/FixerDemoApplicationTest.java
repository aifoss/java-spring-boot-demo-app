package demo.fixer;

import demo.fixer.FixerDemoApplication;
import demo.fixer.TestHelper;
import demo.fixer.model.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Test for {@link FixerDemoApplication}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FixerDemoApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void contextLoads() {
    }

    @Test
    public void testAppGetRatesWithNoParamOK() {
        Result result = this.restTemplate.getForObject("/rates", Result.class);
        TestHelper.validateGetRatesResultWithNoParamOK(result);
    }

    @Test
    public void testAppGetRatesWithBaseParamOK() {
        Result result = this.restTemplate.getForObject("/rates?base=USD", Result.class);
        TestHelper.validateGetRatesResultWithBaseParamOK(result);
    }

    @Test
    public void testAppGetRatesWithBaseAndTargetOK() {
        Result result = this.restTemplate.getForObject("/rates?base=USD&target=CAD,EUR,GBP", Result.class);
        TestHelper.validateGetRatesResultWithBaseAndTargetOK(result);
    }

    @Test
    public void testAppGetRatesWithBaseTargetAndTimestampOK() {
        Result result = this.restTemplate.getForObject("/rates?base=USD&target=CAD,EUR,GBP&timestamp=2017-01-10T00:00:01Z", Result.class);
        TestHelper.validateGetRatesResultBaseTargetAndTimestampOK(result);
    }

    @Test
    public void testAppGetRatesWithInvalidBaseParamError() {
        Result result = this.restTemplate.getForObject("/rates?base=XXX", Result.class);
        TestHelper.validateGetRatesResultWithInvalidBaseParamError(result);
    }

    @Test
    public void testAppGetRatesWithInvalidTargetParamError() {
        Result result = this.restTemplate.getForObject("/rates?target=CAD,YYY", Result.class);
        TestHelper.validateGetRatesResultWithInvalidTargetParamError(result);
    }

    @Test
    public void testAppGetRatesWithInvalidTimestampParamError() {
        Result result = this.restTemplate.getForObject("/rates?timestamp=ZZZ", Result.class);
        TestHelper.validateGetRatesResultWithInvalidTimestampParamError(result);
    }

    @Test
    public void testAppGetRatesWithInvalidTimestampParamException() {
        Result result = this.restTemplate.getForObject("/rates?timestamp=1900-01-01T00:00:01Z", Result.class);
        TestHelper.validateGetRatesResultWithInvalidTimestampParamException(result);
    }

}
