// HelloClient.java, stringified object reference version

import java.io.*;
import org.omg.CORBA.*;
import HelloApp.*;

public class HelloClient 
{
    public static void main(String args[])
    {
	try{
	    // create and initialize the ORB
	    ORB orb = ORB.init(args, null);

	    // Get the stringified object reference and destringify it.
	    String filename = System.getProperty("user.home")+
	        System.getProperty("file.separator")+"HelloIOR";
	    FileInputStream fis = new FileInputStream(filename);
            DataInputStream dis = new DataInputStream(fis) ;
            String ior = dis.readLine() ;
            org.omg.CORBA.Object obj = orb.string_to_object(ior) ;

            Hello helloRef = HelloHelper.narrow(obj);

	    // call the Hello server object and print results
	    String Hello = helloRef.sayHello();
	    System.out.println(Hello);

	} catch (Exception e) {
	    System.out.println("ERROR : " + e) ;
	    e.printStackTrace(System.out);
	}
    }
}
