package br.com.lucasfeitosa;

import br.com.lucasfeitosa.hamburgueria.BurguerType;
import br.com.lucasfeitosa.hamburgueria.Chef;
import br.com.lucasfeitosa.hamburgueria.HamburguerBuilder;

import java.util.*;

import static br.com.lucasfeitosa.hamburgueria.BurguerType.findByValue;

public class Main {

    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);
        System.out.print("Insira o pedido: ");
        String order = sc.nextLine();

        String response = processOrder(order);
        System.out.println(response);
    }

    public static String processOrder(String order) {
        String formatedOrder = order.toLowerCase(Locale.ROOT).replaceAll("\\s","");
        List<String> pedido = new ArrayList<>(Arrays.asList(formatedOrder.split(",")));

        BurguerType burguerType = findByValue(pedido.get(0));

        if(burguerType == null) System.out.println("Invalido");

        List<String> observations = pedido.subList(1, pedido.size());

        Chef chef = new Chef();
        HamburguerBuilder builder = new HamburguerBuilder();

        try {
            switch (burguerType) {
                case X_BURGUER:
                    chef.cookBurguer(builder, observations);
                    break;
                case X_SALADA:
                    chef.cookXsalada(builder, observations);
                    break;
                case X_BACON:
                    chef.cookXbacon(builder, observations);
                    break;
                case X_TUDO:
                    chef.cookXtudo(builder, observations);
                    break;
            }
        } catch (Exception e) {
            return e.getMessage();
        }

        Object[] sortedIngredients = builder.getIngredients().toArray();
        Arrays.sort(sortedIngredients);

        return Arrays.toString(sortedIngredients).replaceAll("\\[", "").replaceAll("\\]", "");
    }
}
