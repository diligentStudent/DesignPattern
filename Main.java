

public class Main{


    public static void main(String[] args){

        UserManager.GetInstance().CreateUser("test", "1234");
        CommandManager.GetInstance().Parser("test create database database1");
        CommandManager.GetInstance().Parser("test use database1");
        CommandManager.GetInstance().Parser("test create table useraccount ( id, password )");
        CommandManager.GetInstance().Parser("test Insert into useraccount values ( kinam, 1234 )");
        CommandManager.GetInstance().Parser("test Delete from useraccount Where id = kinam");

  /*     
        CommandManager.GetInstance().Parser("test Select * from useraccount");


        UserManager.GetInstance().CreateUser("test2", "1234");
       // CommandManager.GetInstance().Parser("test2 create database database1");
       CommandManager.GetInstance().Parser("test2 use database1");
       CommandManager.GetInstance().Parser("test2 Insert into useraccount values ( kinam2, 1234 )");
*/       

        while(true){

        }

    }

}