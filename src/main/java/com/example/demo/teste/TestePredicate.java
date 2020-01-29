package com.example.demo.teste;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestePredicate {

    public static String filtro(List<String> lista, Predicate<String> condicao) {
        lista.forEach(s -> {
            if (condicao.test(s)) {
                System.out.println(s);
            }
        });

        lista.stream().filter(condicao::test).forEach(System.out::println);

        return lista.stream().filter(s -> condicao.test(s)).collect(Collectors.joining());
    }

    public static void main(String[] args) {


        List<String> list = Arrays.asList("Maria", "Joao", "Carla", "Daniela");

        System.out.println(filtro(list, s -> s.contains("Ma")));
    }
}
