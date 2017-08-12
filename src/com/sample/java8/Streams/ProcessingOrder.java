package com.sample.java8.Streams;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by anubandhans on 09/08/17.
 */
public class ProcessingOrder {

    public static void main (String[] arg) {

        // An important characteristic of intermediate operations is laziness.
        // Look at this sample where a terminal operation is missing.
        // --> Below method nothing is printed to the console.
        // That is because intermediate operations will only be executed when a terminal operation is present.

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                });

        //Let's extend the above example by the terminal operation forEach
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));
        /*The order of the result might be surprising.
        A naive approach would be to execute the operations horizontally one after another on all elements of the stream.
        But instead each element moves along the chain vertically. The first string "d2" passes filter then forEach,
        only then the second string "a2" is processed.*/


        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });

        // map:      d2
        // anyMatch: D2
        // map:      a2
        // anyMatch: A2 --> As soon as Any Match is reached loop ends. this will help reducing cycles.
                            // And also because of thie feature ordering matters.


        // ----->  ----> Order matters

        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));

            // map:     d2
            // filter:  D2
            // map:     a2
            // filter:  A2
            // forEach: A2   --> Is called only once, after it successfully executed filter.
            // map:     b1
            // filter:  B1
            // map:     b3
            // filter:  B3
            // map:     c
            // filter:  C


        /*
        All those primitive streams work just like regular object streams with the following differences:
        Primitive streams use specialized lambda expressions, e.g. IntFunction instead of Function or IntPredicate instead of Predicate.
        And primitive streams support the additional terminal aggregate operations sum() and average()
         */

        Arrays.stream(new int[] {1, 2, 3})
                .map(n -> 2 * n + 1)
                .average()
                .ifPresent(System.out::println);  // 5.0

        // IntStreams can replace the regular for-loop utilizing IntStream.range()

        IntStream.range(1, 4)
                .forEach(System.out::println);

        //Primitive streams can be transformed to object streams via mapToObj()
        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);


        // Reusing stream:

        // BELOW CODE WIL NOT EXECUTE. Java 8 streams cannot be reused. As soon as you call any terminal operation the stream is closed
       /* Stream<String> stream =
                Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        stream.anyMatch(s -> true);    // ok
        stream.noneMatch(s -> true);  */ // exception


        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> {System.out.println(s.startsWith("a"));return s.startsWith("a");});   // ok
        streamSupplier.get().noneMatch(s -> {System.out.println(s.startsWith("a")+" :: None");return s.startsWith("a");});  // ok


    }

}
