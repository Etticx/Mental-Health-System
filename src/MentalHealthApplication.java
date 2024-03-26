import java.io.*;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.text.DecimalFormat;

public class MentalHealthApplication
{
    public static void main(String [] args)
    {
        try
        {
            //File Input
            FileReader fr = new FileReader("PatientInfo.txt");
            BufferedReader br = new BufferedReader(fr);

            //Scanner for Input
            Scanner sc = new Scanner(System.in);
            sc.useDelimiter("\n");

            //Decimal Format
            DecimalFormat df = new DecimalFormat("0.00");

            LinkedList patientList = new LinkedList();
            String userName, inData = null, icNum, email, dob, servType;
            int day;
            double dur;
            while((inData = br.readLine())!=null)
            {
                StringTokenizer st  = new StringTokenizer(inData,";");
                userName = st.nextToken();
                icNum = st.nextToken();
                email = st.nextToken();
                dob = st.nextToken();
                servType = st.nextToken();
                day = Integer.parseInt(st.nextToken());
                dur = Double.parseDouble(st.nextToken());
                Patient pt = new Patient(userName,icNum,email,dob,servType,day,dur);
                patientList.insertAtBack(pt);
            }

            /**Process 1: Searching patients who chose Individual Counseling, where its more than 2 days and the duration is above 3 hours*/
            Patient obj;
            obj = patientList.getHead();
            System.out.println("==========================================================================================================================================");
            System.out.println("\t\t\t\t\t\tDETAILS OF PATIENT THAT CHOSE INDIVIDUAL COUNSELING WHERE ITS MORE THAN 2 DAYS AND ABOVE 3 HOURS");
            System.out.println("==========================================================================================================================================");
            System.out.println(String.format("%-30s %-20s %-30s %-15s %-10s %-10s %-10s","Name", "IC Number", "Email", "Date-Of-Birth", "Service", "Day", "Duration(Hours)"));
            while(obj != null)
            {
                if(obj.getServiceType().equalsIgnoreCase("IC") && obj.getDay() > 2 && obj.getDuration() > 3)
                {
                    System.out.println(obj.toString());
                }
                obj = patientList.getNext();
            }

            /**Process 2: Searching IC Number based on name given */
            System.out.print("\nEnter FULL NAME to search for the user IC: ");
            String fName = sc.next();

            boolean found = false;
            Patient fObj = null;
            obj = patientList.getHead();
            while(obj != null)
            {
                if(obj.getUsername().equalsIgnoreCase(fName))
                {
                    fObj = obj;
                    found = true;
                }

                obj = patientList.getNext();
            }
            if(found)
            {
                System.out.println("Patient Found, IC: " + fObj.getIcNum());
            }
            else
            {
                System.out.println("Patient Not Found !");
            }

            /**process 3: Display a list of patients receiving treatment for substance abuse who are 50 years of age or older.*/
            System.out.println("\n==========================================================================================================================================");
            System.out.println("\t\t\t\t\tNAME OF PATIENTS THAT RECEIVING TREATMENT FOR SUBSTANCE ABUSE WHO ARE 50 YEARS OF AGE OR OLDER");
            System.out.println("==========================================================================================================================================");
            obj = patientList.getHead();
            int age;
            while(obj != null)
            {
                if(obj.getServiceType().equalsIgnoreCase("ST") && obj.getAge() >= 50)
                {
                    System.out.println(obj.getUsername());
                }
                obj = patientList.getNext();
            }

            /**Process 4: Removing and displaying all patients that are in Substance abuse treatment where the day is greater than 5 days from patientList into a new linked list called criticalList*/
            LinkedList criticalList = new LinkedList();
            LinkedList tempList = new LinkedList();
            obj = patientList.getHead();
            while(!patientList.isEmpty())
            {
                obj = patientList.removeFromFront();
                if(obj.getServiceType().equalsIgnoreCase("ST") && obj.getDay() > 5)
                {
                    criticalList.insertAtFront(obj);
                }else
                    tempList.insertAtFront(obj);
            }
            //restoring
            while(!tempList.isEmpty())
                patientList.insertAtBack(tempList.removeFromBack());

            //Displaying
            System.out.println("\n==========================================================================================================================================");
            System.out.println("\t\t\t\t\t\t\t\t  DETAILS OF CRITICAL SUBSTANCE ABUSE TREATMENT PATIENTS");
            System.out.println("==========================================================================================================================================");
            System.out.println(String.format("%-30s %-20s %-30s %-15s %-10s %-10s %-10s","Name", "IC Number", "Email", "Date-Of-Birth", "Service", "Day", "Duration(Hours)"));
            obj = criticalList.getHead();
            while(obj != null)
            {
                System.out.println(obj.toString());
                obj = criticalList.getNext();
            }

            /**Process 5: Calculating Average Payment from PatientList */
            double totalPayment = 0.0, avgPayment = 0.0;
            obj = patientList.getHead();
            while(obj != null)
            {
                totalPayment += obj.calculate();
                obj = patientList.getNext();
            }
            avgPayment = totalPayment / patientList.getSize();
            System.out.println("\nTOTAL PAYMENT FROM PATIENT LIST = RM" + df.format(totalPayment));
            System.out.println("TOTAL AVERAGE PAYMENT FROM PATIENT LIST = RM" + df.format(avgPayment));

            /**Process 6: removing the node/object from anywhere in the linked list by entering the name of the patient*/
            System.out.print("\nPlease enter a name to be remove: ");
            String ffname = sc.next();
            Patient ffObj = null;
            Boolean ffound = false;
            obj = patientList.getHead();
            while(obj != null)
            {
                if(obj.getUsername().equalsIgnoreCase(ffname))
                {
                    ffObj = obj;
                    ffound = true;
                }

                obj = patientList.getNext();
            }
            patientList.removeNode(ffObj);
            if(ffound)
                System.out.println("Patient Removed !");
            else
                System.out.println("Patient Not Found!");

            /** Process 7 : Updating  data from any linked list inside the program*/
            char updType = ' ';
            double updServType = 0.00;
            boolean Upfound = false;
            Patient fobj = null;

            System.out.print("\n==========================================================================================================================================");
            System.out.print("\n\t\t\t\t\t UPDATING DURATION/HOURS BASED ON USER CHOICES");
            System.out.print("\n==========================================================================================================================================");

            System.out.print("\nPlease Enter [p - Patient|c - Critical] To Update Duration/Hours  : ");
            updType = sc.next().charAt(0);

            System.out.print("\nPlease Enter [FULL NAME] To Update Duration/Hours : ");
            String upName = sc.next();
            
            if(updType == 'p' || updType == 'P')
            {
                obj = patientList.getHead(); 
                while(obj != null)
                {
                    if(obj.getUsername().equalsIgnoreCase(upName))
                    {
                        System.out.print("\nPlease Enter New Duration To Update:");
                        updServType = sc.nextDouble();
                        Upfound = true;  
                        fobj = obj;
                    }
                    obj = patientList.getNext();
                }
            }else if(updType == 'c' || updType == 'C')
            {
                obj = criticalList.getHead();
                while(obj != null)
                {
                    if(obj.getUsername().equalsIgnoreCase(upName))
                    {
                        System.out.print("\nPlease Enter New Duration To Update:");
                        updServType = sc.nextDouble();
                        Upfound = true;  
                        fobj = obj;  
                    }
                    obj = criticalList.getNext();
                }
            }

            if(Upfound)
            {
                fobj.setDuration(updServType);
                System.out.println(String.format("\n%-30s %-20s %-30s %-15s %-10s %-10s %-10s","Name", "IC Number", "Email", "Date-Of-Birth", "Service", "Day", "Duration(Hours)"));
                System.out.print(fobj.toString());
            }
            else
            {
                System.out.print("Error , Not Update!");
            } 

            br.close();
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println(fnfe.getMessage());
        }
        catch (IOException io)
        {
            System.out.println(io.getMessage());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}