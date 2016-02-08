package com.qk.cms.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.qk.cms.entity.CmsUser;

public class OLTPServiceManager {

	private static final String BASE_URL = "http://localhost:8080/";
	private static final String APPLICATION = "ContentManagementSystem";
	private static final String USER_ENDPOINT = "rest/user";
	private static OLTPServiceManager INSTANCE = new OLTPServiceManager();

	public static OLTPServiceManager getInstance() {
		return INSTANCE;
	}

	public CmsUser getUser(String userName) {

		HttpURLConnection conn = null;
		try {

			URL url = new URL(BASE_URL + APPLICATION + "/" + USER_ENDPOINT
					+ "/" + userName);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			int responseCode = conn.getResponseCode();
			if (responseCode == HttpServletResponse.SC_NOT_FOUND
					|| responseCode == HttpServletResponse.SC_NO_CONTENT) {
				return null;
			}

			if (responseCode == HttpServletResponse.SC_OK) {

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

				Gson gson = new Gson();
				CmsUser user = gson.fromJson(br, CmsUser.class);
				return user;
			}

			throw new RuntimeException("Failed : HTTP error code : "
					+ responseCode);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return null;
	}

	public boolean createUser(CmsUser user) {

		HttpURLConnection conn = null;
		try {

			URL url = new URL(BASE_URL + APPLICATION + "/" + USER_ENDPOINT);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setDoOutput(true);
			Gson gson = new Gson();
			String json = gson.toJson(user);
			conn.getOutputStream().write(json.getBytes());
			conn.getOutputStream().close();
			int responseCode = conn.getResponseCode();
			if (responseCode == HttpServletResponse.SC_CONFLICT) {
				return false;
			}

			if (responseCode == HttpServletResponse.SC_CREATED) {
				return true;
			}

			throw new RuntimeException("Failed : HTTP error code : "
					+ responseCode);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return false;
	}
}
