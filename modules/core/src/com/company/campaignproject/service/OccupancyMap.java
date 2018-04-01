package com.company.campaignproject.service;

import com.company.campaignproject.entity.CampaignBannerPosition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OccupancyMap {

    Date date;
    List<CampaignBannerPosition> campaignBannerPositionList;

    public OccupancyMap(){
        this(new Date(),new ArrayList<CampaignBannerPosition>());

    }
    public OccupancyMap(Date date,List<CampaignBannerPosition> campaignBannerPositionList){
        this.date = date;
        this.campaignBannerPositionList = campaignBannerPositionList;
    }

    public Date getKey() {
        return date;
    }

    public void setKey(Date date) {
        this.date = date;
    }



    public List<CampaignBannerPosition> getValue() {
        return campaignBannerPositionList;
    }

    public void addValue(CampaignBannerPosition campaignBannerPosition) {
        campaignBannerPositionList.add(campaignBannerPosition);
    }


}
