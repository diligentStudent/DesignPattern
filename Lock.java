

class Lock{
    
    private Transaction transaction;

    public Lock(Transaction_Type type,String userId, String name){
        System.out.println("create Lock");
        transaction = TransactionManager.GetInstance().CreateTransaction(type,userId,name);
    }

    public void finalize(){
        System.out.println("finalize!!");
        TransactionManager.GetInstance().RemoveTransaction(transaction);
    }

}