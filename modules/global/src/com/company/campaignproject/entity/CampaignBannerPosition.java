package com.company.campaignproject.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.validation.constraints.NotNull;
import com.haulmont.chile.core.annotations.MetaProperty;
import javax.persistence.Transient;

@NamePattern("%s %s|bannerPosition,impressions")
@Table(name = "CAMPAIGNPROJECT_CAMPAIGN_BANNER_POSITION")
@Entity(name = "campaignproject$CampaignBannerPosition")
public class CampaignBannerPosition extends StandardEntity {
    private static final long serialVersionUID = 8912004248705382135L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAMPAIGN_ID")
    protected Campaign campaign;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BANNER_POSITION_ID")
    protected BannerPosition bannerPosition;

    @Column(name = "IMPRESSIONS")
    protected Integer impressions;

    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE", nullable = false)
    protected Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE", nullable = false)
    protected Date endDate;

    @Transient
    @MetaProperty
    protected Integer impressionsDelta;

    public void setImpressionsDelta(Integer impressionsDelta) {
        this.impressionsDelta = impressionsDelta;
    }

    public Integer getImpressionsDelta() {
        return impressionsDelta;
    }


    public void setImpressions(Integer impressions) {
        this.impressions = impressions;
    }

    public Integer getImpressions() {
        return impressions;
    }


    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setBannerPosition(BannerPosition bannerPosition) {
        this.bannerPosition = bannerPosition;
    }

    public BannerPosition getBannerPosition() {
        return bannerPosition;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }


}