package com.company.campaignproject.service;

import com.company.campaignproject.entity.BannerPosition;
import com.company.campaignproject.entity.BannerPositionOccupacy;
import com.company.campaignproject.entity.BannerPositionOccupancy;
import com.company.campaignproject.entity.CampaignBannerPosition;
import com.company.campaignproject.utils.OccupancyTree;
import com.company.campaignproject.utils.TreeNode;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;

@Service(BannerPositionOccupacyService.NAME)
public class BannerPositionOccupacyServiceBean implements BannerPositionOccupacyService {

    @Inject
    Metadata metadata;

    @Inject
    Persistence persistence;

    @Inject
    private DataManager dataManager;

    private OccupancyTree occupancyTree;
    private List<BannerPositionOccupancy> occupacyList;
    private TreeNode root;

    private Date moveDate(Date date ,int days,String operation){
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
    private List<CampaignBannerPosition> loadCampaignBannerPositionEntries(Date startDate, Date endDate, BannerPosition bannerPosition){
        List<CampaignBannerPosition> resultList =null;

        LoadContext<CampaignBannerPosition> loadContext = LoadContext.create(CampaignBannerPosition.class)
                .setQuery(LoadContext.createQuery("select e from campaignproject$CampaignBannerPosition e where e.bannerPosition.id =:bannerPositionId and e.startDate <=:inputEndDate AND e.endDate >=:inputStartDate").setParameter("bannerPositionId",bannerPosition.getId())
                        .setParameter("inputEndDate", endDate).setParameter("inputStartDate", startDate)).setView("campaignBannerPosition-view");

        resultList= dataManager.loadList(loadContext);
        return resultList;

    }
    private TreeNode generateTreeNode(Date compareDate,List<CampaignBannerPosition> insertList){
        OccupancyMap map = new OccupancyMap();

        map.setKey(compareDate);
        for(CampaignBannerPosition item: insertList){
            if((item.getStartDate().before(compareDate)||item.getStartDate().compareTo(compareDate)==0) &&
                    (item.getEndDate().after(compareDate)||item.getEndDate().compareTo(compareDate)==0 ) ){
                map.addValue(item);
            }
        }
        TreeNode treeNode = new TreeNode(compareDate,map,true);
        return treeNode;
    }
    private void fillOccupancyTree(Date startDate,Date endDate, List<CampaignBannerPosition> insertList) {
        if(occupancyTree ==null){
            root =generateTreeNode(startDate,insertList);
            occupancyTree = new OccupancyTree(root);
            startDate = moveDate(startDate,1,"ADD");
        }
        endDate =  moveDate(endDate,1,"ADD");
        while(startDate.before(endDate)){
                TreeNode treeNode =generateTreeNode(startDate,insertList);
                occupancyTree.insert(root,startDate,treeNode.getValue());
            startDate = moveDate(startDate,1,"ADD");
        }

    }
    private List<BannerPositionOccupancy> processOccupacyTree(Date startDate,Date endDate){
        List<BannerPositionOccupancy> occupacyList = new ArrayList<>();
        Date newEndDate = moveDate(endDate,1,"ADD");
        while(startDate.before(newEndDate)){
            OccupancyMap currentNodeValue =occupancyTree.searchValue(startDate);
            BannerPositionOccupancy currentItem = constructBannerPositionOccupacyInstance(currentNodeValue);
            occupacyList.add(currentItem);
            startDate = moveDate(startDate,1,"ADD");
        }
        return occupacyList;
    }
    private BannerPositionOccupancy constructBannerPositionOccupacyInstance(OccupancyMap occupancyMap){
        BannerPositionOccupancy item = metadata.create(BannerPositionOccupancy.class);
        List<CampaignBannerPosition> campaignBannerPositionList = occupancyMap.getValue();
        BannerPosition bannerPosition =  campaignBannerPositionList.get(0).getBannerPosition();
        item.setBannerPosition(bannerPosition);
        Date itemDate = occupancyMap.getKey();
        item.setOccupacyDay(itemDate);
        int impressionsSum = 0;
        double percentidge=0;
       for(CampaignBannerPosition instance: campaignBannerPositionList){
           impressionsSum += instance.getImpressions();
       }
       if(impressionsSum>0){
           int impressionsLimit = bannerPosition.getImpressionsLimit();
           percentidge = ((double)impressionsSum/impressionsLimit)*100;
       }
       item.setImpressionsSum(impressionsSum);
       item.setPercentidge(percentidge);
        return item;
    }
    private List<BannerPositionOccupancy> generateEmptyOccupancyList(Date startDate, Date endDate, BannerPosition bannerPosition){
        Date newEndDate = moveDate(endDate,1,"ADD");
        List<BannerPositionOccupancy> emptyList = new ArrayList<>();
        while(startDate.before(newEndDate)){
            BannerPositionOccupancy newInstance = metadata.create(BannerPositionOccupancy.class);
            newInstance.setBannerPosition(bannerPosition);
            newInstance.setImpressionsSum(0);
            newInstance.setPercentidge(0.0);
            newInstance.setOccupacyDay(startDate);
            emptyList.add(newInstance);
            startDate = moveDate(startDate,1,"ADD");
        }
        return emptyList;
    }

    @Override
    public void processOccupacyRequest(Date startDate, Date endDate, BannerPosition bannerPosition) {
        List<CampaignBannerPosition> dbList =loadCampaignBannerPositionEntries(startDate, endDate, bannerPosition);
        if(dbList !=null && dbList.size()>0){
            fillOccupancyTree(startDate,endDate, dbList);
            occupacyList = processOccupacyTree(startDate,endDate) ;
        }else{
            occupacyList= generateEmptyOccupancyList(startDate, endDate, bannerPosition);
        }

    }

    @Override
    public Collection<BannerPositionOccupancy> getEntities(Map<String, Object> params) {
        Date startDate = (Date)params.get("startDate");
        Date endDate = (Date)params.get("endDate");
        BannerPosition bannerPosition =  (BannerPosition)params.get("bannerPosition");
        processOccupacyRequest(startDate, endDate, bannerPosition);
        return occupacyList;
    }
}