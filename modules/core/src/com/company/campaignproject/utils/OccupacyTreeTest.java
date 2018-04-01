package com.company.campaignproject.utils;


import com.company.campaignproject.entity.BannerPositionOccupacy;
import com.company.campaignproject.entity.CampaignBannerPosition;
import com.company.campaignproject.service.OccupancyMap;
import com.haulmont.cuba.core.global.Metadata;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OccupacyTreeTest {


    public static void main(String[] args){
        //09.03
        Date startDate1 = new Date();
        //03.03
        Date startDate2 = moveDate(startDate1,6,"REMOVE");
        // 15.03
        Date startDate3 = moveDate(startDate1,6,"ADD");
        //01.03
        Date startDate4 = moveDate(startDate2,2,"REMOVE");
        //06.03
        Date startDate5 = moveDate(startDate1,3,"REMOVE");
        //12.03
        Date startDate6 = moveDate(startDate1,3,"ADD");
        //18.03
        Date startDate7= moveDate(startDate1,9,"ADD");
        //05.03
        Date startDate8 = moveDate(startDate1,4,"REMOVE");
        //07.03
        Date startDate9 = moveDate(startDate1,2,"REMOVE");
        Date endDate = moveDate(startDate1,16,"ADD");
        // initialize lists
        List<CampaignBannerPosition> list1 = new ArrayList<>();
        CampaignBannerPosition campaignBannerPosition1 =createCampaignBannerPostionInstance(startDate1, endDate);
        list1.add(campaignBannerPosition1);
        OccupancyMap map1 = new OccupancyMap(startDate1,list1);
        List<CampaignBannerPosition> list2 = new ArrayList<>();
        list2.add(createCampaignBannerPostionInstance(startDate2, endDate));
        OccupancyMap map2 = new OccupancyMap(startDate2,list2);
        List<CampaignBannerPosition> list3 = new ArrayList<>();
        list3.add(createCampaignBannerPostionInstance(startDate3, endDate));
        OccupancyMap map3 = new OccupancyMap(startDate3,list3);
        List<CampaignBannerPosition> list4 = new ArrayList<>();
        list4.add(createCampaignBannerPostionInstance(startDate4, endDate));
        OccupancyMap map4 = new OccupancyMap(startDate4,list4);
        List<CampaignBannerPosition> list5 = new ArrayList<>();
        list5.add(createCampaignBannerPostionInstance(startDate5, endDate));
        OccupancyMap map5 = new OccupancyMap(startDate5,list5);
        List<CampaignBannerPosition> list6 = new ArrayList<>();
        list6.add(createCampaignBannerPostionInstance(startDate6, endDate));
        OccupancyMap map6 = new OccupancyMap(startDate6,list6);
        List<CampaignBannerPosition> list7 = new ArrayList<>();
        list7.add(createCampaignBannerPostionInstance(startDate7, endDate));
        OccupancyMap map7 = new OccupancyMap(startDate7,list7);
        List<CampaignBannerPosition> list8 = new ArrayList<>();
        list8.add(createCampaignBannerPostionInstance(startDate8, endDate));
        OccupancyMap map8 = new OccupancyMap(startDate8,list8);
        List<CampaignBannerPosition> list9 = new ArrayList<>();
        list9.add(createCampaignBannerPostionInstance(startDate9, endDate));
        OccupancyMap map9 = new OccupancyMap(startDate9,list9);
        TreeNode node1 =  new TreeNode(startDate1,map1,false);
      /*  TreeNode node2 =  new TreeNode(startDate2,map2,true);
        TreeNode node3 =  new TreeNode(startDate3,map3,true);
        TreeNode node4 =  new TreeNode(startDate4,map4,true);
        TreeNode node5 =  new TreeNode(startDate5,map5,true);
        TreeNode node6 =  new TreeNode(startDate6,map6,true);
        TreeNode node7 =  new TreeNode(startDate7,map7,true);
        TreeNode node8 =  new TreeNode(startDate8,map8,true);
        TreeNode node9 =  new TreeNode(startDate9,map9,true);*/
        OccupancyTree  tree = new OccupancyTree(node1);
        TreeNode node =tree.insert(node1,startDate2,map2);
        node =tree.insert(node,startDate3,map3);
        node =tree.insert(node,startDate4,map4);
        node = tree.insert(node,startDate5,map5);
        node = tree.insert(node,startDate6,map6);
        node =tree.insert(node,startDate7,map7);
        node =tree.insert(node,startDate8,map8);
        node =tree.insert(node,startDate9,map9);
        printTree(node);
    }
    private static void printTree(TreeNode node){
        if(node ==null){
            return;
        }
        System.out.println(node.getKey());
        printTree(node.getLeftNode());
        printTree(node.getRightNode());
    }
    private static CampaignBannerPosition createCampaignBannerPostionInstance(Date startDate, Date endDate){
        CampaignBannerPosition campaignBannerPosition =new CampaignBannerPosition();
        campaignBannerPosition.setImpressions(4500);
        campaignBannerPosition.setStartDate(startDate);
        campaignBannerPosition.setEndDate(endDate);
        return campaignBannerPosition;
    }

    private static Date moveDate(Date date ,int days,String operation){
        Calendar calendar = Calendar.getInstance();
        // add one day
        calendar.setTime(date);
        if(operation.equals("ADD")) {
            calendar.add(Calendar.DATE, days);
        }else {
            calendar.add(Calendar.DATE, -days);
        }

        date = calendar.getTime();
        return date;
    }

}
