package demo.fixer.model;

import lombok.Data;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Model representing status info portion of currency rate service result.
 */
@Data
public class Status {

    public enum StatusCode {
        OK,
        ERROR,
        EXCEPTION;
    }

    StatusCode statusCode;
    String message;

    public Status() {}

    public Status(StatusCode statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}
