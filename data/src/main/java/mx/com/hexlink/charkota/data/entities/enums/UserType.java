package mx.com.hexlink.charkota.data.entities.enums;



public enum UserType {
	ADMINISTRATOR (0, "ADMINISTRADOR", "Administrador del sistema"),
	VETERINARY (1, "VETERINARIO", "Veterinario del departamento"),
	CASHIER (1, "CAJERO", "Cajero de la tienda");



	private final Integer id;
	private final String name;
	private final String description;



	UserType(Integer id, String name, String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}



	public Integer getId() {
		return id;
	}

	public String getName(){
		return name;
	}

	public String getDescription() {
		return description;
	}
}
