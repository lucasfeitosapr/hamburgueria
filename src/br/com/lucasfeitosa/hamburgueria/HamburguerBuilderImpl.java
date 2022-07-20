package br.com.lucasfeitosa.hamburgueria;

import java.util.Set;

public class HamburguerBuilderImpl implements IHamburguerBuilder {

    private Set<String> ingredients;
    private BurguerType type;

    @Override
    public void setBurguerType(BurguerType type) {
        this.type = type;
    }

    @Override
    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }
}
