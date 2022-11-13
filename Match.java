package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Match {
	public String team1;
	public String team2;
	public String competition;
	public String round;
	public int team1goals;
	public int team2goals;
	
	public static int getNumberOfGoals(String team, int year) {
		
		RestTemplate rest = new RestTemplate();
		int goals_number_home = 0;
		int goals_number_outdoor = 0;
		
		/*---------Home------*/
		
		String url_home = "https://jsonmock.hackerrank.com/api/football_matches?year="+year+"&team1="+team;
		Page p_home = rest.getForObject(url_home, Page.class);
		int number_total_pages = p_home.total_pages; //total_pages
		String var_url;
		
		for(int i=1;i<number_total_pages+1;i++) {

			var_url=url_home+"&page="+i;
			p_home = rest.getForObject(var_url, Page.class);
			for(Match m : p_home.data) { //we want team1goals because we're at home
				goals_number_home+=m.team1goals;
			}
			
		}
		
		/*---------Outdoor------*/
		
		String url_outdoor = "https://jsonmock.hackerrank.com/api/football_matches?year="+year+"&team2="+team;
		Page p_outdoor = rest.getForObject(url_outdoor, Page.class);
		number_total_pages = p_outdoor.total_pages; //total_pages 
		
		for(int i=1;i<number_total_pages+1;i++) {

			var_url=url_outdoor+"&page="+i;
			p_outdoor = rest.getForObject(var_url, Page.class);
			for(Match m : p_outdoor.data) { //we want team2goals because we're outdoor
				goals_number_outdoor+=m.team2goals;
			}
			
		}
		
		//System.out.println(goals_number_home);
		//System.out.println(goals_number_outdoor);
		
		int final_goals = goals_number_home+goals_number_outdoor;
		return final_goals;
	}
}
