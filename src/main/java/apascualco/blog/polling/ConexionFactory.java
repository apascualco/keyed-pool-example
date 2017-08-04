package apascualco.blog.polling;

import org.apache.commons.pool.BaseKeyedPoolableObjectFactory;

public class ConexionFactory extends BaseKeyedPoolableObjectFactory<String, Conexion>{

    public Conexion makeObject(String s) throws Exception {
        return new Conexion(s);
    }

}
