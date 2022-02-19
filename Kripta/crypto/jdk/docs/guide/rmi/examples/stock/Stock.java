/*
 * Copyright (c) 1996,1997,1998,1999 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

package examples.stock;

import java.util.Random;

/**
 * A Stock object is used to encapsulate stock update information
 * that is sent in an update notification. Note that the class
 * implements the java.io.Serializable interface in order to be
 * passed as an argument or return value in RMI.
 */
public class Stock implements java.io.Serializable {
    String symbol;
    float current;

    private static Random random = new Random();
    private final static float MAX_VALUE = 67;

    /**
     * Constructs a stock with the given name with a initial random
     * stock price.
     */
    public Stock(String name) 
    {
	symbol = name;
	if (symbol.equals("Sun")) {
	    current = 30.0f;
	} else {
	    // generate random stock price between 20 and 60
	    current = (float)(Math.abs(random.nextInt()) % 40 + 20);
	}
    }

    /**
     * Update the stock price (generates a random change).
     */
    public float update() 
    {
	float change = ((float)(random.nextGaussian() * 1.0));
	if (symbol.equals("Sun") && current < MAX_VALUE - 5)
	    change = Math.abs(change);		// what did you expect?

	float newCurrent = current + change;

	// don't allow stock price to step outside range
	if (newCurrent < 0 || newCurrent > MAX_VALUE)
	    change = 0;

	current += change;
	
	return change;
    }
}

