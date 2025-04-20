import java.util.Scanner;

class experiment1_22BCS13785{
    public static void main(String[] args){

        String[][] data = {{"1001","1002","1003","1004","1005","1006","1007"}
                            ,{"Ashish","Sushma","Rahul","Chahat","Ranjan","Suman","Tanmay"},
                            {"e","c","k","r","m","e","c"},
                            {"R&D","PM","Acct","Front Desk","Engg","Manufacturing","PM"},
                            {"20000","30000","10000","12000","50000","23000","29000"},
                            {"8000","12000","8000","6000","20000","9000","12000"},
                            {"3000","9000","1000","2000","20000","4400","10000"}};
                            
        String[][] designations = {
                                {"e", "Engineer", "20000"},
                                {"c", "Consultant", "32000"},
                                {"k", "Clerk", "12000"},
                                {"r", "Receptionist", "15000"},
                                {"m", "Manager", "40000"}
                            };

        Scanner obj = new Scanner(System.in);
        System.out.print("Enter the Employee ID: ");
        String ID = obj.nextLine();
        System.out.println("Searching the details of ID: "+ ID);

        String des_code = "";
        int emp_index = -1;             
        
        for(int i=0;i<7;i++){
            if(data[0][i].equals(ID)){              
                des_code = data[2][i];
                emp_index = i;
                break;
            }
            
        }

        if (emp_index == -1)
        {
            System.out.print("Entered employee ID " + ID +" is not available.");
            return;
        }
        
        int  da= 0;
        int salary = 0;
        String desig = "";
        switch(des_code){
            case "e":
                desig = "Engineer";
                da = Integer.parseInt(designations[0][2]);
                break;
            
            case "c":
                desig = "Consultant";
                da = Integer.parseInt(designations[1][2]);
                break;    

            case "k":
                desig = "Clerk";
                da = Integer.parseInt(designations[2][2]);
                break;

            case "r":
                desig = "Receptionist";
                da = Integer.parseInt(designations[0][2]);
                break;   
            
            case "m":
                desig = "Manager";
                da = Integer.parseInt(designations[0][2]);
                break;
            
            
        }
        
        salary = da + (Integer.parseInt(data[4][emp_index]))+ (Integer.parseInt(data[5][emp_index])) - (Integer.parseInt(data[6][emp_index]));
        System.out.println("Emp No: " + data[0][emp_index]);
        System.out.println("Emp Name: " + data[1][emp_index]);
        System.out.println("Department: " + data[3][emp_index]);
        System.out.println("Designation: " + desig);
        System.out.println("Salary: "+ salary);
        }
        
}

                  
    
