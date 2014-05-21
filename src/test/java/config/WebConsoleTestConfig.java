package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * User: aconstantin
 * Date: 5/19/14 9:06 PM
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@Import(value = {
        DataConsoleTestConfig.class
})
public class WebConsoleTestConfig extends WebConsoleConfig {
}
