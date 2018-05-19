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

public class ProfileFilter implements Filter {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ProfileFilter.class);

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
        LOGGER.info("Profile filter started");
        if (session == null) {
            LOGGER.info("Profile filter redirect to login");
            response.sendRedirect(Pages.LOGIN_PATH);
            return;
        }
        String accountRole = (String) session.getAttribute(Attributes.ACCOUNT_ROLE);
        if (Constants.ADMIN_ROLE.equals(accountRole)) {
            LOGGER.info("Profile filter redirect to admin");
            response.sendRedirect(Pages.ADMIN_PATH);
            return;
        }
        if (Constants.USER_ROLE.equals(accountRole)) {
            LOGGER.info("Profile filter redirect to user");
            response.sendRedirect(Pages.ADMIN_PATH);
            return;
        }
             String isLogin = (String) session.getAttribute(Attributes.IS_LOGIN);
        if (isLogin == null) {
            LOGGER.info("Profile filter redirect to login cuz isLogin is null");
            response.sendRedirect(Pages.LOGIN_PATH);
            return;
        }
        if ("false".equals(isLogin)) {
            LOGGER.info("Profile filter redirect to login cuz isLogin is false");
            response.sendRedirect(Pages.LOGIN_PATH);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
