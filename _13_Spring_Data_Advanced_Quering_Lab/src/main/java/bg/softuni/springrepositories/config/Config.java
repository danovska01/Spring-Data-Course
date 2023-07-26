package bg.softuni.springrepositories.config;

import bg.softuni.springrepositories.repositories.IngredientsRepository;
import bg.softuni.springrepositories.services.IngredientsService;
import bg.softuni.springrepositories.services.IngredientsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class Config {

    private final Environment environment;

    public Config(Environment environment) {
        this.environment = environment;

        int property = this.environment.getProperty("bg.softuni.max-shampoos", int.class);

        System.out.println(property);
    }


    @Bean
    public IngredientsService createIngredientsService(IngredientsRepository ingredientRepo) {
        return new IngredientsServiceImpl(ingredientRepo);
    }
}
