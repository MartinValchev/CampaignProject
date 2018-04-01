package com.company.campaignproject.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.haulmont.cuba.core.entity.*;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@NamePattern("%s %s|occupacyDay,percentidge")
@Table(name = "CAMPAIGNPROJECT_BANNER_POSITION_OCCUPACY")
@Entity(name = "campaignproject$BannerPositionOccupacy")
public class BannerPositionOccupacy extends BaseLongIdEntity {
    private static final long serialVersionUID = -3634201889909685198L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BANNER_POSITION_ID")
    protected BannerPosition bannerPosition;

    @Column(name = "IMPRESSIONS_SUM")
    protected Integer impressionsSum;

    @Column(name = "PERCENTIDGE")
    protected Double percentidge;

    @Temporal(TemporalType.DATE)
    @Column(name = "OCCUPACY_DAY", nullable = false)
    protected Date occupacyDay;

    @Column(name = "UPDATE_TS")
    protected Date updateTs;

    @Column(name = "UPDATED_BY", length = 50)
    protected String updatedBy;

    @Column(name = "CREATE_TS")
    protected Date createTs;

    @Column(name = "CREATED_BY", length = 50)
    protected String createdBy;

    @Column(name = "VERSION", nullable = false)
    protected Integer version;

    @Column(name = "DELETE_TS")
    protected Date deleteTs;

    @Column(name = "DELETED_BY", length = 50)
    protected String deletedBy;

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