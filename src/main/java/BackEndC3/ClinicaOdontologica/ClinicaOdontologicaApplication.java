package BackEndC3.ClinicaOdontologica;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

	public static void 	main(String[] args) {
		BasicConfigurator.configure();

		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
	}

}
