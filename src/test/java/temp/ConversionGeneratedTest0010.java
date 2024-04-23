package temp; 

import org.apache.commons.lang3.Conversion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConversionGeneratedTest0010 {



    @Test
    public void testBinaryToByte() {
        byte result = Conversion.binaryToByte(new boolean[]{false, true, true, false, true}, 1, (byte)0x00, 2, 4);
        assertEquals((byte)0x2C, result);
    }


    @Test
    public void testBinaryToHexDigit_withSrcPos() {
        char result = Conversion.binaryToHexDigit(new boolean[]{false, true, false, true}, 1);
        assertEquals('5', result);
    }

    @Test
    public void testBinaryToHexDigitMsb0_4bits() {
        char result = Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, false, false, true});
        assertEquals('9', result);
    }

}