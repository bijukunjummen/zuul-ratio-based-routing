package demo.routing.zuulspike;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class RatioRoutingPropertiesTest {

    @Test
    public void testRatioRoutingProps() {
        RatioRoutingProperties ratioProps = new RatioRoutingProperties();

        ratioProps.setOldPercent(40);

        assertThat(ratioProps.getOldPercent()).isEqualTo(40);
    }
}
