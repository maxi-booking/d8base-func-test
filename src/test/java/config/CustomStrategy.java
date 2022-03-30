package config;

import drivers.WebDriverConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.platform.engine.ConfigurationParameters;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfiguration;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfigurationStrategy;

public class CustomStrategy implements ParallelExecutionConfiguration, ParallelExecutionConfigurationStrategy {

    private static final WebDriverConfig config =
            ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    @Override
    public int getParallelism() {
        return config.getThreads();
    }

    @Override
    public int getMinimumRunnable() {
        return config.getThreads();
    }

    @Override
    public int getMaxPoolSize() {
        return config.getThreads();
    }

    @Override
    public int getCorePoolSize() {
        return config.getThreads();
    }

    @Override
    public int getKeepAliveSeconds() {
        return 30;
    }

    @Override
    public ParallelExecutionConfiguration createConfiguration(final ConfigurationParameters configurationParameters) {
        return this;
    }
}