import java.util.Scanner;
public class BankUI {
    static int accNo;
    static double amount;
    static String choices = null;
    static Scanner input= new Scanner(System.in);
    public  BankUI() {
            do {
                //Displaying option
                System.out.println("Enter your choice \n 1. Deposit \n 2. Withdraw \n 3. Balance Check \n 4. Generate Statement \n 5. Exit");

                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        BankBusinessService BS1=new BankBusinessService();
                        System.out.println("Enter Account Number [Starting 1001]");

                        //user Input[account  number]/usr/lib/jvm/java-8-openjdk-amd64

                        accNo=input.nextInt();

                        //Account Validation
                        if(accNo>1000 && BS1.accountNumValidation(accNo)){
                            System.out.println("Enter Amount to be deposit");

                            //user Input [amount]
                            amount=input.nextInt();
                            if(amount>0) {

                                //Deposit amount
                                BS1.deposit(accNo,amount);
                                System.out.println("Amount deposited RS " + amount +"\n Balance : " + BS1.checkBalance(accNo));
                            }
                            else {
                                System.out.println("Invalid amount");
                            }
                        }
                        else System.out.println("INVALID ACCOUNT NUMBER");
                        System.out.print("Do You Want to continue...\npress Y/y => YES or N/n => NO  ");

                        //taking input for user choice
                        choices = input.next();
                        break;
                    case 2:
                        BankBusinessService BS2=new BankBusinessService();
                        System.out.println("Enter Account Number [Starting 1001]");

                        //user Inputs [account number]
                        accNo=input.nextInt();

                        //Account Validation
                        if (accNo>1000 && BS2.accountNumValidation(accNo)) {
                            System.out.println("Enter Amount to be withdraw");

                            //user Inputs [amount]
                            amount = input.nextInt();
                            if (amount < BS2.checkBalance(accNo) && amount > 0) {

                                //withdraw amount
                                BS2.withdraw(accNo,amount);
                                System.out.println("Amount withdraw RS " + amount + "\n Balance : " + BS2.checkBalance(accNo));
                            } else {
                                System.out.println("Insufficient Balance");
                            }
                        }
                        else System.out.println("INVALID ACCOUNT NUMBER");
                        System.out.print("Do You Want to continue...\npress Y/y => YES or N/n => NO  ");

                        //taking user input for user choice
                        choices = input.next();
                        break;
                    case 3:
                        BankBusinessService BS3=new BankBusinessService();
                        System.out.println("Enter Account Number [Starting 1001]");

                        //user inputs [Account number]
                        accNo=input.nextInt();

                        //Account Number validation
                        if(accNo>1000 && BS3.accountNumValidation(accNo))

                            //checking Balance
                            System.out.println("Account Balance : " + BS3.checkBalance(accNo));
                        else{System.out.println("INVALID ACCOUNT NUMBER");}
                        System.out.print("Do You Want to continue...\npress Y/y => YES or N/n => NO  ");

                        //user Inputs for choice
                        choices = input.next();
                        break;
                    case 4:
                        BankBusinessService BS4=new BankBusinessService();
                        System.out.println("Enter Account Number [Starting 1001]");

                        //User Input [Account Number]
                        accNo=input.nextInt();

                        //Account Validation
                       if(accNo>1000 && BS4.accountNumValidation(accNo)) BS4.generateStatement(accNo);
                       else System.out.println("INVALID ACCOUNT NUMBER");
                        System.out.print("Do You Want to continue...\npress Y/y => YES or N/n => NO  ");

                        //taking input from user for user choice
                        choices = input.next();
                        break;
                    case 5:

                        //Exit operation
                        return;
                    default:

                        //Wrong choice intimation
                        System.out.println("choose correct choice");
                }
            }while (choices.equals("Y")|| choices.equals("y"));
    }
}