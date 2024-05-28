package webapp.restapi.dsmtt.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webapp.restapi.dsmtt.models.Activity;
import webapp.restapi.dsmtt.repo.ActivityRepository;

@Service
public class ActivityService {

	@Autowired
	private ActivityRepository activityRepo;
	
	public void addActivity(String newActivity, String userId){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
        String formattedDateTime = LocalDateTime.now().format(formatter);
		
		activityRepo.save(new Activity(formattedDateTime, newActivity, userId));
		
	}
	
	public List<Activity> getActivity(String userId){
		
		return activityRepo.findAllByUserId(userId);
		
	}
	
	public void deleteActivity(String userId) {
		activityRepo.deleteAllByUserId(userId);;
	}
	
}
