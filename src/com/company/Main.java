package com.company;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.tools.remoteapi.RemoteApiInstaller;
import com.google.appengine.tools.remoteapi.RemoteApiOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        DAO dao = new DAO();
//        String username = System.console().readLine("username: ");
//        String password =
//                new String(System.console().readPassword("password: "));

        String username = "test@example.com";
        String password = "";

        RemoteApiOptions options = new RemoteApiOptions()
                .server("localhost", 8080)
//                .server("your_app_id.appspot.com", 443)
                .credentials(username, password);
        RemoteApiInstaller installer = new RemoteApiInstaller();
        installer.install(options);
        try {
            DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

            Map<String, String> data = new HashMap<String, String>();
            data.put("Networking is one letter from Not working", //
                    "http://25.media.tumblr.com/f74d771d1fc640d6eb32d4f7242b4b71/tumblr_moca9xWmSv1rcufs7o1_1280.png");
            data.put("Teamwork: Tackle life side by side.", //
                    "http://24.media.tumblr.com/9eed7a0ca40b301569a67fe29e3d3cc6/tumblr_mnotrbV7Hz1rcufs7o1_1280.png");
            data.put("Crash: it does not just happen to computers.", //
                    "http://24.media.tumblr.com/a1dfdda0494a2506a3fc4bb9184540f0/tumblr_mnryxtDBMu1rcufs7o1_1280.jpg");

            List<Player> players = new ArrayList<Player>();
            for (Map.Entry<String, String> e : data.entrySet()) {
                Player p = new Player();
                p.setText(e.getKey());
                p.setPictureUrl(e.getValue());
                players.add(p);
            }
            dao.ofy().put(players);

            System.out.println("Done!");
//            System.out.println("Key of new entity is " +
//                    ds.put(new Entity("Hello Remote API!")));
        } finally {
            installer.uninstall();
        }
    }
}
