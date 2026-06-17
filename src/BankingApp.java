import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingApp {
   private  static String url="jdbc:mysql://127.0.0.1:3306/banking_system";
   private static String username="Your_username";
   private static String password="Your_password";
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try{
            Connection con= DriverManager.getConnection(url,username,password);
            Scanner sc=new Scanner(System.in);
            User user=new User(con,sc);
            Accounts accounts=new Accounts(con,sc);
            AccountManager accountManager=new AccountManager(con,sc);
            String email;
            long acc_no;
            while(true) {
                System.out.println(" Choose one option :- ");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.println();

                int a=sc.nextInt();

                switch (a) {
                    case 1:
                        user.register();
                        break;
                    case 2:
                        email=user.login();
                        if(email!=null){
                            System.out.println("User logged in");
                            if(!accounts.accountExist(email)){
                                System.out.println("1: Open Bank Account");
                                System.out.println("2: Exit");
                                int input=sc.nextInt();
                                if(input==1){
                                   acc_no= accounts.openAcount(email);
                                    System.out.println("Account opened successfully");
                                    System.out.println("Your account no is "+acc_no);
                                }
                                else{
                                    break;
                                }

                            }
                            acc_no=accounts.getAccountNumber(email);
                            int choice=0;
                            while(choice!=5){
                                System.out.println();
                                System.out.println("1 : Debit Money");
                                System.out.println("2 : Credit Money");
                                System.out.println("3 : Transfer Money");
                                System.out.println("4 : Check Balance");
                                System.out.println("5 : Log out");
                                choice= sc.nextInt();
                                switch (choice){
                                    case 1:
                                        accountManager.debit_money(acc_no);
                                        break;
                                    case 2:
                                        accountManager.credit_money(acc_no);
                                        break;
                                    case 3:
                                        accountManager.transfer_money(acc_no);
                                        break;
                                    case 4:
                                        accountManager.get_balance(acc_no);
                                        break;
                                    case 5:
                                        break;
                                    default:
                                        System.out.println("Choose valid option");
                                }
                            }


                        }
                    case 3:
                        System.out.println("Thank You For Using Banking Sytstem");
                        return;
                    default:
                        System.out.println("Choose Valid Option");

                }
            }



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}
