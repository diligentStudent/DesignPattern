import java.util.HashMap;

public class CommandManager {
   private HashMap<String,Functions> CommandFunc = new HashMap();
   static CommandManager instance = new CommandManager();
   private CommandManager() {
      Initialize();
   }
   
   public static CommandManager GetInstance() {

      return instance;
   }
   
   public void Parser(String Command) {
         String lowerCommand = Command.toLowerCase();

         String[] parameters = lowerCommand.split(" ");
         String[] key_values;
         switch(parameters[1]){
            case "create":  
            case "show":
            case "drop":
                  key_values = distribution(lowerCommand, 2);
                  
            break;

            default:
                  key_values = distribution(lowerCommand, 1);
         }




        for (Object var : key_values) {
           System.out.println(var);
           
        }
        


        Functions func = CommandFunc.get(key_values[1]);
        func.CallBack(key_values[0],key_values[2]);
   
   }

   private String[] distribution(String str, int count){
      String[] initialize = str.split(" ",2); // id 랑 명령어 
      String[] parameters = initialize[1].split(" "); // 명령어랑 파라미터
      

      String id = new String(initialize[0]);
      if(count >= parameters.length)
         return new String[]{id,initialize[1], null};


      
      String key = new String(parameters[0]);
      String value = new String(parameters[count]);
      for(int i = 1; i< count ; i++){
         key += " " + parameters[i];
      }

      for (int i = count + 1; i < parameters.length; i++){
         value += " " + parameters[i];
      }



      return new String[]{ id,key, value};

   }


   public void RegistFunc(String Command, Functions funcs){
        CommandFunc.put(Command,funcs);
   }




   private void Initialize(){
      //create database
      
      RegistFunc("create database",new Functions() {
         @Override
         public void CallBack(String userid, String parameter){


            Lock lock = TransactionManager.GetInstance().Locking(Transaction_Type.Table, userid, parameter);
            if(lock == null) return;   
           
            DataBaseManager.GetInstance().CreateDatabase(userid,parameter);

            lock.finalize();
            
         }
     });

     RegistFunc("use",new Functions() {
      @Override
      public void CallBack(String userid, String parameter){
         DataBaseManager.GetInstance().UseDatabase(userid, parameter);


            
      }
  });

     RegistFunc("drop database",new Functions() {
      @Override
      public void CallBack(String userid,String parameter){

         Lock lock = TransactionManager.GetInstance().Locking(Transaction_Type.Table, userid, parameter);
         if(lock == null) return;   
        
         DataBaseManager.GetInstance().DropDatabase(userid , parameter);


         lock.finalize();
            
      }
      });


      // show databases
      RegistFunc("show databases",new Functions() {
            @Override
            public void CallBack(String userid,String parameter){
               DataBaseManager.GetInstance().ShowDatabases(userid);
               
            
            }
      });

      RegistFunc("show tables",new Functions() {
         @Override
         public void CallBack(String userid,String parameter){
            
            DataBaseManager.GetInstance().ShowTables(userid , parameter);
            
         }
      });
     
      RegistFunc("select",new Functions() {
         @Override
         public void CallBack(String userid,String parameter){
            Lock lock = TransactionManager.GetInstance().Locking(Transaction_Type.Table, userid, parameter.split(" ",3)[1]);
            if(lock == null) return;   
           
            DataBaseManager.GetInstance().SelectTable(userid , parameter);
            lock.finalize();
            
         }
      });
//////////
      RegistFunc("create table",new Functions() {
         @Override
         public void CallBack(String userid,String parameter){
            Lock lock = TransactionManager.GetInstance().Locking(Transaction_Type.Table, userid, parameter.split(" ",3)[1]);
            if(lock == null) return;   
            
            DataBaseManager.GetInstance().CreateTable(userid , parameter);
             lock.finalize();
             
         }
      });

      RegistFunc("drop table",new Functions() {
         @Override
         public void CallBack(String userid,String parameter){
            Lock lock = TransactionManager.GetInstance().Locking(Transaction_Type.Table, userid, parameter);
            if(lock == null) return;   
           
            DataBaseManager.GetInstance().DropTable(userid , parameter);
         
            lock.finalize();
            
         }
         });

      RegistFunc("insert",new Functions() {
         @Override
         
         public void CallBack(String userid,String parameter){
           
            Lock lock = TransactionManager.GetInstance().Locking(Transaction_Type.Table, userid, parameter.split(" ",3)[1]);
            if(lock == null) return;   
            DataBaseManager.GetInstance().InsertData(userid , parameter);

            lock.finalize();
             
         }
     }); 



      RegistFunc("delete",new Functions() {
         @Override
         public void CallBack(String userid,String parameter){

            Lock lock = TransactionManager.GetInstance().Locking(Transaction_Type.Table, userid, parameter.split(" ",3)[1]);
            if(lock == null) return;   
           
            DataBaseManager.GetInstance().DeleteData(userid , parameter);

            lock.finalize();
         }
     });

   }
}