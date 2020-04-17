package hu.mta.sztaki.lpds.cloud.simulator.helpers.trace.file;

import java.lang.reflect.InvocationTargetException;

import hu.mta.sztaki.lpds.cloud.simulator.helpers.job.Job;

public class PreziReader extends TraceFileReaderFoundation {

	public PreziReader( String fileName, int from, int to, boolean allowReadingFurther,
			Class<? extends Job> jobType) throws SecurityException, NoSuchMethodException {
		super("Prezi", fileName, from, to, allowReadingFurther, jobType);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean isTraceLine(String line) {
		String[] lineFormat = line.trim().split("\\s+");
        try {

            Long.parseLong(lineFormat[0]);
            Float.parseFloat(lineFormat[1]);
            if (!lineFormat[2].contains(" ")) {
                if(lineFormat[3].equals("url") || lineFormat[3].equals("default") ||  lineFormat[3].equals("export")) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        catch(Exception e) {
            return false;
        }
	
	}

	@Override
	protected void metaDataCollector(String line) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Job createJobFromLine(String line) 
			throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		// 
		final String[] elements = line.trim().split("\\s+");

        long waitTime = 0; 
        int processors =1; 
        long averageMemory = 512; 
        String user = null; 
        String groupId = null; 
        Job preecedingJob = null; 
        long delayAfter = 0;
        long jobArrivalTime = Long.parseLong(elements[0]); 
        long jobDuration = (long) Float.parseFloat(elements[1]);
        String jobId = elements[2]; 
        String jobExecutableName = elements[3];
        double ppCpu = -1;

        return jobCreator.newInstance(jobId, 
        		jobArrivalTime, 
        		waitTime, 
        		jobDuration, 
        		processors, 
        		ppCpu, 
        		averageMemory, 
        		user, 
        		groupId, 
        		jobExecutableName, 
        		preecedingJob, 
        		delayAfter );


    }

}
