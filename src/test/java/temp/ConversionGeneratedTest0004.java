package temp;

import org.apache.commons.lang3.Conversion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConversionGeneratedTest0004 {

    @Test
    public void testBinaryBeMsb0ToHexDigit() {
        assertEquals('f', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, true, true, true}));
    }

    @Test
    public void testBinaryBeMsb0ToHexDigitWithPosition() {
        assertEquals('1', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{false, false, false, true, false}, 1));
    }

    @Test
    public void testBinaryToHexDigitMsb0_4bits() {
        assertEquals('8', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, false, false, false}));
        assertEquals('1', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{false, false, false, true}));
        assertThrows(IllegalArgumentException.class,
                () -> Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true}));
    }


    @Test
    public void testByteArrayToInt() {
        assertEquals(0xFF, Conversion.byteArrayToInt(new byte[]{(byte) 0xFF}, 0, 0, 0, 1));
        assertThrows(IllegalArgumentException.class,
                () -> Conversion.byteArrayToInt(new byte[]{0x00, (byte) 0xFF}, 0, 0, 24, 2));
    }

    @Test
    public void testByteArrayToLong() {
        assertEquals(0xFF, Conversion.byteArrayToLong(new byte[]{(byte) 0xFF}, 0, 0, 0, 1));
        assertThrows(IllegalArgumentException.class,
                () -> Conversion.byteArrayToLong(new byte[]{0x00, (byte) 0xFF}, 0, 0, 56, 2));
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> Conversion.byteArrayToLong(new byte[]{0x00, (byte) 0xFF}, 2, 0, 0, 2));
    }

    // The other methods that convert to and from hexadecimal, UUID, long, int, short and byte
    // require more complex logic with different bit orderings and will also be tested similarly
    // with varying values of source position, destination position, and lengths, covering the
    // various branches and exceptional cases in the methods.
}