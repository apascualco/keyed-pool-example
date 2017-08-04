package apascualco.blog.polling.test;

import apascualco.blog.polling.Conexion;
import apascualco.blog.polling.ConexionPool;
import org.apache.commons.pool.KeyedObjectPool;
import org.apache.commons.pool.impl.StackKeyedObjectPool;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

@RunWith(Parameterized.class)
public class TestPooling {

    @Parameterized.Parameters
    public static Collection<String> data() {
        List<String> usuarios = new LinkedList<String>();
        usuarios.add("usuario");
        usuarios.add("usuario2");
        usuarios.add("usuario3");
        usuarios.add("usuario4");
        usuarios.add("usuario");
        usuarios.add("usuario2");
        usuarios.add("usuario3");
        usuarios.add("usuario4");
        usuarios.add("usuario");
        usuarios.add("usuario2");
        usuarios.add("usuario3");
        usuarios.add("usuario4");
        usuarios.add("usuario");
        usuarios.add("usuario2");
        usuarios.add("usuario3");
        usuarios.add("usuario4");
        return usuarios;
    }

    private String usuario;

    public TestPooling(String usuario) {
        this.usuario = usuario;
    }

    @Test
    public void test_parametrized() throws Exception {
        KeyedObjectPool<String, Conexion> pool =  ConexionPool.getInstance().getPool();
        Conexion conexion = pool.borrowObject(usuario);
        Assert.assertEquals("El usuario " + usuario + " no coincide con el esperado " + conexion.getUsuario(), usuario.toLowerCase(), conexion.getUsuario().toLowerCase());
        Stack stack = (Stack) ((StackKeyedObjectPool) pool).getPools().get(usuario);
        Assert.assertEquals(pool.getNumActive(usuario), 1);
        //El objeto es creado, pero esta siendo usado y esta fuera del stack(pool)
        Assert.assertEquals(stack.size(), 0);
        //Se devuelve el objeto al stack (pool)
        pool.returnObject(usuario, conexion);
    }

}
