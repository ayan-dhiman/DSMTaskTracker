package webapp.restapi.dsmtt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import webapp.restapi.dsmtt.models.Team;
import webapp.restapi.dsmtt.repo.TeamRepository;

@Slf4j
@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepo;
	
	public Team addTeam(Team newTeam)
	{
		log.info("Adding new team: {}", newTeam);
		return teamRepo.save(newTeam);
	}
	
//	public List<Team> getAllTeamsByUser(String userId)
//	{
//		log.info("Sending all teams");
//		return teamRepo.findAllByUserId(userId);
//	}
	
	public List<Team> getAllTeams(String userId)
	{
		log.info("Sending all teams");
		System.out.println(userId+"-->"+teamRepo.findAllByUserId(userId));
		return teamRepo.findAllByUserId(userId);
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
	
	public void deleteTeams(List<String> teams)
	{
		
		if(!teams.isEmpty())
		{
			for(String teamId : teams) {
				log.info("Deleting team with id: {}", teamId);
				if (teamRepo.findById(teamId) != null) {
					teamRepo.deleteById(teamId);
				}
			}
		}
		
	}
	
}
