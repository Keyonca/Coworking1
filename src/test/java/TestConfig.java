import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = ("ru.parmacoworking.jpa"))
@EntityScan(basePackages = ("ru.parmacoworking.entity"))
public class TestConfig {
}
