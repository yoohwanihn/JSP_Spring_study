package ch11;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class ListenerExam implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener, HttpSessionAttributeListener {

    public ListenerExam() {

    }

    public void sessionCreated(HttpSessionEvent se)  { 
    	se.getSession().getServletContext().log("Session 생성됨 : " + se.getSession().getId());
    }

    public void attributeAdded(ServletContextAttributeEvent scae)  { 
    	scae.getServletContext().log("ServletContext 속성 추가 : " + scae.getValue());
    }

    public void attributeRemoved(ServletContextAttributeEvent scae)  {  
    	scae.getServletContext().log("ServletContext 속성 제거 : " + scae.getValue());
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	se.getSession().getServletContext().log("Session 소멸됨 : " + se.getSession().getId());
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	sce.getServletContext().log("ServletContext 종료됨!!");
    }

    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	se.getSession().getServletContext().log("Session 속성 추가 : " + se.getValue());
    }

    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	se.getSession().getServletContext().log("Session 속성 제거 : " + se.getValue());
    }

    public void attributeReplaced(HttpSessionBindingEvent se)  { 
    	se.getSession().getServletContext().log("Session 속성 변경 : " + se.getValue());
    }

    public void attributeReplaced(ServletContextAttributeEvent scae)  {  
    	scae.getServletContext().log("ServletContext 속성 변경 : " + scae.getValue());
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	sce.getServletContext().log("ServletContext 시작됨!!");
    }
	
}
