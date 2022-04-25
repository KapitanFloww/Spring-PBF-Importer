package de.flowwindustries.osm.commons.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Enable Async when the given property is set to true.
 */
@Slf4j
@Configuration
@EnableAsync
@ConditionalOnProperty(name = "spring:application:async:enabled", havingValue = "true")
public class AsyncConfiguration {
}
