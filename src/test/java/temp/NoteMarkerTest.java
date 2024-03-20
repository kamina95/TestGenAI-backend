package temp; 

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class NoteMarkerTest {
    @Test
    void checkNotesFirstClass() {
        String result = NoteMarker.checkNotes(7);
        Assertions.assertEquals("first class", result);
    }
}
