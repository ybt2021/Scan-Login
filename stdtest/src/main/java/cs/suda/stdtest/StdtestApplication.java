package cs.suda.stdtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})public class StdtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(StdtestApplication.class, args);
    }

}
