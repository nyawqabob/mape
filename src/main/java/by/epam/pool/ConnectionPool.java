package by.epam.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {

    private static Queue<Connection> connectionQueue;
    private static int poolSize;
    private static ConnectionPool instance;
    private static AtomicBoolean isCreated = new AtomicBoolean();
    private static Lock lock = new ReentrantLock();
    private static Lock anothLock = new ReentrantLock();
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private ConnectionPool() {
        registerJDBCDriver();
        init();
    }

    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            anothLock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    isCreated.set(true);
                }
            } finally {
                anothLock.unlock();
            }
        }
        return instance;

    }

    public void destroyConnections() {
        for (int index = 0; index < poolSize; index++) {
            Connection connection = null;
            try {
                connection = connectionQueue.remove();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException ex) {

                    }
                }
            }
        }
    }

    private static void registerJDBCDriver() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        Connection cn = null;
        lock.lock();
        try {
            while (cn == null) {
                try {
                    cn = connectionQueue.remove();
                } catch (NoSuchElementException ex) {
                    LOGGER.info("Connection is busy, wait for connection", ex);
                }
            }
        } finally {
            lock.unlock();
        }

        return cn;
    }

    public void putConnectionBack(Connection connection) {
        lock.lock();
        try {
            connectionQueue.add(connection);
        } finally {
            lock.unlock();
        }

    }

    public static void init() {

        final String DATABASE_PROPERTY = "database";
        final String DATABASE_URL = "db.url";
        final String DATABASE_USER = "db.user";
        final String DATABASE_PASSWORD = "db.password";
        final String DATABASE_CHARACTER_ENCODING = "db.characterEncoding";
        final String DATABASE_USE_UNICODE = "db.useUnicode";
        final String DATABASE_POOL_SIZE = "db.poolSize";

        ResourceBundle resourceBundle;
        try {
            resourceBundle = ResourceBundle.getBundle(DATABASE_PROPERTY);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        final String URL = resourceBundle.getString(DATABASE_URL);
        final String USER = resourceBundle.getString(DATABASE_USER);
        final String PASSWORD = resourceBundle.getString(DATABASE_PASSWORD);
        final String CHARACTER_ENCODING = resourceBundle.getString(DATABASE_CHARACTER_ENCODING);
        final String USE_UNICODE = resourceBundle.getString(DATABASE_USE_UNICODE);
        final String POOL_SIZE = resourceBundle.getString(DATABASE_POOL_SIZE);

        Properties properties = new Properties();
        properties.put("user", USER);
        properties.put("password", PASSWORD);
        properties.put("characterEncoding", CHARACTER_ENCODING);
        properties.put("useUnicode", USE_UNICODE);

        poolSize = Integer.parseInt(POOL_SIZE);
        connectionQueue = new ArrayDeque<>(poolSize);

        for (int index = 0; index < poolSize; index++) {
            Connection connection;
            try {
                connection = DriverManager.getConnection(URL, properties);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            connectionQueue.add(connection);
        }
    }
}
