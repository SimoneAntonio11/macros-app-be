package it.macros.app.controllers.beans.responses;

import it.macros.app.controllers.beans.Esito;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class GenericResponse {
	private Esito esito = new Esito();
}