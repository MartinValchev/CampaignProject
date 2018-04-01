package com.company.campaignproject.service;

import com.company.campaignproject.entity.BannerPositionOccupacy;
import com.company.campaignproject.entity.CampaignBannerPosition;

import java.util.List;

public interface CampaignService {
    String NAME = "CampaignService";
    void processAddCampaignBannerPosition(CampaignBannerPosition campaignBannerPosition, List<BannerPositionOccupacy> bannerPositionOccupacyList);
    void processRemoveCampaignBannerPosition(CampaignBannerPosition campaignBannerPosition, List<BannerPositionOccupacy> bannerPositionOccupacyList);
    void processEditCampaignBannerPosition(String property,CampaignBannerPosition campaignBannerPosition, List<BannerPositionOccupacy> bannerPositionOccupacyList);
    List<BannerPositionOccupacy> getUpdateList();
    List<BannerPositionOccupacy> getRemoveList();
}