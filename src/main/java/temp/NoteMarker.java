package temp; 

public class NoteMarker {
    public static String checkNotes(int notes){
        String result = "";
        if(notes>=0 && notes<4){
            result = "fail";
        }else if(notes>=4 && notes < 6){
            result = "second class";
        }else if(notes >= 6 && notes <=10){
            result = "first class";
        }else{
            result = "incorrect number";
        }
        return result;
    }
}
