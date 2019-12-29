package kr.bigskypark.whatsnew.collector;

import kr.bigskypark.whatsnew.core.dto.JobConfiguration;

public interface DataCollectingJob {

  void run(JobConfiguration configuration);
}
