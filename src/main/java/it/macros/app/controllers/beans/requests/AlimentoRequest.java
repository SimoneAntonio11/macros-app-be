package it.macros.app.controllers.beans.requests;

import it.macros.app.repositories.entities.Alimento;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class AlimentoRequest extends GenericRequest{

	private Alimento alimento;
}
