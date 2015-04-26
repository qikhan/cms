package com.qk.cms.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.springframework.context.ApplicationContext;

import com.qk.cms.doa.CmsUserDoa;
import com.qk.cms.entity.CmsUser;
import com.qk.cms.service.ApplicationContextProvider;

@Path("/")
public class CmsUserEndpoint {

	@Context
	HttpServletRequest request;

	ApplicationContext applicationContext = ApplicationContextProvider
			.getApplicationContext();

	@POST
	@Consumes({ "application/xml", "application/json" })
	@Path("/user")
	public Response create(final CmsUser cmsuser) {

		return Response.created(
				UriBuilder.fromResource(CmsUser.class)
						.path(String.valueOf(cmsuser.getUserName())).build())
				.build();
	}

	@PUT
	@Path("/user/{userName}")
	@Consumes({ "application/xml", "application/json" })
	public Response update(@PathParam("userName") String userName,
			final CmsUser cmsuser) {
		return Response.noContent().build();
	}

	@DELETE
	@Path("/user/{userName}")
	public Response delete(@PathParam("userName") final String userName) {
		return Response.noContent().build();
	}

	@GET
	@Path("/user/{userName}")
	@Produces({ "application/xml", "application/json" })
	public Response find(@PathParam("userName") final String userName) {
		CmsUser cmsuser = getCmsUser(userName);
		if (cmsuser == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok(cmsuser).build();
	}

	private CmsUser getCmsUser(final String userName) {

		CmsUserDoa cmsUserDoa = applicationContext.getBean(CmsUserDoa.class);
		CmsUser cmsUser = cmsUserDoa.findByUserName(userName);

		return cmsUser;
	}

	@GET
	@Path("/users")
	@Produces({ "application/xml", "application/json" })
	public List<CmsUser> listAll(
			@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		// TODO: retrieve the cmsusers
		final List<CmsUser> cmsusers = new ArrayList<CmsUser>(1);
		cmsusers.add(getCmsUser("qikhan"));
		return cmsusers;
	}

}
