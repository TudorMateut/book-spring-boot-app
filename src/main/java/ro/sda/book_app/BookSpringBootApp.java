package ro.sda.book_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class BookSpringBootApp {
    public static void main(String[] args) {
        SpringApplication.run(BookSpringBootApp.class, args);

//        Optional<String> stringOpt = Optional.of("abc");
//        if (stringOpt.isPresent()) {
//            String unwrapped = stringOpt.get();
//            System.out.println(unwrapped);
//        }
//
//        Optional<String> emptyOpt = Optional.empty();
//        if (emptyOpt.isPresent()) {
//            String unwrapped = emptyOpt.get();
//            System.out.println(unwrapped);
//        }
//
//        if (emptyOpt.isEmpty()) {
////          String unwrapped = emptyOpt.get();
//            System.out.println("Optional empty!");
//        }
//
//        Optional<String> optOfNull = Optional.ofNullable(null);
//        if (optOfNull.isPresent()) {
//            System.out.println("Present!");
//        }
    }
}
