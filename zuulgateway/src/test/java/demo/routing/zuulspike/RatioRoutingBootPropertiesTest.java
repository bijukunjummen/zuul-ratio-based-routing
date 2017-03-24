package demo.routing.zuulspike;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatioRoutingBootPropertiesTest {

    @Autowired
    private RatioRoutingProperties ratioRoutingProperties;

    @Test
    public void testRatioRoutingProps() {
        assertThat(ratioRoutingProperties.getOldPercent()).isEqualTo(25);
    }

    @Configuration
    @EnableConfigurationProperties(RatioRoutingProperties.class)
    public static class SpringConfig {

        @Bean
        public RatioRoutingProperties ratioRoutingProperties() {
            return new RatioRoutingProperties();
        }
    }
}
