package temp;

import org.apache.commons.lang3.Conversion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConversionGeneratedTest0002 {

    @Test
    public void testBinaryBeMsb0ToHexDigitWithExceptions() {
        // srcPos index out of bound
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, true, true, true}, 5));
    }

    @Test
    public void testBinaryToByteWithExceptions() {
        // nBools-1+dstPos is greater or equal to than 8
        Assertions.assertThrows(IllegalArgumentException.class, () -> Conversion.binaryToByte(new boolean[]{true, false, true, false}, 0, (byte) 0, 4, 5));
    }

    @Test
    public void testBinaryToShortWithExceptions() {
        // nBools-1+dstPos is greater or equal to than 16
        Assertions.assertThrows(IllegalArgumentException.class, () -> Conversion.binaryToShort(new boolean[]{true, false, true, false}, 0, (short) 0, 12, 5));
    }

    @Test
    public void testBinaryToIntWithExceptions() {
        // nBools-1+dstPos is greater or equal to than 32
        Assertions.assertThrows(IllegalArgumentException.class, () -> Conversion.binaryToInt(new boolean[]{true, false, true, false}, 0, 0, 28, 5));
    }

    @Test
    public void testBinaryToLongWithExceptions() {
        // nBools-1+dstPos is greater or equal to than 64
        Assertions.assertThrows(IllegalArgumentException.class, () -> Conversion.binaryToLong(new boolean[]{true, false, true, false}, 0, 0L, 60, 5));
    }

    @Test
    public void testByteArrayToIntWithExceptions() {
        // (nBytes-1)*8+dstPos is greater or equal to than 32
        Assertions.assertThrows(IllegalArgumentException.class, () -> Conversion.byteArrayToInt(new byte[]{1, 2, 3, 4}, 0, 0, 28, 2));
    }

    @Test
    public void testByteArrayToLongWithExceptions() {
        // (nBytes-1)*8+dstPos is greater or equal to than 64
        Assertions.assertThrows(IllegalArgumentException.class, () -> Conversion.byteArrayToLong(new byte[]{1, 2, 3, 4}, 0, 0L, 60, 2));
    }

    @Test
    public void testByteArrayToShortWithExceptions() {
        // (nBytes-1)*8+dstPos is greater or equal to than 16
        Assertions.assertThrows(IllegalArgumentException.class, () -> Conversion.byteArrayToShort(new byte[]{1, 2, 3, 4}, 0, (short) 0, 12, 2));
    }

    @Test
    public void testBinaryToHexDigitWithNonEmptyArray() {
        Assertions.assertEquals('f', Conversion.binaryToHexDigit(new boolean[]{true, true, true, true}));
    }

    @Test
    public void testBinaryToHexDigitMsb0_4bitsWithExceptions() {
        // src.length>8
        Assertions.assertThrows(IllegalArgumentException.class, () -> Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, true, true, true, true, true, true, true, true}));
        // src.length-srcPos<4
        Assertions.assertThrows(IllegalArgumentException.class, () -> Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, true, true}, 1));
    }


    // Add more tests coverage for your list here

}