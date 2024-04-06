package com.projectPS;

import com.projectPS.Testing.Cezar;
import com.projectPS.Testing.PassType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class ProjectPsApplication {


	public static void main(String[] args) {
		//SpringApplication.run(ProjectPsApplication.class, args);
		Cezar cezar = new Cezar();
		System.out.println(cezar.cryptPassoword(PassType.MEDIUM,"JAVA"));

	}



}
