package bg.softuni.springrepositories;

import bg.softuni.springrepositories.services.IngredientsService;
import bg.softuni.springrepositories.services.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final ShampooService shampooService;

    private final IngredientsService ingredientsService;


    public Runner(ShampooService shampooService, IngredientsService ingredientsService) {
        this.shampooService = shampooService;
        this.ingredientsService = ingredientsService;

    }

    @Override
    public void run(String... args) throws Exception {


//       List<Shampoo> byBrand = shampooService.findByBrand("Swiss Green Apple & Nettle");
//       byBrand.forEach(System.out::println);
//
//       List<Shampoo> byBrandAndSize = shampooService.findByBrandAndSize("Swiss Green Apple & Nettle", Size.SMALL);
//       byBrandAndSize.forEach(System.out::println);
//
//// 01.  Select Shampoos by Size - given size, ordered by shampoo id
//        List<Shampoo> bySize = shampooService.findBySize(Size.MEDIUM);
//        bySize.forEach(s -> System.out.println(s.getBrand() + " " + s.getPrice() + "lv."));

//// 02.  Select Shampoos by Size or Label - shampoos with a given size or label id. Sort the result ascending by price.
//        List<Shampoo> result = shampooService.findBySizeOrLabelId(Size.MEDIUM, 10L);
//        result.forEach(s -> System.out.println(s.getBrand() + " " + s.getPrice() + "lv."));

//// 03.  Select Shampoos by Price - select all shampoos, which price is higher than a given price. Sort the result descending by price.
//        List<Shampoo> result = shampooService.findByPriceGreaterThan(BigDecimal.valueOf(5));
//        result.forEach(s -> System.out.println(s.getBrand() + " " + s.getSize() + " " + s.getPrice() + "lv."));

//// 04.  Select Ingredients by Name - select all ingredients, which name starts with given letters.
//        List<Ingredient> result = ingredientsService.findByName("M");
//        result.forEach(i -> System.out.println(i.getName()));

//// 05.  Select Ingredients by Names - select all ingredients, which are contained in a given list. Sort the result ascending by price.
//        List<Ingredient> result = ingredientsService.findByNameWithin(List.of("Lavender","Herbs","Apple"));
//        result.forEach(i -> System.out.println(i.getName()));

//// 06.  Count Shampoos by Price - count all shampoos with price lower than a given price.
//        int count = shampooService.findCheaperThanCount(BigDecimal.valueOf(8.50));
//        System.out.println(count);


// ======================================= JPQL Querying ===========================================================================================


////  07. Select Shampoos by Ingredients - select all shampoos with ingredients included in a given list.
//        List<Shampoo> result = shampooService.findAllWithIngredients(List.of("Berry", "Mineral-Collagen"));
//        result.forEach(s -> System.out.println(s.getBrand()));

////  09. Delete Ingredients by Name
//        ingredientsService.deleteByName("Apple");

//// 10.  Update Ingredients by Price
//        ingredientsService.increasePrice();


    }
}
