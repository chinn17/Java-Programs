package operators;
import operand.*;

public class PoundOperator extends Operator{
    public int priority(){
        return 0;
    }
    public Operand execute(Operand op1, Operand op2){
        return null;
    }
}
