package cotato.Bookluetooth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
//@EnableJpaRepositories(basePackages = {"cotato.Bookluetooth.users.domain"})
//@EntityScan(basePackages = "cotato.Bookluetooth.users.domain")
public class BookluetoothBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookluetoothBackendApplication.class, args);
	}

}
