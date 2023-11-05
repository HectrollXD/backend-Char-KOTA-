package mx.com.hexlink.charkota.api.restful.enums;



public enum Status {
	OK (true, "La operación solicitada se ha realizado con éxito."),
	ERROR ( true, "Ocurrión un error al momento de procesar la solicitud."),
	DELETED (true, "Se han eliminado los registros solicitados de manera correcta."),
	UPDATED (true, "Se han actualizado los registros solicitados."),
	UNAUTHORIZED (false, "El usuario en sesión no tiene autorización para realizar la acción solicitada."),
	NOT_FOUND (false, "No se encontrón el registro solicitado.");



	private final Boolean success;
	private final String description;



	Status(Boolean success, String description){
		this.success = success;
		this.description = description;
	}



	public String getDescription() {
		return description;
	}

	public Boolean isSuccess() {
		return success;
	}
}
