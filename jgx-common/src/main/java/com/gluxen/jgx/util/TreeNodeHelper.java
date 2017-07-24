package com.gluxen.jgx.util;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeHelper {
	private StringBuilder jsonStrBuilder = new StringBuilder();

	public static <T> String getJsonStr(LigerTreeNode root, List<LigerTreeNode> list) {
		return new TreeNodeHelper().recursionFn(root, list);
	}

	// 递归拼凑菜单结构
	public String recursionFn(LigerTreeNode root, List<LigerTreeNode> list) {
		if (hasChild(root, list)) {
			jsonStrBuilder.append("{\"id\":");
			jsonStrBuilder.append("\"" + root.getId() + "\"");
			if(StringUtil.isNotBlank(root.getParentId())){
				jsonStrBuilder.append(",\"parentId\":");
				jsonStrBuilder.append("\"" + String.valueOf(root.getParentId()) + "\"");
			}
			jsonStrBuilder.append(",\"text\":");
			jsonStrBuilder.append("\"" + root.getText() + "\"");
			jsonStrBuilder.append(",\"ischecked\":");
			jsonStrBuilder.append("\"" + root.getIschecked() + "\"");
			jsonStrBuilder.append(",\"isexpand\":");
			jsonStrBuilder.append("\"" + root.getIsexpand() + "\"");
			if(root.getUrl() != null) {
				jsonStrBuilder.append(",\"url\":");
				jsonStrBuilder.append("\"" + root.getUrl() + "\"");
			}
            if(root.getType() != null) {
                jsonStrBuilder.append(",\"type\":");
				jsonStrBuilder.append("\"" + root.getType() + "\"");
            }
			jsonStrBuilder.append(",\"children\":[");
			List<LigerTreeNode> childList = getChildList(root, list);
			for (LigerTreeNode bean : childList) {
				recursionFn(bean, list);
			}
			jsonStrBuilder.append("]},");
		} else {
			jsonStrBuilder.append("{\"id\":");
			jsonStrBuilder.append("\"" + String.valueOf(root.getId()) + "\"");
			if(StringUtil.isNotBlank(root.getParentId())){
				jsonStrBuilder.append(",\"parentId\":");
				jsonStrBuilder.append("\"" + String.valueOf(root.getParentId()) + "\"");
			}
			jsonStrBuilder.append(",\"text\":");
			jsonStrBuilder.append("\"" + root.getText() + "\"");
			if(StringUtil.isNotBlank(root.getIschecked())){
				jsonStrBuilder.append(",\"ischecked\":");
				jsonStrBuilder.append("\"" + root.getIschecked() + "\"");
			}
			if(root.getIsFolder()){
				jsonStrBuilder.append(",\"isexpand\":");
				jsonStrBuilder.append("\"" + root.getIsexpand() + "\"");
				jsonStrBuilder.append(",\"children\":[]");
				
			}
			if (root.getUrl() != null) {
				jsonStrBuilder.append(",\"url\":");
				jsonStrBuilder.append("\"" + root.getUrl() + "\"");
			}
            if(root.getType() != null) {
                jsonStrBuilder.append(",\"type\":");
				jsonStrBuilder.append("\"" + root.getType() + "\"");
            }
			jsonStrBuilder.append("},");
		}

		return modifyStr(jsonStrBuilder.toString());
	}

	// 判断是否有子节点
	public boolean hasChild(LigerTreeNode root, List<LigerTreeNode> list) {
		return getChildList(root, list).size() > 0 ? true : false;
	}

	// 根据TreeNode中的id来获取子菜单list
	public List<LigerTreeNode> getChildList(LigerTreeNode root, List<LigerTreeNode> list) {
		List<LigerTreeNode> childList = new ArrayList<LigerTreeNode>();
		for (LigerTreeNode bean : list) {
			String id = root.getId();
			String parentId = bean.getParentId();
			if (id.equals(parentId))
				childList.add(bean);
		}
		return childList;
	}

	// 生成最终的Json格式
	public String modifyStr(String returnStr) {
		return ("[" + returnStr + "]").replaceAll(",]", "]");
	}

	public static void main(String[] args) {
		LigerTreeNode root = new LigerTreeNode();
		LigerTreeNode node1 = new LigerTreeNode();
		List<LigerTreeNode> list = new ArrayList<LigerTreeNode>();

		root.setId("0");
		root.setText("level1");
		root.setParentId("-1");
		list.add(root);

		node1 = new LigerTreeNode();
		node1.setId("1");
		node1.setText("level2");
		node1.setParentId("0");
		list.add(node1);

		node1 = new LigerTreeNode();
		node1.setId("11");
		node1.setText("level2");
		node1.setParentId("0");
		list.add(node1);

		node1 = new LigerTreeNode();
		node1.setId("2");
		node1.setText("level3");
		node1.setParentId("1");
		list.add(node1);

		System.out.println("list=" + list);
		System.out.println(TreeNodeHelper.getJsonStr(root, list));
	}
}
