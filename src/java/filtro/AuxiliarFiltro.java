/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtro;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

/**
 *
 * @author crisd
 */
public class AuxiliarFiltro implements Filter{

    private FilterConfig configuration;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.configuration = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try{
            String tipo = ((HttpServletRequest) request).getSession().getAttribute("tipo").toString();
            
            if(tipo.equals("Auxiliar")) {
                chain.doFilter(request, response);
            } 
            else{
                ((HttpServletResponse)response).sendRedirect("/PrestamosLabElectronicaUIHC/aplicativo/sesionInvalida.xhtml");
            }
        }
        catch(NullPointerException e){
            ((HttpServletResponse)response).sendRedirect("/PrestamosLabElectronicaUIHC/aplicativo/sesionInvalida.xhtml");
        }
    }

    @Override
    public void destroy() {
        this.configuration = null;
    }
    
}
