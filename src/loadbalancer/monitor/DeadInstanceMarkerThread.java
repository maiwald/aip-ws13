package loadbalancer.monitor;

import java.util.ArrayList;
import java.util.List;

import loadbalancer.Instance;

class DeadInstanceMarkerThread extends Thread {
	public void run() {
		try {
			while (true) {
				sleep(Monitor.INSTANCE_CLEANUP_DELAY * 1000);
				setDeadInstances();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void setDeadInstances() {
		for (Instance elem : getDeadInstances()) {
			elem.setStatusDead();
		}
	}

	private List<Instance> getDeadInstances() {
		List<Instance> result = new ArrayList<Instance>();
		for (Instance elem : Monitor.getInstances()) {
			if (elem.getLifeTimeInSeconds() > Monitor.INSTANCE_LIFETIME)
				result.add(elem);
		}
		return result;
	}

}