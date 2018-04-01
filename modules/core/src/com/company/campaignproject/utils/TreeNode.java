package com.company.campaignproject.utils;

import com.company.campaignproject.service.OccupancyMap;

import java.util.Date;

public class TreeNode {

    private Date key;
    private OccupancyMap value;
    private boolean color;
    private TreeNode leftNode;
    private TreeNode rightNode;

    private TreeNode parentNode;

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }

    public TreeNode(Date key, OccupancyMap value, boolean color) {
        this.key = key;
        this.value = value;
        this.color = color;
    }

    public Date getKey() {
        return key;
    }

    public void setKey(Date key) {
        this.key = key;
    }

    public OccupancyMap getValue() {
        return value;
    }

    public void setValue(OccupancyMap value) {
        this.value = value;
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }


    public TreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(TreeNode parentNode) {
        this.parentNode = parentNode;
    }

}
