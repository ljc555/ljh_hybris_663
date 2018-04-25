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

import static com.mandy.b2c.constants.MandyintegrationwebConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.mandy.b2c.constants.MandyintegrationwebConstants;
import com.mandy.b2c.service.MandyintegrationwebService;


@SystemSetup(extension = MandyintegrationwebConstants.EXTENSIONNAME)
public class MandyintegrationwebSystemSetup
{
	private final MandyintegrationwebService mandyintegrationwebService;

	public MandyintegrationwebSystemSetup(final MandyintegrationwebService mandyintegrationwebService)
	{
		this.mandyintegrationwebService = mandyintegrationwebService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		mandyintegrationwebService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return MandyintegrationwebSystemSetup.class.getResourceAsStream("/mandyintegrationweb/sap-hybris-platform.png");
	}
}
