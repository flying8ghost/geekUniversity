package org.geekbang.week05.c08;

import org.geekbang.week05.c02.JavaConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JavaConfig.class)
public class SchoolAutoConfiguration {
}
