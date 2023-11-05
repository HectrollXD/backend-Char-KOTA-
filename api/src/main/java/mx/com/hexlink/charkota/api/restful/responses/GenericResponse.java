package mx.com.hexlink.charkota.api.restful.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> {
	private Boolean status;
	private String message;
	private T data;
}
