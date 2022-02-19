/*
 * Copyright (c) 1998, 1999 Sun Microsystems, Inc. All Rights Reserved.
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

package examples.rmisocfac;

import java.io.*;
import java.net.*;

class XorSocket extends Socket {
  
    /*
     * The pattern used to "encrypt" and "decrypt" each byte sent
     * or received by the socket.
     */
    private byte pattern;
  
    /* The InputStream used by the socket. */
    private InputStream in = null;
  
    /* The OutputStream used by the socket */
    private OutputStream out = null;
  
    /* 
     * Constructor for class XorSocket. 
     */
    public XorSocket(byte pattern)
        throws IOException 
    {
        super();
        this.pattern = pattern;
    }
  
    /* 
     * Constructor for class XorSocket. 
     */
    public XorSocket(String host, int port, byte pattern)
        throws IOException
    {
        super(host, port);
        this.pattern = pattern;
    }
  
    /* 
     * Returns a stream of type XorInputStream. 
     */
    public InputStream getInputStream() throws IOException {
        if (in == null) {
            in = new XorInputStream(super.getInputStream(), pattern);
        }
        return in;
    }
  
    /* 
     *Returns a stream of type XorOutputStream. 
     */
    public OutputStream getOutputStream() throws IOException {
        if (out == null) {
            out = new XorOutputStream(super.getOutputStream(), pattern);
        }
        return out;
    }
}
