package bg.softuni.springrepositories.services;

import bg.softuni.springrepositories.entities.Ingredient;

import java.util.List;

public interface IngredientsService {
    List<Ingredient> findByName(String name);

    List<Ingredient> findByNameWithin(List<String> names);

    void increasePrice();

    void deleteByName(String name);
}
