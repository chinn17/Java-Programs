import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {
    private TextField txField = new TextField();
    private Panel buttonPanel = new Panel();
    
    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] bText = {
    "7", "8", "9", "+", "4", "5", "6", "- ", "1", "2", "3",
    "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };
    private Button[] buttons = new Button[ bText.length ];
    
    public static void main(String argv[]) {
        EvaluatorUI calc = new EvaluatorUI();
    }
    
    public EvaluatorUI() {
        setLayout( new BorderLayout() );
        
        add( txField, BorderLayout.NORTH );
        txField.setEditable( false );
        
        add( buttonPanel, BorderLayout.CENTER );
        buttonPanel.setLayout( new GridLayout( 5, 4 ));
        
        //create 20 buttons with corresponding text in bText[] array
        for ( int i = 0; i < 20; i++ ) {
            buttons[ i ] = new Button( bText[ i ]);
        }
        
        //add buttons to button panel
        for (int i=0; i<20; i++) {
            buttonPanel.add( buttons[ i ]);
        }
        
        //set up buttons to listen for mouse input
        for ( int i = 0; i < 20; i++ ) {
            buttons[ i ].addActionListener( this );
        }
        
        setTitle( "Calculator" );
        setSize( 400, 400 );
        setLocationByPlatform( true  );
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setVisible( true );
    }
    
    public void actionPerformed( ActionEvent arg0 ) {
        Evaluator evaluator = new Evaluator();
        String toDisplay = null;
        
        if(arg0.getActionCommand() == "C"){
            txField.setText(toDisplay);
        }else if(arg0.getActionCommand() == "CE"){
            toDisplay = txField.getText().substring(0, txField.getText().length() - 1);
            txField.setText(toDisplay);
        }else {
            String alreadyInDisplay = txField.getText();
            toDisplay = (alreadyInDisplay + arg0.getActionCommand()).toString().trim();
            txField.setText(toDisplay);
            
            if(arg0.getActionCommand().toString().trim().equals("=")) {
                String strToProcess = toDisplay.substring(0, toDisplay.length() - 1);
                int result = evaluator.eval(strToProcess);
                String resultToDisplay = Integer.toString(result);
                txField.setText(resultToDisplay);
            }
        }
    }
}
