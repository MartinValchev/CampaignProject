package com.company.campaignproject.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s %s|name,website")
@Table(name = "CAMPAIGNPROJECT_BANNER_POSITION")
@Entity(name = "campaignproject$BannerPosition")
public class BannerPosition extends StandardEntity {
    private static final long serialVersionUID = -3190726743753864486L;

    @Column(name = "NAME", nullable = false, length = 50)
    protected String name;

    @Column(name = "IMPRESSIONS_LIMIT", nullable = false)
    protected Integer impressionsLimit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WEBSITE_ID")
    protected Website website;

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImpressionsLimit(Integer impressionsLimit) {
        this.impressionsLimit = impressionsLimit;
    }

    public Integer getImpressionsLimit() {
        return impressionsLimit;
    }


}