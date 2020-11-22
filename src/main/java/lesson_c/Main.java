package lesson_c;

import java.io.*;
import java.util.*;

public class Main {
    public static String FILES_DIR = "FilesTask3/";
    public static int BUFF = 1800;
    private static int numberFiles = 5;
    private static ArrayList<InputStream> ali = new ArrayList<>();

    public static void main(String[] args) {

        String fileName = FILES_DIR + "text50b.txt";
        String text = "Hello World! Hello World! Hello World! Hello World!";
        createFile(fileName, text);
        readFile(fileName);
        createFiveFiles(text, numberFiles);
        String fileNameNew = "FILES_DIR + text6.txt"; // файл склеяный
        sequenceFiles(fileNameNew);
        readFile(fileNameNew);
        String book = FILES_DIR + "book.txt"; // файл более 10Мб
        readPage(book);
    }

    public static void createFile(String fileName, String text) {
        OutputStream outputStream = null;
        delete(fileName);
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(fileName), text.length());
            outputStream.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) outputStream.close();
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии" + e.getMessage());
            }
        }

    }

    private static void delete(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists()) file.delete(); //удалить файл, если есть
        } catch (Exception e) {
            System.out.println("Ошибка при удалении файла! " + fileName + e.getMessage());
        }
    }

    public static void readFile(String fileName) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            byte[] arr = new byte[(int) new File(fileName).length()];
            int i;
            while ((i = fileInputStream.read(arr)) > 0) {
                System.out.println(Arrays.toString(arr));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) fileInputStream.close();
            } catch (Exception e) {
                System.out.println("Ошибка при зкарытии" + e.getMessage());
            }

        }
    }

    private static void createFiveFiles(String text, int numberFiles) {
        OutputStream outputStream = null;
        try {
            String textOverOneHundredBytes = text.concat(text);
            for (int i = 1; i <= numberFiles; i++) {
                String file = FILES_DIR + "text" + i;
                delete(file);
                outputStream = new BufferedOutputStream(new FileOutputStream(file), textOverOneHundredBytes.length());
                outputStream.write(textOverOneHundredBytes.getBytes());
                ali.add(new FileInputStream(file));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) outputStream.close();
            } catch (Exception e) {
                System.out.println("Ошибка при закрытии" + e.getMessage());
            }
        }
    }

    private static void sequenceFiles(String fileNameNew) {
        SequenceInputStream sequenceInputStream = null;
        OutputStream outputStream = null;
        try {
            Enumeration<InputStream> enumeration = Collections.enumeration(ali);
            sequenceInputStream = new SequenceInputStream(enumeration);
            outputStream = new FileOutputStream(fileNameNew);
            int i;
            byte[] arr = new byte[BUFF];
            while ((i = sequenceInputStream.read(arr)) > 0) {
                outputStream.write(arr, 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (sequenceInputStream != null) sequenceInputStream.close();
                if (outputStream != null) outputStream.close();
            } catch (Exception e) {
                System.out.println("Ошибка при закрытии" + e.getMessage());
            }
        }


    }

    private static void readPage(String fileName) {
        int PAGE_SIZE = 1800;
        int page = 0; // выбранная страница
        int maxPage = (int) new File(fileName).length() / PAGE_SIZE;
        Scanner scanner = new Scanner(System.in);
        RandomAccessFile randomAccessFile = null;
        byte[] buff = new byte[PAGE_SIZE];
        try {
            randomAccessFile = new RandomAccessFile(fileName, "r");
            do {
                System.out.println("Введите номер старницы");
                page = scanner.nextInt();
            }
            while (page < 0 && page < maxPage);
            randomAccessFile.seek(page * PAGE_SIZE);
            randomAccessFile.read(buff);
            System.out.println(new String(buff));

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(randomAccessFile != null)randomAccessFile.close();
            }catch (Exception e){
                System.out.println("Ошибка при закрытии " + e.getMessage());
            }
        }
    }
}

