package temp; 

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class NoteMarkerGeneratedTest {
    @Test
    public void testCheckNotes_OutOfLowerBound() {
        assertEquals("incorrect number", NoteMarker.checkNotes(-1));
    }
}
