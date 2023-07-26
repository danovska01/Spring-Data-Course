package bg.softuni.springrepositories.services;

import bg.softuni.springrepositories.entities.Ingredient;
import bg.softuni.springrepositories.repositories.IngredientsRepository;
import jakarta.transaction.Transactional;

import java.util.List;

public class IngredientsServiceImpl implements IngredientsService {

    private final IngredientsRepository ingredientRepository;

    public IngredientsServiceImpl(IngredientsRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    @Transactional
    public List<Ingredient> findByName(String name) {
        List<Ingredient> ingredients = ingredientRepository.findByNameStartingWith(name);

        for (Ingredient ingredient : ingredients) {
            System.out.printf("%s -> %d\n", ingredient.getName(), ingredient.getShampoos().size());
        }

        return ingredients;
    }

    @Override
    public List<Ingredient> findByNameWithin(List<String> names) {
        return ingredientRepository.findByNameInOrderByPriceAsc(names);
    }

    @Override
    public void increasePrice() {
        ingredientRepository.increasePriceBy10Percent();
    }

    @Override
    public void deleteByName(String name) {
        ingredientRepository.deleteIngredientsByName(name);
    }
}
