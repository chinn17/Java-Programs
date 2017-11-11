package operators;
import operand.*;

public class PowerOperator extends Operator {
    public int priority(){
        return 4;
    }
    public Operand execute(Operand op1, Operand op2){
        return new Operand( (int)Math.pow(op1.getValue(), op2.getValue()));
    }
}
