
public class ClassFive implements Interface {
	
	private ClassThree classThree;
	
	public ClassFive(ClassThree classThree) {
		this.classThree = classThree;
	}

	@Override
	public String getInformation() {
		// TODO Auto-generated method stub
		return "this is from class five";
	}

	@Override
	public String getExtraInformationForAnother() {
		// TODO Auto-generated method stub
		return this.classThree.anotherGetInformation();
	}

	
	
}
