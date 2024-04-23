package temp; 

import org.apache.commons.lang3.Conversion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

class ConversionGeneratedTest0012 {

    @Test
    void testBinaryBeMsb0ToHexDigit() {
        assertEquals('8', Conversion.binaryBeMsb0ToHexDigit(new boolean[] { true, false, false, false }));
        assertEquals('4', Conversion.binaryBeMsb0ToHexDigit(new boolean[] { true, false, false, false, false, false, false, false, false, false, false, true, false, true, false, false }));
    }

    @Test
    void testBinaryToHexDigit() {
        assertEquals('1', Conversion.binaryToHexDigit(new boolean[] { true, false, false, false }));
        assertEquals('c', Conversion.binaryToHexDigit(new boolean[] { false, false, true, true }));
    }
    
    @Test
    void testBinaryToHexDigitMsb0_4bits() {
        assertEquals('1', Conversion.binaryToHexDigitMsb0_4bits(new boolean[] { false, false, false, true }));
        assertEquals('8', Conversion.binaryToHexDigitMsb0_4bits(new boolean[] { true, false, false, false }));
    }

    @Test
    void testBinaryToInt() {
        assertEquals(32, Conversion.binaryToInt(new boolean[] { false, false, false, false, false, true }, 0, 0, 0, 6));
    }

    @Test
    void testBinaryToShort() {
        assertEquals((short) 32, Conversion.binaryToShort(new boolean[] { false, false, false, false, false, true }, 0, (short) 0, 0, 6));
    }

    @Test
    void testByteArrayToInt() {
        assertEquals(1, Conversion.byteArrayToInt(new byte[] { 1, 0, 0, 0 }, 0, 0, 0, 4));
    }

    @Test
    void testByteArrayToLong() {
        assertEquals(1L, Conversion.byteArrayToLong(new byte[] { 1, 0, 0, 0, 0, 0, 0, 0 }, 0, 0L, 0, 8));
    }

    @Test
    void testByteArrayToShort() {
        assertEquals((short) 1, Conversion.byteArrayToShort(new byte[] { 1, 0 }, 0, (short) 0, 0, 2));
    }

}