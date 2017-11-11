import java.util.*;
import operators.*;
import operand.*;

public class Evaluator {
    private Stack<Operand> operandStack;
    private Stack<Operator> operatorStack;
    
    private StringTokenizer tokenizer;
    private static final String DELIMITERS = "+-*^/#! ";
    
    public Evaluator() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }
    
    public int eval( String expression ) {
        String token;
        this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );
        operatorStack.push(new PoundOperator());
        
        while ( this.tokenizer.hasMoreTokens() ) {
            // filter out spaces
            if ( !( token = this.tokenizer.nextToken() ).equals( " " )) {
                // check if token is an operand
                if ( Operand.check( token )) {
                    operandStack.push( new Operand( token ));
                } else {
                    if ( ! Operator.check( token )) {
                        System.out.println( "*****invalid token******" + token );
                        System.exit( 1 );
                    }
                    Operator newOperator = Operator.getOperator( token );
                    if ( token == ")") {
                        while ( operatorStack.peek().getClass() != ParenthesesOperator.class ){
                            evaluateFinalResults();
                        }
                        operatorStack.pop();
                    }
                    
                    while ( operatorStack.peek().priority() >= newOperator.priority() ) {
                        evaluateFinalResults();
                    }
                    operatorStack.push( newOperator );
                }
            }
        }
        
        while( operatorStack.peek().getClass() != PoundOperator.class ){
            evaluateFinalResults();
        }
        return operandStack.pop().getValue();
    }
    
    public void evaluateFinalResults(){
        Operator oldOperator = operatorStack.pop();
        Operand operator2 = operandStack.pop();
        Operand operator1 = operandStack.pop();
        operandStack.push( oldOperator.execute( operator1, operator2 ));
    }
}
