import JavaAdvance.impl.RunCodeFI;
import JavaAdvance.models.Student;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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

        //P3: Find length of each String in a String Array
        Consumer<String> lengthProcessor = name -> System.out.println("Length of "+ name + " : " + name.length());
        for (String name : names) {
            lengthProcessor.accept(name);
        }

        //P4: Check if String is all character in alphabet
        List<String> testList = Arrays.asList("Test1FailString","TestPassString","Special@@@");
        Predicate<String> isAllValidCharacters = name -> name.matches("[a-zA-Z]+");
        for (String name : testList) {
            if (isAllValidCharacters.test(name)) {
                System.out.println(name  +" is valid");
            }else{
                System.out.println(name  +" is not valid");
            }
        }


        //Stream API
        //1. Filter by name start with "K"
        List<String> listName = Arrays.asList("Khoa", "Khoa1", "Khoa2","Na","Ba","Ca");
        List<String> result = listName.stream()
                            .filter(name -> name.startsWith("K"))
                            .collect(Collectors.toList());
        System.out.println(result);

        //2. Sum of even number
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sum = numbers.stream()
                .filter(number -> number % 2==0)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(sum);

        //3. Print names that has length > 4 chars
        List<String> validString = listName.stream()
                .filter(name -> name.length() >= 4)
                .collect(Collectors.toList());
        System.out.println("Valid Strings are: ");
        System.out.println(validString);

        //4. Find max number
        Random rand = new Random();
        List<Integer> listNumber = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listNumber.add(rand.nextInt(100));
        }
        System.out.println("List random numbers: "+listNumber);
        Optional<Integer> maxNumber = listNumber.stream()
                .max(Integer::compareTo);
        maxNumber.ifPresent(System.out::println);

        //5. Count sequence of each char in String
        List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");
        Map<String,Integer> map = new HashMap<>();
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            }else{
                map.put(word, 1);
            }
        }
        System.out.println("Normal way: "+map);
        //Stream API
        Map<String, Long> resultMap = words.stream()
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()
                ));
        System.out.println("Stream API way: "+resultMap);

        //6. Uppercase String
        List<String> uppercaseResult = words.stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println("Uppercase: "+uppercaseResult);

        //7. Sum of number that can divide for 3 and 5
        List<Integer> numbers2 = Arrays.asList(15, 45, 3, 4, 5, 6, 7, 8, 9, 10);
        int requireSum = numbers2.stream()
                .filter(number -> (number % 3 == 0 && number % 5 ==0))
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum of divide by 3 and 5: "+requireSum);

        //8. List of students that score > 8
        Student student1 = new Student(1, "vip smith", 5.6,"Class 1");
        Student student2 = new Student(2, "john pro", 8.5,"Class 1");
        Student student3 = new Student(3, "bob super max", 9.0,"Class 2");
        Student student4 = new Student(4, "jones so stupid", 7.0,"Class 2");

        List<Student> students = Arrays.asList(student1, student2, student3, student4);
        List<Student> goodStudents = students.stream()
                .filter(student -> student.getScore() >= 8.0)
                .toList();
        System.out.println("Good students: "+goodStudents);

        //9. Merge string
        String mergeResult = String.join(",", words);
        System.out.println("Merge result: "+mergeResult);

        //10. Max score student finding
        Optional<Student> maxScoreStudent = students.stream()
                .max(Comparator.comparing(Student::getScore));
        maxScoreStudent.ifPresent(System.out::println);

        //11. Order by score
        List<Student> orderedStudent = students.stream()
                .filter(student -> student.getScore() >= 7.0)
                .sorted(Comparator.comparing(Student::getScore).reversed())
                .toList();
        System.out.println("Ordered students: "+orderedStudent);

        //12. Group by class
        Map<String,List<Student>> groupedStudent = students.stream()
                .collect(Collectors.groupingBy(Student::getClassName));
        System.out.println("Grouped students: "+groupedStudent);

        //13. Sum of score
        double sumOfScore = students.stream()
                .mapToDouble(Student::getScore)
                .sum();
        System.out.println("Sum of score of students: "+sumOfScore);

        //14. Filter to get good student, change name to uppercase and into new list
        List<Student> resultStudent = students.stream()
                .filter(student -> student.getScore() >= 8)
                .map(student -> new Student(
                        student.getId(),
                        toUppercaseName(student.getName()),
                        student.getScore(),
                        student.getClassName()
                ))
                .toList();
        System.out.println("Results students: "+resultStudent);
    }

    private static String toUppercaseName(String name) {
        if (name.isEmpty()){
            return name;
        }
        return Arrays.stream(name.split(" "))
                .map(word -> word.isEmpty()? word:
                        Character.toUpperCase(word.charAt(0))+word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
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