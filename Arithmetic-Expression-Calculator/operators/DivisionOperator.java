package operators;
import operand.*;

public class DivisionOperator extends Operator {

    public int priority() {
        return 3;
    }


    public Operand execute( Operand op1, Operand op2 ){

        int operatorValue1 = op1.getValue();
        int operatorValue2 = op2.getValue();

        Operand result = new Operand(operatorValue1 / operatorValue2);

        return result;

    }
}
