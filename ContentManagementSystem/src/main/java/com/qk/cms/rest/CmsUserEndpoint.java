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

import com.qk.cms.entity.CmsUser;

@Path("/")
public class CmsUserEndpoint {

	@Context
	HttpServletRequest request;

	@POST
	@Consumes({ "application/xml", "application/json" })
	@Path("/user")
	public Response create(final CmsUser cmsuser) {
		// TODO: process the given cmsuser
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
		// TODO: process the given cmsuser
		return Response.noContent().build();
	}

	@DELETE
	@Path("/user/{userName}")
	public Response delete(@PathParam("userName") final String userName) {
		// TODO: process the cmsuser matching by the given id
		return Response.noContent().build();
	}

	@GET
	@Path("/user/{userName}")
	@Produces({ "application/xml", "application/json" })
	public Response find(@PathParam("userName") final String userName) {
		// TODO: retrieve the cmsuser
		CmsUser cmsuser = getCmsUser(userName);
		if (cmsuser == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok(cmsuser).build();
	}

	private CmsUser getCmsUser(final String userName) {
		return new CmsUser(userName, "qikhan", "qikhan@gmail.com", "Quamrul",
				"Khan");
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
