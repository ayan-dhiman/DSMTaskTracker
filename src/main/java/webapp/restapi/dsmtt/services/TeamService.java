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
		log.info("Adding new team: {}", newTeam);
		return teamRepo.save(newTeam);
	}
	
	public List<Team> getAllTeams()
	{
		log.info("Sending all teams");
		return teamRepo.findAll();
	}
	
	public Team updateTeamName(String teamId, Team updatedTeam)
	{
		log.info("Updating team with id: {}", teamId);
		
		Team existingTeam = teamRepo.findById(teamId).orElse(null);
		
		if (existingTeam != null) {
			if (updatedTeam.getName() != null) {
				existingTeam.setName(updatedTeam.getName());
			}

			return teamRepo.save(existingTeam);
		} else {
			
			log.error("Team not found with id: {}", teamId);
			
			return null;
		}
	}

	public boolean deleteTeam(String teamId)
	{
		log.info("Deleting team with id: {}", teamId);
		if (teamRepo.findById(teamId) != null) {
			teamRepo.deleteById(teamId);
			return true;
		}
		log.error("Team not found with id: {}", teamId);
		return false;
	}
	
}
