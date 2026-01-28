package me.will0mane.software.ignite;

public interface IgniteHandler {

    void inc(String id);

    void inc(String id, double amount);

    void dec(String id);

    void inc(String id, String label, String... labelNames);

    void inc(String id, String label, double amount, String... labelNames);

    void dec(String id, String label, String... labelNames);

    void dec(String id, String label, double amount, String... labelNames);

    void set(String id, String label, double amount, String... labelNames);

}
