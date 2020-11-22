package lesson_a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Generalizations<T> {
    private T[]arr;

    public Generalizations(T[] arr) {
        this.arr = arr;
    }

    public T[] getArr() {
        return arr;
    }

    public void setArr(T[] arr) {
        this.arr = arr;
    }

    //Задание 1.
    public void changeElementsInArray(T[]arr, int index1, int index2){
        T buff = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = buff;
    }
    //Задание 2.
    public void arrayToArrayList(T[]arr){
        ArrayList<T>arrList = new ArrayList<>();
        Collections.addAll(arrList,arr);
    }
}
