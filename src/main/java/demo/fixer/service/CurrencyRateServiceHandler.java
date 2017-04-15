package demo.fixer.service;

import demo.fixer.constant.Constants;
import demo.fixer.datamodel.Currency;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Handler for currency rate service.
 */
@Component
public class CurrencyRateServiceHandler implements Constants {

    /**
     * Method to validate request params for currency rate service.
     *
     * @param base name of base currency
     * @param target name/names of target currency/currencies
     * @param timestamp timestamp (date) for currency rate info
     * @return empty string if no error; otherwise error message
     */
    public String validateGetRatesParams(String base, String target, String timestamp) {
        if (!StringUtils.isEmpty(base)) {
            try {
                Currency.valueOf(base);
            } catch (IllegalArgumentException e) {
                return ERROR_MESSAGE_PREFIX_INVALID_BASE + base;
            }
        }

        if (!StringUtils.isEmpty(target)) {
            String[] targets = target.split(",");

            for (String t : targets) {
                try {
                    Currency.valueOf(t);
                } catch (IllegalArgumentException e) {
                    return ERROR_MESSAGE_PREFIX_INVALID_TARGET + target;
                }
            }
        }

        if (!StringUtils.isEmpty(timestamp)) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
                simpleDateFormat.parse(timestamp);
            } catch (ParseException e) {
                return ERROR_MESSAGE_PREFIX_INVALID_TIMESTAMP + timestamp;
            }
        }

        return "";
    }

    /**
     * Method to connect to Fixer.io API to get currency rate info.
     *
     * @param url Fixer.io API URL to which to send a request
     * @return string representing the payload received from Fixer.io API
     * @throws IOException
     */
    public String connectToFixerApi(String url) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);

        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        return sb.toString();
    }

}
