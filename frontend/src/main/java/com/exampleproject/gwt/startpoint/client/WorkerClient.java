package com.exampleproject.gwt.startpoint.client;



import com.exampleproject.model.shared.BankDto;
import com.exampleproject.model.shared.CompanyDto;
import com.exampleproject.model.shared.TestDto;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;


public interface WorkerClient extends RestService {

	@GET
	@Path("/test")
	void get(MethodCallback<TestDto> callback);

	@GET
	@Path("/testDb")
	void getDb(MethodCallback<Boolean> callback);

	@GET
	@Path("/Company")
	void getAllCompanies(MethodCallback<List<CompanyDto>> callback);

	@GET
	@Path("/Bank")
	void getAllBanks(MethodCallback<List<BankDto>> callback);



	@POST
    @Path("/addCompany")
    void add(CompanyDto companyDto, MethodCallback<Boolean> callback);



}
