package br.com.lucasfeitosa.hamburgueria;

import java.util.Set;

public interface Builder {

    void setBurguerType(BurguerType type);
    void setIngredients(Set<String> ingredients);

}
