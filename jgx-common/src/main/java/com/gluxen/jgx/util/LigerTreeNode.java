package com.gluxen.jgx.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * 树形结构类
* @author lishiqiang
* @date 2017-3-15
* modify history
 */
public class LigerTreeNode {

	private String id;
	private String parentId;
	private boolean isFolder = false;
	private String text;
	private String url;
    private String type;
	private String ischecked;
	private String isexpand;
	private java.util.List<LigerTreeNode> children= new ArrayList<LigerTreeNode>();
	private Map<String, Object> attributes;

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

	public boolean getIsFolder() {
		return isFolder;
	}

	public void setIsFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIschecked() {
		return ischecked;
	}

	public void setIschecked(String ischecked) {
		this.ischecked = ischecked;
	}

	public String getIsexpand() {
		return isexpand;
	}

	public void setIsexpand(String isexpand) {
		this.isexpand = isexpand;
	}

	public void addChildren(LigerTreeNode n) {
		children.add(n);
	}

    public void setChildren(Collection<LigerTreeNode> c) {
		children.addAll(c);
	}

	public LigerTreeNode[] getChildren() {

		if (children.size() == 0) {
			return null;
		}
		return children.toArray(new LigerTreeNode[children.size()]);
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", parentId=" + parentId + ", text=" + text + ", url=" + url + ", isexpand="
				+ isexpand + ", children=" + children + ", attributes=" + attributes + "]";
	}
}
