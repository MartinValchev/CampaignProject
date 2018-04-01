package com.company.campaignproject.service;

import com.company.campaignproject.entity.BannerPositionOccupacy;
import com.company.campaignproject.entity.CampaignBannerPosition;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

import java.util.*;


@Service(CampagnServiceBean.NAME)
public class CampagnServiceBean implements CampaignService {


    @Inject
    private Metadata metaData;

    private List<BannerPositionOccupacy> updateList =  new ArrayList<>();
    private List<BannerPositionOccupacy> removeList =  new ArrayList<>();

    private BannerPositionOccupacy  createOccupacyRecord(CampaignBannerPosition campBannerPosition,Date occupacyDay){
        BannerPositionOccupacy newInstance = metaData.create(BannerPositionOccupacy.class);
        newInstance.setOccupacyDay(occupacyDay);
        newInstance.setImpressionsSum(campBannerPosition.getImpressions());
        newInstance.setBannerPosition(campBannerPosition.getBannerPosition());
        Integer impressionsLimit = campBannerPosition.getBannerPosition().getImpressionsLimit();
        Double percentidgeValue = ((campBannerPosition.getImpressions().doubleValue() / impressionsLimit.doubleValue()) * 100);
        Double percentidge = Double.valueOf(percentidgeValue);
        newInstance.setPercentidge(percentidge);
        return newInstance;
    }


    private Date moveDate(Date date ,int days){
        for(int counter =1;counter<=days;counter++){
            Calendar calendar = Calendar.getInstance();
            // add one day
            calendar.setTime(date);
            calendar.add(Calendar.DATE, days);
            date = calendar.getTime();
        }

        return date;
    }

    @Override
    public void processAddCampaignBannerPosition(CampaignBannerPosition campaignBannerPosition, List<BannerPositionOccupacy> bannerPositionOccupacyList) {
        Date startDate = campaignBannerPosition.getStartDate();
        Date endDate = campaignBannerPosition.getEndDate();
        if(bannerPositionOccupacyList.size()>0) {
            Date occupacyListStartDate = bannerPositionOccupacyList.get(0).getOccupacyDay();
            int startIndex = 0;
            while (startDate.before(occupacyListStartDate)) {
                BannerPositionOccupacy newInstance = createOccupacyRecord(campaignBannerPosition, startDate);
                updateList.add(newInstance);
                startDate = moveDate(startDate, 1);
            }
            processOccupacyListImpressions(bannerPositionOccupacyList, campaignBannerPosition);
            Date lastRecordDate = bannerPositionOccupacyList.get(bannerPositionOccupacyList.size() - 1).getOccupacyDay();
            while (lastRecordDate.before(endDate)) {
                lastRecordDate = moveDate(lastRecordDate, 1);
                BannerPositionOccupacy newInstance = createOccupacyRecord(campaignBannerPosition, lastRecordDate);
                updateList.add(newInstance);
            }
        }else{
            endDate = moveDate(endDate,1);
            while(startDate.before(endDate)){
                BannerPositionOccupacy newInstance = createOccupacyRecord(campaignBannerPosition, startDate);
                updateList.add(newInstance);
                startDate = moveDate(startDate,1);
            }
        }
    }

    @Override
    public void processRemoveCampaignBannerPosition(CampaignBannerPosition campaignBannerPosition, List<BannerPositionOccupacy> bannerPositionOccupacyList) {
        Integer newDeltaImpressions = -(campaignBannerPosition.getImpressionsDelta());
        campaignBannerPosition.setImpressionsDelta(newDeltaImpressions);
        processOccupacyListImpressions(bannerPositionOccupacyList,campaignBannerPosition);
    }

    @Override
    public void processEditCampaignBannerPosition(String property, CampaignBannerPosition campaignBannerPosition, List<BannerPositionOccupacy> bannerPositionOccupacyList) {
        switch(property){
            case Constants.IMPRESSIONS_PROPERTY: processOccupacyListImpressions(bannerPositionOccupacyList, campaignBannerPosition); break;
            case Constants.START_DATE_PROPERTY: processOccupacyListStartDate(bannerPositionOccupacyList, campaignBannerPosition); break;
            case Constants.END_DATE_PROPERTY:processOccupacyListEndDate(bannerPositionOccupacyList, campaignBannerPosition); break;
        }
    }

    private void processOccupacyListStartDate(List<BannerPositionOccupacy> bannerPositionOccupacyList, CampaignBannerPosition campaignBannerPosition){
        Date iterationsDate = null;
        Date occupacyDate =null;

            iterationsDate =campaignBannerPosition.getStartDate();
            occupacyDate = bannerPositionOccupacyList.get(0).getOccupacyDay();
        // if iteration date before occupancy day remove impressions
        int startIndex= 0;
        if(iterationsDate.before(occupacyDate)) {
            while (iterationsDate.before(occupacyDate)) {
                BannerPositionOccupacy newInstance = createOccupacyRecord(campaignBannerPosition, iterationsDate);
                iterationsDate = moveDate(iterationsDate, 1);
                updateList.add(newInstance);
            }
        }else if(occupacyDate.before(iterationsDate)) {
            int index=0;
            while (occupacyDate.before(iterationsDate)) {
                BannerPositionOccupacy currentInstance = bannerPositionOccupacyList.get(index);
                Integer newImpressionsSum = currentInstance.getImpressionsSum() - campaignBannerPosition.getImpressions();
                currentInstance.setImpressionsSum(newImpressionsSum);
                if (newImpressionsSum == 0) {
                    currentInstance.setPercentidge(0.0);
                } else {
                    Double newPercent = (double) newImpressionsSum / (double) campaignBannerPosition.getBannerPosition().getImpressionsLimit();
                    currentInstance.setPercentidge(newPercent);
                }
                updateList.add(currentInstance);
                occupacyDate = moveDate(occupacyDate, 1);
                index++;
            }
        }
    }
    private void processOccupacyListEndDate(List<BannerPositionOccupacy> bannerPositionOccupacyList, CampaignBannerPosition campaignBannerPosition){
            BannerPositionOccupacy lastBannerOccupacyRecord = bannerPositionOccupacyList.get(bannerPositionOccupacyList.size()-1);
            Date lastBannerOccupacyRecordDate = lastBannerOccupacyRecord.getOccupacyDay();
            Date campaignEndDate = campaignBannerPosition.getEndDate();
            int index=0;
            while(campaignEndDate.before(lastBannerOccupacyRecordDate)){
                BannerPositionOccupacy currentInstance = bannerPositionOccupacyList.get(index);
                Integer newImpressionsSum = currentInstance.getImpressionsSum() - campaignBannerPosition.getImpressions();
                currentInstance.setImpressionsSum(newImpressionsSum);
                Double percent = newImpressionsSum == 0? 0.0
                        :(double) newImpressionsSum / (double) campaignBannerPosition.getBannerPosition().getImpressionsLimit();

                if(getIndexFromUpdateList(currentInstance.getOccupacyDay(), campaignBannerPosition) ==null){

                    currentInstance.setPercentidge(percent);
                    updateList.add(currentInstance);
                }else{
                    Integer itemIndex = getIndexFromUpdateList(currentInstance.getOccupacyDay(), campaignBannerPosition);
                    currentInstance.setPercentidge(percent);
                    updateList.get(itemIndex).setImpressionsSum(newImpressionsSum);
                    updateList.get(itemIndex).setPercentidge(percent);
                }

                campaignEndDate = moveDate(campaignEndDate, 1);
                index++;
            }
            if(lastBannerOccupacyRecordDate.before(campaignEndDate)) {
                campaignEndDate = moveDate(campaignEndDate,1);
            while (lastBannerOccupacyRecordDate.before(campaignEndDate)) {
                lastBannerOccupacyRecordDate = moveDate(lastBannerOccupacyRecordDate, 1);
                BannerPositionOccupacy newInstance = createOccupacyRecord(campaignBannerPosition, lastBannerOccupacyRecordDate);
                updateList.add(newInstance);
            }
        }
    }


    @Override
    public List<BannerPositionOccupacy> getUpdateList() {
        return updateList;
    }

    @Override
    public List<BannerPositionOccupacy> getRemoveList() {
        return removeList;
    }

    private void processOccupacyListImpressions(List<BannerPositionOccupacy> bannerPositionOccupacyList, CampaignBannerPosition campaignBannerPosition){
        int startIndex=0;

        while(startIndex<bannerPositionOccupacyList.size()){
            BannerPositionOccupacy currentItem = bannerPositionOccupacyList.get(startIndex);
            // check if this item is already added in update list
            Integer newImpressionsSum = null;
            if(getIndexFromUpdateList(currentItem.getOccupacyDay(), campaignBannerPosition) ==null){
                currentItem.setBannerPosition(campaignBannerPosition.getBannerPosition());
                newImpressionsSum = campaignBannerPosition.getImpressionsDelta()+currentItem.getImpressionsSum();
                Integer impressionsLimit = campaignBannerPosition.getBannerPosition().getImpressionsLimit();
                Double percent = ((double)newImpressionsSum/(double)impressionsLimit*100);
                currentItem.setImpressionsSum(newImpressionsSum);
                currentItem.setPercentidge(percent);
                 updateList.add(currentItem);
            }else{
                Integer itemIdex = getIndexFromUpdateList(currentItem.getOccupacyDay(), campaignBannerPosition);
                Integer updateImpressionsSum = updateList.get(itemIdex).getImpressionsSum();
                newImpressionsSum = campaignBannerPosition.getImpressionsDelta()+updateImpressionsSum;
                Integer impressionsLimit = campaignBannerPosition.getBannerPosition().getImpressionsLimit();
                Double percent = ((double)newImpressionsSum/(double)impressionsLimit*100);
                updateList.get(itemIdex).setImpressionsSum(newImpressionsSum);
                updateList.get(itemIdex).setPercentidge(percent);
            }
            startIndex++;
        }
    }
    private Integer getIndexFromUpdateList(Date occupacyDay, CampaignBannerPosition campaignBannerPosition){
        Integer itemIndex = null;
        for(int index =0; index<updateList.size()
                ;index++){
            if(updateList.get(index).getBannerPosition().getId().equals(campaignBannerPosition.getBannerPosition().getId())
                    && occupacyDay.compareTo(updateList.get(index).getOccupacyDay())==0){
                itemIndex = index;
            }
        }
        return itemIndex;
    }
    private List<BannerPositionOccupacy> mergeLists(List<BannerPositionOccupacy> commitContextList,List<BannerPositionOccupacy> operationList){

        return commitContextList;
    }
}