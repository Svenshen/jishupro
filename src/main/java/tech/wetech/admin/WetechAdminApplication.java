package tech.wetech.admin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author cjbi
 */

@SpringBootApplication(exclude = {QuartzAutoConfiguration.class})
@EnableTransactionManagement
@EnableWebMvc
@EnableCaching
@EnableSwagger2
@EnableScheduling
public class WetechAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WetechAdminApplication.class, args);
    }
}
