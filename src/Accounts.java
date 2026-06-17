import java.sql.*;
import java.util.Scanner;

public class Accounts {
    Connection con;
    Scanner sc;

    Accounts(Connection con, Scanner sc) {
        this.con = con;
        this.sc = sc;
    }

    public long openAcount(String email) {
        if(!accountExist(email)){

            String query= "insert into accounts(account_number,full_name,email,balance,security_pin) values (? ,?, ?, ?, ?)";
            try{
                long acc_no=generateaccountNumber();
                PreparedStatement pstmt= con.prepareStatement(query);
                System.out.println("Enter full name");
                sc.nextLine();
                String name=sc.nextLine();
                System.out.println("Enter initial amount");
                double amnt=sc.nextDouble();
                System.out.println("Enter security pin");
                int pin=sc.nextInt();
                pstmt.setLong(1,acc_no);
                pstmt.setString(2,name);
                pstmt.setString(3,email);
                pstmt.setDouble(4,amnt);
                pstmt.setInt(5,pin);
                int rows= pstmt.executeUpdate();
                if(rows>0){
                    return acc_no;
                }



            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        throw new RuntimeException("Account already exist");


    }

    public Long getAccountNumber(String email) {
        String q="Select account_number from accounts where email = ?";
        try {
            PreparedStatement pstmt=con.prepareStatement(q);
            pstmt.setString(1,email);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                return rs.getLong("account_number");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new RuntimeException("Account doesn't exist");


    }


    public long generateaccountNumber() {
        try{
            Statement stmt= con.createStatement();
            ResultSet rs= stmt.executeQuery("select account_number from accounts ORDER BY account_number DESC LIMIT 1 ");
           if(rs.next()){
            long last_accountNo=rs.getLong("account_number");
            long acc_no=last_accountNo+1;
           return acc_no;}
           else{
               return 4488001;
           }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 4488001;
    }
    public Boolean accountExist(String email){
        String query=" select * from accounts where email= ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,email);
            ResultSet rs= pstmt.executeQuery();
            if(rs.next()){
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }return false;

    }
}
