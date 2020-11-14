package lesson_a;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Integer[]arr = {0, 15, 20};
        Generalizations<Integer> generalizations = new Generalizations<>(arr);
        generalizations.changeElementsInArray(arr, 1, 2);
        generalizations.arrayToArrayList(arr);

        ArrayList<Apple> apples = new ArrayList<>();
        for (int i = 0; i <= 11; i++){
            apples.add(new Apple());
        }
        ArrayList<Orange>oranges = new ArrayList<>();
        for (int i = 0; i <= 1; i++){
            oranges.add(new Orange());
        }

        Box<Apple> appleBox = new Box<>(apples);
        System.out.println(appleBox.getWeight(new Apple()));

        Box<Orange> orangeBox = new Box<>(oranges);
        System.out.println(orangeBox.getWeight(new Orange()));

        System.out.println(appleBox.compare(orangeBox));

        Box appleBox2 = appleBox.pouring(new Box<>());

        orangeBox.addFruitToTheBox(new Orange());
        System.out.println(orangeBox.getBox());
    }
}
