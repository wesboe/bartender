package nl.ing.jfall.bar.bartender.configuration;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tags;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Configuration
@Slf4j
public class MonitoringConfig {

    private static final String BUILD_INFO = "build_info";
    private static final int ONE = 1;
    private final BuildProperties buildProperties;

    public MonitoringConfig(final BuildProperties buildProperties, MeterRegistry registry) {
        this.buildProperties = buildProperties;
    }

    @EventListener
    public void runner(ContextRefreshedEvent event) {
        log.info(String.format("%s started with version %s and build time %s.",
                buildProperties.getName(), buildProperties.getVersion(), buildProperties.getTime()));
        Metrics.gauge("build_info", Tags.of("version", buildProperties.getVersion()), 1);
    }
}
