package me.will0mane.software.ignite;

import io.prometheus.metrics.exporter.httpserver.HTTPServer;

import java.io.IOException;

public class Exposer {

    private HTTPServer server;

    private final int port;

    public Exposer(int port) {
        this.port = port;
    }

    public void open() throws IOException {
        server = HTTPServer.builder()
                .port(port)
                .hostname("0.0.0.0")
                .buildAndStart();
    }

    public void close() {
        server.close();
    }

}
