/*
Project Cryto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
*/

package net.soft_systems.crypto.event;
import java.util.Vector;
public interface SystemChangeListener {
	public void elementsChanged(Vector subjects, Vector resources);
	public void rightsChanged(Vector availRights);
}

