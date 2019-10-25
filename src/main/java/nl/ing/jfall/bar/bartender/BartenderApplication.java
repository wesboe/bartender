package nl.ing.jfall.bar.bartender;

import nl.ing.jfall.bar.bartender.model.MenuItem;
import nl.ing.jfall.bar.bartender.repository.MenuRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BartenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BartenderApplication.class, args);
    }

    @Bean
    public CommandLineRunner onStartUp(MenuRepository menuRepository) {
        return arg -> {
            List<MenuItem> menuItems = Arrays.asList(
                    MenuItem.builder().name("americano").description("delicious black coffee").price(2.50).build(),
                    MenuItem.builder().name("espresso").description("small coffee").price(2.00).build(),
                    MenuItem.builder().name("cortado").description("double espresso with steamed milk").price(3.50).build(),
                    MenuItem.builder().name("cappucino").description("coffee with steamed milk").price(3.50).build()
            );

            menuRepository.saveAll(menuItems);
        };
    }

}
