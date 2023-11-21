package it.macros.app.controllers.beans;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Esito {

	private Integer code = 0;
	private String target = null;
	private String[] args = null;

	/**
	 * @param code
	 * @param target
	 */
	public Esito(Integer code, String target) {
		this.code = code;
		this.target = target;
	}

	
	
}