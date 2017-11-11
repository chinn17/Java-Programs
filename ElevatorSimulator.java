
import java.util.Scanner;

/*
 * Chintan Puri
 * SFSU ID : 916541358
 * Date : 07/07/2016
 */
public class ElevatorSimulator {


    public static void main(String[] args) {
       
        
        /* Initializing Elevator 1 at floor number 1 
                        Elevator 2 at floor number 3
                        Elevator 3 at floor number 5
                        Elevator 4 at floor number 9 */
        int elevator1 = 1;
        int elevator2 = 3;
        int elevator3 = 5;
        int elevator4 = 9;
        boolean valid;
        
        double diff1,diff2,diff3,diff4;
        int entry,destination = 0;
        
        while (true) {
        
        /* Prompt user to enter the entry floor number */    
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the floor that you want to enter from : ");
        entry = input.nextInt();
       
        /* Check if user has entered a valid floor number between 1 and 9 */
        if(entry<1 || entry>9)
        {
            System.out.println("Please enter a number between 1 and 9 \n");
            continue;
        }
        
        /* Calculate difference between the entry floor and the elevator floors */
        diff1 = entry - elevator1;
        diff2= entry - elevator2;
        diff3= entry - elevator3;
        diff4=entry - elevator4;
        
        /* Evaluate the absolute value of the differences */
        double absolute1;
        absolute1 = Math.abs(diff1);
        double absolute2;
        absolute2 = Math.abs(diff2);
        double absolute3;
        absolute3 = Math.abs(diff3);
        double absolute4;
        absolute4 = Math.abs(diff4);
       
       
        
        
        /* Check if Elevator 1 is nearest to the user */
       if (absolute1<=absolute2 && absolute1<=absolute3 && absolute1<=absolute4) {
       System.out.println("Elevator 1 is coming to pick you up \n");
            
            /* Print the sequence of floors the elevator has to travel to reach the user */
                if (entry>=elevator1) {
                        for( int i = elevator1 ; i<=entry ; i++) {
                    
                    System.out.println(i + "\n");
                   
                         }
                }   
            else {
                        for( int i = elevator1 ; i>=entry ; i--) {
                    
                        System.out.println(i+ "\n");
                   
                         }   
                 }
                
      
           
      valid = true; 
      while (valid==true) {     
      /* Ask user to enter the destination floor number */      
       System.out.print("Enter destination floor : ");
       destination=input.nextInt();
       
        /* Check if the destination floor entered is between 1 and 9 */
       if(destination<1 || destination>9) {
           System.out.println("Please enter destination floor between 1 and 9");
       }
       else{
           valid = false;
       }
       }
       
        /* Print sequence of floors from the entry floor to destination floor */
      if(destination>=entry) {
           for(int i = entry ; i<= destination ; i++)
               System.out.println(i + "\n");
       }
      else {
           for(int i = entry ; i>= destination ; i--){
               System.out.println(i + "\n");
       }
       }
      
       /* Assign the destination floor number to Elevator 1 */
       elevator1=destination;
       System.out.println(" You have arrived at your destination");
       System.out.println("Elevator 1 is now at floor number " + destination + "\n");
        }
    
        
        
       
       
       
       
       
       
        
         /* Check if Elevator 2 is nearest to the user */
        else if (absolute2<=absolute1 && absolute2<=absolute3 && absolute2<=absolute4) {
        System.out.println("Elevator 2 is coming to pick you up");
         /* Print the sequence of floors the elevator has to travel to reach the user */
                if (entry>=elevator2) {
                        for(int i = elevator2 ; i<=entry ; i++) {
                    
                    System.out.println(i + "\n");
                   
                        }
                
                }
                else {
                        for( int i = elevator2 ; i>=entry ; i--) {
                    
                    System.out.println(i + "\n");
                   
                        }
                }
       valid = true;
       while (valid==true) {         
        /* Ask user to enter the destination floor number */       
       System.out.print("Enter destination floor : ");
       destination=input.nextInt();
       
       /* Check if the destination floor entered is between 1 and 9 */
       if(destination<1 || destination>9) {
           System.out.println("Please enter destination floor between 1 and 9");
       }
       else{
           valid = false;
       }
       }
       
       /* Print sequence of floors from the entry floor to destination floor */
        if (destination>=entry) {
           for(int i = entry ; i<= destination ; i++)
               System.out.println(i + "\n");
       }
       else {
           for(int i = entry ; i>= destination ; i--)
               System.out.println(i + "\n");
        }
       
       /* Assign the destination floor number to Elevator 2 */
       elevator2=destination;
       System.out.println(" You have arrived at your destination");
       System.out.println("Elevator 2 is now at floor number " + destination+ "\n");
       
        }
        
        
        
        
        
        
        
        
    
        
        
         /* Check if Elevator 3 is nearest to the user */
        else if (absolute3<=absolute2 && absolute3<=absolute1 && absolute3<=absolute4) {
       System.out.println("Elevator 3 is coming to pick you up");
                if (entry>=elevator3) {
            
                         for(int i = elevator3 ; i<=entry ; i++) {
                    
                    System.out.println(i + "\n");
                    
                        }
                }   
                else {
          
                         for( int i = elevator3 ; i>=entry ; i--) {
                    
                    System.out.println(i + "\n");
                   
                        }
                }
                
       valid = true;         
       while (valid==true) {
         /* Ask user to enter the destination floor number */  
       System.out.print("Enter destination floor : ");
       destination=input.nextInt();
       
        /* Check if the destination floor entered is between 1 and 9 */
     
       if(destination<1 || destination>9) {
           System.out.println("Please enter destination floor between 1 and 9");
       }
       else{
           valid = false;
       }
       }
                
        /* Print sequence of floors from the entry floor to destination floor */
        if (destination>=entry) {
           for(int i = entry ; i<= destination ; i++)
               System.out.println(i + "\n");
       }
       else {
           for(int i = entry ; i>= destination ; i--)
               System.out.println(i + "\n");
                }
      
       
       /* Assign the destination floor number to Elevator 3 */
       elevator3=destination;
       System.out.println(" You have arrived at your destination");
       System.out.println("Elevator 3 is now at floor number " + destination+ "\n");
        }
        
        
        
        
        
        
        
        
        
        
        /* Check if Elevator 4 is nearest to the user */
        else if (absolute4<=absolute2 && absolute4<=absolute3 && absolute4<=absolute1) {
       System.out.println("Elevator 4 is coming to pick you up");
                if (entry>=elevator4) {
                        for( int i = elevator4 ; i<=entry ; i++) {
                    
                    System.out.println(i + "\n");
                   
                        }
                }
                else {
                        for( int i = elevator4 ; i>=entry ; i--) {
                    
                    System.out.println(i + "\n");
                   
                        }
                }
       
        valid = true;        
        while (valid==true) {        
        /* Ask user to enter the destination floor number */                  
       System.out.print("Enter destination floor : ");
       destination=input.nextInt();
       
        /* Check if the destination floor entered is between 1 and 9 */
     
       if(destination<1 || destination>9) {
           System.out.println("Please enter destination floor between 1 and 9");
       }
       else{
           valid = false;
       }
       }        
         /* Print sequence of floors from the entry floor to destination floor */      
        if (destination>=entry) {
           for(int i = entry ; i<= destination ; i++)
               System.out.println(i + "\n");
       }
       else {
           for(int i = entry ; i>= destination ; i--)
               System.out.println(i + "\n");
                }
       
       
       /* Assign the destination floor number to Elevator 4 */
       elevator4=destination;
       System.out.println(" You have arrived at your destination");
       System.out.println("Elevator 4 is now at floor number " + destination+ "\n");
        }
        
        
        else {
            System.out.println("Invalid Input");
        }
      }
    }
}
    

