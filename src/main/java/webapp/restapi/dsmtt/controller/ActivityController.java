package webapp.restapi.dsmtt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webapp.restapi.dsmtt.models.Activity;
import webapp.restapi.dsmtt.services.ActivityService;

@RestController
@RequestMapping("api/activities")
@CrossOrigin(origins = "*")
public class ActivityController {

	@Autowired
	private ActivityService activityService;
	
	@GetMapping("/{userId}")
	public List<Activity> getActivities(@PathVariable String userId)
	{
		return activityService.getActivity(userId);
	}
	
}
