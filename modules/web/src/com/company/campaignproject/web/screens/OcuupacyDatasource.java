package com.company.campaignproject.web.screens;
import com.company.campaignproject.entity.BannerPositionOccupacy;
import com.company.campaignproject.entity.BannerPositionOccupancy;
import com.haulmont.cuba.gui.data.impl.CustomCollectionDatasource;

import java.util.*;

public class OcuupacyDatasource extends CustomCollectionDatasource<BannerPositionOccupancy, UUID> {
   private  List<BannerPositionOccupancy> bannerPositionOccupacyList = new ArrayList();
    @Override
    protected Collection<BannerPositionOccupancy> getEntities(Map<String, Object> params) {


        return bannerPositionOccupacyList;
    }

    @Override
    public void addItem(BannerPositionOccupancy item) {
        bannerPositionOccupacyList.add(item);
    }

}
