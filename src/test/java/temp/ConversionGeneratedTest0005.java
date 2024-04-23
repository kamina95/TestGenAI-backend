package temp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConversionGeneratedTest0005 {

    @Test
    void testBinaryBeMsb0ToHexDigitEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> Conversion.binaryBeMsb0ToHexDigit(new boolean[0]));
    }

    @Test
    void testBinaryBeMsb0ToHexDigitOutOfBound() {
        assertThrows(IndexOutOfBoundsException.class, () -> Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, false, true}, 4));
    }

    @Test
    void testBinaryBeMsb0ToHexDigit() {
        assertEquals('1', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{false, false, false, true}));
        assertEquals('f', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, true, true, true}));
    }

    @Test
    void testBinaryBeMsb0ToHexDigitWithSrcPos() {
        assertEquals('e', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, true, true, false, true}, 1));
        assertEquals('8', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, false, false, false, true}, 1));
    }

    @Test
    void testBinaryToByteEmptyArray() {
        assertEquals((byte) 0, Conversion.binaryToByte(new boolean[0], 0, (byte) 0, 0, 0));
    }

    @Test
    void testBinaryToByte() {
        assertEquals((byte) 1, Conversion.binaryToByte(new boolean[]{true}, 0, (byte) 0, 0, 1));
        assertEquals((byte) 128, Conversion.binaryToByte(new boolean[]{true}, 0, (byte) 0, 7, 1));
    }

    @Test
    void testBinaryToByteInvalidParameters() {
        boolean[] booleans = new boolean[]{false, true, false, true, true, false};
        assertThrows(IllegalArgumentException.class,
                () -> Conversion.binaryToByte(booleans, 0, (byte) 0, 0, 9));
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Conversion.binaryToByte(booleans, 10, (byte) 0, 0, 1));
    }

    @Test
    void testBinaryToHexDigitEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> Conversion.binaryToHexDigit(new boolean[0]));
    }

    @Test
    void testBinaryToHexDigit() {
        assertEquals('1', Conversion.binaryToHexDigit(new boolean[]{true}));
        assertEquals('0', Conversion.binaryToHexDigit(new boolean[]{false}));
        assertEquals('f', Conversion.binaryToHexDigit(new boolean[]{true, true, true, true}));
    }

    @Test
    void testBinaryToHexDigitWithSrcPos() {
        assertEquals('8', Conversion.binaryToHexDigit(new boolean[]{true, false, false, false, true}, 1));
    }

    @Test
    void testBinaryToHexDigitMsb0_4bitsEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> Conversion.binaryToHexDigitMsb0_4bits(new boolean[0]));
    }

    @Test
    void testBinaryToHexDigitMsb0_4bitsInvalidLength() {
        assertThrows(IllegalArgumentException.class, () -> Conversion.binaryToHexDigitMsb0_4bits(new boolean[9]));
    }

    @Test
    void testBinaryToHexDigitMsb0_4bitsSinglePosAboveLimit() {
        assertThrows(IllegalArgumentException.class, () -> Conversion.binaryToHexDigitMsb0_4bits(new boolean[7], 4));
    }

    @Test
    void testBinaryToHexDigitMsb0_4bits() {
        assertEquals('8', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, false, false, false}));
        assertEquals('f', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, true, true, true}));
    }

    @Test
    void testBinaryToIntEmptyArray() {
        assertEquals(0, Conversion.binaryToInt(new boolean[0], 0, 0, 0, 0));
    }

    @Test
    void testBinaryToIntInvalidParameters() {
        boolean[] booleans = new boolean[]{false, true, false, true, true, false};
        assertThrows(IllegalArgumentException.class,
                () -> Conversion.binaryToInt(booleans, 0, 0, 0, 33));
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Conversion.binaryToInt(booleans, 10, 0, 0, 1));
    }

    @Test
    void testBinaryToInt() {
        assertEquals(1, Conversion.binaryToInt(new boolean[]{true}, 0, 0, 0, 1));
        assertEquals(Integer.MIN_VALUE, Conversion.binaryToInt(new boolean[]{true}, 0, 0, 31, 1));
    }

    @Test
    void testBinaryToLongEmptyArray() {
        assertEquals(0L, Conversion.binaryToLong(new boolean[0], 0, 0L, 0, 0));
    }

    @Test
    void testBinaryToLongInvalidParameters() {
        boolean[] booleans = new boolean[]{false, true, false, true, true, false};
        assertThrows(IllegalArgumentException.class,
                () -> Conversion.binaryToLong(booleans, 0, 0L, 0, 65));
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Conversion.binaryToLong(booleans, 10, 0L, 0, 1));
    }

    @Test
    void testBinaryToLong() {
        assertEquals(1L, Conversion.binaryToLong(new boolean[]{true}, 0, 0L, 0, 1));
        assertEquals(Long.MIN_VALUE, Conversion.binaryToLong(new boolean[]{true}, 0, 0L, 63, 1));
    }

    @Test
    void testBinaryToShortEmptyArray() {
        assertEquals((short) 0, Conversion.binaryToShort(new boolean[0], 0, (short) 0, 0, 0));
    }

    @Test
    void testBinaryToShortInvalidParameters() {
        boolean[] booleans = new boolean[]{false, true, false, true, true, false};
        assertThrows(IllegalArgumentException.class,
                () -> Conversion.binaryToShort(booleans, 0, (short) 0, 0, 17));
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Conversion.binaryToShort(booleans, 10, (short) 0, 0, 1));
    }

    @Test
    void testBinaryToShort() {
        assertEquals((short) 1, Conversion.binaryToShort(new boolean[]{true}, 0, (short) 0, 0, 1));
        assertEquals(Short.MIN_VALUE, Conversion.binaryToShort(new boolean[]{true}, 0, (short) 0, 15, 1));
    }

    @Test
    void testByteArrayToIntEmptyArray() {
        assertEquals(0, Conversion.byteArrayToInt(new byte[0], 0, 0, 0, 0));
    }

    @Test
    void testByteArrayToIntInvalidParameters() {
        byte[] bytes = new byte[]{0, 1, 2, 3, 4, 5};
        assertThrows(IllegalArgumentException.class,
                () -> Conversion.byteArrayToInt(bytes, 0, 0, 0, 5));
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Conversion.byteArrayToInt(bytes, 10, 0, 0, 1));
    }

    @Test
    void testByteArrayToInt() {
        assertEquals(1, Conversion.byteArrayToInt(new byte[]{1}, 0, 0, 0, 1));
        assertEquals(16909060, Conversion.byteArrayToInt(new byte[]{4, 3, 2, 1}, 0, 0, 0, 4));
    }

    @Test
    void testByteArrayToLongEmptyArray() {
        assertEquals(0L, Conversion.byteArrayToLong(new byte[0], 0, 0L, 0, 0));
    }

    @Test
    void testByteArrayToLongInvalidParameters() {
        byte[] bytes = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThrows(IllegalArgumentException.class,
                () -> Conversion.byteArrayToLong(bytes, 0, 0L, 0, 9));
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Conversion.byteArrayToLong(bytes, 10, 0L, 0, 1));
    }

    @Test
    void testByteArrayToLong() {
        assertEquals(1L, Conversion.byteArrayToLong(new byte[]{1}, 0, 0L, 0, 1));
        assertEquals(72623859790382856L, Conversion.byteArrayToLong(new byte[]{8, 7, 6, 5, 4, 3, 2, 1}, 0, 0L, 0, 8));
    }

    @Test
    void testByteArrayToShortEmptyArray() {
        assertEquals((short) 0, Conversion.byteArrayToShort(new byte[0], 0, (short) 0, 0, 0));
    }

    @Test
    void testByteArrayToShortInvalidParameters() {
        byte[] bytes = new byte[]{0, 1, 2, 3, 4, 5};
        assertThrows(IllegalArgumentException.class,
                () -> Conversion.byteArrayToShort(bytes, 0, (short) 0, 0, 3));
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Conversion.byteArrayToShort(bytes, 10, (short) 0, 0, 1));
    }

    @Test
    void testByteArrayToShort() {
        assertEquals((short) 1, Conversion.byteArrayToShort(new byte[]{1}, 0, (short) 0, 0, 1));
        assertEquals((short) 1027, Conversion.byteArrayToShort(new byte[]{3, 4}, 0, (short) 0, 0, 2));
    }

    // Tests for byteArrayToUuid are not generated because there is no specified line coverage related to this method in the input
}