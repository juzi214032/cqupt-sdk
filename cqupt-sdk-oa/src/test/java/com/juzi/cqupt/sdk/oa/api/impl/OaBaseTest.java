package com.juzi.cqupt.sdk.oa.api.impl;

import com.juzi.cqupt.sdk.oa.api.OaService;
import com.juzi.cqupt.sdk.oa.config.OaConfig;
import com.juzi.cqupt.sdk.oa.config.OaSimpleConfig;
import org.junit.jupiter.api.TestInstance;

/**
 * @author Juzi
 * @since 2019/12/28 22:51
 * Blog https://juzibiji.top
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OaBaseTest {
    protected OaConfig oaConfig;
    protected OaService oaService = new OaServiceImpl(oaConfig);

    {
        OaConfig oaConfig = new OaSimpleConfig(true);
        this.oaConfig = oaConfig;
    }
}

