/*
 * Copyright 2018, Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.SimpleCalc;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit4 unit tests for the calculator logic. These are local unit tests; no device needed
 */
@RunWith(JUnit4.class)
@SmallTest
public class CalculatorTest {

    private Calculator mCalculator;

    /**
     * Set up the environment for testing
     */
    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    /**
     * Test for simple addition
     */
    @Test
    public void addTwoNumbers() {
        double resultAdd = mCalculator.add(1d, 1d);
        assertThat(resultAdd, is(equalTo(2d)));
    }

    @Test
    public void powTwoPositiveInt() {
        int resultPow = (int) mCalculator.pow(2, 3);
        assertThat(resultPow, is(equalTo(8)));
    }

    @Test
    public void powNegativePositive() {
        int resultPow = (int) mCalculator.pow(-2, 3);
        assertThat(resultPow, is(equalTo(-8)));
    }

    @Test
    public void powPositiveNegative() {
        double resultPow = mCalculator.pow(2, -3);
        assertThat(resultPow, is(equalTo(0.125)));
    }

    @Test
    public void powZeroPositive() {
        int resultPow = (int) mCalculator.pow(0,3);
        assertThat(resultPow, is(equalTo(0)));
    }

    @Test
    public void powPositiveZero() {
        double powResult = mCalculator.pow(3, 0);
        assertThat(powResult, is(equalTo(1d)));
    }

    @Test
    public void powZeroMinusOne() {
        double powResult = mCalculator.pow(0, -1);
        assertThat(powResult, is(equalTo(Double.POSITIVE_INFINITY)));
    }

    // If you try this calculation in the app, the result is -Infinity
    @Test
    public void powMinusZeroNegativeOdd() {
        double powResult = mCalculator.pow(-0, -3);
        // assertThat(powResult, is(equalTo(Double.NEGATIVE_INFINITY)));
        assertThat(powResult, is(equalTo(Double.POSITIVE_INFINITY)));
    }

    @Test
    public void powMinusZeroNegativeEven(){
        double powResult = mCalculator.pow(-0, -4);
        assertThat(powResult, is(equalTo(Double.POSITIVE_INFINITY)));
    }
}