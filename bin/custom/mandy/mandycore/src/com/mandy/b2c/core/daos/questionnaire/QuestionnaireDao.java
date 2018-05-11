package com.mandy.b2c.core.daos.questionnaire;

import com.mandy.b2c.core.model.QuestionnaireModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.CustomerModel;

/**
 * @author ljh
 * @version 1.0.0
 * @PackageName com.mandy.b2c.core.daos.questionnaire
 * @ClassName QuestionnaireDao
 * @Description 问卷调查Dao
 * @Date 2018/5/8 9:58
 */
public interface QuestionnaireDao {
    /**
     * 分页查询问卷调查
     * @param pageableData
     * @return
     */
    SearchPageData<QuestionnaireModel> getQuestionnairePageData(PageableData pageableData, CustomerModel currentUser);

    /**
     * 根据code查询问卷调查
     * @param code
     * @return
     */
    QuestionnaireModel getQuestionnaireDataByCode(String code, CustomerModel currentUser);
}
