package com.juzi.cqupt.sdk.jwzx.api.impl;

import com.juzi.cqupt.sdk.jwzx.api.JwzxService;
import com.juzi.cqupt.sdk.jwzx.config.JwzxConfig;
import com.juzi.cqupt.sdk.jwzx.config.JwzxSimpleConfig;
import lombok.Data;
import org.junit.jupiter.api.TestInstance;

/**
 * @author Juzi
 * @since 2019/9/6 17:41
 * Blog https://juzibiji.top
 */
@Data
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JwzxBaseTest {

    protected JwzxConfig jwzxConfig;
    protected JwzxService jwzxService = new JwzxServiceImpl(jwzxConfig);
    protected String username = "1655633";
    protected String password = "jqsmx1731815301";

    {
        JwzxSimpleConfig jwzxSimpleConfig = new JwzxSimpleConfig(true);
        jwzxSimpleConfig
                .setUsername("2017214032")
                .setPassword("067678")
                .setDomainOut("http://jwzx.cqupt.juzibiji.top");

        this.jwzxConfig = jwzxSimpleConfig;
    }
}
