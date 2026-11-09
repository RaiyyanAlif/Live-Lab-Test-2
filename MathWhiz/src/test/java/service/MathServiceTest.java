package service;

import model.MathModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathServiceTest {

    // ---- Prime
    @Test void primeSeven()        { assertEquals(1, MathService.isPrime(7)); }
    @Test void primeEight()        { assertEquals(0, MathService.isPrime(8)); }
    @Test void primeOne()          { assertEquals(0, MathService.isPrime(1)); }
    @Test void primeZeroNegative() {
        assertEquals(0, MathService.isPrime(0));
        assertEquals(0, MathService.isPrime(-7));
    }
    @Test void primeSmallAndLarge() {
        assertEquals(1, MathService.isPrime(2));
        assertEquals(1, MathService.isPrime(3));
        assertEquals(0, MathService.isPrime(9));
        assertEquals(1, MathService.isPrime(97));
        assertEquals(1, MathService.isPrime(2147483647));   // largest int prime
    }

    // ---- Modulus
    @Test void modSeventeenFive()  { assertEquals(2, MathService.calculateMod(17, 5)); }
    @Test void modNegatives() {
        assertEquals(-2, MathService.calculateMod(-17, 5));
        assertEquals(2, MathService.calculateMod(17, -5));
        assertEquals(0, MathService.calculateMod(0, 5));
    }
    @Test void modZeroDivisor() {
        assertThrows(IllegalArgumentException.class, () -> MathService.calculateMod(10, 0));
    }

    // ---- Trigonometry
    @Test void sin30()  { assertEquals(0.5, MathService.calculateSin(30), 1e-9); }
    @Test void cos60()  { assertEquals(0.5, MathService.calculateCos(60), 1e-9); }
    @Test void tan45()  { assertEquals(1.0, MathService.calculateTan(45), 1e-9); }
    @Test void tanUndefined() {
        assertThrows(ArithmeticException.class, () -> MathService.calculateTan(90));
        assertThrows(ArithmeticException.class, () -> MathService.calculateTan(270));
        assertThrows(ArithmeticException.class, () -> MathService.calculateTan(-90));
    }
    @Test void trigDispatcher() {
        assertEquals(0.5, MathService.calculateTrig("SIN", 30), 1e-9);
        assertThrows(IllegalArgumentException.class, () -> MathService.calculateTrig("sec", 30));
    }

    // ---- Tolerance
    @Test void toleranceCheck() {
        assertTrue(MathService.isClose(0.8660254, 0.87));
        assertTrue(MathService.isClose(0.5, 0.5));
        assertFalse(MathService.isClose(0.5, 0.6));
    }

    // ---- Marks
    @Test void marksAddUpToTen() { assertEquals(10, MathModule.totalMarks()); }

    @Test void plainFormatting() {
        assertEquals("30", MathService.plain(30.0));
        assertEquals("0.5", MathService.plain(0.5));
        assertEquals("0", MathService.plain(0.0));
    }
}
