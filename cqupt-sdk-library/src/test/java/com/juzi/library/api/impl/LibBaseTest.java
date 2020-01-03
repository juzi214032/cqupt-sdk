package com.juzi.library.api.impl;

import com.juzi.library.api.LibService;
import com.juzi.library.config.LibConfig;
import com.juzi.library.config.LibSimpleConfig;
import org.junit.jupiter.api.TestInstance;

/**
 * @author Juzi
 * @since 2019/11/22 9:00
 * Blog https://juzibiji.top
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LibBaseTest {
    protected LibConfig libConfig;
    protected LibService libService = new LibServiceImpl(this.libConfig);

    {
        LibSimpleConfig libSimpleConfig = new LibSimpleConfig(false);
        libSimpleConfig.setDomainOut("http://library.cqupt.juzibiji.top");
        this.libConfig = libSimpleConfig;
    }
}
