package self.ref;

import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Startup implements ApplicationListener<ApplicationReadyEvent> {

	private final JobScheduler jobScheduler;

	private final JobExecutor jobExecutor;
	
	private final ObjectProvider<HttpClient> objectProvider;

	@Autowired
	public Startup(JobScheduler jobScheduler, JobExecutor jobExecutor, ObjectProvider<HttpClient> objectProvider) {
		this.jobScheduler = jobScheduler;
		this.jobExecutor = jobExecutor;
		this.objectProvider = objectProvider;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		HttpClient httpClient = this.objectProvider.getObject();
		this.jobScheduler.scheduleRecurrently("job-executor", Cron.minutely(), () -> this.jobExecutor.execute(httpClient));
	}

}
