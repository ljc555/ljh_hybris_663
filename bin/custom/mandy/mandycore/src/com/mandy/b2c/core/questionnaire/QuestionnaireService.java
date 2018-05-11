package com.mandy.b2c.core.questionnaire;

import com.mandy.b2c.core.model.QuestionnaireModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.CustomerModel; /**
 * @author ljh
 * @version 1.0.0
 * @PackageName com.mandy.b2c.core.questionnaire
 * @ClassName QuestionnaireService
 * @Description 问卷调查Service
 * @Date 2018/5/8 16:32
 */
public interface QuestionnaireService {
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
