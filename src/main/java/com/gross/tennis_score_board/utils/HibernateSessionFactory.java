package com.gross.tennis_score_board.utils;

import com.gross.tennis_score_board.model.Match;
import com.gross.tennis_score_board.model.Player;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateSessionFactory {
    private static final org.hibernate.SessionFactory hibernateSessionFactory;

    static {
        try {
            // Создание конфигурации
            Configuration configuration = new Configuration();

            // Настройки подключения к H2
            configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem:tennis_db;DB_CLOSE_DELAY=-1");
            configuration.setProperty("hibernate.connection.username", "sa");
            configuration.setProperty("hibernate.connection.password", "");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

            // Дополнительные настройки Hibernate
            configuration.setProperty("hibernate.hbm2ddl.auto", "create");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");

            // Регистрируем классы-сущности
            configuration.addAnnotatedClass(Player.class);
            configuration.addAnnotatedClass(Match.class);

            // Создание ServiceRegistry и SessionFactory
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            hibernateSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception ex) {
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed. " + ex);
        }
    }

    public static org.hibernate.SessionFactory getHibernateSessionFactory() {
        return hibernateSessionFactory;
    }
}
