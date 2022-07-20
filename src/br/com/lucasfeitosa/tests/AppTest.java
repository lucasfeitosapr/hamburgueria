package br.com.lucasfeitosa.tests;

import static org.junit.Assert.*;
import java.io.*;

import br.com.lucasfeitosa.Main;
import org.junit.Assert;
import org.junit.Test;

public class AppTest {

    @Test
    public void shouldBuildXSalada() {
        String order = "x-salada";
        String ingredients = Main.processOrder(order);

        Assert.assertEquals("cebola, hamburguer, maionese, pao, queijo, tomate", ingredients);
    }

    @Test
    public void shouldBuildXBurguer() {
        String order = "x-burguer";
        String ingredients = Main.processOrder(order);

        Assert.assertEquals("hamburguer, maionese, pao, queijo", ingredients);
    }

    @Test
    public void shouldBuildXBurguerWithBacon() {
        String order = "X-Burguer,+Bacon";
        String ingredients = Main.processOrder(order);

        Assert.assertEquals("bacon, hamburguer, maionese, pao, queijo", ingredients);
    }

    @Test
    public void shouldBuildXBacon() {
        String order = "x-bacon";
        String ingredients = Main.processOrder(order);

        Assert.assertEquals("bacon, hamburguer, maionese, pao, queijo, tomate", ingredients);
    }

    @Test
    public void shouldBuildXTudo() {
        String order = "x-tudo";
        String ingredients = Main.processOrder(order);

        Assert.assertEquals("bacon, cebola, hamburguer, maionese, pao, queijo, tomate", ingredients);
    }

    @Test
    public void shouldBuildXTudoIgnoresCaseSenstive() {
        String order = "X-TUDO";
        String ingredients = Main.processOrder(order);

        Assert.assertEquals("bacon, cebola, hamburguer, maionese, pao, queijo, tomate", ingredients);
    }

    @Test
    public void shouldBuildXTudoWithAllAdditionals() {
        String order = "X-TUDO, +PICLES";
        String ingredients = Main.processOrder(order);

        Assert.assertEquals("bacon, cebola, hamburguer, maionese, pao, picles, queijo, tomate", ingredients);
    }

    @Test
    public void shouldReturnInvalidXTudoWithWrongAdditionals() {
        String order = "X-TUDO, +batata";
        String ingredients = Main.processOrder(order);

        Assert.assertEquals("Invalido", ingredients);
    }

    @Test
    public void shouldBuildXSaladaWithoutTomate() {
        String order = "x-salada,-tomate";
        String ingredients = Main.processOrder(order);

        Assert.assertEquals("cebola, hamburguer, maionese, pao, queijo", ingredients);
    }

    @Test
    public void shouldBuildXSaladaWithoutTomateWithPicles() {
        String order = "x-salada,-tomate,+picles";
        String ingredients = Main.processOrder(order);

        Assert.assertEquals("cebola, hamburguer, maionese, pao, picles, queijo", ingredients);
    }

    @Test
    public void shouldReturnInvalidForXSaladaWithoutPicles() {
        String order = "x-salada,-picles";
        String ingredients = Main.processOrder(order);

        Assert.assertEquals("Invalido", ingredients);
    }

    @Test
    public void shouldReturnInvalidForXSaladaWith() {
        String order = "x-salada,-picles";
        String ingredients = Main.processOrder(order);

        Assert.assertEquals("Invalido", ingredients);
    }

    @Test
    public void shouldReturnInvalidForXBurguerWithoutCebola() {
        String order = "x-burguer,+bacon,-cebola";
        String ingredients = Main.processOrder(order);

        Assert.assertEquals("Invalido", ingredients);
    }

}
