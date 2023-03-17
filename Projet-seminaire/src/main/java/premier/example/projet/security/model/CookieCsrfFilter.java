//package premier.example.projet.security.model;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//public class CookieCsrfFilter extends OncePerRequestFilter {
//	
//	/**
//	   * {@inheritDoc}
//	   */
//	  @Override
//	  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//	                                  FilterChain filterChain) throws ServletException, IOException {
//	    CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
//	    response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
//	    filterChain.doFilter(request, response);
//	  }
//
//}
