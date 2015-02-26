package com.qk.cms.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.qk.cms.entity.CmsUser;

public class OLTPServiceManager {

	private static final String BASE_URL = "http://localhost:8080/";
	private static final String APPLICATION = "ContentManagementSystem";
	private static final String USER_ENDPOINT = "rest/user/";
	private static OLTPServiceManager INSTANCE = new OLTPServiceManager();

	public static OLTPServiceManager getInstance() {
		return INSTANCE;
	}

	public CmsUser getUser(String userName) {

		HttpURLConnection conn = null;
		try {

			URL url = new URL(BASE_URL + APPLICATION + "/" + USER_ENDPOINT
					+ userName);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			int responseCode = conn.getResponseCode();
			if (responseCode == 404 || responseCode == 204) {
				return null;
			}

			if (responseCode == 200) {

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
}
