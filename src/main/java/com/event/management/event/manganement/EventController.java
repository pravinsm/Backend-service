package com.event.management.event.manganement;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.EventListener;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/event")
public class EventController {
	@GetMapping
	public String printString() {
		return "TEAM AMBUSH";
	}
	@GetMapping
	@RequestMapping("/name")
	public String printString1() {
		return "S.M.PRAVIN";
	}
	@PostMapping
	public String addEvent(@RequestBody String s) {
		return s;
	}
	@PostMapping
	@RequestMapping("/add")
	public ArrayList<Event> addString(@RequestBody Event event) throws SQLException{
		
		ArrayList<Event> e=new ArrayList<>();
		c(event.getName(),event.getType(),event.getDate(),event.getVenue(),e);
		
		
		return e;
		
	}
	@GetMapping
	@RequestMapping("/details")
	public ArrayList<Event> returnDetails() throws SQLException  {
		ArrayList<Event> eventlist=new ArrayList<>();
		Connection connection= null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/rly3eNsRqE","rly3eNsRqE","1CYjXrFZYX");
			Statement statement =connection.createStatement();
			ResultSet results =statement.executeQuery("select * from Event");
			while(results.next()) {
				Event event=new Event();
				event.setId(results.getInt("id"));
				event.setName(results.getString("name"));
				event.setType(results.getString("type"));
				event.setDate(results.getString("date"));
				event.setVenue(results.getString("venue"));
				eventlist.add(event);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		
		
		
		return eventlist;
	}
	public void c(String b,String c,String d,String e,ArrayList<Event> l) throws SQLException {
		/*
		Event s=new Event();
		s.setName(b);
		s.setType(c);
		s.setDate(d);
		s.setVenue(e);
		*/
		Connection connection= null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/rly3eNsRqE","rly3eNsRqE","1CYjXrFZYX");
			PreparedStatement pst = connection.prepareStatement("insert into Event(name,type,date,venue)values(?,?,?,?)");
			pst.setString(1,b);
			pst.setString(2,c);
			pst.setString(3,d);
			pst.setString(4,e);
			pst.executeUpdate();
			
		}catch(Exception i) {
			i.printStackTrace();
		}finally {
			connection.close();
		}
		System.out.println("done");
	}
}
