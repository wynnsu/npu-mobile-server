package edu.npu.cs595.resthandler;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;

import edu.npu.cs595.exceptions.UnknownResourceExResolver;

@ApplicationPath("/")
public class RestApplicationConfig extends Application {
	private Set<Class<?>> restClassSet = new HashSet<Class<?>>();

	public RestApplicationConfig() {
		restClassSet.add(JacksonFeature.class);
		restClassSet.add(NPUMobileRestHandler.class);
		restClassSet.add(UnknownResourceExResolver.class);
	}

	public Set<Class<?>> getClasses() {
		return restClassSet;
	}
}
