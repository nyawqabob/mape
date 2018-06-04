package by.epam.pool;

import by.epam.constant.PoolData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
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

    /**
     * Need to register driver and init properties when pool called in first
     * time
     */
    private ConnectionPool() {
        registerJDBCDriver();
        init();
    }

    /**
     * Singleton method of this class, use double check singleton
     *
     * @return needed list
     */
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

    /**
     * Need to destroy all connections
     */
    public void destroyConnections() {
        Iterator<Connection> iterator = connectionQueue.iterator();
        while (iterator.hasNext()) {
            Connection connection = null;
            try {
                connection = iterator.next();
                iterator.remove();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        LOGGER.info("Connection after destroy wasn't close", ex);
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

    /**
     * Need to get connection from deque of connections, if queue is empy wait
     * for put back connection by while{}
     *
     * @return connectiom from queue
     */
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

    /**
     * Need to put connection back to deque
     *
     * @param connection this is what we putting back
     */
    public void putConnectionBack(Connection connection) {
        lock.lock();
        try {
            connectionQueue.add(connection);
        } finally {
            lock.unlock();
        }

    }

    /**
     * Need to put properties and add certain number of connections do deque
     */
    public static void init() {

        Properties properties = new Properties();
        properties.put("user", PoolData.USER);
        properties.put("password", PoolData.PASSWORD);
        properties.put("characterEncoding", PoolData.CHARACTER_ENCODING);
        properties.put("useUnicode", PoolData.USE_UNICODE);

        poolSize = Integer.parseInt(PoolData.POOL_SIZE);
        connectionQueue = new ArrayDeque<>(poolSize);

        for (int index = 0; index < poolSize; index++) {
            Connection connection;
            try {
                connection = DriverManager.getConnection(PoolData.URL, properties);
            } catch (SQLException ex) {
                LOGGER.error("Runtime in con pool in init", ex);
                throw new RuntimeException();
            }
            connectionQueue.add(connection);
        }
    }
}
