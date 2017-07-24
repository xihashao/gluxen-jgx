package com.gluxen.jgx.util;

public class TreeNode {
	
	private String id;
	
	private String parentId;
	
	private String name;
	
	private Object handle;
	
	private String iconSkin; 
	
	private int alarmNum;

	public int getAlarmNum() {
		return alarmNum;
	}

	public void setAlarmNum(int alarmNum) {
		this.alarmNum = alarmNum;
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getHandle() {
		return handle;
	}

	public void setHandle(Object handle) {
		this.handle = handle;
	}
	

}
