package br.uem.apoioarestaurante.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Maicon
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static String configFilePath;

    public HibernateUtil() {
    }

    private static void setConfigFilePath(String env) {
        switch (env) {
            case EnvironmentUtil.PROD_ENV:
                configFilePath = "/configs/hibernate.cfg.xml";
                break;
            case EnvironmentUtil.TEST_ENV:
                configFilePath = "/configs/hibernate-test.cfg.xml";
                break;
            case EnvironmentUtil.LOCAL_ENV:
            default:
                configFilePath = "/configs/hibernate-local.cfg.xml";
        }
    }

    public static SessionFactory getSessionFactory() {
        setConfigFilePath(EnvironmentUtil.getCurrentEnv());

        if (sessionFactory == null || sessionFactory.isClosed()) {
            try {
                Configuration configuration = new Configuration();
                sessionFactory = configuration.configure(HibernateUtil.class.getResource(configFilePath)).buildSessionFactory();
            } catch (Throwable ex) {
                System.out.println("Erro ao inicar o Hibernte " + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }

        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}
