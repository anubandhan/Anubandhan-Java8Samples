package com.sample.java8.lamda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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


public class Sorting {

    // Old version of sorting:


    public static void main (String[] arg){

        // Old version of sorting:
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
/*
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        System.out.println("Old way: ");
        System.out.println(names);*/

        names = Arrays.asList("peter", "anna", "mike", "xenia");

        //lambda expressions:
        // The static utility method Collections.sort accepts a list
        // and a comparator in order to sort the elements of the given list.
        // You often find yourself creating anonymous comparators and pass them to the sort method.
        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });

        System.out.println("New way: ");
        System.out.println(names);

        //OR
        names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names,(String a, String b) -> b.compareTo(a));
        System.out.println("New way 2: ");
        System.out.println(names);


    }


}
