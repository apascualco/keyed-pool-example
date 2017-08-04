package apascualco.blog.polling;

import org.apache.commons.pool.KeyedObjectPool;
import org.apache.commons.pool.impl.StackKeyedObjectPool;

public class ConexionPool {

    private KeyedObjectPool<String, Conexion> pool;

    private static class SingletonHolder {
        private static final ConexionPool INSTANCE = new ConexionPool();
    }

    public static ConexionPool getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private ConexionPool() {
        pool = new StackKeyedObjectPool<String, Conexion>(new ConexionFactory(), 10);
    }

    public KeyedObjectPool<String, Conexion> getPool() {
        return pool;
    }

}
