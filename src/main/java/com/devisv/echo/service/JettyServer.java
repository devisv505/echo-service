package com.devisv.echo.service;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

import static com.devisv.echo.service.SimpleServlet.PATCH;

public class JettyServer {

    private static final int PORT = 9000;

    private static Server server = new Server();

    private JettyServer() {
    }

    public static void start() throws Exception {
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(PORT);

        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(SimpleServlet.class, PATCH);

        server.setConnectors(new Connector[] {serverConnector});
        server.setHandler(servletHandler);

        server.start();
        server.join();
    }

    public static void stop() throws Exception {
        if (server.isStarted()
                && !server.isStopped()
                && !server.isStopping()) {
            server.stop();
        }
    }
}
