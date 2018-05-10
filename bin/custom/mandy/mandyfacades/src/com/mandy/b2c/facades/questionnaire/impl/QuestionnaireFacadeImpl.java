package com.mandy.b2c.facades.questionnaire.impl;

import com.mandy.b2c.core.questionnaire.QuestionnaireService;
import com.mandy.b2c.facades.questionnaire.QuestionnaireFacade;
import com.mandy.platform.data.QuestionnaireData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ljh
 * @version 1.0.0
 * @PackageName com.mandy.b2c.facades.questionnaire.impl
 * @ClassName QuestionnaireFacadeImpl
 * @Description 调查问卷Facade业务
 * @Date 2018/5/8 16:43
 */
public class QuestionnaireFacadeImpl implements QuestionnaireFacade{

    @Autowired
    QuestionnaireService questionnaireService;

    @Override
    public QuestionnaireData getQuestionnaireDataByCode(String code) {
        return null;
    }

    @Override
    public SearchPageData<QuestionnaireData> getQuestionnairePageData(PageableData pageableData) {
        return null;
    }

    @Override
    public void createQuestionnaire(QuestionnaireData questionnaireData) {

    }
}
