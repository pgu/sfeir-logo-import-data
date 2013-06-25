package com.company;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.tools.remoteapi.RemoteApiInstaller;
import com.google.appengine.tools.remoteapi.RemoteApiOptions;

import java.io.IOException;

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

            Player player = new Player();
            player.setText("Crash: it does not just happen to computers.");
            player.setPictureUrl("http://24.media.tumblr.com/a1dfdda0494a2506a3fc4bb9184540f0/tumblr_mnryxtDBMu1rcufs7o1_1280.jpg");
            dao.ofy().put(player);

            System.out.println("Player id: " + player.getId());
//            System.out.println("Key of new entity is " +
//                    ds.put(new Entity("Hello Remote API!")));
        } finally {
            installer.uninstall();
        }
    }
}
