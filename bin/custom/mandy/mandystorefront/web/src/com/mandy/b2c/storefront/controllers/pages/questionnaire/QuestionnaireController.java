package com.mandy.b2c.storefront.controllers.pages.questionnaire;

import com.mandy.b2c.facades.questionnaire.QuestionnaireFacade;
import com.mandy.b2c.storefront.Validator.QuestionnaireValidator;
import com.mandy.b2c.storefront.forms.QuestionnaireForm;
import com.mandy.platform.data.QuestionnaireData;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import java.io.UnsupportedEncodingException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ljh
 * @version 1.0.0
 * @PackageName questionnaire
 * @ClassName QuestionnaireController
 * @Description 问卷调查
 * @Date 2018/5/8 9:30
 */
@Controller
@RequestMapping(value = "/questionnaire")
public class QuestionnaireController extends AbstractSearchPageController {
    private static final Logger LOG = LoggerFactory.getLogger(QuestionnaireController.class);


    private static final String QUESTIONNAIRE_RECORD_CMS_PAGE = "questionnaire";
    private static final String QUESTIONNAIRE_DETAIL_CMS_PAGE = "questionnaireDetail";
    private static final String QUESTIONNAIRE_LIST_CMS_PAGE = "questionnaireList";

    private static final String BREADCRUMBS_ATTR = "breadcrumbs";
    private static final String TEXT_DEMAND_PROFILE = "text.account.profile.questionnaireForm";
    private static final String FORM_GLOBAL_ERROR = "form.global.error";
    private static final String DEFAULT_CURRENT_PAGE = "0";
    private static final String DEFAULT_PAGE_SIZE = "10";
    private static final String DEFAULT_CVS_PAGE_SIZE = "50";

    @Resource(name = "questionnaireFacade")
    QuestionnaireFacade questionnaireFacade;

    @Resource(name = "accountBreadcrumbBuilder")
    private ResourceBreadcrumbBuilder accountBreadcrumbBuilder;

    @Resource(name = "questionnaireValidator")
    private QuestionnaireValidator questionnaireValidator;

    /**
     * 填写问卷调查
     * @param model
     * @param request
     * @param response
     * @return
     * @throws CMSItemNotFoundException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/recordForm", method = RequestMethod.GET)
    @RequireHardLogIn
    public String recordForm(final Model model,final HttpServletRequest request,
        final HttpServletResponse response,QuestionnaireForm form)
        throws CMSItemNotFoundException, UnsupportedEncodingException
    {
        storeCmsPageInModel(model, getContentPageForLabelOrId(QUESTIONNAIRE_RECORD_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(QUESTIONNAIRE_RECORD_CMS_PAGE));
        model.addAttribute("labelOrId",QUESTIONNAIRE_RECORD_CMS_PAGE);
        model.addAttribute(BREADCRUMBS_ATTR, accountBreadcrumbBuilder.getBreadcrumbs(TEXT_DEMAND_PROFILE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);

        return getViewForPage(model);
    }

    /**
     * 查看问卷调查
     * @param code
     * @param model
     * @param request
     * @param response
     * @return
     * @throws CMSItemNotFoundException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    @RequireHardLogIn
    public String getQuestionnaire(@PathVariable final String code,final Model model, final HttpServletRequest request,
        final HttpServletResponse response)
        throws CMSItemNotFoundException, UnsupportedEncodingException
    {
        QuestionnaireData questionnaireData = questionnaireFacade.getQuestionnaireDataByCode(code);

        model.addAttribute("questionnaireData",questionnaireData);

        storeCmsPageInModel(model, getContentPageForLabelOrId(QUESTIONNAIRE_DETAIL_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(QUESTIONNAIRE_DETAIL_CMS_PAGE));
        model.addAttribute("labelOrId",QUESTIONNAIRE_DETAIL_CMS_PAGE);
        model.addAttribute(BREADCRUMBS_ATTR, accountBreadcrumbBuilder.getBreadcrumbs(TEXT_DEMAND_PROFILE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);

        return getViewForPage(model);
    }

    /**
     * 保存问卷调查
     * @param model
     * @param bindingResult
     * @param request
     * @param response
     * @return
     * @throws CMSItemNotFoundException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequireHardLogIn
    public String saveQuestionnaire(final Model model, QuestionnaireForm form, final BindingResult bindingResult,
        final HttpServletRequest request, final HttpServletResponse response)
        throws CMSItemNotFoundException, UnsupportedEncodingException {

        LOG.info("user add questionnaire form,productName=" +form.getProductName());

        questionnaireValidator.validate(form,bindingResult);

        if (bindingResult.hasErrors())
        {
            return setErrorMessagesAndCMSPage(model, QUESTIONNAIRE_RECORD_CMS_PAGE);
        }

        QuestionnaireData questionnaireData = new QuestionnaireData();

        try {
            org.springframework.beans.BeanUtils.copyProperties(questionnaireData, form);
            questionnaireFacade.createQuestionnaire(questionnaireData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return REDIRECT_PREFIX + "/questionnaire/query";
    }

    /**
     * 问卷调查查询
     * @param model
     * @param currentPage 当前页
     * @param pageSize 每页数目
     * @return 列表
     * @throws CMSItemNotFoundException
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @RequireHardLogIn
    public String search(final Model model,
        @RequestParam(value = "page", defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
        @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) final int pageSize)
        throws CMSItemNotFoundException
    {
        final PageableData pageableData = new PageableData();
        pageableData.setCurrentPage(currentPage);
        pageableData.setPageSize(pageSize);

        SearchPageData<QuestionnaireData> searchPageData = questionnaireFacade.getQuestionnairePageData(pageableData);
        populateModel(model, searchPageData, ShowMode.Page);
        model.addAttribute(BREADCRUMBS_ATTR, accountBreadcrumbBuilder.getBreadcrumbs(TEXT_DEMAND_PROFILE));

        storeCmsPageInModel(model, getContentPageForLabelOrId(QUESTIONNAIRE_LIST_CMS_PAGE));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(QUESTIONNAIRE_LIST_CMS_PAGE));
        model.addAttribute("labelOrId",QUESTIONNAIRE_LIST_CMS_PAGE);
        model.addAttribute(BREADCRUMBS_ATTR, accountBreadcrumbBuilder.getBreadcrumbs(TEXT_DEMAND_PROFILE));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);

        return getViewForPage(model);
    }

    /**
     * 定义Error信息
     * @param model
     * @param cmsPageLabelOrId
     * @return
     * @throws CMSItemNotFoundException
     */
    protected String setErrorMessagesAndCMSPage(final Model model, final String cmsPageLabelOrId) throws CMSItemNotFoundException
    {
        GlobalMessages.addErrorMessage(model, FORM_GLOBAL_ERROR);
        storeCmsPageInModel(model, getContentPageForLabelOrId(cmsPageLabelOrId));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(cmsPageLabelOrId));
        model.addAttribute(BREADCRUMBS_ATTR, accountBreadcrumbBuilder.getBreadcrumbs(TEXT_DEMAND_PROFILE));
        return getViewForPage(model);
    }


}
