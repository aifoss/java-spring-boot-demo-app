package demo.fixer.helper;

import demo.fixer.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Helper for currency rate service.
 */
@Component
public class CurrencyRateServiceHelper implements Constants {

    private static final Logger LOG = LoggerFactory.getLogger(CurrencyRateServiceHelper.class);

    /**
     * Helper method to compose currency rate service API request URL.
     */
    public static String getRequestUrl(String base, String target, String timestamp) {
        String requestUrl = RATE_SERVICE_URL_PREFIX;

        requestUrl += "base=" + (StringUtils.isEmpty(base) ? "" : base);
        requestUrl += "&target=" + (StringUtils.isEmpty(target) ? "" : target);
        requestUrl += "&timestamp=" + (StringUtils.isEmpty(timestamp) ? "" : timestamp);

        return requestUrl;
    }

    /**
     * Helper method to compose Fixer.io API URL.
     */
    public static String getFixerApiUrl(String base, String target, String timestamp) throws ParseException {
        String url = FIXER_BASE_URL;

        if (!StringUtils.isEmpty(timestamp)) {
            DateFormat dateFormat = new SimpleDateFormat(TIMESTAMP_DATE_FORMAT);
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = dateFormat.parse(timestamp);

            dateFormat = new SimpleDateFormat(TIMESTAMP_DATE_OUTPUT_FORMAT);
            dateFormat.format(date);
            String dateStr = new SimpleDateFormat(TIMESTAMP_DATE_OUTPUT_FORMAT).format(date);

            LOG.info("date input: "+timestamp);
            LOG.info("date output: "+dateStr);

            url += dateStr + "?";
            
        } else {
            url += LATEST_API;
        }

        url += BASE_PARAM;

        if (!StringUtils.isEmpty(base)) {
            url += base;
        } else {
            url += DEFAULT_BASE_CURRENCY;
        }

        if (!StringUtils.isEmpty(target)) {
            url += "&";
            url += SYMBOLS_PARAM;
            url += target;
        }

        return url;
    }

}
