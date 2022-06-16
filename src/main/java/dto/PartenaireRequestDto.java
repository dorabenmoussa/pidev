package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartenaireRequestDto {
	
	 private String partenaireName;


	    private String partenaireType;

	
	    private String matriculation;
}
