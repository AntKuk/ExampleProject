package com.exampleproject.gwt.startpoint.client;



import com.exampleproject.model.shared.TestDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


public interface WorkerClient extends RestService {

	@GET
	@Path("/test")
	void get(MethodCallback<TestDto> callback);

	@GET
	@Path("/testDb")
	void getDb(MethodCallback<Boolean> callback);

}
