package lesson_e;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {
    public static final int CARS_COUNT = 20;
    public static final Semaphore SEMAPHORE_TUNNEL = new Semaphore(CARS_COUNT / 2,true);
    public static CyclicBarrier barrier=new CyclicBarrier(CARS_COUNT+1);
    public static volatile int WIN_COUNT=3;
    public static volatile int WIN=0;

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        Object monitor=new Object();
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(monitor,barrier,race, 20 + (int) (Math.random() * 10));
        }
        for (Car car : cars) {
            new Thread(car).start();
        }
        barrier.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        barrier.await();
        barrier.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}





