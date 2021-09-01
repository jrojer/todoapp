package todoapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DbConf {
    public final String userName;
    public final String password;
    public final String url;

    public DbConf() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream(new File("db.properties")));

        userName = props.getProperty("user");
        password = props.getProperty("password");
        url = props.getProperty("url");
    }
}