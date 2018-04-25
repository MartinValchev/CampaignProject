package com.company.campaignproject.web.campaignbannerposition;

import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.campaignproject.entity.CampaignBannerPosition;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.TextField;

import com.haulmont.cuba.gui.data.Datasource;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.Map;

public class CampaignBannerPositionEdit extends AbstractEditor<CampaignBannerPosition> {
    Date campaignStartDate;
    Date campaignEndDate;
    @Inject
    @Named("campaignBannerPositionDs")
    Datasource campaignBannerPositionDs;

    @Inject
    @Named("startDateField")
    DateField startDateField;

    @Inject
    @Named("endDateField")
    DateField endDateField;

    @Inject
    @Named("impressionsField")
    TextField impressionsField;

    private void initializePropertyChangeListener(){
        campaignBannerPositionDs.addItemPropertyChangeListener(e ->{
            String changedProperty = e.getProperty();
            switch(changedProperty){
                case "startDate":
                    Date startDate = (Date)e.getValue();
                    startDateChanged(startDate, (Date)e.getPrevValue()); break;
                case "endDate":
                    Date endDate = (Date)e.getValue();
                    endDateChanged(endDate,(Date)e.getPrevValue()); break;
                case "bannerPosition":
                    break;
                case "impressions":
                    Integer impressions = (Integer)e.getValue();
                    Integer prevImpressions = (Integer)e.getPrevValue();
                    Integer bannerPositionLimit = ((CampaignBannerPosition)(campaignBannerPositionDs.getItem())).getBannerPosition().getImpressionsLimit();
                    impressionsChanged(prevImpressions,impressions,bannerPositionLimit); break;
            }
        });
    }
    private void startDateChanged(Date newStartDate,Date prevStartDate){
        Date endDate = ((CampaignBannerPosition)(campaignBannerPositionDs.getItem())).getEndDate();
        if(endDate !=null&& newStartDate.after(endDate)){
            showNotification("Start date must be before end date");
            startDateField.setValue(prevStartDate);
        }
        if(newStartDate !=null && (newStartDate.before(campaignStartDate)||newStartDate.after(campaignEndDate)) ){
            showNotification("Start date must be before or equal to campaign start date and not after campaign end date");
            startDateField.setValue(prevStartDate);
        }
    }
    private void endDateChanged(Date newEndDate, Date prevEndDate){
        Date startDate = ((CampaignBannerPosition)(campaignBannerPositionDs.getItem())).getStartDate();
        if(startDate!=null && newEndDate !=null && newEndDate.before(startDate)){
            showNotification("End date must be after start date");
            endDateField.setValue(prevEndDate);
        }
        if(newEndDate !=null && (newEndDate.after(campaignEndDate)|| newEndDate.before(campaignStartDate))){
            showNotification("End date must be before or equal to campaign end date and not before campaignStartDate");
            endDateField.setValue(prevEndDate);
        }
    }
    private void impressionsChanged(Integer prevImpressions,Integer impressions, Integer bannerPositionLimit){
        if(impressions !=null) {
            if (impressions <= 0) {
                showNotification("Impressions limit must be greater than zero");
                impressionsField.setValue(prevImpressions);
            }
            if (impressions > bannerPositionLimit) {
                showNotification("Impressions must not be grater than banner position impressions");
                impressionsField.setValue(prevImpressions);
            }
        }
    }

    @Override
    public void init(Map<String,Object> params){
        campaignStartDate = (Date)params.get("campaignStartDate");
        campaignEndDate = (Date)params.get("campaignEndDate");
        initializePropertyChangeListener();
    }


}