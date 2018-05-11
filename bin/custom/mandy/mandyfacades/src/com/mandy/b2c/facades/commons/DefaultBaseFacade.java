package com.mandy.b2c.facades.commons;

import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.product.UnitService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.model.ModelService;
import javax.annotation.Resource;

/**
 * Created by Jianghong.Li on 07/5/2018.
 */
public abstract class DefaultBaseFacade {
    private final String currency = "CNY";
    private final String UNIT_PIECES = "pieces";

    @Resource
    private KeyGenerator keyGenerator;
    @Resource
    private ModelService modelService;
    @Resource
    private UnitService unitService;
    @Resource
    private CommonI18NService commonI18NService;

    public <S, T> SearchPageData<T> convertPageData(final SearchPageData<S> source, final Converter<S, T> converter)
    {
        final SearchPageData<T> result = new SearchPageData<T>();
        result.setPagination(source.getPagination());
        result.setSorts(source.getSorts());
        result.setResults(Converters.convertAll(source.getResults(), converter));
        return result;
    }

    public String getCode()
    {
        final Object generatedValue = keyGenerator.generate();
        if (generatedValue instanceof String)
        {
            return (String) generatedValue;
        }
        else
        {
            return String.valueOf(generatedValue);
        }
    }

    public PriceRowModel createPriceRowModel(final Double price)
    {
        if (price == null || price.equals(""))
        {
            return null;
        }
        final CurrencyModel currencyModel = commonI18NService.getCurrency(currency);

        final PriceRowModel priceRowModel = modelService.create(PriceRowModel.class);
        priceRowModel.setProductMatchQualifier((long) -1);
        priceRowModel.setUnit(unitService.getUnitForCode(UNIT_PIECES));
        priceRowModel.setCurrency(currencyModel);
        priceRowModel.setPrice(price);
        return priceRowModel;
    }
}
