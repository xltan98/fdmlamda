package lambda.Exercises;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Runner implements Function<BankAccount,String>, Predicate<BankAccount>,Consumer<BankAccount>,BiConsumer<BankAccount,Integer>{
@Override
public void accept(BankAccount account, Integer deduct){

}
     
    @Override
    public Boolean test(BankAccount account) {
        return account.getAccountType().equalsIgnoreCase("current/checking");
   }


        @Override
        public String apply(BankAccount account){
                return String.format("Name: %s Balance: %f",account.getAccountHolder(),account.getBalance());
        }
        @Override
        public void accept(BankAccount account) {
                double newBalance = account.getBalance()-10;
                account.setBalance(newBalance);
                
            }

public static void main(String[] args) {
     


        BankAccount account1 = new BankAccount (12345678,987654,"Mr J Smith", "savings",1.1,25362.91);
        BankAccount account2 = new BankAccount (97854321,234567,"Ms J Jones", "current/checking",02,550);
        BankAccount account3 = new BankAccount(74639572, 946284, "Dr T Williams", "savings", 1.1, 4763.32);
        BankAccount account4 = new BankAccount(94715453, 987654, "Ms S Taylor", "savings", 1.1, 10598.01);
        BankAccount account5 = new BankAccount(16254385, 234567, "Mr P Brown", "current/checking", 0.2, -195.74);
        BankAccount account6 = new BankAccount(38776543, 946284, "Ms F Davies", "current/checking", 0.2, -503.47);
        BankAccount account7 = new BankAccount(87609802, 987654, "Mr B Wilson", "savings", 1.1, 2.5);
        BankAccount account8 = new BankAccount(56483769, 234567, "Dr E Evans", "current/checking", 0.2, -947.72);

        //Function<BankAccount, String> getInfo = new Runner();
       // Function<BankAccount, String > calculateInterest = (account)-> {double interest=(account.getBalance())*(account.getInterestRate()/1000);
        //return Double.toString(interest);};

        Function<BankAccount, String > calculateInterest = (account)-> {double interest=(account.getBalance())*(account.getInterestRate()/1000);
                if (interest>0){
                return Double.toString(interest);}
                else{ return "0";}
        };

        Runner runner = new Runner();

        Predicate<BankAccount> isCurrentAccount = (account)->{return account.getAccountType().contains("current");};

        Consumer<BankAccount> deductFee= (account)-> {
                double newbalance = account.getBalance()-10;
        account.setBalance(newbalance);};

        BiConsumer<BankAccount,Integer> withdraw= (account,deduct)->{
                double newBalance=account.getBalance()-deduct;
                account.setBalance(newBalance);
        };

        deductFee.accept(account2);
        withdraw.accept(account1,100);

        

        System.out.println(calculateInterest.apply(account2));
        System.out.println(calculateInterest.apply(account8));
        System.out.println(isCurrentAccount.test(account1));
        System.out.println(isCurrentAccount.test(account6));
        System.out.println(account2.getBalance());
        System.out.println(account1.getBalance());
        



      //getInfo = (account)-> String.format ("%s : %f ", account.getAccountHolder(),account.getBalance());
     

ArrayList<BankAccount> accounts = new ArrayList<>();

accounts.add(account1);
accounts.add(account2);
accounts.add(account3);
accounts.add(account4);
accounts.add(account5);
accounts.add(account6);
accounts.add(account7);
accounts.add(account8);



accounts.stream()
.forEach((account) -> account.setBalance(account.getBalance() - 10));

/*accounts.stream()
.filter((account)->account.getBalance()< (-500))
.forEach(accounts::remove);*/
//:: is a reference operator

Comparator<BankAccount> compareByBalance =(a1,a2)-> Double.compare(a1.getBalance(),a2.getBalance());
accounts.sort(compareByBalance);

accounts.stream()
.forEach ((account)-> System.out.println(String.format("Account Number: %d Account Holder: %s Account Type: %s Balance: %f",
account.getAccountNumber(), account.getAccountHolder(), account.getAccountType(), account.getBalance())));

}



}

