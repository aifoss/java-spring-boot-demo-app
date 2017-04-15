package demo.fixer.model;

import lombok.Data;

/**
 * Created by sofia on 4/15/17.
 */

/**
 * Model representing the error response received from Fixer.io API.
 */
@Data
public class FixerError {

    private String error;

    public FixerError() {}

}
