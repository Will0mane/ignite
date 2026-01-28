package me.will0mane.software.ignite;

import java.util.ArrayList;
import java.util.Collection;

public class Ignite {

    private static IgniteHandler handler = new BaseIgniteHandler();

    private static final Collection<Exposer> exposers = new ArrayList<>();

    private Ignite() {
    }

    public static void set(IgniteHandler handler) {
        Ignite.handler = handler;
    }

    public static IgniteHandler get() {
        return handler;
    }

    public static void register(MetricPreset preset) {
        preset.register(get());
    }

    public static void expose(int port) throws Exception {
        Exposer exposer = new Exposer(port);
        exposer.open();
        exposers.add(exposer);
    }

    public static void close() {
        for (Exposer exposer : exposers) {
            exposer.close();
        }
        exposers.clear();
    }

}
