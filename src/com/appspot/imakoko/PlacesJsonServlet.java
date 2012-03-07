package com.appspot.imakoko;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.arnx.jsonic.JSON;

@SuppressWarnings("serial")
public class PlacesJsonServlet extends HttpServlet {
	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		/* 返されるデータの形式をapplication/jsonに変更する */
		resp.setContentType("application/json; charset=UTF-8");
		/* 入力情報を取得する */
		String tag = req.getParameter("tag");
		String lastDateParam = req.getParameter("lastDate");
		Date lastDate = null;
		if (lastDateParam != null && !"".equals(lastDateParam)) {
			lastDate = new Date(Long.parseLong(lastDateParam));
		}
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			/* 条件を設定する */
			StringBuilder decParams = new StringBuilder();
			StringBuilder filter = new StringBuilder();
			HashMap<String, Object> params = new HashMap<String, Object>();
			if (tag != null && !"".equals(tag)) {
				filter.append(" && tag == tagParam");
				decParams.append(", String tagParam");
				params.put("tagParam", tag);
			}
			if (lastDate != null) {
				filter.append(" && registDate > lastDateParam");
				decParams.append(", java.util.Date lastDateParam");
				params.put("lastDateParam", lastDate);
			}
			if (params.size() > 0) {
				decParams.delete(0, 2);
				filter.delete(0, 4);
			}
			/* 条件に合わせて位置情報を検索する */
			Query query = pm.newQuery(Place.class);
			query.setOrdering("registDate desc");
			query.setRange(0, 30);
			List<Place> places = null;
			if (params.size() == 0) {
				places = (List<Place>) query.execute();
			} else {
				query.setFilter(filter.toString());
				query.declareParameters(decParams.toString());
				places = (List<Place>) query.executeWithMap(params);
			}
			/* 検索結果のJavaオブジェクトをJSONに変換してブラウザに返す */
			JSON.encode(places, resp.getOutputStream());
		} finally {
			pm.close();
		}
	}
}
