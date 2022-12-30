
import java.util.ArrayList;
import java.util.Scanner;
import net.objecthunter.exp4j.ExpressionBuilder;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 *
 * @author Elza Morgan
 */
public class Secant {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                ArrayList<Double> a= new ArrayList<>();
                ArrayList<Double> b= new ArrayList<>(); 
                ArrayList<Double> c= new ArrayList<>();
                ArrayList<Double> f_a= new ArrayList<>();
                ArrayList<Double> f_b= new ArrayList<>();
                ArrayList<Double> f_c= new ArrayList<>();
                ArrayList<Boolean> con1= new ArrayList<>();
                ArrayList<Boolean> con2= new ArrayList<>();
                ArrayList<String> root= new ArrayList<>();
                
                
              //enters the data
            Scanner input = new Scanner(System.in);
            System.out.println("Eneter the equation: ");
            String equation=input.next();
            System.out.println("Enter the value for 'A': ");
            double a_exp=input.nextDouble();
            System.out.println("Enter the value for 'B': ");
            double b_exp=input.nextDouble();
            System.out.println("Enter the value for 'Tol': ");
            double tol=input.nextDouble();
            System.out.println("Enter which pro to work with 0=secant,1=modified secant");
            int z=input.nextInt();
            int n = (int) (Math.log((b_exp-a_exp)/tol)/Math.log(2));//cal the itration
            double fOfA=check(equation,a_exp);
            double fOfB=check(equation,b_exp);

                    a.add(a_exp);
                    b.add(b_exp);
                    f_a.add(fOfA);
                    f_b.add(fOfB);
                  
        /*if condion is to check if there is a root in the first place or not
        if the result came out -ve then we will enter the else and do the
        the calculation*/
        switch (z) {
            case 0:
                if(fOfA*fOfB>0)
                {
                    System.out.println("no root for this equation");
                }
                else{
                    boolean cond1; //fc >0.01
                    boolean cond2; //i==n
                    int i;
                    for(i=0; i<n ; i++)//it loops depending on the calcaultion of n
                    {
                        cond1=false;
                        cond2=false;
                        
                        double c_exp=b_exp-fOfB*((b_exp-a_exp)/(fOfB-fOfA));// cal of c
                        c_exp= Math.round(c_exp*10000.0)/10000.0;//this is for rounding it
                        c.add(c_exp);//adding c to the arry
                        
                        double fOfC=check(equation,c_exp);//sending it to the function to do cal the sub x in c
                        fOfC= Math.round(fOfC*10000.0)/10000.0;//rounding the f(c)
                        f_c.add(fOfC);//adding the f(c) in the array
                        
                        if(Math.abs(fOfC)<= tol)//this if for the f(c)<=tolerance
                        {
                            cond1=true;//this will flag will change since it
                            con1.add(cond1);//adding it to the array
                            break;       
                        }
                        else{
                            con2.add(cond2);   
                            con1.add(cond1);
                            
                            a_exp=b_exp;
                            a.add(a_exp);
                            
                            b_exp=c_exp;
                            b.add(b_exp);
                            
                            fOfA=fOfB;
                            f_a.add(fOfA);
                            
                            fOfB=fOfC;
                            f_b.add(fOfB);
                          }      
                    }
                    // to check itration
                    if(i == n){
                        
                    cond2=true;
                    con2.add(cond2);
                    
                    }   
                    
                    else{
                        
                        cond2=false;
                        con2.add(cond2);    
                        
                        }
                    
                   printTableSecant(a,b,c,f_a,f_b,f_c,con1,con2,n); 
                } break;
            case 1:
                if(fOfA*fOfB>0)
                {
                    System.out.println("no root for this equation");
                }
                else{

                    boolean cond1; //fc >0.01
                    int i;
                    boolean cond2;
                    for(i=0; i<n ; i++)//it loops depending on the calcaultion of n      
                    {   
                        cond1=false;//this condtion will be false unless it entered the next if-condtion
                        cond2=false;
                        double c_exp=b_exp-fOfB*((b_exp-a_exp)/(fOfB-fOfA));// cal of c
                        c_exp= Math.round(c_exp*10000.0)/10000.0;//this is for rounding it
                        c.add(c_exp);//adding c to the array
                       
                        double fOfC=check(equation,c_exp);//sending it to the function to do cal the sub x in c
                        fOfC= Math.round(fOfC*10000.0)/10000.0;//rounding the f(c)
                        f_c.add(fOfC);//adding the f(c) in the array
                        
                        if(Math.abs(fOfC)<= tol)//this if for the f(c)<=tolerance
                            {
                                cond1=true;//this will flag will change since it
                                con1.add(cond1);//adding it to the array
                                break;                        
                             }
                    else{
                            
                         con2.add(cond2);   
                         con1.add(cond1);
                        //if is to check for root c&b
                        if(fOfB*fOfC<0)
                        {         
                            b.add(b_exp);
                            
                            a_exp=c_exp;
                            a.add(a_exp);
                            
                            fOfA=fOfC;
                            f_a.add(fOfA);
                            
                            f_b.add(fOfB);
                            
                            root.add("B&C");                
                        }
                        
                        else if(fOfA*fOfC<0)// C&A
                        {   a.add(a_exp); 
                            
                            b_exp=c_exp;
                            b.add(b_exp);
                                 
                            f_a.add(fOfA);
                            
                             fOfB=fOfC;
                            f_b.add(fOfB);
                            
                            root.add("A&C");
                        }
                        else{
                            
                            System.out.println("functions must have oppsoite signs to work");
                            break;
                            }
                    }
                    
                    }
                    //check itration
                    if(i == n){
                        
                        cond2=true;
                        con2.add(cond2);
                        root.add("found");
                   
                                }   
                    
                    else{
                        
                        cond2=false;
                        con2.add(cond2);  
                        root.add("found");
                    }
                   printTableSecantModified(a,b,c,f_a,f_b,f_c,con1,con2,root);         
                }break;
            default:
                System.out.println("Not found");
                break;
        }
   
     }
   
        /*this is used to sub anything into the equation*/
       
    public static double check(String f_x, double z)
            { 
                net.objecthunter.exp4j.Expression exp = new ExpressionBuilder(f_x).variables("x").build().setVariable("x", z);
                double result = exp.evaluate();
                return result;
            }
        /*this function is used to print all the arrays in table*/
        public static void printTableSecant( ArrayList<Double> a,ArrayList<Double> b, ArrayList<Double> c, ArrayList<Double> f_a,
                ArrayList<Double> f_b,ArrayList<Double> f_c,ArrayList<Boolean> con1,ArrayList<Boolean> con2,int n)
        {
           System.out.printf("%-20s%-15s%-15s%-20s%-15s%-17s%-20s%s%n", "A", "B", "C", "F(A)", "F(B)","F(C)"
                   ,"Con1","Con2");
           for (int i = 0; i < a.size(); i++) {
            System.out.printf("%-20s%-15s%-15s%-20s%-15s%-17s%-20s%s%n",
                        a.get(i), b.get(i),
                        c.get(i), f_a.get(i), f_b.get(i),f_c.get(i),con1.get(i),con2.get(i)
                       );
            }
           
            System.out.println("The root will be : "+ c.get(c.size()-1));
     }
        public static void printTableSecantModified( ArrayList<Double> a,ArrayList<Double> b, ArrayList<Double> c, ArrayList<Double> f_a,
                ArrayList<Double> f_b,ArrayList<Double> f_c,ArrayList<Boolean> con1,ArrayList<Boolean> con2,ArrayList<String>root)
        {
           System.out.format("%-20s%-15s%-15s%-20s%-15s%-17s%-20s%-25s%s%n", "A", "B", "C", "F(A)", "F(B)","F(C)"
                   ,"Con1","Con2","Root");
           for (int i = 0; i < a.size(); i++) {
            System.out.format("%-20s%-15s%-15s%-20s%-15s%-17s%-20s%-25s%s%n",
                        a.get(i), b.get(i),
                        c.get(i), f_a.get(i), f_b.get(i),f_c.get(i),con1.get(i),con2.get(i),root.get(i)
                       );
            }
           
            System.out.println("The root will be : "+ c.get(c.size()-1));
     }

}


    

