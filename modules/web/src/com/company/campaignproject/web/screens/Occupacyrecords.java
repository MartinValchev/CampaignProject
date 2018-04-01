package com.company.campaignproject.web.screens;

import com.company.campaignproject.entity.BannerPosition;
import com.company.campaignproject.entity.BannerPositionOccupacy;
import com.company.campaignproject.entity.BannerPositionOccupancy;
import com.company.campaignproject.service.BannerPositionOccupacyService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.Formatter;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

public class Occupacyrecords extends AbstractWindow {

    @Inject
    @Named("startDate")
    DateField startDate;

    @Inject
    @Named("endDate")
    DateField endDate;

    @Inject
    @Named("bannerPosition")
    LookupField bannerPosition;

    @Inject
    @Named("bannerPositionOccupaciesDs")
    CollectionDatasource bannerPositionOccupaciesDs;

    BannerPositionOccupacyService bannerPositionOccupacyService = AppBeans.get(BannerPositionOccupacyService.class);

    @Override
    public void init(Map<String, Object> params) {
        initializeEndDateListener();
    }

    public void onGenerateBtnClick(){
        if(isAllFieldsSet()) {
            Date selectStartDate = startDate.getValue();
            Date selectedEndDate = endDate.getValue();
            BannerPosition bannerPos = bannerPosition.getValue();
            Map<String, Object> inputMap = new HashMap<String, Object>();
            inputMap.put("startDate", selectStartDate);
            inputMap.put("endDate", selectedEndDate);
            inputMap.put("bannerPosition", bannerPos);
            cleanDatasource();
            Collection<BannerPositionOccupancy> occupacyList = bannerPositionOccupacyService.getEntities(inputMap);
            for (BannerPositionOccupancy item : occupacyList) {
                bannerPositionOccupaciesDs.addItem(item);
            }
            bannerPositionOccupaciesDs.refresh();
        }else{
            showNotification("Please make sure Banner Position, Start Date and End Date fields are filled");
        }
    }
    private boolean isAllFieldsSet(){
        boolean isAllFieldsSet = false;
        if(bannerPosition.getValue() !=null && startDate.getValue() !=null && endDate!=null){
            isAllFieldsSet= true;
        }
        return isAllFieldsSet;
    }
    public static class PercentFormatter implements Formatter<Double> {

        @Override
        public String format(Double value) {
           String  tempValue ="" + value;
           String newValue =tempValue;
           if((tempValue.substring(tempValue.indexOf('.'),tempValue.length()-1)).length()>1) {
               newValue = tempValue.substring(0, tempValue.indexOf('.') + 2);
           }
            return newValue +"%";
        }
    }
    private void cleanDatasource(){
        if(bannerPositionOccupaciesDs.getItems().size()>0) {
            Collection<BannerPositionOccupancy> items= bannerPositionOccupaciesDs.getItems();
            for (BannerPositionOccupancy item:items){
                bannerPositionOccupaciesDs.removeItem(item);
            }
        }
    }
    private void initializeEndDateListener(){

        endDate.addValueChangeListener(event->{
            if(endDate.getValue() !=null) {
                Date startDateValue = startDate.getValue();
                if (startDateValue == null || (endDate.getValue().before(startDateValue)
                        || endDate.getValue().compareTo(startDateValue) == 0)) {
                    showNotification("End Date must be after Start Date!!!");
                    Date prevDate = (Date) event.getPrevValue();
                    endDate.setValue(null);
                }
            }
        });
    }
}