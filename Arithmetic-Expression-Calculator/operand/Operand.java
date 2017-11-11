package operand;

import operators.*;

public class Operand {
    
    private int value;
    
    public Operand( String token ) {
        this.value = Integer.parseInt(token);
    }
    
    public Operand( int value ) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static boolean check( String token ) {
        try {
            Integer.parseInt(token);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
}
