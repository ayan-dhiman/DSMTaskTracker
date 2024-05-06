package webapp.restapi.dsmtt.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import webapp.restapi.dsmtt.models.Team;
import webapp.restapi.dsmtt.repo.TeamRepository;

@Slf4j
@Service
public class TeamService {

	private TeamRepository teamRepo;
	
	public Team addTeam(Team newTeam)
	{
		return teamRepo.save(newTeam);
	}
	
	public List<Team> getAllTeams()
	{
		return teamRepo.findAll();
	}
	
	public Team updateTeamName(String teamId, Team updatedTeam)
	{
		Team existingTeam = teamRepo.findById(teamId).orElse(null);
		
		if (existingTeam != null) {
			if (updatedTeam.getName() != null) {
				existingTeam.setName(updatedTeam.getName());
			}

			return teamRepo.save(existingTeam);
		} else {
			
			log.error("Task not found with id: {}", teamId);
			
			return null;
		}
	}

	public boolean deleteTeam(String teamId)
	{
		log.info("Deleting task with id: {}", teamId);
		if (teamRepo.findById(teamId) != null) {
			teamRepo.deleteById(teamId);
			return true;
		}
		log.error("Task not found with id: {}", teamId);
		return false;
	}
	
}
