package lexer;


/**
 *  The Lexer class is responsible for scanning the source file
 *  which is a stream of characters and returning a stream of
 *  tokens; each token object will contain the string (or access
 *  to the string) that describes the token along with an
 *  indication of its location in the source program to be used
 *  for error reporting; we are tracking line numbers; white spaces
 *  are space, tab, newlines
 */
public class Lexer {
    private boolean atEOF = false;
    private char ch;     // next character to process
    private SourceReader source;
    
    // positions in line of current token
    private int startPosition, endPosition;
    
    public Lexer(String sourceFile) throws Exception {
        new TokenType();  // init token table
        source = new SourceReader(sourceFile);
        ch = source.read();
    }
    
    public static void main(String args[]) {
        if (args.length < 1) {
            System.err.println("This program needs an argument if it is being run as a jar");
            System.exit(1);
        }
        String fileInput = args[0];
        String program = "";
        int lineNo = 0;
        Token token;
        try {
            Lexer lexer = new Lexer(fileInput);
            while (true) {
                token = lexer.nextToken();
                if (lineNo < lexer.source.getLineno()) {
                    if (lexer.source.getLineno() - lineNo > 1) {
                        program += String.format("%1$-3s:", lineNo + 1);
                        program += "\n";
                    }
                    program += String.format("%1$-3s: %2$-50s", lexer.source.getLineno(), lexer.source.getNextLine());
                    lineNo = lexer.source.getLineno();
                    program += "\n";
                }
                String readableOutput = String.format(" %1$-10s left: %2$-8s  right: %3$-8s line: %4$-8s   %5$s",
                                                      token.toString(),
                                                      token.getLeftPosition(),
                                                      token.getRightPosition(),
                                                      lexer.source.getLineno(),
                                                      
                                                      token.getKind().toString()
                                                      );
                
                System.out.println(readableOutput);
                
            }
        } catch (Exception e) {
            System.out.println(program);
        }
    }
    
    
    public void printProgram(String fileInput) {
        String program = "";
        int lineNo = 0;
        Token token;
        try {
            Lexer lexer = new Lexer(fileInput);
            while (true) {
                token = lexer.nextToken();
                if (lineNo < lexer.source.getLineno()) {
                    if (lexer.source.getLineno() - lineNo > 1) {
                        program += String.format("%1$-3s:", lineNo + 1);
                        program += "\n";
                    }
                    program += String.format("%1$-3s: %2$-50s", lexer.source.getLineno(), lexer.source.getNextLine());
                    lineNo = lexer.source.getLineno();
                    program += "\n";
                }
                
            }
        } catch (Exception e) {
            System.out.println(program);
        }
    }
    
    /**
     *  newIdTokens are either ids or reserved words; new id's will be inserted
     *  in the symbol table with an indication that they are id's
     *  @param id is the String just scanned - it's either an id or reserved word
     *  @param startPosition is the column in the source file where the token begins
     *  @param endPosition is the column in the source file where the token ends
     *  @return the Token; either an id or one for the reserved words
     */
    public Token newIdToken(String id,int startPosition,int endPosition) {
        return new Token(startPosition,endPosition,Symbol.symbol(id,Tokens.Identifier));
    }
    
    /**
     *  number tokens are inserted in the symbol table; we don't convert the
     *  numeric strings to numbers until we load the bytecodes for interpreting;
     *  this ensures that any machine numeric dependencies are deferred
     *  until we actually run the program; i.e. the numeric constraints of the
     *  hardware used to compile the source program are not used
     *  @param number is the int String just scanned
     *  @param startPosition is the column in the source file where the int begins
     *  @param endPosition is the column in the source file where the int ends
     *  @return the int Token
     */
    public Token newNumberToken(String number,int startPosition,int endPosition) {
        return new Token(startPosition,endPosition,
                         Symbol.symbol(number,Tokens.INTeger));
    }
    
    
    public Token newCharToken (String character, int startPosition, int endPosition) {
        return new Token(startPosition, endPosition, Symbol.symbol(character, Tokens.CHARacter));
    }
    
    public Token newStringToken(String str, int startPosition, int endPosition) {
        return new Token(startPosition, endPosition, Symbol.symbol(str, Tokens.StringLit));
    }
    
    /**
     *  build the token for operators (+ -) or separators (parens, braces)
     *  filter out comments which begin with two slashes
     *  @param s is the String representing the token
     *  @param startPosition is the column in the source file where the token begins
     *  @param endPosition is the column in the source file where the token ends
     *  @return the Token just found
     */
    public Token makeToken( String s, int startPosition, int endPosition ) {
        // filter comments
        if( s.equals("//") ) {
            try {
                int oldLine = source.getLineno();
                
                do {
                    ch = source.read();
                } while( oldLine == source.getLineno() );
            } catch (Exception e) {
                atEOF = true;
            }
            
            return nextToken();
        }
        
        // ensure it's a valid token
        Symbol sym = Symbol.symbol( s, Tokens.BogusToken );
        
        if( sym == null ) {
            System.out.println( "******** illegal character: " + s );
            atEOF = true;
            return nextToken();
        }
        
        return new Token( startPosition, endPosition, sym );
    }
    
    
    
    /**
     *  @return the next Token found in the source file
     */
    public Token nextToken() { // ch is always the next char to process
        if (atEOF) {
            if (source != null) {
                source.close();
                source = null;
            }
            return null;
        }
        try {
            while (Character.isWhitespace(ch)) {  // scan past whitespace
                ch = source.read();
            }
        } catch (Exception e) {
            atEOF = true;
            return nextToken();
        }
        startPosition = source.getPosition();
        endPosition = startPosition - 1;
        
        if (Character.isJavaIdentifierStart(ch)) {
            // return tokens for ids and reserved words
            String id = "";
            try {
                do {
                    endPosition++;
                    id += ch;
                    ch = source.read();
                } while (Character.isJavaIdentifierPart(ch));
            } catch (Exception e) {
                atEOF = true;
            }
            return newIdToken(id,startPosition,endPosition);
        }
        
        if( Character.isDigit( ch )) {
            // return number tokens
            String number = "";
            
            try {
                do {
                    endPosition++;
                    number += ch;
                    ch = source.read();
                } while( Character.isDigit( ch ));
            } catch( Exception e ) {
                atEOF = true;
            }
            
            return newNumberToken( number, startPosition, endPosition );
        }
        
        
        if (ch == '\'') {
            int check = 0;
            // return number tokens
            String c = "";
            try {
                do {
                    if (ch != '\'' && check == 0) {
                        endPosition++;
                        c += ch;
                        check = 1;
                    }
                    else if (ch != '\'' && check != 0 && ch != ' '){
                        System.out.println("\nError on line " + source.getLineno() + ". Char can only have one character (e.g char ex = 'g')");
                        atEOF = true;
                        return nextToken();
                    }
                    else if (ch == ' '){
                        System.out.println("\nChar type should have two single quotes '' (e.g char ex = 'g') on line " + source.getLineno());
                        atEOF = true;
                        return nextToken();
                    }
                    
                    ch = source.read();
                } while (ch != '\'');
            } catch (Exception e) {
                atEOF = true;
            }
            ch = ' ';
            return newCharToken(c, startPosition, endPosition);
        }
        
        
        if(ch == '"') {
            
            String character = "";
            try {
                ch = source.read();
                do {
                    endPosition++;
                    character += ch;
                    ch = source.read();
                } while( ch != '"');
            } catch( Exception e ) {
                atEOF = true;
                return nextToken();
            }
            ch = ' ';
            return newStringToken( character, startPosition, endPosition );
        }
        
        
        // At this point the only tokens to check for are one or two
        // characters; we must also check for comments that begin with
        // 2 slashes
        String charOld = "" + ch;
        String op = charOld;
        Symbol sym;
        try {
            endPosition++;
            ch = source.read();
            op += ch;
            // check if valid 2 char operator; if it's not in the symbol
            // table then don't insert it since we really have a one char
            // token
            sym = Symbol.symbol(op, Tokens.BogusToken);
            if (sym == null) {  // it must be a one char token
                return makeToken(charOld,startPosition,endPosition);
            }
            endPosition++;
            ch = source.read();
            return makeToken(op,startPosition,endPosition);
        } catch (Exception e) {}
        atEOF = true;
        if (startPosition == endPosition) {
            op = charOld;
        }
        return makeToken(op,startPosition,endPosition);
    }
}
