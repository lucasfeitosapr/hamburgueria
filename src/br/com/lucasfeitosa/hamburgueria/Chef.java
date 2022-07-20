package br.com.lucasfeitosa.hamburgueria;

import java.util.*;
import java.util.stream.Collectors;

import static br.com.lucasfeitosa.hamburgueria.Constants.*;

public class Chef {

    private List<String> baseIngredients = new ArrayList<>();
    private final List<String> additionals = new ArrayList<>(Arrays.asList(BACON, TOMATE, ONION, PICLES));

    public void cookBurguer(Builder builder, List<String> order) throws Exception {
        this.baseIngredients = new ArrayList<String>(Arrays.asList(BREAD, CHEESE, HAMBURGUER, MAYONNAISE));
        Set<String> ingredients = this.applyObservations(order);
        builder.setBurguerType(BurguerType.X_BURGUER);
        builder.setIngredients(ingredients);
    }

    public void cookXsalada(Builder builder, List<String> order) throws Exception {
        this.baseIngredients = new ArrayList<String>(Arrays.asList(BREAD, CHEESE, HAMBURGUER, MAYONNAISE, TOMATE, ONION));
        Set<String> ingredients = this.applyObservations(order);
        builder.setBurguerType(BurguerType.X_SALADA);
        builder.setIngredients(ingredients);
    }

    public void cookXbacon(Builder builder, List<String> order) throws Exception {
        this.baseIngredients =  new ArrayList<String>(Arrays.asList(BREAD, CHEESE, HAMBURGUER, BACON, TOMATE, MAYONNAISE));
        Set<String> ingredients = this.applyObservations(order);
        builder.setBurguerType(BurguerType.X_BACON);
        builder.setIngredients(ingredients);
    }

    public void cookXtudo(Builder builder, List<String> order) throws Exception {
        this.baseIngredients =  new ArrayList<String>(Arrays.asList(BREAD, CHEESE, HAMBURGUER, BACON, TOMATE, ONION, MAYONNAISE));
        Set<String> ingredients = this.applyObservations(order);
        builder.setBurguerType(BurguerType.X_TUDO);
        builder.setIngredients(ingredients);
    }

    private Set<String> applyObservations(List<String> observations) throws Exception {

        Set<String> ingredients = new HashSet<>(this.baseIngredients);

        List<String> ingredientsToAdd = getIngredientsToAdd(observations);
        List<String> ingredientsToRemove = getIngredientsToRemove(observations);

        for (String item: ingredientsToRemove) {
            ingredients.remove(item);
        }

        ingredients.addAll(ingredientsToAdd);

        return ingredients;
    }

    private List<String> getIngredientsToAdd(List<String> observations) throws Exception {

        List<String> ingredientsToAdd = this.filterIngredientsToAdd(observations);
        boolean hasValidAdditionals = this.additionalIsValid(ingredientsToAdd);

        if(!hasValidAdditionals) throw new Exception("Invalido");

        boolean alreadyHasIngredients = this.validateIngredients(ingredientsToAdd);

        if(alreadyHasIngredients) throw new Exception("Invalido");

        return ingredientsToAdd;
    }


    private List<String> getIngredientsToRemove(List<String> observations) throws Exception {

        List<String> ingredientsToRemove = this.filterIngredientsToRemove(observations);
        boolean hasValidIngredient = this.validateIngredients(ingredientsToRemove);

        if(ingredientsToRemove.size() > 0 && !hasValidIngredient) throw new Exception("Invalido");

        return ingredientsToRemove;
    }

    /**
     * Filters a list, removing all "+" chars from items and returning a new list with
     * ingredients to add.
     * @param observations list with observations
     * @return a filtered list with ingredients to add, example: [bacon, maionese, picles]
     */
    private List<String> filterIngredientsToAdd(List<String> observations) {
        List<String> toAdd = observations.stream().filter(item -> item.startsWith("+")).collect(Collectors.toList());

        return toAdd.stream().map(item -> item.split("\\+")[1]).collect(Collectors.toList());

    }

    private List<String> filterIngredientsToRemove(List<String> observations) {
        List<String> toRemove = observations.stream().filter(item -> item.startsWith("-")).collect(Collectors.toList());

        return toRemove.stream().map(item -> item.split("-")[1]).collect(Collectors.toList());
    }

    private boolean validateIngredients(List<String> ingredients) {
        return baseIngredients.stream().anyMatch(ingredients.stream().collect(Collectors.toSet())::contains);
    }

    private boolean additionalIsValid(List<String> additionals) {
        return additionals.stream().anyMatch(this.additionals.stream().collect(Collectors.toSet())::contains);
    }
}
