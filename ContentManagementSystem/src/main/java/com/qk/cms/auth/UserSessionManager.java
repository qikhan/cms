package com.qk.cms.auth;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.qk.cms.vo.LoginVo;

public class UserSessionManager {

	private static UserSessionManager INSTANCE = new UserSessionManager();

	private Cache<String, UserSession> userSessions = CacheBuilder.newBuilder()
			.maximumSize(10000).expireAfterWrite(30, TimeUnit.MINUTES)
			.removalListener(new RemovalListener<String, UserSession>() {
				@Override
				public void onRemoval(
						RemovalNotification<String, UserSession> removal) {
					UserSession userSession = removal.getValue();
					// session information is persisted to the DB then can be
					// used to delete the DB entry
				}
			}).concurrencyLevel(10).build();

	private UserSessionManager() {

	}

	public static UserSessionManager getInstance() {
		return INSTANCE;
	}

	public UserSession getUserSession(final String sessionId)
			throws ExecutionException, SessionNotFoundException {
		return addUserSession(sessionId, null);
	}

	public void invalidateUserSession(final String sessionId) {
		userSessions.invalidate(sessionId);
	}

	public UserSession addUserSession(final String sessionId,
			final LoginVo loginVo) throws ExecutionException,
			SessionNotFoundException {

		return userSessions.get(sessionId, new Callable<UserSession>() {
			@Override
			public UserSession call() throws SessionNotFoundException {
				if (loginVo == null) {
					throw new SessionNotFoundException();
				}
				// if possible to persist the session information to the
				// database and put in the cache as well as a server fall back.
				// In that case before
				// throwing the exception it would have to query the DB to
				// ensure there is not entry available
				return new UserSession(sessionId, loginVo);
			}
		});
	}
}
