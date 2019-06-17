package tacos;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
public class Taco {


	private Long id;

	private Date createdAt;
	
	@NotNull
	@Size(min=5, message = "the length must be more than 5 characters")
	private String name;
	@Size(min = 1, message = "must be have at lest 1 ingredient")
	private List<Ingredient> ingredients;
}
