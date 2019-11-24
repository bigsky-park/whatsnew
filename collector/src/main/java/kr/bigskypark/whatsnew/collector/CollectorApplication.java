package kr.bigskypark.whatsnew.collector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CollectorApplication {

    public static void main(String[] args) {
        int exitCode = 0;
        try {
            SpringApplication.run(CollectorApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
            exitCode = 1;
        } finally {
            System.exit(exitCode);
        }
    }

}
