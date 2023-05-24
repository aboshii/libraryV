package com.library.springlibrary.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VisitCounterTest {

    private VisitCounter visitCounter;

    @BeforeEach
    void setUp() {
        visitCounter = new VisitCounter();
    }

    @Test
    void shouldIncrementVisitCounter() {
        //given

        //when
        visitCounter.visitCounterCountUp();

        //then
        assertEquals(visitCounter.getVisitCount(), 1);
    }
}