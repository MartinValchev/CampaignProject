package com.company.campaignproject.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.chile.core.annotations.MetaProperty;
import java.util.Date;

@MetaClass(name = "campaignproject$BannerPositionOccupancy")
public class BannerPositionOccupancy extends BaseUuidEntity {
    private static final long serialVersionUID = 479821298830741243L;

    @MetaProperty
    protected BannerPosition bannerPosition;

    @MetaProperty
    protected Integer impressionsSum;

    @MetaProperty
    protected Double percentidge;

    @MetaProperty
    protected Date occupacyDay;

    public void setBannerPosition(BannerPosition bannerPosition) {
        this.bannerPosition = bannerPosition;
    }

    public BannerPosition getBannerPosition() {
        return bannerPosition;
    }

    public void setImpressionsSum(Integer impressionsSum) {
        this.impressionsSum = impressionsSum;
    }

    public Integer getImpressionsSum() {
        return impressionsSum;
    }

    public void setPercentidge(Double percentidge) {
        this.percentidge = percentidge;
    }

    public Double getPercentidge() {
        return percentidge;
    }

    public void setOccupacyDay(Date occupacyDay) {
        this.occupacyDay = occupacyDay;
    }

    public Date getOccupacyDay() {
        return occupacyDay;
    }


}