/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import controller.login.LoginBean;
import entities.Usuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pablo
 */
@WebFilter("/superAdministrador/*")
public class AuthSuperadministradorFilter implements Filter{
    
    
    private FilterConfig configuration;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.configuration = filterConfig;
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (((HttpServletRequest) request).getSession().getAttribute(LoginBean.getUSER_KEY()) == null || !((Usuario)((HttpServletRequest) request).getSession().getAttribute(
                LoginBean.getUSER_KEY())).getLeyendaTipo().equals("SuperAdministrador")){
            
            ((HttpServletResponse) response).sendRedirect("../login.xhtml");
            
        } else {
           
            chain.doFilter(request, response);
            
        }
    }
    
    @Override
    public void destroy() {
        configuration = null;
    }
}
