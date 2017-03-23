package de.rgse.craftman.rest;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import org.reflections.Reflections;

@ApplicationPath("/rest")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Reflections reflections = new Reflections("de.rgse.craftman.rest.resource");
        return reflections.getTypesAnnotatedWith(Path.class);
    }
}
