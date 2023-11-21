package it.macros.app.controllers.beans.responses;


import java.util.List;

import it.macros.app.repositories.entities.Piatto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class PiattoListResponse extends GenericResponse {

	private List<Piatto> list;
}
