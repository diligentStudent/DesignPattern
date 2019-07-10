import java.util.*;

public class DataBase {
   private String name_;
   private List<Table> tableArray = new ArrayList<Table>();

   public DataBase(String name){
       this.name_ = new String(name);
   }

   public String GetName(){
       return name_;
   }


   // database 에서 fileamanager::createfile
   public void CreateTable(String userid, String parameter) {
        System.out.println("Call Database: create table");
        //FileManager.createfile
        
        String[] parameters = parameter.split(" ",2); // paramters = name + scheme

        Table newTable = FindTable(userid,parameters[0]);
        
        if(newTable == null){
            tableArray.add(new Table(this,parameters[0],parameters[1]));
        }

        

   }

   public void Select(String userid, String parameter) {
        System.out.println("Call Database: select");

        String tableName = GetNextParameter("from", parameter);

        Table tempTable = null;
        if( (tempTable = FindTable(userid, tableName)) == null)
            return;

        String[] parameters = parameter.split(" ");


        String key = new String(parameters[0]);

        for (int i = 1; i < parameters.length ; i++){
            if(parameters[i].compareTo("from") == 0)
                break;
            key += " " + parameters[i];
        }

        tempTable.SelectData(key);

               
    }

    

    public String GetNextParameter(String target, String parameter){

        String[] parameters = parameter.split(" ");
        
        for (int i = 0 ; i < parameters.length ; i++){
            if(parameters[i].compareTo(target) == 0){
                return parameters[i+1];
            }
        }
        return null;


    }


    public boolean Delete(String userid, String parameter) {
        System.out.println("Call Database: key");

        for (Table var : tableArray){
            if(var.GetName().compareTo(parameter.split(" ",3)[1]) == 0){
                var.DeleteData(parameter.split(" ",3)[2]);
            }
       }

        return true;
    }

   public void Insert(String userid, String parameter) {

    String tableName = GetNextParameter("into", parameter);
    String retString;

    Table tempTable = null;
    if( (tempTable = FindTable(userid, tableName)) == null)
        return;
        
        int charPos = parameter.indexOf("(");
        retString = parameter.substring(charPos+1, parameter.lastIndexOf(")")-1);
        tempTable.InsertData(retString);
    }



   public Table FindTable(String userid, String parameter) {
        for(Table var : tableArray){
            if(var.GetName().compareTo(parameter) == 0)
                return var;
        }
        return null;
   }

   public void ShowTables() {
    System.out.println("Call Database: show tables");
    
        for (Table var : tableArray){
             System.out.println(var.GetName());
        }
    
    }

    public void DropTable(String userid, String parameter){
        for (Table var : tableArray){
            if(var.GetName().compareTo(parameter) == 0){
                var.Drop(parameter);
            }
       }
    }
}