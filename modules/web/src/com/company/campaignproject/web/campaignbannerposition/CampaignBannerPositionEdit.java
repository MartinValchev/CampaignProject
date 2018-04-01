package com.company.campaignproject.web.campaignbannerposition;

import com.company.campaignproject.entity.BannerPositionOccupacy;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.campaignproject.entity.CampaignBannerPosition;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.GroupDatasource;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

public class CampaignBannerPositionEdit extends AbstractEditor<CampaignBannerPosition> {

    @Inject
    @Named("bannerPositionOccupaciesDs")
    GroupDatasource bannerPositionOccupaciesDs;
    public void onUpdateRecordBtnClick(){
        bannerPositionOccupaciesDs.refresh();
       Collection<BannerPositionOccupacy> occupacyCollection = bannerPositionOccupaciesDs.getItems();
        BannerPositionOccupacy bannerPositionOccupacy = occupacyCollection.iterator().next();
        bannerPositionOccupacy.setImpressionsSum(4545);
        bannerPositionOccupaciesDs.setItem(bannerPositionOccupacy);
        bannerPositionOccupaciesDs.commit();
    }
}