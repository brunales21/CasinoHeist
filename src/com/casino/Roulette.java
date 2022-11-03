package com.casino;

import java.util.*;
import java.util.stream.Collectors;

public class Roulette {
    public List<Case> roulette = new ArrayList<>();
    public List<Integer> rouletteNumbers = new ArrayList<>();


    public Roulette() {
        initRoulette();
    }
    public void initRoulette() {
        for (int i = 0; i<=36; i++) {
            if (i != 0) {
                Case c = new Case(i, i%2);
                roulette.add(c);
            } else {
                Case c = new Case(i, Color.Y);
                roulette.add(c);
            }
        }
    }

    public Map<Case, Double> getStats(List<Case> plays) {
        Set<Case> distincts = new HashSet<>(plays);

        System.out.println("------------------------");
        System.out.println(distincts.stream().sorted((o1, o2) -> o1.getNumber() - o2.getNumber()).collect(Collectors.toList()));
        System.out.println("------------------------");

        Map<Case, Double> myMap = new HashMap<>();
        for (var aCase: distincts) {
            double counter = getOcurrences(plays, aCase.getNumber());
            myMap.put(aCase, (counter/plays.size()) * 100);
        }
        return myMap;
    }

    public Case spinRoulette() {
        return getRoulette().get(new Random().nextInt(37));
    }
    public List<Case> spinRoulette(int n) {
        List<Case> plays = new ArrayList<>();
        for (int i = 0; i<n; i++) {
            plays.add(getRoulette().get(new Random().nextInt(37)));
        }
        return plays;
    }

    public class CaseComparator implements Comparator<Case> {

        @Override
        public int compare(Case o1, Case o2) {
            return o2.getNumber() - o1.getNumber();
        }
    }

    public List<Case> highToLow(List<Case> plays) {

        // EL MEJOR:
        // return plays.stream().sorted((o1, o2) -> o2.getNumber() - o1.getNumber()).collect(Collectors.toList());

        //trabajo con auxList para no modificar la lista plays
        List<Case> orderedPlays = new ArrayList<>(plays);
        /*
        //Version mas explicita posible....
        CaseComparator caseComparator = new CaseComparator();
        orderedPlays.sort(caseComparator);

        //Clase Anonima
        orderedPlays.sort(new Comparator<Case>() {
            @Override
            public int compare(Case o1, Case o2) {
                return o2.getNumber() - o1.getNumber();
            }
        });
        */

        orderedPlays.sort((o1, o2) -> o2.getNumber() - o1.getNumber());

        /*
        List<Case> orderedPlays = new ArrayList<>();
        for (int i = 0; i<plays.size(); i++) {
            //move the higher number from auxList to orderedPlays
            orderedPlays.add(getHigher(auxList));
            removeHigher(auxList);
        }

         */
        return orderedPlays;
    }


    public List<Case> lowToHigh(List<Case> plays) {
        //trabajo con auxList para no modificar la lista plays
        List<Case> auxList = new ArrayList<>();
        auxList.addAll(plays);
        List<Case> orderedPlays = new ArrayList<>();
        for (int i = 0; i<plays.size(); i++) {
            //move the lower number from 'auxList' to 'orderedPlays'
            orderedPlays.add(getLower(auxList));
            removeLower(auxList);
        }
        return orderedPlays;
    }


    public void removeHigher(List<Case> list1) {
        Case aux = new Case(0);
        int index = 0;
        for (int i = 0; i<list1.size(); i++) {
            if (list1.get(i).getNumber() > aux.getNumber()) {
                aux.setCase(list1.get(i).getNumber(), list1.get(i).getColor());
                index = i;
            }
        }
        list1.remove(index);
    }

    public void removeLower(List<Case> list1) {
        Case aux = new Case(37);
        int index = 0;
        for (int i = 0; i<list1.size(); i++) {
            if (list1.get(i).getNumber() < aux.getNumber()) {
                aux.setCase(list1.get(i).getNumber(), list1.get(i).getColor());
                index = i;
            }
        }
        list1.remove(index);
    }


    public Case getLower(List<Case> list1) {
        Case aux = new Case(37);
        for (int i = 0; i<list1.size(); i++) {
            if (list1.get(i).getNumber() < aux.getNumber()) {
                aux.setCase(list1.get(i).getNumber(), list1.get(i).getColor());
            }
        }
        return aux;
    }

    public Case getHigher(List<Case> list1) {
        Case aux = new Case(0);
        for (int i = 0; i<list1.size(); i++) {
            if (list1.get(i).getNumber() > aux.getNumber()) {
                aux.setCase(list1.get(i).getNumber(), list1.get(i).getColor());
            }
        }
        return aux;
    }

    public List<Case> getRoulette() {
        return roulette;
    }

    public int getOcurrences(List<Case> list, int n) {
        int counter = 0;
        for (Case i: list) {
            if (i.getNumber() == n) {
                counter++;
            }
        }
        return counter;
    }
}
