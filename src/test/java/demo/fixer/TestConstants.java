package demo.fixer;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Common constants for tests.
 */
public interface TestConstants {

    String DATE_FORMAT = "\\d{4}-\\d{2}-\\d{2}";

    String REQUEST_URL_NO_PARAM = "http://localhost:9000/rates?base=&target=&timestamp=";
    String FIXER_API_URL_NO_PARAM = "http://api.fixer.io/latest?base=USD";

    String REQUEST_URL_BASE_PARAM = "http://localhost:9000/rates?base=USD&target=&timestamp=";
    String FIXER_API_URL_BASE_PARAM = "http://api.fixer.io/latest?base=USD";

    String REQUEST_URL_BASE_AND_TARGET = "http://localhost:9000/rates?base=USD&target=CAD,EUR,GBP&timestamp=";
    String FIXER_API_URL_BASE_AND_TARGET = "http://api.fixer.io/latest?base=USD&symbols=CAD,EUR,GBP";

    String REQUEST_URL_BASE_TARGET_AND_TIMESTAMP = "http://localhost:9000/rates?base=USD&target=CAD,EUR,GBP&timestamp=2017-01-10T00:00:01Z";
    String FIXER_API_URL_BASE_TARGET_AND_TIMESTAMP = "http://api.fixer.io/2017-01-09?base=USD&symbols=CAD,EUR,GBP";

    String REQUEST_URL_INVALID_BASE_PARAM = "http://localhost:9000/rates?base=XXX&target=&timestamp=";
    String REQUEST_URL_INVALID_TARGET_PARAM = "http://localhost:9000/rates?base=&target=CAD,YYY&timestamp=";
    String REQUEST_URL_INVALID_TIMESTAMP_PARAM = "http://localhost:9000/rates?base=&target=&timestamp=ZZZ";

    String REQUEST_URL_EXCEPTION_TIMESTAMP = "http://localhost:9000/rates?base=&target=&timestamp=1900-01-01T00:00:01Z";
    String FIXER_API_URL_EXCEPTION_TIMESTAMP = "http://api.fixer.io/1899-12-31?base=USD";

}
