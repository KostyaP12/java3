package lesson_a;

import java.util.ArrayList;
import java.util.Collections;

public class Box<T extends Fruit> {
    private ArrayList<T> box;
    private float weight;

    public Box(ArrayList<T> box) {
        this.box = box;
    }

    public Box() {
    }

    public ArrayList<T> getBox() {
        return box;
    }

    public void setBox(ArrayList<T> box) {
        this.box = box;
    }

    public float getWeight(Object a) {
        getWeights(a);
        return weight;
    }

    public void getWeights(Object a) {
        if (a instanceof Apple) {
            weight = box.size() * ((Apple) a).getWeight();
        } else if (a instanceof Orange) {
            weight = box.size() * ((Orange) a).getWeight();
        }
    }

    public boolean compare(Box<?> another) {
        return (this.weight == another.weight);
    }

    public Box pouring(Box<T> newBox) {
        newBox.box = new ArrayList<>(this.box);
        this.box.clear();
        return newBox;
    }

    public void addFruitToTheBox(T fruit) {
        this.box.add(fruit);
    }
}

