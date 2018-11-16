package searchservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.LinkedHashMap;
import elasticsearch.ElasticSearch;
import elasticsearch.ElasticSearchException;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String searchValue =  request.getParameter("searchValue");
		String lowPrice =  request.getParameter("lowPrice");
		String highPrice = request.getParameter("highPrice");
		String minLike = request.getParameter("minLike");
		String sortCriteria =  request.getParameter("sortCriteria");
		
		if(lowPrice == null || lowPrice.isEmpty()) {
			lowPrice="0";
		}
		if(highPrice == null || highPrice.isEmpty()) {
			highPrice="99999999";
		}
		if(minLike == null || minLike.isEmpty()) {
			minLike="0";
		}
		if(sortCriteria == null || sortCriteria.isEmpty()) {
			sortCriteria="";
		}		
		System.out.println(searchValue);
		System.out.println(lowPrice);
		System.out.println(highPrice);
		System.out.println(minLike);
		System.out.println(sortCriteria);
		
		Double intLowPrice = Double.parseDouble(lowPrice);
		Double intHighPrice =  Double.parseDouble(highPrice);
		int intminLike =  Double.valueOf(minLike).intValue();
		
		
		ElasticSearch elasticSearch = new ElasticSearch();
//
		LinkedHashMap<String, LinkedHashMap<String, String>> productMap = new LinkedHashMap<>();
//
		
		try {
			productMap = elasticSearch.searchExportData("yes", searchValue, intminLike, intLowPrice, intHighPrice, sortCriteria);
		}
		catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for(String key : productMap.keySet()) {
//			System.out.println(key);
//		}
		

		request.getSession().setAttribute("sqlData", productMap);
		request.getRequestDispatcher("searchResult.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String searchValue =  request.getParameter("searchValue");
		String lowPrice =  request.getParameter("lowPrice");
		String highPrice = request.getParameter("highPrice");
		String minLike = request.getParameter("minLike");
		String sortCriteria =  request.getParameter("sortCriteria");
//		LinkedHashMap<String, String> sqlData = new LinkedHashMap<>();
//		sqlData.put("abc", "search1");
//		for(int i=0;i<1;i++) {
//			sqlData.put("searchvalue" + i, "searchvalue"+i);
//		}
		
		if(lowPrice == null || lowPrice.isEmpty()) {
			lowPrice="0";
		}
		if(highPrice == null || highPrice.isEmpty()) {
			highPrice="99999999";
		}
		if(minLike == null || minLike.isEmpty()) {
			minLike="0";
		}
		if(sortCriteria == null || sortCriteria.isEmpty()) {
			sortCriteria="";
		}		
		System.out.println(searchValue);
		System.out.println(lowPrice);
		System.out.println(highPrice);
		System.out.println(minLike);
		System.out.println(sortCriteria);
		
		Double intLowPrice = Double.parseDouble(lowPrice);
		Double intHighPrice =  Double.parseDouble(highPrice);
		int intminLike =  Double.valueOf(minLike).intValue();
		
		
		ElasticSearch elasticSearch = new ElasticSearch();
//
		LinkedHashMap<String, LinkedHashMap<String, String>> productMap = new LinkedHashMap<>();
//
		
		try {
			productMap = elasticSearch.searchExportData("yes", searchValue, intminLike, intLowPrice, intHighPrice, sortCriteria);
		}
		catch (ElasticSearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for(String key : productMap.keySet()) {
//			System.out.println(key);
//		}
		

		request.getSession().setAttribute("sqlData", productMap);
		request.getRequestDispatcher("searchResult.jsp").forward(request,response);
		
		
		
		
		
		
		
	}

}
