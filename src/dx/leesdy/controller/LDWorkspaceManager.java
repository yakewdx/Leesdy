package dx.leesdy.controller;

import java.util.ArrayList;

public class LDWorkspaceManager {
	
	
	/**
	 * Current selected controller
	 * -1 : no one is selected
	 * 0  : id #0 id selected
	 */
	private int currentlySelectedID;
	
	private ArrayList<LDControlCenter> controllers;
	
	public int getCount() {
		return this.controllers.size();
	}
	
	public int getCurrentlySelectedID() {
		return currentlySelectedID;
	}

	public void setCurrentlySelectedID(int currentlySelectedID) {
		this.currentlySelectedID = currentlySelectedID;
	}
	
	public LDWorkspaceManager() {
		this.currentlySelectedID = -1;
		this.controllers = new ArrayList<LDControlCenter>();
	}

	public LDControlCenter getControlCenterById(int id) {
		return controllers.get(id);
	}
	
	public void addControlCenter(LDControlCenter controlCenter) {
		this.controllers.add(controlCenter);
	}
	
	public ArrayList<LDControlCenter> getControllers() {
		return controllers;
	}

	public void setControllers(ArrayList<LDControlCenter> controllers) {
		this.controllers = controllers;
	}
	
	public LDControlCenter getCurrentSelectedController() {
		if (this.currentlySelectedID != -1)
		return this.getControlCenterById(currentlySelectedID);
		else return null;
	}
	
	public void removeSelectedController() {
		if (this.currentlySelectedID != -1)
		this.controllers.remove(currentlySelectedID);
	}
	
	public LDControlCenter getTop() {
		if (this.controllers.size() != 0)
		return this.controllers.get(this.controllers.size()-1);
		else return null;
	}
	
}
