package temp; 

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConversionGeneratedTest0008 {

    @Test
    void testBinaryBeMsb0ToHexDigitIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> Conversion.binaryBeMsb0ToHexDigit(new boolean[4], 5));
    }

    @Test
    void testBinaryToByteSrcLengthZero() {
        assertEquals((byte) 0, Conversion.binaryToByte(new boolean[0], 0, (byte) 0, 0, 0));
    }
    
    @Test
    void testBinaryToByteInvalidNBools() {
        assertThrows(IllegalArgumentException.class, () -> Conversion.binaryToByte(new boolean[8], 0, (byte)0, 0, 9));
    }

    @Test
    void testBinaryToIntEmptyArray() {
        assertEquals(0, Conversion.binaryToInt(new boolean[0], 0, 0, 0, 0));
    }

    @Test
    void testBytearrayToIntSrcLengthZero() {
        assertEquals(0, Conversion.byteArrayToInt(new byte[0], 0, 0, 0, 0));
    }
    
    @Test
    void testBytearrayToLongSrcPosOutOfBounds() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Conversion.byteArrayToLong(new byte[10], 10, 0L, 0, 1));
    }
    
    @Test
    void testBytearrayToShortDstPos8NBytes1() {
        assertEquals((short) 0xFF00, Conversion.byteArrayToShort(new byte[] { (byte) 255 }, 0, (short) 0, 8, 1));
    }
    
    @Test
    void testBytearrayToShortSrcLengthZero() {
        assertEquals((short) 0, Conversion.byteArrayToShort(new byte[0], 0, (short) 0, 0, 0));
    }
    
    @Test
    void testBytearrayToUuidLessThan16Bytes() {
        assertThrows(IllegalArgumentException.class, () -> Conversion.byteArrayToUuid(new byte[15], 0));
    }

}