package com.qk.cms.doa;

import java.util.List;

import com.qk.cms.entity.CmsUser;

public interface CmsUserDoa {

	public boolean save(CmsUser user);

	public List<CmsUser> list();

	public CmsUser findByUserName(String username);
}