package todoapp;

import java.lang.RuntimeException;
import spark.Route;
import spark.Spark;
import spark.route.HttpMethod;

/*
    A class with common stuff for concreate API endpoint handlers.
*/
public abstract class BaseController implements Route {
    /*
      Although both the registry and the BaseController are not likely to change
       much. The registry is in a separate class because storing services is out of BaseController's
       funcitonal scope.
    */
    protected static final ServiceRegistry registry = new ServiceRegistry();

    protected void start() {
        switch (getHttpMethod()) {
            case post:
                Spark.post(getUri(), this);
                break;
            case put:
                Spark.put(getUri(), this);
                break;
            case get:
                Spark.get(getUri(), this);
                break;
            case delete:
                Spark.delete(getUri(), this);
                break;
            default:
                throw new RuntimeException("unknown http method " + getHttpMethod());
        }
    }

    /*
     The use of abstract methods makes it impossible for the user to omit (by mistake) properties
       definitions because override to be checked in compile time.
    */
    public abstract HttpMethod getHttpMethod();
    public abstract String getUri();
}