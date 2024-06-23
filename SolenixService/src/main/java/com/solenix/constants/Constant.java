/**
 * 
 */
package com.solenix.constants;

/**
 * @author solenix
 *
 */
public enum Constant {
	
	USER_HOME("user.home");

    private final String value;

    Constant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
