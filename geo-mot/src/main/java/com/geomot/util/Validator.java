/**
 * 
 */
package com.geomot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author andre
 *
 */
public class Validator {
	private static Logger logger = LoggerFactory.getLogger(Validator.class);

	/**
	 * Metodo de validacion de string
	 * 
	 * @param value
	 * @param nameMetodo
	 * @return
	 */
	public boolean validationString(String value, String nameMetodo) {
		boolean state = true;
		try {
			if (value == null || value == "" || value.isEmpty()) {
				state = false;
			}
		} catch (Exception e) {
			state = false;
			String message = "valiactionString + " + "Exception" + nameMetodo + " " + e.getMessage();
			logger.error(message);
		}
		return state;
	}

	/**
	 * Metodo de validacion de numeros
	 * 
	 * @param value
	 * @param nameMetodo
	 * @return
	 */
	public boolean validationNumber(String value, String nameMetodo) {
		boolean state = true;
		try {
			if (validationString(value, nameMetodo)) {
				try {
					if (!value.equals("0")) {
						Integer.parseInt(value);
					} else {
						state = false;
					}
				} catch (NumberFormatException e) {
					state = false;
					String message = "validationNumber + " + "NumberFormatException" + nameMetodo + " "
							+ e.getMessage();
					logger.error(message);
				}
			} else {
				state = false;
			}
		} catch (Exception e) {
			state = false;
			String message = "validationNumber + " + "Exception" + nameMetodo + " " + e.getMessage();
			logger.error(message);
		}
		return state;
	}
}
