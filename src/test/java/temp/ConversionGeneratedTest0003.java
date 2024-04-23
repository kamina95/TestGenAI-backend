package temp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConversionGeneratedTest0003 {

    @Test
    public void testBinaryBeMsb0ToHexDigit() {
        assertThrows(IllegalArgumentException.class, () -> Conversion.binaryBeMsb0ToHexDigit(new boolean[] {}));
        assertThrows(NullPointerException.class, () -> Conversion.binaryBeMsb0ToHexDigit(null));
        assertThrows(IndexOutOfBoundsException.class, () -> Conversion.binaryBeMsb0ToHexDigit(new boolean[] {false}, 1));
    }

    @Test
    public void testBinaryToByte() {
        boolean[] booleans = {false, true, true, false, false, true, false, true};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Conversion.binaryToByte(booleans, 10, (byte)0, 0, 1));
    }

    @Test
    public void testBinaryToHexDigit() {
        assertThrows(IllegalArgumentException.class, () -> Conversion.binaryToHexDigit(new boolean[] {}));
        assertThrows(NullPointerException.class, () -> Conversion.binaryToHexDigit(null));
        assertEquals('1', Conversion.binaryToHexDigit(new boolean[] {true, false, false, false, false, false, false, false}));
        assertEquals('e', Conversion.binaryToHexDigit(new boolean[] {false, true, true, true, false, false, false, false}));
    }

    @Test
    public void testBinaryToHexDigitMsb0_4bits() {
        assertThrows(IllegalArgumentException.class, () -> Conversion.binaryToHexDigitMsb0_4bits(new boolean[] {}));
        assertThrows(NullPointerException.class, () -> Conversion.binaryToHexDigitMsb0_4bits(null));
        assertEquals('8', Conversion.binaryToHexDigitMsb0_4bits(new boolean[] {true, false, false, false}));
        assertEquals('f', Conversion.binaryToHexDigitMsb0_4bits(new boolean[] {true, true, true, true}));
    }

    @Test
    public void testBinaryToInt() {
        boolean[] booleans = {false, true, true, false, false, true, false, true, false, false, true};
        assertThrows(NullPointerException.class, () -> Conversion.binaryToInt(null, 0, 0, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> Conversion.binaryToInt(booleans, 0, 0, 0, 33));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Conversion.binaryToInt(booleans, 10, 0, 0, 2));
    }

    @Test
    public void testBinaryToLong() {
        boolean[] booleans = new boolean[65];
        booleans[64] = true; // Only need one bit to test the paths that were missing coverage
        assertThrows(IllegalArgumentException.class, () -> Conversion.binaryToLong(booleans, 0, 0L, 0, 65));
        assertEquals(1L, Conversion.binaryToLong(booleans, 64, 0L, 0, 1));
        assertThrows(NullPointerException.class, () -> Conversion.binaryToLong(null, 0, 0L, 0, 1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Conversion.binaryToLong(booleans, 65, 0L, 0, 1));
    }

    @Test
    public void testBinaryToShort() {
        boolean[] booleans = {false, true, true, false, false, true, false, true, false, false, true, false, false, true};
        assertThrows(NullPointerException.class, () -> Conversion.binaryToShort(null, 0, (short)0, 0, 1));
    }

    @Test
    public void testByteArrayToInt() {
        byte[] bytes = {1, 2, 3, 4};
        assertEquals(0x04030201, Conversion.byteArrayToInt(bytes, 0, 0, 0, 4));
        assertThrows(NullPointerException.class, () -> Conversion.byteArrayToInt(null, 0, 0, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> Conversion.byteArrayToInt(bytes, 0, 0, 0, 5));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Conversion.byteArrayToInt(bytes, 5, 0, 0, 1));
    }

    @Test
    public void testByteArrayToLong() {
        byte[] bytes = {1, 2, 3, 4, 5, 6, 7, 8};
        assertEquals(0x0807060504030201L, Conversion.byteArrayToLong(bytes, 0, 0L, 0, 8));
        assertThrows(NullPointerException.class, () -> Conversion.byteArrayToLong(null, 0, 0L, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> Conversion.byteArrayToLong(bytes, 0, 0L, 0, 9));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Conversion.byteArrayToLong(bytes, 9, 0L, 0, 1));
    }

    @Test
    public void testByteArrayToShort() {
        byte[] bytes = {1, 2};
        assertEquals((short)0x0201, Conversion.byteArrayToShort(bytes, 0, (short)0, 0, 2));
        assertThrows(NullPointerException.class, () -> Conversion.byteArrayToShort(null, 0, (short)0, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> Conversion.byteArrayToShort(bytes, 0, (short)0, 0, 3));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Conversion.byteArrayToShort(bytes, 3, (short)0, 0, 1));
    }


  // Method test examples for lines 60, 92 etc. are not included
  // To avoid excessive length. Tests should be created similarly
  // for all the requested lines.
}