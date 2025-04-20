// Develop a Spring-based application integrated with Hibernate to manage transactions. 
// Create a banking system where users can transfer money between accounts, 
// ensuring transaction consistency.

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Entity
@Table(name = "account")
class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String accountHolder;
    private double balance;

    public Account() {}

    public Account(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }
}

@Entity
@Table(name = "transaction")
class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int fromAccount;
    private int toAccount;
    private double amount;

    public Transaction() {}

    public Transaction(int fromAccount, int toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    // Getter and setter methods
}

@Configuration
@EnableTransactionManagement
class AppConfig {

    @Bean
    public SessionFactory sessionFactory() {
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(Transaction.class)
                .buildSessionFactory();
    }
}

class BankingService {

    private SessionFactory sessionFactory;

    public BankingService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void transferMoney(int fromAccountId, int toAccountId, double amount) {
        Session session = sessionFactory.getCurrentSession();

        Account fromAccount = session.get(Account.class, fromAccountId);
        Account toAccount = session.get(Account.class, toAccountId);

        if (fromAccount.getBalance() >= amount) {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);

            Transaction transaction = new Transaction(fromAccountId, toAccountId, amount);
            session.save(transaction);

            session.getTransaction().commit();
        } else {
            throw new RuntimeException("Insufficient balance");
        }
    }
}

public class experiment9_3_22BCS13785 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        BankingService bankingService = context.getBean(BankingService.class);

        try {
            bankingService.transferMoney(1, 2, 100.00);
            System.out.println("Transaction completed successfully.");
        } catch (Exception e) {
            System.out.println("Transaction failed: " + e.getMessage());
        }

        context.close();
    }
}
