package com.company.campaignproject.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;
import javax.persistence.OneToMany;

@NamePattern("%s (%s)|name,webversion")
@Table(name = "CAMPAIGNPROJECT_WEBSITE")
@Entity(name = "campaignproject$Website")
public class Website extends StandardEntity {
    private static final long serialVersionUID = -741057644120681616L;

    @Column(name = "NAME", nullable = false, length = 50)
    protected String name;

    @Column(name = "WEBVERSION", nullable = false)
    protected String webversion;


    @OneToMany(mappedBy = "website")
    protected List<BannerPosition> bannerPosition;

    public void setBannerPosition(List<BannerPosition> bannerPosition) {
        this.bannerPosition = bannerPosition;
    }

    public List<BannerPosition> getBannerPosition() {
        return bannerPosition;
    }


    public void setWebversion(Webversion webversion) {
        this.webversion = webversion == null ? null : webversion.getId();
    }

    public Webversion getWebversion() {
        return webversion == null ? null : Webversion.fromId(webversion);
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}