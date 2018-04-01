package com.company.campaignproject.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.List;
import javax.persistence.OneToMany;

@NamePattern("%s|name")
@Table(name = "CAMPAIGNPROJECT_CAMPAIGN")
@Entity(name = "campaignproject$Campaign")
public class Campaign extends StandardEntity {
    private static final long serialVersionUID = -3863148709128702949L;

    @Column(name = "NAME", nullable = false, length = 50)
    protected String name;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "campaign")
    protected List<CampaignBannerPosition> campaignBannerPosition;

    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE", nullable = false)
    protected Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE", nullable = false)
    protected Date endDate;


    public void setCampaignBannerPosition(List<CampaignBannerPosition> campaignBannerPosition) {
        this.campaignBannerPosition = campaignBannerPosition;
    }

    public List<CampaignBannerPosition> getCampaignBannerPosition() {
        return campaignBannerPosition;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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