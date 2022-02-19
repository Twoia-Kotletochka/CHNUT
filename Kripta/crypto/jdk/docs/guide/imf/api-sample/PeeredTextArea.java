/*
 * @(#)PeeredTextArea.java	1.2 99/08/02
 * 
 * Copyright 1997-1999 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;

public class PeeredTextArea extends TextArea {

    public PeeredTextArea() {
        super();
        setText("Peered component\n");
        setSize(300, 80);
	setForeground(Color.black);
        setBackground(Color.white);
	setVisible(true);
	setEnabled(true);
    }
    
    public void setFontSize(int size) {
        setFont(new Font("Dialog", Font.PLAIN, size));
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(300, 80);
    }
}
