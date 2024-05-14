package webapp.restapi.dsmtt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webapp.restapi.dsmtt.models.Team;
import webapp.restapi.dsmtt.services.TeamService;

@RestController
@RequestMapping("/api/teams")
@CrossOrigin(origins = "*")
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	
	@PostMapping
	public Team addTeam(@RequestBody Team newTeam)
	{
		return teamService.addTeam(newTeam);
	}
	
	@GetMapping
	public List<Team> addTeam()
	{
		return teamService.getAllTeams();
	}
	
	@PutMapping("/{teamId}")
	public Team updateTeam(@PathVariable String teamId, @RequestBody Team updateTeam)
	{
		return teamService.updateTeamName(teamId, updateTeam);
	}
	
	@DeleteMapping("/{teamId}")
	public boolean deleteTeam(@PathVariable String teamId)
	{
		return teamService.deleteTeam(teamId);
	}
	
}
