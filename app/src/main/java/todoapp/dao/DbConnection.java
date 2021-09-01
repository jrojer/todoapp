package todoapp;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection {
    HikariDataSource ds;

    public DbConnection() {
        DbConf db = null;
        try {
            db = new DbConf();
        } catch (IOException e) {
            Logger.error("Failed to get database config " + e.toString());
        }
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(db.url);
        config.setUsername(db.userName);
        config.setPassword(db.password);
        config.setDriverClassName("org.postgresql.Driver");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        this.ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
