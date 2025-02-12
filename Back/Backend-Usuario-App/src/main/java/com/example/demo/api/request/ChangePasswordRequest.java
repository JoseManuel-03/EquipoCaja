package com.example.demo.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordRequest {
	@Schema(description =  "Id usuario al que se desea cambiar el password")
	public Object getID;
	@Schema(description =  "Password actual. Se debe enviar sin codificar.")
	public Object getOldPassword;
	@Size(min = 8)
	@Schema(description =  "Password nueva. Mínimo 8 caracteres. Se debe enviar sin codificar.")
	public Object newPassword;

}
