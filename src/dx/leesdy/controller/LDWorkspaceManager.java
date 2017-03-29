package dx.leesdy.controller;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class LDWorkspaceManager {
	
	
	/**
	 * Current selected controller
	 * -1 : no one is selected
	 * 0  : id #0 id selected
	 */
	private IntegerProperty currentlySelectedID;
	
	public void setCurrentlySelectedID(IntegerProperty currentlySelectedID) {
		this.currentlySelectedID = currentlySelectedID;
	}

	private ArrayList<LDControlCenter> controllers;
	
	public int getCount() {
		return this.controllers.size();
	}
	
	
	public IntegerProperty getCurrentlySelectedIDProperty() {
		return this.currentlySelectedID;
	}
	
	public int getCurrentlySelectedID() {
		return currentlySelectedID.get();
	}

	public void setCurrentlySelectedID(int currentlySelectedID) {
		if (this.currentlySelectedID.get() != -1) this.getControlCenterById(this.currentlySelectedID.get()).setFocus(false);
		this.currentlySelectedID.set(currentlySelectedID);
	}
	
	public LDWorkspaceManager() {
		this.currentlySelectedID = new SimpleIntegerProperty();
		this.currentlySelectedID.set(-1);
		this.controllers = new ArrayList<LDControlCenter>();
	}

	public LDControlCenter getControlCenterById(int id) {
		return controllers.get(id);
	}
	
	public void addControlCenter(LDControlCenter controlCenter) {
		controlCenter.setId(this.getCount());
		this.controllers.add(controlCenter);
		controlCenter.setManager(this);
	}
	
	public ArrayList<LDControlCenter> getControllers() {
		return controllers;
	}
	
	public LDControlCenter getCurrentSelectedController() {
		if (this.currentlySelectedID.get() != -1)
		return this.getControlCenterById(currentlySelectedID.get());
		else return null;
	}
	
	public void removeSelectedController() {
		if (this.currentlySelectedID.get() != -1)
		this.controllers.remove(currentlySelectedID);
	}
	
	public LDControlCenter getTop() {
		if (this.controllers.size() != 0)
		return this.controllers.get(this.controllers.size()-1);
		else return null;
	}
	
	public int getTopID() {
		return this.getCount()-1;
	}
	
}
