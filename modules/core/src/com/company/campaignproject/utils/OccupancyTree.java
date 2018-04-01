package com.company.campaignproject.utils;


import com.company.campaignproject.service.OccupancyMap;
import java.util.Date;

public class OccupancyTree {

    private TreeNode root;

    public OccupancyTree(TreeNode treeRoot){
        this.root =treeRoot;
    }
    public static final boolean RED = true;
    public static final boolean BLACK = false;


    private TreeNode rotateLeft(TreeNode nodeH){
        TreeNode nodeX = nodeH.getRightNode();
        TreeNode tempLeftNode =new TreeNode(nodeH.getKey(),nodeH.getValue(),nodeH.getColor());
        tempLeftNode.setLeftNode(nodeH.getLeftNode());
        tempLeftNode.setRightNode(nodeX.getLeftNode());
        nodeH.setKey(nodeX.getKey());
        nodeH.setValue(nodeX.getValue());
        nodeH.setLeftNode(tempLeftNode);
        nodeH.getLeftNode().setParentNode(nodeH);
        nodeH.setRightNode(nodeX.getRightNode());
        nodeH.setColor(RED);
        return nodeX;
    }
    private TreeNode rotateRight(TreeNode nodeH){
        TreeNode nodeX = nodeH.getLeftNode();
        TreeNode tempRightNode = new TreeNode(nodeH.getKey(),nodeH.getValue(),nodeH.getColor());
        tempRightNode.setRightNode(nodeH.getRightNode());
        tempRightNode.setLeftNode(nodeX.getRightNode());
        tempRightNode.setParentNode(nodeH);
        nodeH.setKey(nodeX.getKey());
        nodeH.setValue(nodeX.getValue());
        nodeH.setLeftNode(nodeX.getLeftNode());
        nodeX.setRightNode(tempRightNode);
        nodeH.setColor(RED);
        return nodeX;
    }
    private void flipColors(TreeNode nodeH){
        nodeH.setColor(RED);
        nodeH.getLeftNode().setColor(BLACK);
        nodeH.getRightNode().setColor(BLACK);
    }
    private boolean isRed(TreeNode nodeH){
        if(nodeH==null){
            return false;
        }
        return nodeH.getColor()== RED;
    }

    public TreeNode insert(TreeNode nodeH, Date date, OccupancyMap occupancyMap){
        if(nodeH.getKey() ==null){
            nodeH.setKey(date);
            nodeH.setValue(occupancyMap);
            nodeH.setColor(RED);
            return nodeH;
        }
        int compare = date.compareTo(nodeH.getKey());
        if(compare<0){
            TreeNode nodeLeft = nodeH.getLeftNode();
            if(nodeLeft==null) {
                nodeLeft = new TreeNode(null, null, false);
                nodeH.setLeftNode(nodeLeft);
                nodeLeft.setParentNode(nodeH);
            }
            insert(nodeLeft,date,occupancyMap);
        }
        else if(compare>0){
            TreeNode rightNode = nodeH.getRightNode();
            if(rightNode ==null){
                rightNode = new TreeNode(null, null, false);
                rightNode.setParentNode(nodeH);
                nodeH.setRightNode(rightNode);
            }
            insert(rightNode,date,occupancyMap);

        }
        else{
             nodeH.setValue(occupancyMap);
        }

        if(!isRed(nodeH.getLeftNode()) && isRed(nodeH.getRightNode()) ){
            nodeH= rotateLeft(nodeH);
        }
        else if(isRed(nodeH.getLeftNode()) && isRed(nodeH.getLeftNode().getLeftNode())){
            nodeH= rotateRight(nodeH);
        }
        else if(isRed(nodeH.getLeftNode()) && isRed(nodeH.getRightNode()) ){
            flipColors(nodeH);
        }
        return nodeH;
    }
    public OccupancyMap searchValue(Date key ){
        TreeNode nodeX =root;
        while(nodeX != null){
            int compare = key.compareTo(nodeX.getKey());
            if(compare<0){
                nodeX = nodeX.getLeftNode();
            }
            else if(compare>0){
                nodeX =nodeX.getRightNode();
            }
            else{
                // node Found
                return nodeX.getValue();
            }
        }
        return null;
    }

}
