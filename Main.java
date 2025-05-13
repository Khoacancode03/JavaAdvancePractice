import JavaAdvance.impl.RunCodeFI;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

    //1. Custom Functional Interface
//        RunCodeFI func = () -> {
//            for (int i = 0; i < 5; i++) {
//                System.out.println("Working at index i = " +i);
//            }
//            System.out.println("Hello World");
//        };
//        func.run();

        //2. Predicate
        List<String> names = Arrays.asList("John Doe", "Jane Doe", "Joe");
        filterAndPrint(names, name -> name.length() > 3);

        //3. Runnable
        Runnable runnable = () -> {
            System.out.println("Hello World");
        };
        new Thread(runnable).start();

        //4. Supplier<T>: Supply data
        //Example: get random name
        Supplier<String> randomName = () -> {
            Random random = new Random();
            return names.get(random.nextInt(names.size()));
        };
        System.out.println("Random name: "+randomName.get());

        //5, Consumer<T>: Process Data
        //Example: Print user with hello + name
        Consumer<String> customCustomer = name -> System.out.println("Hello "+name);
        customCustomer.accept("Khoa");

        //6. Function<T,R>: change data types
        Function<String, Integer> convert = name -> name.length();
        System.out.println(convert.apply("Khoa"));

        //7. Predicate<T> : check condition
        Predicate<Integer> isOdd = number -> number % 2 != 0;
        int numberToTest = 4;
        if (isOdd.test(numberToTest)) {
            System.out.println("Number is odd");
        }else{
            System.out.println("Number is not odd");
        }


        //Practice:
        //P1. Thread: wait 3 seconds then print
        Runnable printer = () -> {
            try {
                Thread.sleep(3000);
                System.out.println("Printer thread finished after 3 seconds");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        new Thread(printer).start();

        //P2. Generate random OTP with 6 digits
        Supplier<String> randomOTP = () -> {
            Random random = new Random();
            int code = 100000 + random.nextInt(100000);
            return String.valueOf(code);
        };
        System.out.println("Random OTP 1: "+randomOTP.get());
        System.out.println("Random OTP 2: "+randomOTP.get());
        System.out.println("Random OTP 3: "+randomOTP.get());

    }

    //2
    static void filterAndPrint(List<String> list, Predicate<String> condition) {
        for (String str : list) {
            if (condition.test(str)) {
                System.out.println(str);
            }
        }
    }

}