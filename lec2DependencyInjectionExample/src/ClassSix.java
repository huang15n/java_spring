
public class ClassSix implements Interface{
	
	private AnotherInterface anotherInterface;

	@Override
	public String getInformation() {
		// TODO Auto-generated method stub
		return "this is from class six";
	}
	
	
	public void setAnotherInterface(AnotherInterface anotherInterface) {
		this.anotherInterface = anotherInterface;
	}
	
	

	@Override
	public String getExtraInformationForAnother() {
		// TODO Auto-generated method stub
		return this.anotherInterface.anotherGetInformation();
	}
	
	

}
