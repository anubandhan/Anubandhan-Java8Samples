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


import java.util.HashMap;
import java.util.Map;

/**
 *As already mentioned maps don't support streams.
 * Instead maps now support various new and useful methods for doing common tasks.
 *
 */

public class NewMap {


    public static void main (String[] arg) {


        // The above code should be self-explaining: putIfAbsent prevents us from writing additional if null checks;
        // forEach accepts a consumer to perform operations for each value of the map.
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }
        map.forEach((id, val) -> System.out.println(val)); // BiConsumer

        map.computeIfPresent(3, (num, val) -> val + num); // Check Code of this Method, Let some one explain.
        map.get(3);             // val33

        map.computeIfPresent(9, (num, val) -> null); // This is internally using a Bi function 2 inputs, 1 output
        map.containsKey(9);     // false

        map.computeIfAbsent(23, num -> "val" + num);
        map.containsKey(23);    // true

        map.computeIfAbsent(3, num -> "bam");
        map.get(3);

        // Next, we learn how to remove entries for a a given key, only if it's currently mapped to a given value
        map.remove(3, "val3");
        map.get(3);             // val33

        map.remove(3, "val33");
        map.get(3);             // null

        //OR
        map.getOrDefault(42, "not found");  // not found

        // Merging entries of a map is quite easy
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val9

        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val9concat

    }

}
