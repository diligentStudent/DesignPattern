

public class DataBaseTransaction extends Transaction{
    
    public DataBaseTransaction(String userId,String name){
        super(userId, name);
        transaction_Type = Transaction_Type.DataBase;
    }

}