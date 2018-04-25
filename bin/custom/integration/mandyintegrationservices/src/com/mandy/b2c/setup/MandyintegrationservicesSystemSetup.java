/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.mandy.b2c.setup;

import static com.mandy.b2c.constants.MandyintegrationservicesConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.mandy.b2c.constants.MandyintegrationservicesConstants;
import com.mandy.b2c.service.MandyintegrationservicesService;


@SystemSetup(extension = MandyintegrationservicesConstants.EXTENSIONNAME)
public class MandyintegrationservicesSystemSetup
{
	private final MandyintegrationservicesService mandyintegrationservicesService;

	public MandyintegrationservicesSystemSetup(final MandyintegrationservicesService mandyintegrationservicesService)
	{
		this.mandyintegrationservicesService = mandyintegrationservicesService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		mandyintegrationservicesService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return MandyintegrationservicesSystemSetup.class.getResourceAsStream("/mandyintegrationservices/sap-hybris-platform.png");
	}
}
