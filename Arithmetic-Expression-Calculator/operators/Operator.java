package operators;
import operand.*;
import java.util.*;



public abstract class Operator {
    
    static HashMap<String,Operator> operators;
    
    static {
        
        operators = new HashMap<String, Operator>();
        operators.put( "+", new AdditionOperator() );
        operators.put( "-", new SubtractionOperator() );
        operators.put( "*", new MultiplicationOperator() );
        operators.put( "/", new DivisionOperator() );
        operators.put("#", new PoundOperator() );
        operators.put("(", new ParenthesesOperator() );
        operators.put( "^", new PowerOperator() );
        
    }
    
    public static Operator getOperator(String token){
        return (Operator)operators.get(token);
    }
    
    public abstract int priority();
    public abstract Operand execute( Operand op1, Operand op2 );
    
    
    public static boolean check( String token ) {
        
        if (operators.containsKey(token)){
            return true;
        }
        
        return false;
    }
    
}
