package com.casino;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        System.out.println(new Case(1, Color.R));
        System.out.println(new Case(1, Color.R));

        Case c1 = new Case(1, Color.R);
        Case c2 = c1;

        System.out.println(c1);
        System.out.println(c2);


        Roulette myRoulette = new Roulette();
        List<Case> listOfPlays = new ArrayList<>();


        listOfPlays.addAll((myRoulette.spinRoulette(50)));
        System.out.println(listOfPlays);

        Map<Case, Double> stats = myRoulette.getStats(listOfPlays);
        stats.keySet()
            .stream()
            .sorted((o1, o2) -> (int) (stats.get(o2) - stats.get(o1)))
            .forEach(c -> System.out.println(stats.get(c) + "%: " + c));

        System.out.println(stats);

        System.out.println(myRoulette.highToLow(listOfPlays));

        //System.out.println("List of plays("+listOfPlays.size()+"): "+listOfPlays);
        //System.out.println("Higher to lower plays: "+myRoulette.highToLow(listOfPlays));
        //System.out.println("Lower to higher plays: "+myRoulette.lowToHigh(listOfPlays));

    }
}