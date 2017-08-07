package com.sample.java8.interfacesandclasses;

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

import com.sample.java8.lamda.Converter;

/**
 *
 * Java 8 enables you to pass references of methods or constructors via the :: keyword.
 * The below example shows how to reference a static method. But we can also reference object methods:
 *
 */
public class MethodAndConstructorReferences {


    static class Something {
        String startsWith(String s) {
            return String.valueOf(s.charAt(0));
        }
    }


    static class Person {
        String firstName;
        String lastName;

        Person() {}

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }
    @FunctionalInterface
    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName); // :: Step 1
       // P create(); //--> multiple overriding method not supported in FunctionalInterface. :: Step 2
    }

    public static void main (String[] arg) {

        // Test 1 : Method Reference
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted);   // 123

        // Test 2 : Method Reference
        Something something = new Something();
        Converter<String, String> converter1 = something::startsWith;
        String converted1 = converter1.convert("Java");
        System.out.println(converted1);

        // Test 3 : Constructor Reference
        // It has mapped implementation of create method with new Person class with variables as constructor.
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person);

        // Test 4 : Constructor Reference, In case above Step 2 is enabled and Step 1 is disabled
        // It has mapped implementation of create method with new Person class without variables as constructor.
        /*PersonFactory<Person> personFactory1 = Person::new;
        Person person1 = personFactory1.create();
        System.out.println(person1);*/

    }


}


