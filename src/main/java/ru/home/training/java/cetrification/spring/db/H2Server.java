package ru.home.training.java.cetrification.spring.db;

import org.h2.tools.Server;
import java.sql.SQLException;

/**
 * Класс для управления TCP-сервером H2 database.
 * Предоставляет методы для запуска и остановки сервера H2 в режиме TCP,
 * что позволяет удаленным клиентам подключаться к базе данных.
 * 
 * <p>Сервер запускается на указанном порту (по умолчанию 9092) с параметрами:
 * <ul>
 *   <li>{@code -tcpAllowOthers} - разрешает подключения с других хостов</li>
 *   <li>{@code -ifNotExists} - автоматически создает базу при первом подключении, если она не существует</li>
 * </ul>
 * 
 * <p>Пример использования:
 * <pre>
 * H2Server server = new H2Server();
 * server.start();
 * // работа с БД...
 * server.stop();
 * </pre>
 * 
 * <p>Класс также предоставляет метод {@code main} для самостоятельного запуска сервера.
 * При запуске через main автоматически регистрируется shutdown hook для корректной
 * остановки сервера при завершении работы приложения.
 * 
 * @see org.h2.tools.Server
 * @see java.sql.SQLException
 */
public class H2Server {
    /**
     * Экземпляр TCP-сервера H2.
     * Инициализируется при вызове {@link #start()}.
     */
    private Server server;

    /**
     * Запускает TCP-сервер H2 с предустановленными параметрами.
     * <p>Параметры сервера:
     * <ul>
     *   <li>Порт: 9092</li>
     *   <li>Разрешены подключения с других хостов</li>
     *   <li>Автоматическое создание БД при необходимости</li>
     * </ul>
     * 
     * <p>В случае успешного запуска выводит сообщение в консоль.
     * Ошибки запуска выводятся в виде stack trace.
     */
    public void start() {
        try {
            // Создание и запуск TCP-сервера H2 с параметрами:
            // -tcpPort 9092 - порт для подключения
            // -tcpAllowOthers - разрешить подключения с других хостов
            // -ifNotExists - создавать базу, если она не существует
            // -baseDir - ОТНОСИТЕЛЬНАЯ директория расположения H2 TCP-сервера (если не указан абс.путь в application.properties)
            server = Server.createTcpServer(
                    "-tcpPort", "9092", 
                    "-tcpAllowOthers", 
                    "-ifNotExists"
                    ).start();
            // Сообщение об успешном запуске
            System.out.println("H2 сервер стартовал и слушает порт 9092.");
        } catch (SQLException e) {
            // Обработка ошибок SQL
            e.printStackTrace();
        }
    }

    /**
     * Останавливает работающий сервер H2.
     * <p>Если сервер не был запущен, метод не выполняет никаких действий.
     * При успешной остановке выводит сообщение в консоль.
     */
    public void stop() {
        if (server != null) {
            // Остановка сервера, если он был запущен
            server.stop();
            // Сообщение об остановке
            System.out.println("H2 сервер остановлен.");
        }
    }

    // Точка входа в приложение
    public static void main(String[] args) {
        
        System.out.println("Кодировка: " + System.getProperty("file.encoding"));

        // Создание экземпляра H2Server
        H2Server h2Server = new H2Server();
        // Запуск сервера
        h2Server.start();
        // Добавление хука для корректной остановки сервера при завершении приложения
        Runtime.getRuntime().addShutdownHook(new Thread(h2Server::stop));
    }
}
