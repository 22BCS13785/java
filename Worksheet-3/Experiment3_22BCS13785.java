abstract class Account{
    double interestrate;
    double amount;
    abstract double calculateint();
}

class fd extends Account{

    double interestrate;
    double amount;
    int days;
    int age;
    double intere;

    public fd(int amt, int time, int age1){
        amount = amt;
        days = time;
        age = age1;
    }

    double calculateint() {
        if(7 >= days && days <= 14){
            interestrate = 4.5;
        }
        else if(15 >= days && days <= 29){
            interestrate = 4.75;
        }
        else if(30 >= days && days <= 45){
            interestrate = 5.5;
        }
        else if(46 >= days && days <= 60){
            interestrate = 7;
        }
        else if(61 >= days && days <= 184){
            interestrate = 7.5;
        }
        else if(185 >= days && days <= 365){
            interestrate = 8;
        }

        if(age > 65){
            interestrate += 0.5;
        }

        intere = amount + (amount * interestrate/100 * days/365);

        return intere;
    }
}

class sb extends Account{

    double interestrate;
    double amount;
    boolean nri;

    sb(int amount1, boolean nri1){
        amount = amount1;
        nri = nri1;
    }

    double calculateint() {

        if(nri){
            interestrate = (amount * 6 * 1/100);
        }
        else{
            interestrate = (amount * 4 * 1/100);
        }

        return amount + interestrate;
    }
}

class rd extends Account{
    double interestrate;
    double amount;
    int months;
    double monthamount;

    rd(int months1, double amount1){
        amount = amount1;
        months = months1;
    }

    double calculateint(){
        if(months == 6){
            interestrate = 7.5;
        }
        else if(months == 9){
            interestrate = 7.75;
        }
        else if(months == 12){
            interestrate = 8;
        }
        else if(months == 15){
            interestrate = 8.25;
        }
        else if(months == 18){
            interestrate = 8.5;
        }
        else if(months == 21){
            interestrate = 8.75;
        }

        return monthamount = amount + (amount * interestrate/365 * months);
    }
}

public class Experiment3_22BCS13785 {
    public static void main(String[] args) {

        fd obj = new fd(10000, 350, 66);
        System.out.println("Interest on FD " + obj.calculateint());

        sb obj1 = new sb(10000, true);
        System.out.println("Interest on SB " + obj1.calculateint());

        rd obj2 = new rd(12, 10000);
        System.out.println("Interest on RD " + obj2.calculateint());
    }
}