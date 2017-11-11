/*************************************
 * Student Name : Chintan Puri
 * Student ID  : 916541358
 * Email  : cpuri@mail.sfsu.edu
 *************************************/

import java.util.Scanner;

public class BasicChess {


 public static void main(String[] args) {
  
    boolean color;
    char c;
    String col;
      
             
  /* Initialising the Chess board to its original state */          
    String[][] board= {
        {"BlackRook","BlackKnight","BlackBishop","BlackQueen","BlackKing","BlackBishop","BlackKnight","BlackRook"},
            {"BlackPawn","BlackPawn","BlackPawn","BlackPawn","BlackPawn","BlackPawn","BlackPawn","BlackPawn"},
            {"---","---","---","---","---","---","---","---"},
            {"---","---","---","---","---","---","---","---"},
            {"---","---","---","---","---","---","---","---"},
            {"---","---","---","---","---","---","---","---"},
            {"WhitePawn","WhitePawn","WhitePawn","WhitePawn","WhitePawn","WhitePawn","WhitePawn","WhitePawn"},
            {"WhiteRook","WhiteKnight","WhiteBishop","WhiteQueen","WhiteKing","WhiteBishop","WhiteKnight","WhiteRook"}
            
    };
    
    System.out.println("What color would you like to start with ? PRESS w for White or ANY key for black ");
      Scanner input = new Scanner(System.in);
    c = input.next().charAt(0);
    if (c == 'w') {
        color = true;
        col="White";
    } else {
                color = false ;
                col="Black";
                }
    
    /* Displaying the color of user and current status of the chess board. */
    System.out.println("Your color is "+col+" and the current status of the chess board is :");
    display(board);
    move(board,color);
    }//end of main method.

   /* Asking for the co-ordinates of the piece and the destination from the user. */
    private static void move(String[][]board,boolean color) {
        Scanner input = new Scanner(System.in);
        int x,y;
        int d1,d2;
        System.out.println("Enter the x co-ordinate of piece :");
        x = input.nextInt();
        System.out.println("Enter the y co-ordinate of piece :");
        y = input.nextInt();
        if(x>7 || x<0 || y>7 || y<0){
            System.out.println("Enter co-ordinates between 0 and 7");
            move(board,color);
        }
         checkcolor(x,y,board,color);
        System.out.println("Enter the x co-ordinate of destination :");
        d1 = input.nextInt();
        System.out.println("Enter the y co-ordinate of destination :");
        d2 = input.nextInt();
        if(d1>7 || d1<0 || d2>7 || d2<0){
            System.out.println("Enter co-ordinates between 0 and 7");
            move(board,color);
        }
       
        checkempty(d1,d2,board,color);
        checkmove(x,y,d1,d2,board,color);
   
    }//end of move method.

    /* Checking color of the piece at the co-ordinates given by user. */
    private static void checkcolor(int x, int y,String[][]board,boolean color) {
        if(color==true){
        if (board[x][y].equals("WhiteRook")||board[x][y].equals("WhiteKnight")||board[x][y].equals("WhiteBishop")||
                board[x][y].equals("WhiteQueen")||board[x][y].equals("WhiteKing")||board[x][y].equals("WhitePawn")) {
            System.out.println("There is a white piece on that location");
        }
            else {
            System.out.println("There is no white piece at that location");
            move(board,color);
                    }
        }
            else 
            if(board[x][y].equals("BlackRook")||board[x][y].equals("BlackKnight")||board[x][y].equals("BlackBishop")||
                board[x][y].equals("BlackQueen")||board[x][y].equals("BlackKing")||board[x][y].equals("BlackPawn")) {
                    System.out.println("There is a black piece at that location");
                    }
            else {
            System.out.println("There is no black piece at that location");
            move(board,color);
                    }   
            
            
        } //end of checkcolor method.

    
    /* Checking if the destination and the squares towards the destination are empty.*/
    private static void checkempty(int d1, int d2,String[][] board, boolean color) {
        if (board[d1][d2]!= "---"){
            System.out.println("There is another piece on the way");
            move(board,color);
        }
    }//end of checkempty method.                            
    
    
    
    
/* Checking which piece is present at the co-ordinates entered by the user. */
    private static void checkmove(int x, int y, int d1, int d2, String[][] board, boolean color) {
        if (board[x][y].equals("WhiteKing")||board[x][y].equals("BlackKing")) {
 checkKing(x,y,d1,d2,board,color);
        }
        
        if (board[x][y].equals("WhiteQueen")||board[x][y].equals("BlackQueen")){
checkQueen(x,y,d1,d2,board,color);
        }
        
        if (board[x][y].equals("WhiteRook")||board[x][y].equals("BlackRook")){
checkRook(x,y,d1,d2,board,color);
        }
        
        if (board[x][y].equals("WhiteBishop")||board[x][y].equals("BlackBishop")){
checkBishop(x,y,d1,d2,board,color);
        }
        
        if (board[x][y].equals("WhiteKnight")||board[x][y].equals("BlackKnight")){
checkKnight(x,y,d1,d2,board,color);
        }
        
        if (board[x][y].equals("WhitePawn")||board[x][y].equals("BlackPawn")){
checkPawn(x,y,d1,d2,board,color);
        }
    
    }//end of checkmove method.

    
    /* Checking if the King can perform the move. */
    private static void checkKing(int x, int y, int d1, int d2, String[][] board,boolean color) {
        if (d1==x+1 || d1==x-1 || d2==y+1 || d2==y-1){
            makemove(x,y,d1,d2,board,color);
        }
        else {
            System.out.println("This move is not allowed for the King.");
            move(board,color);
            
        }
    } //end of checkKing method.
    
     
    
    
    /* Checking if the Queen can perform the move. */
    private static void checkQueen(int x, int y, int d1, int d2, String[][] board, boolean color) {
              for(int i=0;i<8;i++) {
         if (d1==x+i && d2==y+i){
           makemove(x,y,d1,d2,board,color);
       }
         else if(d1==x-i && d2==y-i){
           makemove(x,y,d1,d2,board,color);
       }
         else if (d1==x+i && d2==y-i){
               makemove(x,y,d1,d2,board,color);
           }
         else if (d1==x-i && d2==y+i){
               makemove(x,y,d1,d2,board,color);
       }
        }
               
              if (d1 == x) {
            for(int i=0;i<8;i++){
                if(d2==i){
                    makemove(x,y,d1,d2,board,color);
                }
            } //end of for loop
        }
        else if (d2==y) {
                        for(int i=0;i<8;i++){
                            if(d1==i){
                    makemove(x,y,d1,d2,board,color);
                                     }
                                            } //end of for loop
        }
                else {
            System.out.println("This move is not allowed for the Queen.");
            move(board,color);
        }
    
    } //end of checkQueen method.
    
  /* Checking if the Rook can perform the move. */
    private static void checkRook(int x, int y, int d1, int d2, String[][] board,boolean color) {
        if (d1 == x) {
            for(int i=0;i<8;i++){
                if(d2==i){
                    
                    makemove(x,y,d1,d2,board,color);
                }
            }
        }else if (d2==y) {
                        for(int i=0;i<8;i++){
                            if(d1==i){
                    
                    makemove(x,y,d1,d2,board,color);
                }
                    }
        } else {
            System.out.println("This move is not allowed for the Rook.");
            move(board,color);
        }
        
    } //end of checkRook method.             
                
               
    
  /* Checking if the Bishop can perform the move. */
    private static void checkBishop(int x, int y, int d1, int d2, String[][] board,boolean color) {
        
        boolean check=false;
        for(int i=0;i<8;i++) {
       if (d1==x+i && d2==y+i){
           
           makemove(x,y,d1,d2,board,color);
           check=true;
       }
       else if(d1==x-i && d2==y-i){
           
           makemove(x,y,d1,d2,board,color);
           check=true;
       }
           else if (d1==x+i && d2==y-i){
               
               makemove(x,y,d1,d2,board,color);
               check=true;
           }
           else if (d1==x-i && d2==y+i){
               
               makemove(x,y,d1,d2,board,color);
               check=true;
    }
       }//end of for loop
        if(check=false){
            System.out.println("This move is not allowed for the Bishop.");
            move(board,color);
        }
        
    } //end of checkBishop method.    
        
           
  /* Checking if the Knight can perform the move. */
    private static void checkKnight(int x, int y, int d1, int d2, String[][] board,boolean color) {
        if (d1==x+2 && d2==y+1){
            makemove(x,y,d1,d2,board,color);
        }
            else if (d1==x+2 && d2==y-1){
                    makemove(x,y,d1,d2,board,color);
            }

            else if(d1==x-2 && d2==y+1){
                makemove(x,y,d1,d2,board,color);
            }
            else if(d1==x-2 && d2==y-1){
                makemove(x,y,d1,d2,board,color);
            }
            else if(d1==x+1 && d2==y+2){
                makemove(x,y,d1,d2,board,color);
            }
            else if(d1==x-1 && d2==y+2){
                makemove(x,y,d1,d2,board,color);
            }
            else if(d1==x+1 && d2==y-2){
                makemove(x,y,d1,d2,board,color);
            }
            else if(d1==x-1 && d2==y-2){
                makemove(x,y,d1,d2,board,color);
            }
            else {
            System.out.println("This move is not allowed for the Knight.");
            
            move(board,color);
            }
    }  //end of checkKnight method.

      /* Checking if the Pawn can perform the move. */
    private static void checkPawn(int x, int y, int d1, int d2, String[][] board,boolean color) {
        if (d1==x+1 && d2==y ){
            makemove(x,y,d1,d2,board,color);
        } else if(d1==x-1 && d2==y){
                    makemove(x,y,d1,d2,board,color);
                    }
        else {
            System.out.println("This move is not allowed for the Pawn.");
            move(board,color);
        }
        
    } //end of checkPawn method.
    
     /* Moving the piece. */
    private static void makemove(int x,int y,int d1,int d2,String[][] board,boolean color){
        board[d1][d2]=board[x][y];
        board[x][y]="---";
     display(board);  
     repeat(board,color);
    } //end of make move method.

    /* Displaying the status of the chess board. */
    private static void display(String[][] board) {
        for(int i=0;i<8;i++){
            System.out.print("\n");
            for(int j=0;j<8;j++){
                System.out.printf("%11s ",board[i][j]);
              
            }  
    }
        System.out.println("\n");
    } //end of display method.

    
    /* Asking the user if he/she wants to keep playing. */
    private static void repeat(String[][] board,boolean color) {
        String rep;
        Scanner input= new Scanner(System.in);
       System.out.println("\n\n Do you want to keep playing ?");
       rep=input.next();
       if (rep.equals("Yes")||rep.equals("yes")||rep.equals("YES")){
           if(color==true){
               System.out.println("Now your colour is Black");
               color=false;
           }
           else {
                       System.out.println("Now your colour is White");
                       color=true;
                }
           move(board,color);
       }
       else{
           System.out.println("Thank You for Playing");
            }
    } 

}

        
        
 



