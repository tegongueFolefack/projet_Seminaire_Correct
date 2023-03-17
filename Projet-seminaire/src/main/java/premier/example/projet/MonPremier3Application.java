package premier.example.projet;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MonPremier3Application implements CommandLineRunner {
	
	@Bean
	public ModelMapper modelMapper () {
		return new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(MonPremier3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Student st = new Student();
		//System.out.println("DALSH");
	}

}
