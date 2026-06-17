import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {
    Connection con;
    Scanner sc;
    AccountManager(Connection con, Scanner sc){
        this.con=con;
        this.sc=sc;
    }
    public void credit_money(long acc_no) {
        System.out.println("How much money you want to credit ");
        double creditAmount = sc.nextDouble();
        System.out.println("Enter security pin");
        int p = sc.nextInt();
        if (acc_no != 0) {
            try {
                con.setAutoCommit(false);
                PreparedStatement preparedStatement = con.prepareStatement("select * from accounts where account_number=? and security_pin=?");
                preparedStatement.setLong(1, acc_no);
                preparedStatement.setInt(2, p);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    double balance = resultSet.getDouble("balance");
                    PreparedStatement preparedStatement1 = con.prepareStatement("update accounts set balance=balance+? where account_number=?");
                    preparedStatement1.setDouble(1, creditAmount);
                    preparedStatement1.setLong(2, acc_no);
                    int rows = preparedStatement1.executeUpdate();
                    if (rows > 0) {
                        System.out.println("Rs " + creditAmount + " credited successfully");
                        con.commit();
                    } else {
                        System.out.println("Transaction failed");
                        con.rollback();
                        con.setAutoCommit(true);
                    }
                } else {
                    System.out.println("Invalid Pin");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void debit_money(long acc_no) {
        System.out.println("Enter the Amount");
        double debitAmount = sc.nextDouble();
        System.out.println("Enter Pin");
        int p = sc.nextInt();
        if (acc_no != 0) {
            try {
                con.setAutoCommit(false);
                PreparedStatement pstmt = con.prepareStatement("select *from accounts where account_number=? and security_pin=?");
                pstmt.setLong(1, acc_no);
                pstmt.setInt(2, p);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    double balance = rs.getDouble("balance");
                    if (balance >= debitAmount) {
                        PreparedStatement ps = con.prepareStatement("Update accounts set balance=balance-? where account_number=?");
                        ps.setDouble(1, debitAmount);
                        ps.setLong(2, acc_no);
                        int rows = ps.executeUpdate();
                        if (rows > 0) {
                            System.out.println("Transaction Successfull");
                            System.out.println("Rs " + debitAmount+" debited successfully");
                            con.commit();
                        } else {
                            System.out.println("Transaction failed");
                            con.rollback();
                            con.setAutoCommit(true);
                        }
                    } else {
                        System.out.println("Insufficient Amount");
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void transfer_money(long sender_acc_no){
        System.out.println("How much money to transfer");
        double amnt=sc.nextDouble();
        System.out.println("Enter the reciever acoount no");
        long recAcc=sc.nextLong();
        System.out.println("Enter pin");
        int p=sc.nextInt();
        try {
            con.setAutoCommit(false);
            if (sender_acc_no != 0 && recAcc!=0) {
                PreparedStatement preparedStatement = con.prepareStatement("select * from accounts where account_number=? and security_pin=?");
                preparedStatement.setLong(1, sender_acc_no);
                preparedStatement.setInt(2, p);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    double current_balanace = resultSet.getDouble("balance");
                    if (current_balanace >= amnt) {
                        PreparedStatement ps = con.prepareStatement("Update accounts set balance=balance-? where account_number=?");
                        PreparedStatement ps1=con.prepareStatement("update accounts set balance=balance+? where account_number=?");
                        ps.setDouble(1, amnt);
                        ps.setLong(2, sender_acc_no);
                        ps1.setDouble(1,amnt);
                        ps1.setLong(2,recAcc);
                        int rows1= ps.executeUpdate();
                        int rows2=ps1.executeUpdate();
                        if (rows1 > 0 && rows2>0) {
                            System.out.println("Transaction Successfull");
                            System.out.println("RS " + amnt+" transfered successfully");
                            con.commit();
                        } else {
                            System.out.println("Transaction failed");
                            con.rollback();
                            con.setAutoCommit(true);
                        }
                    } else {
                        System.out.println("Insufficient Amount");
                    }

                }
                else {
                    System.out.println("Invalid Pin");
                }
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }






    }
    public void get_balance(long acc_no){
        System.out.println("Enter the pin ");
        int p=sc.nextInt();
        if(acc_no!=0){
            try{
                PreparedStatement preparedStatement= con.prepareStatement("select * from accounts where security_pin=? and account_number=?");
                preparedStatement.setInt(1,p);
                preparedStatement.setLong(2,acc_no);
                ResultSet rs= preparedStatement.executeQuery();
                if(rs.next()){
                    double balanace=rs.getDouble("balance");
                    System.out.println("Balance is " + balanace+" Rs");
                }
                else {
                    System.out.println("Invalid pin");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }


        }
    }
}
