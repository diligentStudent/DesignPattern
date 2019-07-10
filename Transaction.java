public class Transaction{

    protected Transaction_Type transaction_Type;
    protected String    propertyId;
    //mutex 대신 Synchronize 를 통한 동기화
    protected String    name;

    public Transaction(String propertyId, String name) {
        this.propertyId = propertyId;
        this.name = name;
        
    }
    public Transaction_Type GetType(){
        return transaction_Type;
    }
    public String GetUserId(){
        return propertyId;
    }

    public String GetName(){
        return name;
    }

}