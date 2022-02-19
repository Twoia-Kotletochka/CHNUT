package net.soft_systems.model.processes;
import net.soft_systems.model.base.*;
import net.soft_systems.integrator.*;
import net.soft_systems.crypto.algo.*;
import net.soft_systems.crypto.beans.process.*;
public class Process_R extends net.soft_systems.model.base.ProcessModelImpl 
{

RSA rsa=new RSA();                                                                                          

		
        
public Process_R(ProcessBean processBean)
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
	        
Long lcode=(Long)recv("in");
long code=lcode.longValue();
logDataMessage("Шифротекст",code);
long decode = rsa.decode( code );
logDataMessage("Расшифрованный текст",decode);
                                                                                                                        
	        
}
public void makeKeys()
{

	        
long x       = 25000;
long y       = 30000;
long fi;
long p,q,e;

for ( p = x; !Numerical.isPrime( p ); p++ ) ;
for ( q = y + 2; !Numerical.isPrime( q ); q++ ) ;
rsa.setSecureKey(p,q);
logDataMessage("Секретный ключ","p=" + p +" q="+q);
fi=rsa.getFi();
for ( e = fi / 10; Numerical.gcd( e, fi ) != 1; e++ ) ;
rsa.checkOpenKey(e);
logDataMessage("Открытый ключ", "e=" + e+" n="+rsa.getN() );
long open_key[]=new long[2];
open_key[0]=e;
open_key[1]=rsa.getN();
send("key_out",open_key);
                                                                                                                        
	        
}
}
