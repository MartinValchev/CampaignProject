package com.company.campaignproject.web.screens;

import com.company.campaignproject.entity.BannerPositionOccupacy;
import com.company.campaignproject.entity.BannerPositionOccupancy;
import com.company.campaignproject.service.BannerPositionOccupacyService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.data.impl.CustomCollectionDatasource;

import java.util.*;


public class BannerPositionOccupacyDatasource extends CustomCollectionDatasource<BannerPositionOccupancy,UUID> {

private List<BannerPositionOccupancy> occupacyList =new ArrayList<>();

@Override
protected Collection<BannerPositionOccupancy> getEntities(Map<String, Object> params) {
        return occupacyList;
    }
    public void addItem(BannerPositionOccupancy item) {

    occupacyList.add(item);
    }
   @Override
    public void removeItem(BannerPositionOccupancy item){
        occupacyList.remove(item);
   }
}
