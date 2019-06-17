package tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

import tacos.Ingredient;
import tacos.Taco;
import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;

@Slf4j
@Controller
@RequestMapping("/design")

public class DesignTacoController {

	private final IngredientRepository ingredientRepo;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo){
		this.ingredientRepo = ingredientRepo;
	}

	// @ModelAttribute
	// private void addAttribues(Model model){
	// 	List<Ingredient> ingredients = Arrays.asList(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
	// 			new Ingredient("COTO", "Corn Tortilla", Type.WRAP), new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
	// 			new Ingredient("CARN", "Carnitas", Type.PROTEIN),
	// 			new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES), new Ingredient("LETC", "Lettuce", Type.VEGGIES),
	// 			new Ingredient("CHED", "Cheddar", Type.CHEESE), new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
	// 			new Ingredient("SLSA", "Salsa", Type.SAUCE), new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
	// 	Type[] types = Ingredient.Type.values();
	// 	for (Type type : types) {
	// 		// log.info(type.toString());
	// 		model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
	// 	}
		
	// 	//return model;
	// }

	@ModelAttribute(name="taco")
	public Taco taco(){
		return new Taco();
	}
	
	@GetMapping
	public String showDesignForm(Model model){
		List<Ingredient>ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i->ingredients.add(i));

		Type[] types = Ingredient.Type.values();
		for(Type type:types){
			model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients, type));
		}
		//model.addAttribute("design", new Taco());
		return "design";
	}
	// @GetMapping
	// public String showDesignForm(Model model) {
	// 	model.addAttribute("design", new Taco());		
	// 	return "design";
	// }


	@PostMapping
	public String processDesign(@Valid @ModelAttribute("taco") Taco taco , Errors errors, Model model) {
		if(errors.hasErrors()){
			//model = addAttribues(model);
			return "design";
		}
		log.info("otra informacion " + taco);
		return "redirect:/orders/current";
	}
	
	private List<Ingredient> filterByType(
			List<Ingredient> ingredients,Type type) {
			//log.info(type.toString());
			//System.out.println(ingredients);
			return ingredients
					.stream()
					.filter(ingredient -> ingredient.getType().equals(type))
					.collect(Collectors.toList());
	}
	
}
