
import java.util.*;

public class DataBaseManager {
    
    private List<DataBase> DataBaseArr;
    
    static DataBaseManager instance = new DataBaseManager();
          
    private DataBaseManager(){
   
      DataBaseArr = new ArrayList<DataBase>();
 
     };
 
    public static DataBaseManager GetInstance() {
       return instance;
    }
    

    private DataBase FindDatabase(String userid,String databaseName){
        for (DataBase var : DataBaseArr){
            if(databaseName.compareTo(var.GetName()) == 0){
                return var;
            }
        }
        return null;

    }

    public void ShowDatabases(String userid) {
        System.out.println("Call DatabaseManager: show databases");
        
        for (DataBase var : DataBaseArr){
            System.out.println(var.GetName());
        }
        
    }
    
    public void UseDatabase(String userid,String parameter) {
        User tempUser = null;
        DataBase tempDataBase = null;
        if( (tempUser = UserManager.GetInstance().FindUser(userid)) == null
            || (tempDataBase = DataBaseManager.GetInstance().FindDatabase(userid, parameter)) == null)
            return;
        
        tempUser.SetDatabase(tempDataBase);

    }
    

    
    public boolean CreateDatabase(String userid,String parameter) {
        System.out.println("Call DatabaseManager: create database");
 //       int result = TransactionManager.GetInstance().CheckUser(Transaction_Type.DataBase, userid, parameter);
 //       if(result == -1)
  //          return false;


        new Lock(Transaction_Type.DataBase,userid,parameter);
        DataBase newDataBase = FindDatabase(userid,parameter);
        if(newDataBase == null){
            DataBaseArr.add(new DataBase(parameter));
            FileManager.GetInstance().CreateNewDirectory(parameter);
        }

              
       return true;
    }

    public void DropDatabase(String userid,String parameter){
        System.out.println("Call DatabaseManager : drop Database");

        for (DataBase var : DataBaseArr){
            if(var.GetName().compareTo(parameter) == 0){
                DataBaseArr.remove(var);
                return;
            }
        }
    }

    public void ShowTables(String userid,String parameter){
        System.out.println("Call Database: show tables");
        User tempUser = null;
        if((tempUser = UserManager.GetInstance().FindUser(userid)) == null)
            return;
        
        for (DataBase var : DataBaseArr){
            if(var == tempUser.GetDatabase()){
                var.ShowTables();
            }
        }
            
        

    }


    public void SelectTable(String userid,String parameter){
        System.out.println("Call Database: select tables");
        
        //Get User's database
        User tempUser = null;
        if((tempUser = UserManager.GetInstance().FindUser(userid)) == null)
            return;

        for (DataBase var : DataBaseArr){
            if(var == tempUser.GetDatabase()){
                var.Select(userid, parameter);
            }
        }
        
    }

    public void CreateTable(String userid,String parameter){
        
        System.out.println("Call Database: create tables");

        User tempUser = null;
        if((tempUser = UserManager.GetInstance().FindUser(userid)) == null)
            return;
        
        for (DataBase var : DataBaseArr){
            if(var == tempUser.GetDatabase()){
                var.CreateTable(userid, parameter);
            }
        }

        /*

        String[] paremeters = parameter.split(" ",2);
        
        //Get User's database

        for (DataBase var : DataBaseArr){
            if(var == User's data base){
                var.selectTables();
            }
        }
        */
    }

    public void InsertData(String userid,String parameter){
        System.out.println("Call Database: Insert Data");
        
        

        User tempUser = null;
        if((tempUser = UserManager.GetInstance().FindUser(userid)) == null)
            return;
        
        for (DataBase var : DataBaseArr){
            if(var == tempUser.GetDatabase()){
                var.Insert(userid, parameter);
            }
        }
        /*
        //Get User's database

        for (DataBase var : DataBaseArr){
            if(var == User's data base){
                var.selectTables();
            }
        }
        */
    }

    public void DeleteData(String userid,String parameter){
        System.out.println("Call Database: Delete Data");

        User tempUser = null;
        if((tempUser = UserManager.GetInstance().FindUser(userid)) == null)
            return;
        
        for (DataBase var : DataBaseArr){
            if(var == tempUser.GetDatabase()){
                var.Delete(userid, parameter);
            }
        }
    }


    public void DropTable(String userid,String parameter){
        System.out.println("Call Database : drop Table");

        
        User tempUser = null;
        if((tempUser = UserManager.GetInstance().FindUser(userid)) == null)
            return;
        
        for (DataBase var : DataBaseArr){
            if(var == tempUser.GetDatabase()){
                var.DropTable(userid, parameter);
            }
        }
    }

 }