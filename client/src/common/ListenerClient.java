package client.src.common;

import com.esotericsoftware.kryonet.*;

public class ListenerClient extends Listener
{
    public void received (Connection connection, Object object) {
        System.out.println("Client : " + connection + " : " + object);
    }
}
