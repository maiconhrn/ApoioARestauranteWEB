package br.uem.apoioarestaurante.utils;

/**
 * @author Maicon
 */
public class EnvironmentUtil {

    public static final String PROD_ENV = "prod";
    public static final String LOCAL_ENV = "local";
    public static final String TEST_ENV = "test";

    public static String getCurrentEnv() {
        String currentEnv = System.getProperty("env");

        return currentEnv != null ? currentEnv : "";
    }
}
