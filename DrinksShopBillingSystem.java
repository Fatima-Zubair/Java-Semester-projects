package project_1;

import java.util.Scanner;

//Abstraction 
abstract class Drinks{
 protected String name; 
 protected double price;
 protected static double total=0;
   
 public abstract void buy();
}      

class Juice extends Drinks {
	
	User_Interface obj=new User_Interface();
	
 public Juice() {
     name = "Juice";
     price = 100.00;
 }
 
 @Override
 public void buy() {
     System.out.println("You choose " + name);
     System.out.print("How many " + name + "s you want to buy? :");
     Scanner input = new Scanner(System.in);
     int quantityBought = input.nextInt();
     total += quantityBought * price;

     System.out.println("You want to buy again? ");
     System.out.println("Press Y for Yes and N for N : ");
     String again = input.next();
     if (again.equalsIgnoreCase("Y")) {
         obj.order();
     } else {
         System.out.println("Your total bill: " + total);
         System.out.println("Thanks for Coming....!!!");
     }
 }
}

class Shake extends Drinks {
	
	User_Interface obj=new User_Interface();
	
 public Shake() {
     name = "Shake";
     price = 150.00;
 }

 @Override
 public void buy() {
     System.out.println("You choose " + name);
     System.out.print("How many " + name + "s you want to buy? :");
     Scanner input = new Scanner(System.in);
     int quantityBought = input.nextInt();
     total += quantityBought * price;

     System.out.println("You want to buy again? ");
     System.out.println("Press Y for Yes and N for N : ");
     String again = input.next();
     if (again.equalsIgnoreCase("Y")) {
         obj.order();
     } else {
         System.out.println("Your total bill: " + total);
         System.out.println("Thanks for Coming....!!!");
     }
 }
}

class ColdDrink extends Drinks {
	
	User_Interface obj=new User_Interface();
	
 public ColdDrink() {
     name = "Cold drink";
     price = 50.00;
 }

 @Override
 public void buy() {
     System.out.println("You choose " + name);
     System.out.print("How many " + name + "s you want to buy? :");
     Scanner input = new Scanner(System.in);
     int quantityBought = input.nextInt();
     total += quantityBought * price;

     System.out.println("You want to buy again? ");
     System.out.println("Press Y for Yes and N for N : ");
     String again = input.next();
     if (again.equalsIgnoreCase("Y")) {
         obj.order();
     } else {
         System.out.println("Your total bill: " + total);
         System.out.println("Thanks for Coming....!!!");
     }
 }
}

class User_Interface{
	public static Scanner input = new Scanner(System.in);

	public void menu() {
	     System.out.println("\t\t\t\t+===================================+");
	     System.out.println("\t\t\t\t           AVAILABLE DRINKS          ");
	     System.out.println("\t\t\t\t   1. Juice             Rs: 100.00");
	     System.out.println("\t\t\t\t   2. Shake             Rs: 150.00");
	     System.out.println("\t\t\t\t   3. Cold drink        Rs: 50.00");
	     System.out.println("\t\t\t\t   4. CANCEL                         ");
	     System.out.println("\t\t\t\t+====================================+");
	 }

	 public void order() {
	     System.out.print("Press the given number of available drink you want to buy? :");
	     int choose = input.nextInt();
	     Drinks drink;
	     switch (choose) {
	         case 1:
	             drink = new Juice();
	             break;
	         case 2:
	             drink = new Shake();
	             break;
	         case 3:
	             drink = new ColdDrink();
	             break;
	         case 4:
	             System.exit(0); // close program
	             return;
	         default:
	             System.out.println("Choose 1 to 4 only!");
	             order();
	             return;
	     }
	     drink.buy();
	     //buy is the overriden method means polymorphism is applied
	 }

}
public class DrinksShopBillingSystem {
  
 public static void main(String[] args) {
	 User_Interface obj=new User_Interface();
     obj.menu(); 
     obj.order();
 }
}


