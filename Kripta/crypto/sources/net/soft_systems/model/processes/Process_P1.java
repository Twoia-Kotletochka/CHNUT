package net.soft_systems.model.processes;
import net.soft_systems.model.base.*;
import net.soft_systems.integrator.*;
import net.soft_systems.crypto.algo.*;
import net.soft_systems.crypto.beans.process.*;
public class Process_P1 extends net.soft_systems.model.base.ProcessModelImpl 
{

         

		
        
public Process_P1(ProcessBean processBean)
	 throws java.rmi.RemoteException
{

		super(processBean);
	        
}
public void onCreate()
{

            	logMessage("Событие onCreate");
	        
            
	        
}
public void onDestroy()
{

	            logMessage("Событие onDestroy");
	        
            
	        
}
public void onRecieve(String nodeId)
{
		
            	logMessage("Событие onRecieve в узле "+nodeId);
	        
            
	        
}
public void do_send()
{

	        
byte key[]=Binary.setFromHex("9B58 086D 9BF9 CD96 C6EA 3381 B1B4 F637");
byte IV[]=Binary.setFromHex("7836 ECD6 C5F0 37B6");
byte data[]=Binary.random(10000);
IDEA idea=new IDEA();
idea.setIV(IV);
idea.setKey(key);
logDataMessage("Открытый текст",data);
TimeUtil t=new TimeUtil();
t.start();
byte code[]=idea.encodeData(data,idea.MODE_CBC);
t.finish();
logDataMessage("Время шифрования ",t.millisec());
logDataMessage("Зашифрованный текст",code);
send("out",code);
                        
	        
}
}
