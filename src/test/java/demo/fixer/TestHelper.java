package demo.fixer;

import demo.fixer.constant.Constants;
import demo.fixer.datamodel.Currency;
import demo.fixer.datamodel.CurrencyRate;
import demo.fixer.model.Result;
import demo.fixer.model.Status;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static demo.fixer.model.Status.StatusCode;
import static org.junit.Assert.*;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Helper for tests.
 */
public class TestHelper implements Constants, TestConstants {

    public static void validateGetRatesResultWithNoParamOK(Result result) {
        assertEquals(REQUEST_URL_NO_PARAM, result.getRequestUrl());
        assertEquals(FIXER_API_URL_NO_PARAM, result.getFixerApiUrl());

        Status status = result.getStatus();

        assertEquals(StatusCode.OK, status.getStatusCode());
        assertEquals(OK_MESSAGE, status.getMessage());

        CurrencyRate currencyRate = result.getCurrencyRate();

        assertEquals("USD", currencyRate.getBase());
        assertFalse(StringUtils.isEmpty(currencyRate.getDate()));
        assertTrue(currencyRate.getDate().matches(DATE_FORMAT));
        assertNotNull(currencyRate.getRates());

        Map<String, Double> currencyRateMap = (Map<String, Double>) currencyRate.getRates();

        assertTrue(Currency.values().length-1 == currencyRateMap.keySet().size());
        assertFalse(currencyRateMap.keySet().contains("USD"));

        Set<Currency> currencies = new HashSet<>(Arrays.asList(Currency.values()));
        currencies.remove(Currency.USD);
        Set<String> currencyNames = currencies.stream().map(c -> c.name()).collect(Collectors.toSet());
        assertEquals(currencyNames, currencyRateMap.keySet());

        assertNotNull(result.getTimestamp());
    }

    public static void validateGetRatesResultWithBaseParamOK(Result result) {
        assertEquals(REQUEST_URL_BASE_PARAM, result.getRequestUrl());
        assertEquals(FIXER_API_URL_BASE_PARAM, result.getFixerApiUrl());

        Status status = result.getStatus();

        assertEquals(StatusCode.OK, status.getStatusCode());
        assertEquals(Constants.OK_MESSAGE, status.getMessage());

        CurrencyRate currencyRate = result.getCurrencyRate();

        assertEquals("USD", currencyRate.getBase());
        assertFalse(StringUtils.isEmpty(currencyRate.getDate()));
        assertTrue(currencyRate.getDate().matches(DATE_FORMAT));
        assertNotNull(currencyRate.getRates());

        Map<String, Double> currencyRateMap = (Map<String, Double>) currencyRate.getRates();

        assertTrue(Currency.values().length-1 == currencyRateMap.keySet().size());
        assertFalse(currencyRateMap.keySet().contains("USD"));

        Set<Currency> currencies = new HashSet<>(Arrays.asList(Currency.values()));
        currencies.remove(Currency.USD);
        Set<String> currencyNames = currencies.stream().map(c -> c.name()).collect(Collectors.toSet());
        assertEquals(currencyNames, currencyRateMap.keySet());

        assertNotNull(result.getTimestamp());
    }

    public static void validateGetRatesResultWithBaseAndTargetOK(Result result) {
        assertEquals(REQUEST_URL_BASE_AND_TARGET, result.getRequestUrl());
        assertEquals(FIXER_API_URL_BASE_AND_TARGET, result.getFixerApiUrl());

        Status status = result.getStatus();

        assertEquals(StatusCode.OK, status.getStatusCode());
        assertEquals(Constants.OK_MESSAGE, status.getMessage());

        CurrencyRate currencyRate = result.getCurrencyRate();

        assertEquals("USD", currencyRate.getBase());
        assertFalse(StringUtils.isEmpty(currencyRate.getDate()));
        assertTrue(currencyRate.getDate().matches(DATE_FORMAT));
        assertNotNull(currencyRate.getRates());

        Map<String, Double> currencyRateMap = (Map<String, Double>) currencyRate.getRates();

        assertTrue(3 == currencyRateMap.keySet().size());
        assertFalse(currencyRateMap.keySet().contains("USD"));
        assertTrue(currencyRateMap.keySet().contains("CAD"));
        assertTrue(currencyRateMap.keySet().contains("EUR"));
        assertTrue(currencyRateMap.keySet().contains("GBP"));

        assertNotNull(result.getTimestamp());
    }

    public static void validateGetRatesResultBaseTargetAndTimestampOK(Result result) {
        assertEquals(REQUEST_URL_BASE_TARGET_AND_TIMESTAMP, result.getRequestUrl());
        assertEquals(FIXER_API_URL_BASE_TARGET_AND_TIMESTAMP, result.getFixerApiUrl());

        Status status = result.getStatus();

        assertEquals(StatusCode.OK, status.getStatusCode());
        assertEquals(Constants.OK_MESSAGE, status.getMessage());

        CurrencyRate currencyRate = result.getCurrencyRate();

        assertEquals("USD", currencyRate.getBase());
        assertFalse(StringUtils.isEmpty(currencyRate.getDate()));
        assertTrue(currencyRate.getDate().matches(DATE_FORMAT));
        assertEquals("2017-01-09", currencyRate.getDate());
        assertNotNull(currencyRate.getRates());

        Map<String, Double> currencyRateMap = (Map<String, Double>) currencyRate.getRates();

        assertTrue(3 == currencyRateMap.keySet().size());
        assertFalse(currencyRateMap.keySet().contains("USD"));
        assertTrue(currencyRateMap.keySet().contains("CAD"));
        assertTrue(currencyRateMap.keySet().contains("EUR"));
        assertTrue(currencyRateMap.keySet().contains("GBP"));

        assertNotNull(result.getTimestamp());
    }

    public static void validateGetRatesResultWithInvalidBaseParamError(Result result) {
        assertEquals(REQUEST_URL_INVALID_BASE_PARAM, result.getRequestUrl());
        assertNull(result.getFixerApiUrl());

        Status status = result.getStatus();

        assertEquals(StatusCode.ERROR, status.getStatusCode());
        assertEquals(Constants.ERROR_MESSAGE_PREFIX_INVALID_BASE+"XXX", status.getMessage());

        assertNull(result.getCurrencyRate());

        assertNotNull(result.getTimestamp());
    }

    public static void validateGetRatesResultWithInvalidTargetParamError(Result result) {
        assertEquals(REQUEST_URL_INVALID_TARGET_PARAM, result.getRequestUrl());
        assertNull(result.getFixerApiUrl());

        Status status = result.getStatus();

        assertEquals(StatusCode.ERROR, status.getStatusCode());
        assertEquals(Constants.ERROR_MESSAGE_PREFIX_INVALID_TARGET+"CAD,YYY", status.getMessage());

        assertNull(result.getCurrencyRate());

        assertNotNull(result.getTimestamp());
    }

    public static void validateGetRatesResultWithInvalidTimestampParamError(Result result) {
        assertEquals(REQUEST_URL_INVALID_TIMESTAMP_PARAM, result.getRequestUrl());
        assertNull(result.getFixerApiUrl());

        Status status = result.getStatus();

        assertEquals(StatusCode.ERROR, status.getStatusCode());
        assertEquals(Constants.ERROR_MESSAGE_PREFIX_INVALID_TIMESTAMP+"ZZZ", status.getMessage());

        assertNull(result.getCurrencyRate());

        assertNotNull(result.getTimestamp());
    }

    public static void validateGetRatesResultWithInvalidTimestampParamException(Result result) {
        assertEquals(REQUEST_URL_EXCEPTION_TIMESTAMP, result.getRequestUrl());
        assertEquals(FIXER_API_URL_EXCEPTION_TIMESTAMP, result.getFixerApiUrl());

        Status status = result.getStatus();

        assertEquals(StatusCode.EXCEPTION, status.getStatusCode());
        assertEquals("Date too old", status.getMessage());

        assertNull(result.getCurrencyRate());

        assertNotNull(result.getTimestamp());
    }

}
