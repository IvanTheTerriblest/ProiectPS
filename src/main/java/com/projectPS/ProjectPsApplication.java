package com.projectPS;

import com.projectPS.Model.Ingredients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@RestController
public class ProjectPsApplication {


	public static void main(String[] args) {
		SpringApplication.run(ProjectPsApplication.class, args);
	}

	@GetMapping
	public List<Ingredients> ingredientsList(){
		return List.of(new Ingredients(1L,"Egg", LocalDate.of(2024, Month.APRIL,6),10));
	}

}
