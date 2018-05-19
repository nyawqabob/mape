package by.epam.controller;

import by.epam.command.CommandFactory;
import by.epam.command.AbstractCommand;
import by.epam.pool.ConnectionPool;
import by.epam.view.View;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
public class ControllerServlet extends HttpServlet {

    /**
     * Destroy con pool here
     */
    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyConnections();
    }

    /**
     * Take command by request from the map in CommandFactory send error page if didn't find
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandFactory factory = new CommandFactory();
        AbstractCommand command = factory.getCommand(req);
        if (command == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found :(");
            return;
        }
        processRequest(command, req, resp);
    }

    private void processRequest(AbstractCommand command, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        View view = command.execute(request);
        if (view.getViewType() == View.ViewType.FORWARD) {
            String path = "/WEB-INF/jsp/" + view.getPagePath() + ".jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(path);
            dispatcher.forward(request, response);
        } else {
            String redirectPath = request.getContextPath() + "/controller/" + view.getPagePath();
            response.sendRedirect(redirectPath);
        }
    }
}
