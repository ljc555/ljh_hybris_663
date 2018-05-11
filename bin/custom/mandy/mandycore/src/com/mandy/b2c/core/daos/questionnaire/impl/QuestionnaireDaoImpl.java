package com.mandy.b2c.core.daos.questionnaire.impl;

import com.mandy.b2c.core.daos.questionnaire.QuestionnaireDao;
import com.mandy.b2c.core.enums.DeleteFlag;
import com.mandy.b2c.core.model.QuestionnaireModel;
import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ljh
 * @version 1.0.0
 * @PackageName com.mandy.b2c.core.daos.questionnaire.impl
 * @ClassName QuestionnaireDaoImpl
 * @Description 问卷调查Dao
 * @Date 2018/5/8 9:59
 */
public class QuestionnaireDaoImpl implements QuestionnaireDao {
    private static final Logger LOG = LoggerFactory.getLogger(QuestionnaireDaoImpl.class);
    private static final String QUERY_QUESTIONNAIRE_PAGE = "SELECT {q.pk} FROM {Questionnaire AS q} WHERE {q.deleted}=?deleted ORDER BY {q.modifiedtime} DESC";
    private static final String QUERY_QUESTIONNAIRE_BY_CODE = "SELECT {q.pk} FROM {Questionnaire AS q} WHERE {q.code}=?code AND {q.owner} = ?owner";

    @Resource
    private FlexibleSearchService flexibleSearchService;
    @Resource
    private PagedFlexibleSearchService pagedFlexibleSearchService;

    @Override
    public SearchPageData<QuestionnaireModel> getQuestionnairePageData(PageableData pageableData,
        CustomerModel currentUser) {
        final Map queryParams = new HashMap();
        queryParams.put("deleted", DeleteFlag.NODELETED);

        final SearchPageData<QuestionnaireModel> list = pagedFlexibleSearchService.search(QUERY_QUESTIONNAIRE_PAGE,
            queryParams, pageableData);

        return list;
    }

    @Override
    public QuestionnaireModel getQuestionnaireDataByCode(String code, CustomerModel currentUser) {
        final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(QUERY_QUESTIONNAIRE_BY_CODE);
        fQuery.addQueryParameter("code", code);
        fQuery.addQueryParameter("owner",currentUser);

        LOG.debug(fQuery.getQuery());

        final SearchResult result = flexibleSearchService.search(fQuery);

        final QuestionnaireModel model = (result != null && result.getCount() > 0)
            ? (QuestionnaireModel) result.getResult().iterator().next() : null;

        return model;
    }
}
