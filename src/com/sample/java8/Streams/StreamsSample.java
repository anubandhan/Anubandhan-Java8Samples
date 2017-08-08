package com.sample.java8.Streams;

/**
 * The MIT License
 * Copyright (c) 2014-2017 Anubandhan Singh Sengar
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 *
 * A java.util.Stream represents a sequence of elements on which one or more operations can be performed.
 * Stream operations are either intermediate or terminal.
 * While terminal operations return a result of a certain type,
 * intermediate operations return the stream itself so you can chain multiple method calls in a row.
 * Streams are created on a source, e.g. a java.util.Collection like lists or sets (maps are not supported).
 * Stream operations can either be executed sequential or parallel.
 *
 */
public class StreamsSample {


    public static void main (String[] arg) {

        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");


        //Filter
        System.out.println("Filter ::");
        // Filter accepts a predicate to filter all elements of the stream.
        // This operation is intermediate which enables us to call another stream operation (forEach) on the result.
        // ForEach accepts a consumer to be executed for each element in the filtered stream.
        // ForEach is a terminal operation. It's void, so we cannot call another stream operation.

        stringCollection
                .stream()
                .filter((s) -> s.startsWith("a")) // intermediate operation
                .forEach(System.out::println); // terminal operation, we can nt add any thing after this.

        //Sorted
        System.out.println("Sorted ::");
        //Sorted is an intermediate operation which returns a sorted view of the stream.
        // The elements are sorted in natural order unless you pass a custom Comparator.

        stringCollection
                .stream()
                .sorted() // --> THis is an  intermediate operation and does not touch existing collection.
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println); // This has automatically

        // Keep in mind that sorted does only create a sorted view of the stream without manipulating the ordering of the backed collection.
        // The ordering of stringCollection is untouched:
        // System.out.println(stringCollection);


        // Map
        System.out.println("Map ::");
        // The intermediate operation map converts each element into another object via the given function.
        // The following example converts each string into an upper-cased string.
        // But you can also use map to transform each object into another type.
        // The generic type of the resulting stream depends on the generic type of the function you pass to map.

        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println);

        // Match
        System.out.println("Match ::");
        // Various matching operations can be used to check whether a certain predicate matches the stream.
        // All of those operations are terminal and return a boolean result.
        boolean anyStartsWithA =
                stringCollection
                        .stream()
                        .anyMatch((s) -> s.startsWith("a")); // As anyMatch function is expecting a Predicate,
                                                            // The statement inside anyMatch automatically created one.

        System.out.println(anyStartsWithA);      // true

        boolean allStartsWithA =
                stringCollection
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA);      // false

        boolean noneStartsWithZ =
                stringCollection
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));

        System.out.println(noneStartsWithZ);      // true


        // Count
        System.out.println("Count ::");
        // Count is a terminal operation returning the number of elements in the stream as a long.
        long startsWithB =
                stringCollection
                        .stream()
                        .filter((s) -> s.startsWith("b"))
                        .count();

        System.out.println(startsWithB);


        // Reduce
        System.out.println("Reduce ::");
        //This terminal operation performs a reduction on the elements of the stream with the given function.
        // The result is an Optional holding the reduced value.
        Optional<String> reduced =
                stringCollection
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println);
        // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"




    }
}
