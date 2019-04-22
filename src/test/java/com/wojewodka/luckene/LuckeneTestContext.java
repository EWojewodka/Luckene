package com.wojewodka.luckene;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@Configuration
@ComponentScan(basePackages = {"com.wojewodka"})
@TestPropertySource("classpath:luckene-test.properties")

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LuckeneTestContext.class})
public abstract class LuckeneTestContext {
}
