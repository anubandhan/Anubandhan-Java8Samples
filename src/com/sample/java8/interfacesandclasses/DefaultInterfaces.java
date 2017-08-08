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

/**
 * Java 8 enables us to add non-abstract method implementations to interfaces by utilizing the default keyword.
 * This feature is also known as Extension Methods. Here is our first example:
 */
public class DefaultInterfaces {

    // This is similar to abstract method.
    interface Formula {
        double calculate(int a);

        default public double sqrt(int a) {
            return Math.sqrt(a);
        }
    }

public static void main (String[] arg){

    //Besides the abstract method calculate the interface Formula also defines the default method sqrt.
    // Concrete classes only have to implement the abstract method calculate.
    // The default method sqrt can be used out of the box.
    Formula formula = new Formula() {
        @Override
        public double calculate(int a) {
            return sqrt(a * 100);
        }
        @Override
        public double sqrt(int a) {
            return Math.abs(a);
        }
    };
    System.out.println(formula.calculate(100)); // 100.0
    System.out.println(formula.sqrt(16)); // 4.0
}


}
