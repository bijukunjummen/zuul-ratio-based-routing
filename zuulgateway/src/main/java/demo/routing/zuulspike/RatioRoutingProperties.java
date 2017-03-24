package demo.routing.zuulspike;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "routing.ratio")
@Component
public class RatioRoutingProperties {

    private int oldPercent;

    public void setOldPercent(int oldPercent) {
        this.oldPercent = oldPercent;
    }

    public int getOldPercent() {
        return oldPercent;
    }
}
