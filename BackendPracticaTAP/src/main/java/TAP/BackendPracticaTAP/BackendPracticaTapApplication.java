package TAP.BackendPracticaTAP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan({"Controllers"})
@EntityScan("Entities")
@EnableJpaRepositories("Repositories")
public class BackendPracticaTapApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendPracticaTapApplication.class, args);
	}

}
