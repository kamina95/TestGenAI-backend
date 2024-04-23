package temp; 

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class ConversionGeneratedTest0007 {

    @Test
    void testBinaryBeMsb0ToHexDigitEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> Conversion.binaryBeMsb0ToHexDigit(new boolean[] {}));
    }

    @Test
    void testBinaryBeMsb0ToHexDigitNullArray() {
        assertThrows(NullPointerException.class, () -> Conversion.binaryBeMsb0ToHexDigit(null));
    }

    @Test
    void testBinaryBeMsb0ToHexDigitOutOfBounds() {
        boolean[] src = { true, true, false, false };
        assertThrows(IndexOutOfBoundsException.class, () -> Conversion.binaryBeMsb0ToHexDigit(src, 5));
    }

    @Test
    void testBinaryToByteIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Conversion.binaryToByte(new boolean[] { true }, 0, (byte)0, 7, 2));
    }
    
    @Test
    void testBinaryToByteArrayIndexOutOfBoundsException() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Conversion.binaryToByte(new boolean[] { true, false, false, true }, 4, (byte)0, 2, 2));
    }

    
    @Test
    void testBinaryToHexDigitEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> Conversion.binaryToHexDigit(new boolean[] {}));
    }

    @Test
    void testBinaryToHexDigitMsb0_4bitsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, 
            () -> Conversion.binaryToHexDigitMsb0_4bits(new boolean[] {true, true, true}, 0));
    }

    @Test
    void testBinaryToIntIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, 
            () -> Conversion.binaryToInt(new boolean[] {true, false, true}, 0, 0, 30, 3));
    }

    @Test
    void testBinaryToIntArrayIndexOutOfBoundsException() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> Conversion.binaryToInt(new boolean[] {true, true, false, false}, 4, 0, 2, 2));
    }


    @Test
    void testBinaryToLongIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, 
            () -> Conversion.binaryToLong(new boolean[] {true, false, true}, 0, 0L, 62, 3));
    }

    @Test
    void testBinaryToLongArrayIndexOutOfBoundsException() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> Conversion.binaryToLong(new boolean[] {true, true, false, false}, 4, 0L, 2, 2));
    }

    @Test
    void testBinaryToShortIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> Conversion.binaryToShort(new boolean[] {true, false, true}, 0, (short)0, 14, 3));
    }

    @Test
    void testBinaryToShortArrayIndexOutOfBoundsException() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Conversion.binaryToShort(new boolean[] {true, true, false, false}, 4, (short)0, 2, 2));
    }

    @Test
    void testByteArrayToIntIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> Conversion.byteArrayToInt(new byte[] {1, 2, 3, 4}, 0, 0, 24, 4));
    }

    @Test
    void testByteArrayToIntArrayIndexOutOfBoundsException() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Conversion.byteArrayToInt(new byte[] {1, 2, 3, 4}, 4, 0, 0, 2));
    }

    @Test
    void testByteArrayToInt() {
        assertEquals(0x03020100, Conversion.byteArrayToInt(new byte[] {0, 1, 2, 3}, 0, 0, 0, 4));
    }

    @Test
    void testByteArrayToLongIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> Conversion.byteArrayToLong(new byte[] {1, 2, 3, 4, 5, 6, 7, 8}, 0, 0L, 56, 8));
    }

    @Test
    void testByteArrayToLongArrayIndexOutOfBoundsException() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Conversion.byteArrayToLong(new byte[] {1, 2, 3, 4, 5, 6, 7, 8}, 8, 0L, 0, 2));
    }

    @Test
    void testByteArrayToLong() {
        assertEquals(0x0706050403020100L,
                Conversion.byteArrayToLong(new byte[] {0, 1, 2, 3, 4, 5, 6, 7}, 0, 0L, 0, 8));
    }

    @Test
    void testByteArrayToShortIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> Conversion.byteArrayToShort(new byte[] {1, 2, 3, 4}, 0, (short)0, 8, 2));
    }

    @Test
    void testByteArrayToShortArrayIndexOutOfBoundsException() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Conversion.byteArrayToShort(new byte[] {1, 2, 3, 4}, 3, (short)0, 0, 2));
    }

    @Test
    void testByteArrayToShort() {
        assertEquals((short)0x0100,
                Conversion.byteArrayToShort(new byte[] {0, 1}, 0, (short)0, 0, 2));
    }

    @Test
    void testByteArrayToUuidIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> Conversion.byteArrayToUuid(new byte[15], 0));
    }

    @Test
    void testByteArrayToUuid() {
        UUID expectedUuid = new UUID(0x0706050403020100L, 0x0f0e0d0c0b0a0908L);
        assertEquals(expectedUuid,
                Conversion.byteArrayToUuid(new byte[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, 0));
    }

    // TODO: Additional tests for the remaining lines specified
}