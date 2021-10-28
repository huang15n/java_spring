public class ClassFour implements Interface{

private AnotherInterface anotherInterface;


public ClassFour(AnotherInterface anotherInterface){

 this.anotherInterface = anotherInterface;
}

@Override
public String getInformation() {
	// TODO Auto-generated method stub
	return "This is from class four";
}

@Override
public String getExtraInformationForAnother() {
	return anotherInterface.anotherGetInformation();
}

}
