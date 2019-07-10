import java.util.*;

public class TransactionManager{


    private List<Transaction> transactionArr = new ArrayList<Transaction>();

    static TransactionManager instance = new TransactionManager();
   

    private TransactionManager(){

    }

    public static TransactionManager GetInstance(){
        return instance;
    }

    public int CheckUser(Transaction_Type type, String userId, String name){

        for (Transaction var : transactionArr){
            if(var.GetType() == type && var.GetName().compareTo(name) == 0){
                if(userId.compareTo(var.GetUserId()) == 0){
                    
                    return 1; // check 한사람이 소유자
                }
                System.out.println("DataBase already using now~!");
                return -1; // check 한 사람이 소유자가 아니다.
            }
            
        }
       return 0; // 트랜잭션이 존재하지 않음
   }

    public Transaction CreateTransaction(Transaction_Type type,String userId, String name){
        Transaction transaction = null;
        
        if(type == Transaction_Type.Table){
            transaction = new TableTransaction(userId,name);
        }
        else if(type == Transaction_Type.DataBase){
            transaction = new DataBaseTransaction(userId,name);
        }

       transactionArr.add(transaction);
          
       return transaction;
   }

    public void RemoveTransaction(Transaction var){
        transactionArr.remove(var);
        
   }


   synchronized public Lock Locking(Transaction_Type type, String userid,String objectName){
    int result = TransactionManager.GetInstance().CheckUser(Transaction_Type.Table, userid, objectName);
             if(result == -1){
                return null;
             }
    return new Lock(Transaction_Type.Table, userid,  objectName);
 }
}