package br.com.lucasfeitosa.hamburgueria;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static br.com.lucasfeitosa.hamburgueria.Constants.*;

public final class Utils {

    //change to get Ingredients.
    public static Set<String> getCustomIngredients(List<String> pedido) {

        Set<String> ingredients = new HashSet<>();
        ingredients = getBaseIngredients();

        for (String item: pedido) {
            if(item.startsWith("+")) {
                ingredients.add(item.split("\\+")[1]);
            } else if (item.startsWith("-")) {
                ingredients.remove(item.split("\\-")[1]);
            }
        }

        return ingredients;

    }

    private static HashSet<String> getBaseIngredients() {
        List<String> baseIngredients = Arrays.asList(BREAD, CHEESE, HAMBURGUER, MAYONNAISE);
        return new HashSet<>(baseIngredients);
    }

    private boolean hasToRemove() {

        return false;
    }

}
