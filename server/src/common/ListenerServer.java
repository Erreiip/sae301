package server.src.common;

import com.esotericsoftware.kryonet.*;

public class ListenerServer extends Listener 
{
    public void received (Connection connection, Object object) {
        System.out.println(connection + " : " + object);
    }
}
