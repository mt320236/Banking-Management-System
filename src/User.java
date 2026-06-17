import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    Connection con;
    Scanner sc;
    User(Connection con, Scanner sc){
        this.con=con;
        this.sc=sc;
    }
    public void register(){
        String register_query="insert into user(full_name,email,password) values(?, ?, ?)";
        System.out.println("Enter Full Name");
        sc.nextLine();
        String name=sc.nextLine();
        System.out.println("Enter email");
        String email=sc.nextLine();
        System.out.println("Enter Password");
        String pass=sc.nextLine();

        try {
            if(userExist(email)){
                System.out.println("User already exist");
                return ;
            }
            else{
            PreparedStatement pstmt = con.prepareStatement(register_query);
            pstmt.setString(1,name);
            pstmt.setString(2,email);
            pstmt.setString(3,pass);
            int rows=pstmt.executeUpdate();
            if(rows>0){
                System.out.println("Registred succesfully");
            }
            else {
                System.out.println("Registration failed");
            }
            }
         } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }
    public String  login(){
        System.out.println("Enter the email");
        sc.nextLine();
        String email=sc.nextLine();
        System.out.println("Enter the password");
        String password=sc.nextLine();
        String loginquery="Select * from user where email=? AND password= ?";
        try{
         PreparedStatement pstmt=con.prepareStatement(loginquery);
         pstmt.setString(1,email);
         pstmt.setString(2,password);
            ResultSet rs= pstmt.executeQuery();
            if(rs.next()){
                return email;
            }
         
          }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }
    public Boolean userExist(String email ) {
        String q = "select * from user where email = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setString(1, email);
            ResultSet rs= pstmt.executeQuery();
            if(rs.next()){
                return true;
            }
        }
         catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }



}
