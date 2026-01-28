package me.will0mane.software.ignite.presets;

import io.prometheus.metrics.instrumentation.jvm.JvmMetrics;
import me.will0mane.software.ignite.IgniteHandler;
import me.will0mane.software.ignite.MetricPreset;

public class JVMPreset implements MetricPreset {
    @Override
    public void register(IgniteHandler handler) {
        JvmMetrics.builder().register();
    }
}
