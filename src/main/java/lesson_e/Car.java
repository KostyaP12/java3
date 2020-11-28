package lesson_e;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Car implements Runnable {
    private static int CARS_COUNT;
    private CyclicBarrier barrier;
    private Object monitor;
    private Race race;
    private int speed;
    private String name;

    static {
        CARS_COUNT = 0;
    }


    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Object monitor, CyclicBarrier barrier, Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.barrier = barrier;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            barrier.await();
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }


        synchronized (monitor) {
            if (Main.WIN_COUNT > 0) {
                System.out.println(this.name + " WIN " + " занял " + ++Main.WIN + " место.");
                Main.WIN_COUNT--;
            }
        }
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}
