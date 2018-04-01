package com.company.campaignproject.service;

import com.company.campaignproject.entity.BannerPosition;
import com.company.campaignproject.entity.BannerPositionOccupancy;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

public interface BannerPositionOccupacyService {
    String NAME = "campaignproject_BannerPositionOccupacyService";

    public void processOccupacyRequest(Date startDate, Date endDate, BannerPosition bannerPosition);
    public Collection<BannerPositionOccupancy> getEntities(Map<String, Object> params);
}