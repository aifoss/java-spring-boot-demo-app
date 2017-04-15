package demo.fixer.datamodel;

import lombok.Data;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Data model representing the structure of currency rate info received using Fixer.io API.
 */
@Data
public class CurrencyRate {

    private String base;
    private String date;
    private Object rates;

    public CurrencyRate() {}

}
