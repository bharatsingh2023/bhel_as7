
/*
 * package com.bhel.project.securityConfigure;
 * 
 * import javax.servlet.ServletContext; import javax.servlet.ServletException;
 * import javax.servlet.http.HttpSessionEvent; import
 * javax.servlet.http.HttpSessionListener;
 * 
 * public class SessionListener implements HttpSessionListener {
 * 
 * @Override public void sessionCreated(HttpSessionEvent event) {
 * logger.info("session created");
 * event.getSession().setMaxInactiveInterval(1); }
 * 
 * @Override public void sessionDestroyed(HttpSessionEvent event) {
 * logger.info("session destroyed"); }
 * 
 * public void onStartup(ServletContext servletContext) throws ServletException
 * { super.onStartup(servletContext); servletContext.addListener(new
 * SessionListener()); } }
 */
