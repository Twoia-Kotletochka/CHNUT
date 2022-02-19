package net.soft_systems.model.processes;
import net.soft_systems.model.base.*;
import net.soft_systems.integrator.*;
import net.soft_systems.crypto.algo.*;
import net.soft_systems.crypto.beans.process.*;
public class Process_Sender extends net.soft_systems.model.base.ProcessModelImpl 
{

         

		
        
public Process_Sender(ProcessBean processBean)
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

	        
int iDataSize = 10000;
byte data[]=Binary.random(iDataSize);
logDataMessage("Открытый текст",data);

DES crypter=new DES();
crypter.setIV(Binary.setFromHex("7836 ECD6 C5F0 37B6"));
crypter.setKey(Binary.setFromHex("9B58 086D 9BF9 CD96 C6EA 3381 B1B4 F637"));

//Crypting
int iWholeTime = 0;
int iDataPtr = 0;
TimeUtil t=new TimeUtil();
while (iDataPtr < iDataSize) {
  byte data16[] = new byte[16];
  int iLocalI = 0, iLocalI2;
  while ((iLocalI < 16) && (iDataPtr < iDataSize)) {
    data16[iLocalI] = data[iDataPtr];
    iDataPtr++;
    iLocalI++;
  }

  t.start();
  byte code[]=crypter.encodeData(data16,crypter.MODE_CFB);
  t.finish();
  iWholeTime += t.millisec();

  iDataPtr -= iLocalI;
  for (int i = 0; i < iLocalI; i++) {
    data[iDataPtr] = code[i];
    iDataPtr++;
  }
}
logDataMessage("Время шифрования ", iWholeTime);
logDataMessage("Зашифрованный текст",data);
send("Выход",data);                                                                                                                                                
	        
}
}
