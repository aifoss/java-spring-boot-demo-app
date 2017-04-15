package demo.fixer.constant;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Common constants.
 */
public interface Constants {

    String RATE_SERVICE_URL_PREFIX = "http://localhost:9000/rates?";

    String FIXER_BASE_URL = "http://api.fixer.io/";
    String LATEST_API = "latest?";
    String BASE_PARAM = "base=";
    String SYMBOLS_PARAM = "symbols=";

    String DEFAULT_BASE_CURRENCY = "USD";

    String TIMESTAMP_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    String OK_MESSAGE = "Currency rates successfully fetched";

    String ERROR_MESSAGE_PREFIX_INVALID_BASE = "Invalid input value for base param: ";
    String ERROR_MESSAGE_PREFIX_INVALID_TARGET = "Invalid input value for target param: ";
    String ERROR_MESSAGE_PREFIX_INVALID_TIMESTAMP = "Invalid input value for timestamp param: ";

}
