package com.geomot.co.validation;

public class TextoValidation {

	/**
	 * Metodo validator is null and empaty
	 * 
	 * @param value
	 * @return
	 */

	public boolean isNullEmpaty(String value) {
		boolean state = false;
		if (value != null && value != "") {
			state = true;
		}
		return state;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public boolean isNumeric(String value) {
		boolean state = false;
		if (isNullEmpaty(value)) {
			Integer.parseInt(value);
			state = true;
		}
		return state;
	}
}
