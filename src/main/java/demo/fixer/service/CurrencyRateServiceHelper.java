package demo.fixer.service;

import demo.fixer.constant.Constants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Helper for currency rate service.
 */
@Component
public class CurrencyRateServiceHelper implements Constants {

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
    public static String getFixerApiUrl(String base, String target, String timestamp) {
        String url = FIXER_BASE_URL;

        if (!StringUtils.isEmpty(timestamp)) {
            int idx = timestamp.indexOf("T");
            String date = timestamp.substring(0, idx);
            url += date + "?";
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
