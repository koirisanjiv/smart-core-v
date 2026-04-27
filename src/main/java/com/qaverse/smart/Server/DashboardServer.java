package com.qaverse.smart.Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;

public class DashboardServer extends NanoHTTPD {

    private static final int PORT = 8090;

    public DashboardServer() {
        super(PORT);
    }

    // ❌ REMOVE auto start from constructor
    public void startServer() {
        try {
            start(SOCKET_READ_TIMEOUT, false);
            System.out.println("Dashboard running: http://localhost:" + PORT);
        } catch (IOException e) {
            throw new RuntimeException("Failed to start DashboardServer", e);
        }
    }

    public void stopServer() {
        stop();
        System.out.println("Dashboard server stopped");
    }

    @Override
    public Response serve(IHTTPSession session) {

        try {
            String uri = session.getUri();

            // 👉 Serve JSON
            if ("/data".equals(uri)) {

                File file = new File("analytics/latest.json");

                if (!file.exists()) {
                    return newFixedLengthResponse(
                            Response.Status.OK,
                            "application/json",
                            "{\"message\":\"No data available\"}"
                    );
                }

                return newFixedLengthResponse(
                        Response.Status.OK,
                        "application/json",
                        new FileInputStream(file),
                        file.length()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newFixedLengthResponse(
                Response.Status.NOT_FOUND,
                "text/plain",
                "404 Not Found"
        );
    }
}