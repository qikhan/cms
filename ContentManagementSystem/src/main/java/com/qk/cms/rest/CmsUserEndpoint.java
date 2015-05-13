package com.qk.cms.rest;

import java.net.URI;
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

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.qk.cms.doa.CmsUserDoa;
import com.qk.cms.entity.CmsUser;
import com.qk.cms.service.ApplicationContextProvider;

@Path("/")
public class CmsUserEndpoint {

	private static LoadingCache<String, CmsUser> cachedCmsUsers_ = CacheBuilder
			.newBuilder().maximumSize(1000)
			.build(new CacheLoader<String, CmsUser>() {
				@Override
				public CmsUser load(String userName) throws Exception {
					CmsUserDoa cmsUserDoa = applicationContext
							.getBean(CmsUserDoa.class);
					CmsUser cmsUser = cmsUserDoa.findByUserName(userName);
					if (cmsUser == null) {
						throw new CmsUserNotFoundException();
					}
					return cmsUser;
				}
			});

	private static ApplicationContext applicationContext = ApplicationContextProvider
			.getApplicationContext();

	@Context
	HttpServletRequest request;

	@POST
	@Consumes({ "application/xml", "application/json" })
	@Path("/user")
	public Response create(final CmsUser cmsUser) {
		// TODO: insert the cmsuser
		CmsUserDoa cmsUserDoa = applicationContext.getBean(CmsUserDoa.class);
		if (cmsUserDoa.save(cmsUser)) {

			String userName = cmsUser.getUserName();
			UriBuilder uriBuilder = UriBuilder.fromResource(CmsUser.class);
			URI uri = uriBuilder.path(String.valueOf(userName)).build();
			return Response.created(uri).build();
		}

		return Response.status(Status.CONFLICT).build();
	}

	@PUT
	@Path("/user/{userName}")
	@Consumes({ "application/xml", "application/json" })
	public Response update(@PathParam("userName") String userName,
			final CmsUser cmsuser) {
		// TODO: update the cmsuser
		return Response.noContent().build();
	}

	@DELETE
	@Path("/user/{userName}")
	public Response delete(@PathParam("userName") final String userName) {
		// TODO: delete the cmsuser
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
		try {
			return cachedCmsUsers_.getUnchecked(userName);
		} catch (Exception e) {
			if (e.getCause() instanceof CmsUserNotFoundException) {
				return null;
			}
			throw e;
		}
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
