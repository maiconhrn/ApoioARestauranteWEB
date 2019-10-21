package br.uem.apoioarestaurante.web.filter;

import br.uem.apoioarestaurante.models.Usuarios;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Maicon
 */

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        Usuarios user = null;
        HttpSession sess = ((HttpServletRequest) request).getSession(false);

        if (sess != null) {
            user = (Usuarios) sess.getAttribute("loggedUser");
        }

        if (user == null) {
            String contextPath = ((HttpServletRequest) request)
                    .getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath
                    + "/security/login.xhtml");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
