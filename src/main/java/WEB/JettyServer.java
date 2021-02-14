package WEB;

import WEB.handler.ServiceHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class JettyServer {

    public void start() throws Exception {
        Server server = new Server(8080);

        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(ServiceHandler.class, "/api/products");

        server.setHandler(servletHandler);

        server.start();
        //server.join();
    }
}
