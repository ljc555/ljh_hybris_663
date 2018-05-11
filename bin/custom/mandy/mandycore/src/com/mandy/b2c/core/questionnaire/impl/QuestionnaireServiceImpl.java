package com.mandy.b2c.core.questionnaire.impl;

import com.mandy.b2c.core.daos.questionnaire.QuestionnaireDao;
import com.mandy.b2c.core.model.QuestionnaireModel;
import com.mandy.b2c.core.questionnaire.QuestionnaireService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.CustomerModel;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ljh
 * @version 1.0.0
 * @PackageName com.mandy.b2c.core.questionnaire.impl
 * @ClassName QuestionnaireServiceImpl
 * @Description 问卷调查Service
 * @Date 2018/5/8 16:34
 */
public class QuestionnaireServiceImpl implements QuestionnaireService{

    @Resource
    private QuestionnaireDao questionnaireDao;

    @Override
    public SearchPageData<QuestionnaireModel> getQuestionnairePageData(PageableData pageableData,
        CustomerModel currentUser) {
        return questionnaireDao.getQuestionnairePageData(pageableData,
            currentUser);
    }

    @Override
    public QuestionnaireModel getQuestionnaireDataByCode(String code, CustomerModel currentUser) {
        return questionnaireDao.getQuestionnaireDataByCode(code, currentUser);
    }
}
