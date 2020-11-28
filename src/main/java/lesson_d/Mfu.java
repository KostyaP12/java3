package lesson_d;

public class Mfu {
    private final Object monitorPrint = new Object();
    private final Object monitorScan = new Object();

    public void print(String text) {
        synchronized (monitorPrint) {
            try {
                System.out.println("Print " + text);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void scan(String text) {
        synchronized (monitorScan) {
            try {
                System.out.println("Scann " + text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void copyPrint(String text) {
        synchronized (monitorScan) {
            synchronized (monitorPrint) {
                try {
                    System.out.println("copyPrint" + text);
                    scan(text);
                    print(text);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

