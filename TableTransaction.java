
public class TableTransaction extends Transaction{
    
    public TableTransaction(String userId,String name){
        super(userId,name);

        transaction_Type = Transaction_Type.Table;
    }

}