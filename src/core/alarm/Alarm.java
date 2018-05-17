package core.alarm;

public abstract class Alarm {
	private String uniqueName;

	public String getUniqueName() {
		return uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}

	public Alarm(String uniqueName) {
		super();
		this.uniqueName = uniqueName;
	}
}
