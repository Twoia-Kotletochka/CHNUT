package net.soft_systems.model.processes;
import net.soft_systems.model.base.*;
import net.soft_systems.integrator.*;
import net.soft_systems.crypto.algo.*;
import net.soft_systems.crypto.beans.process.*;
public class Process_B extends net.soft_systems.model.base.ProcessModelImpl 
{

BinaryVector ids=new BinaryVector();
byte key[];
byte s[];                                                                                 

		
        
public Process_B(ProcessBean processBean)
	 throws java.rmi.RemoteException
{

		super(processBean);
	        
}
public void onCreate()
{

            	logMessage("Событие onCreate");
	        
ids.put(Binary.setFromHex("AAAA 1111 2222 3333"),0);
ids.put(Binary.setFromHex("BBBB CCCC 4444 6666"),1);
                                                                                                            
	        
}
public void onDestroy()
{

	            logMessage("Событие onDestroy");
	        
            
	        
}
public void onRecieve(String nodeId)
{
		
            	logMessage("Событие onRecieve в узле "+nodeId);
	        
if (hasMoreData("id_in"))
{  
  int id=((Integer)recv("id_in")).intValue();
  key=ids.get(id);
} else if (hasMoreData("es_in"))
{
  byte es[]=(byte[])recv("es_in");
  s=DES.decode(key,es);  
}                                                                                                             
	        
}
public void sendAS()
{

	        
byte ds[]=MD5.digest(s);
DES des = new DES();
des.setKey(key);
byte as[] = des.encodeData(ds,BlockCipher.MODE_ECB);
send("as_out",as);                                                                                                            
	        
}
public void OnEvent_1()
{

	        

	        
}
}
