package by.epam.filter;

import by.epam.constant.Attributes;
import by.epam.constant.Constants;
import by.epam.constant.Pages;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginFilter implements Filter {
    
    private static final Logger LOGGER = LogManager.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        doFilter(request, response, chain);
    }

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        LOGGER.info("Login filter started");
            if (session == null) {
                return;
            }
            String accountRole = (String) session.getAttribute(Attributes.ACCOUNT_ROLE);
            if (Constants.USER_ROLE.equals(accountRole)) {
                LOGGER.info("Login filter redirect to user");
                 response.sendRedirect(Pages.USER_PATH);
                return;
            }
            if (Constants.ADMIN_ROLE.equals(accountRole)) {
                LOGGER.info("Login filter redirect to admin");
                response.sendRedirect(Pages.ADMIN_PATH);
                return;
            }
            chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
