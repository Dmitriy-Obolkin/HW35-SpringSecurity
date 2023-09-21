//package ua.ithillel.config;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.springframework.context.annotation.Bean;
//
//@org.springframework.context.annotation.Configuration
//public class HibernateSession {
//    public static final SessionFactory sessionFactory;
//
//    static {
//        Configuration configuration = new Configuration();
//        configuration.configure();
//
//        configuration.setProperty("hibernate.connection.url", System.getenv("JDBC_URL"));
//        configuration.setProperty("hibernate.connection.username", System.getenv("JDBC_USERNAME"));
//        configuration.setProperty("hibernate.connection.password", System.getenv("JDBC_PASSWORD"));
//
//        sessionFactory = configuration.buildSessionFactory();
//    }
//
//    @Bean
//    public static SessionFactory getSessionFactory(){
//        return sessionFactory;
//    }
//}
