package self.ref;

import org.jobrunr.jobs.annotations.Job;
import org.springframework.stereotype.Service;

@Service
public class JobExecutor {

	@Job(name = "Executor", retries = 1)
	public void execute(HttpClient httpClient) {

	}

}
