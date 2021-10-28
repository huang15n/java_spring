
public class ClassSeven implements Interface {
	
	private String variable1;
	private String variable2;
	
	
 

	public String getVariable1() {
		return variable1;
	}

	public void setVariable1(String variable1) {
		this.variable1 = variable1;
	}

	public String getVariable2() {
		return variable2;
	}

	public void setVariable2(String variable2) {
		this.variable2 = variable2;
	}

	@Override
	public String getInformation() {
		// TODO Auto-generated method stub
		return this.variable1;
	}

	@Override
	public String getExtraInformationForAnother() {
		// TODO Auto-generated method stub
		return this.variable2;
	}

}
