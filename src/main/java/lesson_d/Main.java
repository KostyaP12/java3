package lesson_d;

public class Main {
    private final Object monitor = new Object();
    static int i = 5;
    private volatile char currentLetter = 'A';

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        // задание 1
        Thread t1 = new Thread(() -> main.printChar('A', 'B'));
        Thread t2 = new Thread(() -> main.printChar('B', 'C'));
        Thread t3 = new Thread(() -> main.printChar('C', 'A'));

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        // задание 2
        Mfu mfu = new Mfu();
        Thread t1mfu = new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print("Человек 1");
            }
        });
        Thread t2mfu = new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.scan("Человек 2");
            }
        });
        Thread t3mfu = new Thread(new Runnable() {
            @Override
            public void run() {mfu.copyPrint("Человек 3");
            }
        });
        t1mfu.start();
        t2mfu.start();
        t3mfu.start();
        t1mfu.join();
        t2mfu.join();
        t3mfu.join();


    }


    public void printChar(char letter, char letter2){
        synchronized (monitor) {
            try {
                for (int y = 1; y <= i; y++) {
                    while (currentLetter != letter) {
                        monitor.wait();
                    }
                    System.out.println(letter);
                    currentLetter = letter2;
                    monitor.notifyAll();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

