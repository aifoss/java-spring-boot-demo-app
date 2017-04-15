package demo.fixer.model;

import lombok.Data;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Model representing the structure of request for currency rate service.
 */
@Data
public class Request {

    private String base;
    private String target;
    private String timestamp;

    public Request() {}

}
