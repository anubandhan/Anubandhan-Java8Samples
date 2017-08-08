package com.sample.java8.interfacesandclasses;

import com.sample.java8.interfacesandclasses.MethodAndConstructorReferences.Person;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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

public class JavaInbuiltFunctionalInterfaces {

    public static void main (String[] arg) {

        // Predicates
        // Predicates are boolean-valued functions of one argument.
        // The interface contains various default methods for composing predicates to complex logical terms
        // (and, or, negate)
        Predicate<String> predicate = (s) -> s.length() > 0;

        System.out.println(predicate.test("foo"));              // true
        System.out.println(predicate.negate().test("foo"));     // false

        // Note conditions are defined below.
        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();



        // Functions accept one argument and produce a result.
        // Default methods can be used to chain multiple functions together (compose, andThen).
        // <Input, Return Type>
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        System.out.println(backToString.apply("123"));

        // Suppliers
        // Suppliers produce a result of a given generic type.
        // Unlike Functions, Suppliers don't accept arguments.
        // <Only Result>
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();   // new Person
        System.out.println(personSupplier.get());


        // Consumers
        // Consumers represents operations to be performed on a single input argument.
        // <Only Input>
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));



        // Comparators
        // Comparators are well known from older versions of Java.
        // Java 8 adds various default methods to the interface.
        // This is becaue its a Functional Interface.
        Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");

        comparator.compare(p1, p2);             // > 0
        comparator.reversed().compare(p1, p2);  // < 0


        // Optionals
        // Optionals are not functional interfaces, instead it's a nifty utility to prevent NullPointerException.
        Optional<String> optional = Optional.of("bam");

        optional.isPresent();           // true
        optional.get();                 // "bam"
        optional.orElse("fallback");    // "bam"

        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
        System.out.println(optional.isPresent());
        System.out.println(optional.get());
        System.out.println(optional.orElse("fallback"));



        // Runnables
        Runnable runnable = () -> System.out.println(UUID.randomUUID());
        runnable.run();


        // Callables

        Callable<UUID> callable = UUID::randomUUID;
        try {
            callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
