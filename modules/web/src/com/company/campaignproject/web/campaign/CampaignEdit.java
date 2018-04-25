package com.company.campaignproject.web.campaign;


import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.campaignproject.entity.Campaign;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;
import com.haulmont.cuba.gui.data.Datasource;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

public class CampaignEdit extends AbstractEditor<Campaign> {
    @Named("bannerPositionTable.create")
    private CreateAction bannerPositionTableCreate;

    @Named("bannerPositionTable.edit")
    private EditAction bannerPositionTableEdit;

    @Inject
    @Named("campaignDs")
    Datasource campaignDs;

    Date campaignStartDate;
    Date campaignEndDate;

        @Override
        public void init(Map<String, Object> params){

        }

        @Override
    public void ready(){
            campaignStartDate = ((Campaign)campaignDs.getItem()).getStartDate();
            campaignEndDate = ((Campaign)campaignDs.getItem()).getEndDate();
            Map<String,Object> params = new HashMap<>();
            params.put("campaignStartDate",campaignStartDate);
            params.put("campaignEndDate",campaignEndDate);
            bannerPositionTableCreate.setWindowParams(params);
            bannerPositionTableEdit.setWindowParams(params);
        }



}
