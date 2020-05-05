package top.gerrytang.r4seo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import top.gerrytang.r4seo.container.DriverContainer;

import java.net.MalformedURLException;

/**
 * the base application
 * @author gerry
 */
@SpringBootApplication
@EnableCaching
public class Application {

    protected static final Logger _logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Value("${selenium.chrome.url}")
    String chromeUrl;

    @Value("${config.driver.num}")
    int driverCount;

    @Bean
    DriverContainer driverContainer() throws MalformedURLException {
        return new DriverContainer(driverCount,chromeUrl);
    }


}
