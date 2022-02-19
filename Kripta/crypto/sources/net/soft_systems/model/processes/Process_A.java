package net.soft_systems.model.processes;
import net.soft_systems.model.base.*;
import net.soft_systems.integrator.*;
import net.soft_systems.crypto.algo.*;
import net.soft_systems.crypto.beans.process.*;
public class Process_A extends net.soft_systems.model.base.ProcessModelImpl 
{

int id=1;
byte key[]=Binary.setFromHex("BBBB CCCC 4444 6666");
byte s[];         

		
        
public Process_A(ProcessBean processBean)
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
	        
if (hasMoreData("as_in"))
{	
  byte ds[]=MD5.digest(s);

  byte as_r[]=(byte[])recv("as_in");

DES des = new DES();
des.setKey(key);
byte ds_r[] = des.decodeData(as_r,BlockCipher.MODE_ECB);

  if (BaseHash.compare(ds,ds_r))
    logMessage("B подлинный");
  else 
    logMessage("B ложный");
  
}                                                                                                            
	        
}
public void sendId()
{

	        
send("id_out",new Integer(id));                                                                                                            
	        
}
public void sendES()
{

	        
s=Binary.random(64);
byte es[]=DES.encode(key,s);
send("es_out",es);                                                                                                            
	        
}
public void OnEvent_1()
{

	        

	        
}
}
