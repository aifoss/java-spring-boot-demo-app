package demo.fixer.model;

import demo.fixer.datamodel.CurrencyRate;
import lombok.Data;

import java.util.Date;

import static demo.fixer.model.Status.StatusCode;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Model representing the structure of result from currency rate service API call.
 */
@Data
public class Result {

    private String requestUrl;
    private String fixerApiUrl;
    private Status status;
    private CurrencyRate currencyRate;
    private Date timestamp;

    public Result() {}

    public Result(String requestUrl, String fixerApiUrl, Status status, CurrencyRate currencyRate) {
        this.requestUrl = requestUrl;
        this.fixerApiUrl = fixerApiUrl;
        this.status = status;
        this.currencyRate = currencyRate;
        this.timestamp = getCurrentTimestamp();
    }

    public static Result createResult(String requestUrl, String fixerApiUrl, StatusCode statusCode, String message, CurrencyRate currencyRate) {
        Status status = new Status(statusCode, message);
        return new Result(requestUrl, fixerApiUrl, status, currencyRate);
    }

    public static Result createSuccessResult(String requestUrl, String fixerApiUrl, String okMessage, CurrencyRate currencyRate) {
        return createResult(requestUrl, fixerApiUrl, StatusCode.OK, okMessage, currencyRate);
    }

    public static Result createErrorResult(String requestUrl, String errorMessage) {
        return createResult(requestUrl, null, StatusCode.ERROR, errorMessage, null);
    }

    public static Result createExceptionResult(String requestUrl, String fixerApiUrl, String exceptionMessage) {
        return createResult(requestUrl, fixerApiUrl, StatusCode.EXCEPTION, exceptionMessage, null);
    }

    private Date getCurrentTimestamp() {
        return new Date(System.currentTimeMillis());
    }

}
