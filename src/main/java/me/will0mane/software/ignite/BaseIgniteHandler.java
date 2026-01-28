package me.will0mane.software.ignite;

import io.prometheus.metrics.core.metrics.Counter;
import io.prometheus.metrics.core.metrics.Gauge;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BaseIgniteHandler implements IgniteHandler {

    private final Map<String, Counter> counters = new ConcurrentHashMap<>();
    private final Map<String, Gauge> gauges = new ConcurrentHashMap<>();

    private Counter getOrPut(String id) {
        Counter counter = counters.get(id);
        if (counter != null) return counter;
        try {
            Counter register = Counter.builder().name(id).help("ignite-generated counter - " + id).register();
            counters.put(id, register);
            return register;
        } catch (Exception ignored) {
        }
        return null;
    }

    private Gauge getOrPutGauge(String id, String... labelNames) {
        Gauge gauge = gauges.get(id);
        if (gauge != null) return gauge;
        try {
            Gauge register = Gauge.builder().name(id).labelNames(labelNames).help("ignite-generated gauge - " + id).register();
            gauges.put(id, register);
            return register;
        } catch (Exception ignored) {
        }
        return null;
    }

    @Override
    public void inc(String id) {
        Counter orPut = getOrPut(id);
        if (orPut == null) return;
        orPut.inc();
    }

    @Override
    public void inc(String id, double amount) {
        Counter orPut = getOrPut(id);
        if (orPut == null) return;
        orPut.inc(amount);
    }

    @Override
    public void dec(String id) {
        Counter orPut = getOrPut(id);
        if (orPut == null) return;
        orPut.inc();
    }

    @Override
    public void inc(String id, String label, String... labelNames) {
        inc(id, label, 1, labelNames);
    }

    @Override
    public void inc(String id, String label, double amount, String... labelNames) {
        Gauge orPutGauge = getOrPutGauge(id, labelNames);
        if (orPutGauge == null) return;
        orPutGauge.labelValues(label).inc(amount);
    }

    @Override
    public void dec(String id, String label, String... labelNames) {
        dec(id, label, 1, labelNames);
    }

    @Override
    public void dec(String id, String label, double amount, String... labelNames) {
        Gauge orPutGauge = getOrPutGauge(id, labelNames);
        if (orPutGauge == null) return;
        orPutGauge.labelValues(label).dec(amount);
    }

    @Override
    public void set(String id, String label, double amount, String... labelNames) {
        Gauge orPutGauge = getOrPutGauge(id, labelNames);
        if (orPutGauge == null) return;
        orPutGauge.labelValues(label).set(amount);
    }
}

