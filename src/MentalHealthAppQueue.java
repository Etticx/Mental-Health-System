import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
public class MentalHealthAppQueue {
    public static void main(String [] args)
    {
        try
        {
            FileReader fr = new FileReader("PatientInfo.txt");
            BufferedReader br = new BufferedReader(fr);

            //Scanner for Input
            Scanner sc = new Scanner(System.in);
            sc.useDelimiter("\n");

            //Decimal Format
            DecimalFormat df = new DecimalFormat("0.00");

            Queue patientQ = new Queue();
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
                patientQ.enqueue(pt);
            }

            //Process 1: Displaying All details in patientQ
            System.out.println("==============================================================================================================================================");
            System.out.println("\t\t\t\t\t\tPROCESS 1: DISPLAYING ALL DETAILS IN PATIENTQ");
            System.out.println("==============================================================================================================================================");
            System.out.println(String.format("%-30s %-20s %-30s %-15s %-10s %-10s %-10s","Name", "IC Number", "Email", "Date-Of-Birth", "Service", "Day", "Duration(Hours)"));
            Queue tempQ = new Queue();
            Patient obj;
            while(!patientQ.isEmpty())
            {
                obj = (Patient)patientQ.dequeue();
                System.out.println(obj.toString());
                tempQ.enqueue(obj);
            }
            //restoring
            while(!tempQ.isEmpty())
                patientQ.enqueue(tempQ.dequeue());


            //Process 2: Find the User Queue Position by entering the patient IC
            System.out.println("\nPROCESS 2: FIND THE USER QUEUE POSITION BY ENTERING PATIENT IC");
            System.out.print("Please Enter patient Identification Card Number to inquire hes/her Queue Number: ");
            String fIc = sc.next();
            int qNum = 0;
            boolean found = false;
            while(!patientQ.isEmpty() && !found)
            {
                obj = (Patient) patientQ.dequeue();
                if(obj.getIcNum().equals(fIc))
                {
                    found = true;
                }
                else
                    qNum++;
                tempQ.enqueue(obj);
            }
            //restoring
            while(!tempQ.isEmpty())
                patientQ.enqueue(tempQ.dequeue());

            if (found)
                System.out.println("Patient Queue Number: " + (qNum + 1));
            else
                System.out.println("Patient Not Found !");


        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println(fnfe.getMessage());
        }
        catch(IOException io)
        {
            System.out.println(io.getMessage());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
