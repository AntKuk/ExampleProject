package com.exampleproject.gwt.startpoint.client;

import com.exampleproject.model.shared.*;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;


public interface WorkerClient extends RestService {

	@GET
	@Path("/Company")
	void getAllCompanies(MethodCallback<List<CompanyDto>> callback);

	@GET
	@Path("/Bank")
	void getAllBanks(MethodCallback<List<BankDto>> callback);

	@GET
	@Path("/Transact")
	void getAllTransacts(MethodCallback<List<TransactDto>> callback);

	@GET
	@Path("/BankAccount")
	void getAllAccounts(MethodCallback<List<BankAccDto>> callback);



	@POST
    @Path("/addCompany")
    void addCompany(CompanyDto companyDto, MethodCallback<Boolean> callback);

	@DELETE
	@Path("/deleteCompany")
	void deleteCompany(Integer id, MethodCallback<Boolean> callback);

	@POST
	@Path("/updateCompany")
	void updateCompany(CompanyDto companyDto, MethodCallback<Boolean> callback);

	@POST
	@Path("/addBank")
	void addBank(BankDto bankDto, MethodCallback<Boolean> callback);

	@DELETE
	@Path("deleteBank")
	void deleteBank(Integer id, MethodCallback callback);

	@POST
	@Path("/updateBank")
	void updateBank(BankDto bankDto, MethodCallback<Boolean> callback);

	@POST
	@Path("/addTransact")
	void addTransact(TransactDto transactDto, MethodCallback<Boolean> callback);

	@DELETE
	@Path("/deleteTransact")
	void deleteTransact(Integer id, MethodCallback callback);

	@POST
	@Path("login")
	void login(UserDto user, MethodCallback<LoginStatus> callback);

	@GET
	@Path("/users")
	void getUsers(MethodCallback<List<UserDto>> callback);

	@POST
	@Path("addUser")
	void addUser(UserDto userDto, MethodCallback<Boolean> callback);

	@DELETE
	@Path("deleteUser")
	void deleteUser(Integer id, MethodCallback callback);

	@POST
	@Path("/addAccount")
	void addAccount(BankAccDto bankAccDto, MethodCallback<Boolean> callback);

	@DELETE
	@Path("deleteAccount")
	void deleteAccount(Long id, MethodCallback callback);

	@POST
	@Path("/getAccounts")
	void getAccounts(String companyName, MethodCallback<List<BankAccDto>> callback);
}
